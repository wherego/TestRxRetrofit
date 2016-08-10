package com.htf.rxretrofit.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * 2016/7/23 13:42
 * Author: htf
 */
public class MyOnScrollListener extends RecyclerView.OnScrollListener {

    private boolean toLast = false;
    private ScrollingLastListener scrollingLastListener;
    
    public interface ScrollingLastListener {
        void onScrolledLast();
    }

    public MyOnScrollListener(ScrollingLastListener scrollingLastListener) {
        this.scrollingLastListener = scrollingLastListener;
    }

    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                /*
                 * dy 表示y轴滑动方向
                 * dx 表示x轴滑动方向
                 */
        if (dy > 0) {
            // 正在向下滑动
            this.toLast = true;
        } else {
            // 停止滑动或者向上滑动
            this.toLast = false;
        }
    }


    @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            // 不滚动
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                // 最后完成显示的item的position 正好是 最后一条数据的index
                if (toLast && manager.findLastCompletelyVisibleItemPosition() ==
                        (manager.getItemCount() - 1)) {
                    scrollingLastListener.onScrolledLast();
                }
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            // 不滚动
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        /*
                         * 由于是StaggeredGridLayoutManager
                         * 取最底部数据可能有两个item，所以判断这之中有一个正好是 最后一条数据的index
                         * 就OK
                         */
                int[] bottom = manager.findLastCompletelyVisibleItemPositions(new int[2]);
                int lastItemCount = manager.getItemCount() - 1;
                if (toLast && (bottom[0] == lastItemCount || bottom[1] == lastItemCount)) {
                    scrollingLastListener.onScrolledLast();
                }
            }
        }
    }
}
