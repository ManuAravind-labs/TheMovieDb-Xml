package com.android4you.moviedb.presentation.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.R
import com.android4you.moviedb.data.constants.Constants
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.databinding.FragmentSettingsBinding
import com.android4you.moviedb.presentation.common.OnLoadMoreListener
import com.android4you.moviedb.presentation.common.RecyclerViewLoadMoreScroll
import com.android4you.moviedb.presentation.people.PeopleViewModel
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    private val newList = ArrayList<MovieResult>()

    private lateinit var adapterGrid: SettingsAdapter

    private lateinit var scrollListener: RecyclerViewLoadMoreScroll

    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private var isFirstTime = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ** Set the adapterLinear of the RecyclerView
        setAdapter()

        // ** Set the Layout Manager of the RecyclerView
        setRVLayoutManager()

        // ** Set the scrollListener of the RecyclerView
        setRVScrollListener()
        lifecycleScope.launch {
//            viewModel.moviesListStateFlow.collect {
//                if (it.isNotEmpty()) {
//                    newList.addAll(it)
//                    adapterGrid.submitList(newList)
//                }
//                if (isFirstTime) {
//                    isFirstTime = false
//                } else {
//                    scrollListener.setLoaded()
//                }
//            }
        }
    }

    private fun setAdapter() {
        adapterGrid = SettingsAdapter()
        binding.list.adapter = adapterGrid
    }

    private fun setRVLayoutManager() {
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        if (isTablet) {
            mLayoutManager = GridLayoutManager(context, 3).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        } else {
            mLayoutManager = GridLayoutManager(context, 3).apply {
                orientation = GridLayoutManager.VERTICAL
            }
        }
        binding.list.layoutManager = mLayoutManager
        binding.list.setHasFixedSize(true)
        binding.list.adapter = adapterGrid
        (mLayoutManager as GridLayoutManager).spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapterGrid.getItemViewType(position)) {
                        Constants.Constant.VIEW_TYPE_ITEM -> 1
                        Constants.Constant.VIEW_TYPE_LOADING -> 3 // number of columns of the grid
                        else -> -1
                    }
                }
            }
    }

    private fun setRVScrollListener() {
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        scrollListener = RecyclerViewLoadMoreScroll(
            mLayoutManager as GridLayoutManager,
            if (isTablet) {
                GridLayoutManager.HORIZONTAL
            } else {
                GridLayoutManager.VERTICAL
            },
        )
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
              //  loadMoreData()
            }
        })

        binding.list.addOnScrollListener(scrollListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
