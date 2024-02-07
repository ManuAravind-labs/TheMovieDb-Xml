package com.android4you.moviedb.presentation.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.moviedb.R
import com.android4you.moviedb.data.response.people.PeopleResult
import com.android4you.moviedb.databinding.FragmentPeopleBinding
import com.android4you.moviedb.presentation.common.MovieLoadingStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    private val peopleAdapter =
        PeopleAdapter { name: PeopleResult -> snackBarClickedPlayer(name) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        startSearchJob()
        binding.swipeRefreshLayout?.setOnRefreshListener {
            peopleAdapter.refresh()
        }
    }

    private fun startSearchJob() {
        lifecycleScope.launch {
            viewModel.getActors().collectLatest {
                it.let {
                    peopleAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun setUpAdapter() {
        binding.peopleList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        binding.peopleList.adapter = peopleAdapter.withLoadStateFooter(
            footer = MovieLoadingStateAdapter { retry() },
        )

        peopleAdapter.addLoadStateListener { loadState ->
            if (loadState.mediator?.refresh is LoadState.Loading) {
                if (peopleAdapter.snapshot().isEmpty()) {
                    binding.progress?.isVisible = true
                }
                binding.errorTxt?.isVisible = false
            } else {
                binding.progress?.isVisible = false
                binding.swipeRefreshLayout?.isRefreshing = false

                val error = when {
                    loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                    loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                    loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (peopleAdapter.snapshot().isEmpty()) {
                        binding.errorTxt?.isVisible = true
                        binding.errorTxt?.text = it.error.localizedMessage
                    }
                }
            }
        }
    }

    private fun retry() {
        peopleAdapter.retry()
    }

    private fun snackBarClickedPlayer(peopleResult: PeopleResult) {
        viewModel.setDetailsInfo(peopleResult)
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        if (!isTablet) {
            findNavController().navigate(R.id.peopleDetailsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
