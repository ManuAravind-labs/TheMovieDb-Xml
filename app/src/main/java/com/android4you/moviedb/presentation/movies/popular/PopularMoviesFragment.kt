package com.android4you.moviedb.presentation.movies.popular

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.android4you.moviedb.R
import com.android4you.moviedb.databinding.FragmentMoviesListBinding
import com.android4you.moviedb.presentation.common.CustomLoadStateAdapter
import com.android4you.moviedb.presentation.common.TEST.ANGRY
import com.android4you.moviedb.presentation.movies.viewmodel.MoviesListViewModel
import com.android4you.moviedb.presentation.movies.adapter.MoviePagerAdapter
import com.android4you.moviedb.utils.common.MovieEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel: MoviesListViewModel by activityViewModels()
    private var selectPosts: Job? = null
    private var movieType: String = MovieEnum.POPULAR.value
    private val adapter = MoviePagerAdapter {
        println(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        arguments?.getString("movie_type")?.let { Log.e("GGGGG  =>", it) }
        arguments?.getString("movie_type")?.let { type ->
            if (type != "null") {
                movieType = type
            }
        }
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        binding.movieList.apply {
            layoutManager =
                GridLayoutManager(context, if (isTablet) 5 else 3).apply {
                    orientation = GridLayoutManager.VERTICAL
                }
            adapter = adapter
        }
        selectPosts?.cancel()
        selectPosts = lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieType.let { movieTypeItem ->
                    viewModel.getMovies(movieTypeItem).collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        }

        binding.movieList.adapter = adapter.withLoadStateFooter(
            footer = CustomLoadStateAdapter {
                adapter.retry()
            },
        )

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect { loadState ->
                    val refreshState = loadState.refresh
                    binding.movieList.isVisible = refreshState is LoadState.NotLoading
                    binding.progressBar.isVisible = refreshState is LoadState.Loading
                    binding.layoutError.isVisible = refreshState is LoadState.Error

                    if (refreshState is LoadState.Error) {
                        when (refreshState.error as Exception) {
                            is HttpException ->
                                binding.labelError.text = getString(R.string.internal_error)

                            is IOException ->
                                binding.labelError.text = getString(R.string.label_no_internet)
                        }
                    }

                    val errorState = loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    errorState?.let {
                        Toast.makeText(
                            requireActivity(),
                            ANGRY + getString(R.string.error_text_label),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            }
        }
        binding.reloadPostsBtn.setOnClickListener {
            adapter.refresh()
        }

        if (!isTablet) {
            binding.swipeRefreshLayout?.setOnRefreshListener {
                binding.swipeRefreshLayout?.isRefreshing = false
                adapter.refresh()
            }
        }
    }
}
