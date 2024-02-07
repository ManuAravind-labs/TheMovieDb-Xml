package com.android4you.moviedb.presentation.tvshows

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.android4you.moviedb.R
import com.android4you.moviedb.databinding.FragmentTvshowsBinding
import com.android4you.moviedb.presentation.common.SpanningLinearLayoutManager
import com.android4you.moviedb.presentation.common.TabLayoutAdapter
import com.android4you.moviedb.utils.common.TVShowEnum

class TvShowsFragment : Fragment() {

    private var _binding: FragmentTvshowsBinding? = null
    private val binding get() = _binding!!

    private var selectedPosition: Int = 0
    private var tabLayoutAdapter: TabLayoutAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTvshowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.tv_container) as NavHostFragment
        val isTablet = requireContext().resources.getBoolean(R.bool.isTablet)
        val tabList = ArrayList<String>()
        tabList.add(getString(R.string.popular))
        tabList.add(getString(R.string.toprated))
        tabList.add(getString(R.string.on_air))
        tabList.add(getString(R.string.airing_today))
        tabLayoutAdapter = TabLayoutAdapter(tabList) {
            selectedPosition = it
            when (it) {
                0 -> {
                    val bundle = bundleOf("tvshow_type" to TVShowEnum.POPULAR.value)
                    navHostFragment.navController.navigate(R.id.TVShowsContentFragment, bundle)
                }

                1 -> {
                    val bundle = bundleOf("tvshow_type" to TVShowEnum.TOPRATED.value)
                    navHostFragment.navController.navigate(R.id.TVShowsContentFragment, bundle)
                }

                2 -> {
                    val bundle = bundleOf("tvshow_type" to TVShowEnum.ON_AIR.value)
                    navHostFragment.navController.navigate(R.id.TVShowsContentFragment, bundle)
                }

                3 -> {
                    val bundle = bundleOf("tvshow_type" to TVShowEnum.AIRING_TODAY.value)
                    navHostFragment.navController.navigate(R.id.TVShowsContentFragment, bundle)
                }

                else -> "NA"
            }
        }
        _binding?.tvTabLayout?.apply {
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
            selectedPosition = savedInstanceState.getInt("POSITION") ?: 0
            tabLayoutAdapter?.setSelectedPosition(selectedPosition)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
