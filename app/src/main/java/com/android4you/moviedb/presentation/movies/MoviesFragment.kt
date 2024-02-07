package com.android4you.moviedb.presentation.movies

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.R
import com.android4you.moviedb.databinding.FragmentMoviesBinding
import com.android4you.moviedb.presentation.common.SpanningLinearLayoutManager
import com.android4you.moviedb.presentation.common.TabLayoutAdapter
import com.android4you.moviedb.presentation.movies.viewmodel.MoviesListViewModel
import com.android4you.moviedb.utils.common.MovieEnum

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MoviesListViewModel by activityViewModels()

    private var selectedPosition: Int = 0
    private var tabLayoutAdapter: TabLayoutAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        val tabList = ArrayList<String>()
        tabList.add(getString(R.string.popular))
        tabList.add(getString(R.string.toprated))
        tabList.add(getString(R.string.nowplaying))
        tabList.add(getString(R.string.upcoming))
        tabLayoutAdapter = TabLayoutAdapter(tabList) {
            selectedPosition = it
            when (it) {
                0 -> {
                    val bundle = bundleOf("movie_type" to MovieEnum.POPULAR.value)
                    navHostFragment.navController.navigate(R.id.moviesListFragment, bundle)
                }

                1 -> {
                    val bundle = bundleOf("movie_type" to MovieEnum.TOPRATED.value)
                    navHostFragment.navController.navigate(R.id.moviesListFragment, bundle)
                }

                2 -> {
                    val bundle = bundleOf("movie_type" to MovieEnum.NOWPLAYING.value)
                    navHostFragment.navController.navigate(R.id.moviesListFragment, bundle)
                }

                3 -> {
                    val bundle = bundleOf("movie_type" to MovieEnum.UPCOMING.value)
                    navHostFragment.navController.navigate(R.id.moviesListFragment, bundle)
                }

                else -> "NA"
            }
        }
        _binding?.tabLayout?.apply {
            layoutManager = if (isTablet) {
                SpanningLinearLayoutManager(requireContext())
            } else {
                SpanningLinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
            adapter = tabLayoutAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("POSITION", selectedPosition)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            selectedPosition = savedInstanceState.getInt("POSITION")?:0
            tabLayoutAdapter?.setSelectedPosition(selectedPosition)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateToMovieDetails() {
        findNavController().navigate(R.id.action_moviesFragment_to_moviesDetailsFragment)
    }
}
