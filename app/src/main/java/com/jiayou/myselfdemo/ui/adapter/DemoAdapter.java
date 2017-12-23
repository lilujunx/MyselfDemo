package com.jiayou.myselfdemo.ui.adapter;

import android.view.View;
import android.widget.Button;

import com.jiayou.myselfdemo.R;
import com.myself.library.adapter.EasyRecyclerViewAdapter;
import com.myself.library.adapter.EasyRecyclerViewHolder;

/**
 * Created by Malik J on 2017/12/23.
 */

public class DemoAdapter extends EasyRecyclerViewAdapter {
    private Button mTvDel;
    private View.OnClickListener mOnClickListener;

    public DemoAdapter setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    @Override
    public boolean needRoot() {
        return false;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_dome};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        mTvDel = (Button) viewHolder.findViewById(R.id.tv_del);
        mTvDel.setTag(position);
        if (mOnClickListener != null) {
            mTvDel.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }
}
