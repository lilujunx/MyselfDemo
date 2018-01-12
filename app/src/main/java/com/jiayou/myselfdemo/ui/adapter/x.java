package com.jiayou.myselfdemo.ui.adapter;

import android.view.View;

import com.myself.library.adapter.EasyRecyclerViewAdapter;
import com.myself.library.adapter.EasyRecyclerViewHolder;

/**
 * Created by Malik J on 2018/1/12.
 */

public class x extends EasyRecyclerViewAdapter {

    private View.OnClickListener mOnClickListener;


    public void setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[0];
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {

    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }
}
