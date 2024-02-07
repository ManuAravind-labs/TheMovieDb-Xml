package com.android4you.moviedb.presentation.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.R
import com.android4you.moviedb.data.response.MovieResult
import com.android4you.moviedb.databinding.FragmentHomeBinding
import com.android4you.moviedb.presentation.common.OnLoadMoreListener
import com.android4you.moviedb.presentation.common.RecyclerViewLoadMoreScroll
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        const val POPULAR_KEY = "popular_key"
        const val UPCOMING_KEY = "upcoming_key"
        const val NOWPLAYING_KEY = "nowplaying_key"
        const val TOPRATED_KEY = "toprated_key"
    }

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val popularAdapter: HomeMoviesAdapter = HomeMoviesAdapter()
    private val upcomingAdapter: HomeMoviesAdapter = HomeMoviesAdapter()
    private val topRatedAdapter: HomeMoviesAdapter = HomeMoviesAdapter()
    private val nowPlayingAdapter: HomeMoviesAdapter = HomeMoviesAdapter()

    private lateinit var mPopularLayoutManager: RecyclerView.LayoutManager
    private lateinit var mUpcomingLayoutManager: RecyclerView.LayoutManager
    private lateinit var mTopRatedLayoutManager: RecyclerView.LayoutManager
    private lateinit var mNowPlayingLayoutManager: RecyclerView.LayoutManager

    private val newPopularList = ArrayList<MovieResult>()
    private val newUpComingList = ArrayList<MovieResult>()
    private val newTopRatedList = ArrayList<MovieResult>()
    private val newNowPlayingList = ArrayList<MovieResult>()

    private lateinit var scrollListenerPopular: RecyclerViewLoadMoreScroll
    private lateinit var scrollListenerUpcoming: RecyclerViewLoadMoreScroll
    private lateinit var scrollListenerTopRated: RecyclerViewLoadMoreScroll
    private lateinit var scrollListenerNowPlaying: RecyclerViewLoadMoreScroll

    private var isFirstTimePopular = true
    private var isFirstTimeuUpcoming = true
    private var isFirstTimeTopRated = true
    private var isFirstTimeNowPlaying = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.popularList.adapter = popularAdapter
        binding.upcomingList.adapter = upcomingAdapter
        binding.topratedList.adapter = topRatedAdapter
        binding.nowPlayingList.adapter = nowPlayingAdapter
        getRVLayoutManager()
        setRVLayoutManager()
//        setRVPopularScrollListener()
//        setRVUpcomingScrollListener()
//        setRVTopRatedScrollListener()
//        setRVNowPlayingScrollListener()
        popularMovies()
        upcomingMovies()
        topratedMovies()
        nowPlayingMovies()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("TEST == > ", newPopularList.size.toString() + "")
        outState.putParcelableArrayList(POPULAR_KEY, newPopularList)
        outState.putParcelableArrayList(NOWPLAYING_KEY, newNowPlayingList)
        outState.putParcelableArrayList(TOPRATED_KEY, newTopRatedList)
        outState.putParcelableArrayList(UPCOMING_KEY, newUpComingList)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        var popularList: ArrayList<MovieResult>? = null
        var topRatedList: ArrayList<MovieResult>? = null
        var nowPlayingList: ArrayList<MovieResult>? = null
        var upcomingList: ArrayList<MovieResult>? = null

        if (savedInstanceState != null) {
            popularList = savedInstanceState.getParcelableArrayList(POPULAR_KEY)
            newPopularList.addAll(popularList!!)
            popularAdapter.submitList(popularList)
            topRatedList = savedInstanceState.getParcelableArrayList(POPULAR_KEY)
            newTopRatedList.addAll(topRatedList!!)
            topRatedAdapter.submitList(topRatedList)
            nowPlayingList = savedInstanceState.getParcelableArrayList(POPULAR_KEY)
            newPopularList.addAll(nowPlayingList!!)
            nowPlayingAdapter.submitList(nowPlayingList)
            upcomingList = savedInstanceState.getParcelableArrayList(POPULAR_KEY)
            newUpComingList.addAll(upcomingList!!)
            upcomingAdapter.submitList(upcomingList)
        }
    }

    private fun popularMovies() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesListPopularStateFlow.collect {
                    if (it.isNotEmpty()) {
                        newPopularList.addAll(it)
                        popularAdapter.submitList(newPopularList)
                    }
                    if (isFirstTimePopular) {
                        isFirstTimePopular = false
                    } else {
                        scrollListenerPopular.setLoaded()
                    }
                }
            }
        }
    }

    private fun upcomingMovies() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesListUpcomingStateFlow.collect {
                    if (it.isNotEmpty()) {
                        newUpComingList.addAll(it)
                        upcomingAdapter.submitList(newUpComingList)
                    }
                    if (isFirstTimeuUpcoming) {
                        isFirstTimeuUpcoming = false
                    } else {
                        scrollListenerUpcoming.setLoaded()
                    }
                }
            }
        }
    }

    private fun topratedMovies() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesListTopRatedStateFlow.collect {
                    if (it.isNotEmpty()) {
                        newTopRatedList.addAll(it)
                        topRatedAdapter.submitList(newTopRatedList)
                    }
                    if (isFirstTimeTopRated) {
                        isFirstTimeTopRated = false
                    } else {
                        scrollListenerTopRated.setLoaded()
                    }
                }
            }
        }
    }

    private fun nowPlayingMovies() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesListNowPlayingStateFlow.collect {
                    if (it.isNotEmpty()) {
                        newNowPlayingList.addAll(it)
                        nowPlayingAdapter.submitList(newNowPlayingList)
                    }
                    if (isFirstTimeNowPlaying) {
                        isFirstTimeNowPlaying = false
                    } else {
                        scrollListenerNowPlaying.setLoaded()
                    }
                }
            }
        }
    }

    private fun getRVLayoutManager() {
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        if (isTablet) {
            mPopularLayoutManager = GridLayoutManager(context, 2).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        } else {
            mPopularLayoutManager = GridLayoutManager(context, 1).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        }
        if (isTablet) {
            mUpcomingLayoutManager = GridLayoutManager(context, 2).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        } else {
            mUpcomingLayoutManager = GridLayoutManager(context, 1).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        }
        if (isTablet) {
            mTopRatedLayoutManager = GridLayoutManager(context, 2).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        } else {
            mTopRatedLayoutManager = GridLayoutManager(context, 1).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        }
        if (isTablet) {
            mNowPlayingLayoutManager = GridLayoutManager(context, 2).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        } else {
            mNowPlayingLayoutManager = GridLayoutManager(context, 1).apply {
                orientation = GridLayoutManager.HORIZONTAL
            }
        }
    }

    private fun setRVLayoutManager() {
        binding.popularList.layoutManager = mPopularLayoutManager
        binding.popularList.setHasFixedSize(true)
        binding.upcomingList.layoutManager = mUpcomingLayoutManager
        binding.upcomingList.setHasFixedSize(true)
        binding.topratedList.layoutManager = mTopRatedLayoutManager
        binding.topratedList.setHasFixedSize(true)
        binding.nowPlayingList.layoutManager = mNowPlayingLayoutManager
        binding.nowPlayingList.setHasFixedSize(true)
    }
//
//    private fun setRVPopularScrollListener() {
//        scrollListenerPopular = RecyclerViewLoadMoreScroll(
//            mPopularLayoutManager as GridLayoutManager,
//            GridLayoutManager.HORIZONTAL,
//        )
//        scrollListenerPopular.setOnLoadMoreListener(object :
//            OnLoadMoreListener {
//            override fun onLoadMore() {
//                Log.e("AAAAA == > ", "DFDDD")
//                //  loadMoreDataPopular()
//            }
//        })
//
//        binding.popularList.addOnScrollListener(scrollListenerPopular)
//    }
//
//    private fun setRVUpcomingScrollListener() {
//        scrollListenerUpcoming = RecyclerViewLoadMoreScroll(
//            mUpcomingLayoutManager as GridLayoutManager,
//            GridLayoutManager.HORIZONTAL,
//        )
//        scrollListenerUpcoming.setOnLoadMoreListener(object :
//            OnLoadMoreListener {
//            override fun onLoadMore() {
//                Log.e("BBBBB == > ", "DFDDD")
//                // loadMoreDataUpcoming()
//            }
//        })
//
//        binding.upcomingList.addOnScrollListener(scrollListenerUpcoming)
//    }
//
//    private fun setRVTopRatedScrollListener() {
//        scrollListenerTopRated = RecyclerViewLoadMoreScroll(
//            mTopRatedLayoutManager as GridLayoutManager,
//            GridLayoutManager.HORIZONTAL,
//        )
//        scrollListenerTopRated.setOnLoadMoreListener(object :
//            OnLoadMoreListener {
//            override fun onLoadMore() {
//                Log.e("CCCCC == > ", "DFDDD")
//                //  loadMoreDataTopRated()
//            }
//        })
//
//        binding.topratedList.addOnScrollListener(scrollListenerTopRated)
//    }
//
//    private fun setRVNowPlayingScrollListener() {
//        scrollListenerNowPlaying = RecyclerViewLoadMoreScroll(
//            mNowPlayingLayoutManager as GridLayoutManager,
//            GridLayoutManager.HORIZONTAL,
//        )
//        scrollListenerNowPlaying.setOnLoadMoreListener(object :
//            OnLoadMoreListener {
//            override fun onLoadMore() {
//                Log.e("DDDD == > ", "DFDDD")
//                // loadMoreDataNowplaying()
//            }
//        })
//
//        binding.nowPlayingList.addOnScrollListener(scrollListenerNowPlaying)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    fun loadMoreDataPopular() {
//        Log.e(" pageIndexPopularValue ", viewModel.pageIndexPopularValue.toString())
//        viewModel.getPopularMovies(viewModel.pageIndexPopularValue, MovieEnum.POPULAR.value)
//    }
//
//    fun loadMoreDataUpcoming() {
//        viewModel.getUpcomingMovies(viewModel.pageIndexUpcomingValue, MovieEnum.UPCOMING.value)
//    }
//
//    fun loadMoreDataTopRated() {
//        viewModel.getTopRatedMovies(viewModel.pageIndexUpcomingValue, MovieEnum.TOPRATED.value)
//    }
//
//    fun loadMoreDataNowplaying() {
//        viewModel.getNowPlayingMovies(viewModel.pageIndexNowPlayingValue, MovieEnum.NOWPLAYING.value)
//    }
}
