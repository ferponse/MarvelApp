package com.marvel.common_ui.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 10
    private var loading = false
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    fun setVisibleThreshold(visibleThreshold: Int) {
        this.visibleThreshold = visibleThreshold
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = mLinearLayoutManager.childCount
            totalItemCount = mLinearLayoutManager.itemCount
            firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

            if (!loading) {
                if (visibleItemCount + firstVisibleItem >= totalItemCount && firstVisibleItem >= 0 && totalItemCount >= visibleThreshold) {
                    loading = true
                    recyclerView.post { onLoadMore() }
                }
            }
        }
    }

    abstract fun onLoadMore()

    fun loadOk() {
        loading = false
    }
}