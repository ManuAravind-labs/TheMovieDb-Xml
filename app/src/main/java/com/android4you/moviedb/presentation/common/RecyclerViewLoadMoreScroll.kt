package com.android4you.moviedb.presentation.common

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewLoadMoreScroll : RecyclerView.OnScrollListener {

    private var visibleThreshold = 5
    private lateinit var mOnLoadMoreListener: OnLoadMoreListener
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var mLayoutManager: RecyclerView.LayoutManager
    private var orientation: Int = RecyclerView.VERTICAL

    fun setLoaded() {
        isLoading = false
    }

    fun getLoaded(): Boolean {
        return isLoading
    }

    fun setOnLoadMoreListener(mOnLoadMoreListener: OnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }

    constructor(layoutManager: LinearLayoutManager, orientation: Int) {
        this.mLayoutManager = layoutManager
        this.orientation = orientation
    }

    constructor(layoutManager: GridLayoutManager, orientation: Int) {
        this.mLayoutManager = layoutManager
        this.orientation = orientation
        visibleThreshold *= layoutManager.spanCount
    }
//
//    constructor(layoutManager: StaggeredGridLayoutManager) {
//        this.mLayoutManager = layoutManager
//        visibleThreshold *= layoutManager.spanCount
//    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        Log.e("Scrolled", "FFFF")
        if (orientation == RecyclerView.VERTICAL) {
            if (dy <= 0) return
        } else {
            if (dx <= 0) return
        }

        totalItemCount = mLayoutManager.itemCount

        if (mLayoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions =
                (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
            // get maximum element within the list
            lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
        } else if (mLayoutManager is GridLayoutManager) {
            lastVisibleItem = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
        } else if (mLayoutManager is LinearLayoutManager) {
            lastVisibleItem = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
            mOnLoadMoreListener.onLoadMore()
            isLoading = true
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}
