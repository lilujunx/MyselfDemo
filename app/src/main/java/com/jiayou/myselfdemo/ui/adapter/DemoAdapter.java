package com.jiayou.myselfdemo.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiayou.myselfdemo.R;
import com.myself.library.adapter.EasyRecyclerViewAdapter;
import com.myself.library.adapter.EasyRecyclerViewHolder;
import com.myself.library.view.love.PeriscopeLayout;

/**
 * Created by Malik J on 2017/12/23.
 */

public class DemoAdapter extends EasyRecyclerViewAdapter {
    PeriscopeLayout mPerLayout;
    private Button mTvDel;
    private View.OnClickListener mOnClickListener;
    private Button mTvGood;
    private TextView mTv;
    private Context mContext;
    private int i = 0;
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    if (i < 20) {
//                        RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(0, 0);
//                        mLayoutParams.leftMargin = mTvGood.getLeft();
//                        mLayoutParams.topMargin = mTvGood.getTop();
//                        Log.e("xx", "mTvGood.getLeft()：" + mTvGood.getLeft() + ",,,," + "mTvGood.getTop():" + mTvGood.getTop());
//                        mPerLayout.addHeart(mTvGood, mLayoutParams);
//                        sendEmptyMessageDelayed(0, 100);
//                        i++;
//                    } else {
//                        sendEmptyMessage(1);
//                    }
//                    break;
//                case 1:
//                    i = 0;
//                    break;
//            }
//        }
//    };

    public DemoAdapter(Context mContext) {
        this.mContext = mContext;
    }

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
        mTv = (TextView) viewHolder.findViewById(R.id.tv);
        mTvGood = (Button) viewHolder.findViewById(R.id.tv_good);
        mTvDel = (Button) viewHolder.findViewById(R.id.tv_del);
        mTv.setText((String) getList().get(position));
        mTvDel.setTag(position);
        mTvGood.setTag(position);
        mTvGood.setTag(R.id.id_viewHolder, viewHolder);
        if (mOnClickListener != null) {
            mTvDel.setOnClickListener(mOnClickListener);
        }
        mTvGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int) v.getTag();
                EasyRecyclerViewHolder mTag = (EasyRecyclerViewHolder) v.getTag(R.id.id_viewHolder);
                mPerLayout = (PeriscopeLayout) mTag.findViewById(R.id.perLayout);
                Log.e("xx", "点到了：" + i + "个");
                RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(0, 0);
                mLayoutParams.leftMargin = mTvGood.getLeft();
                mLayoutParams.topMargin = mTvGood.getTop();
                Log.e("xx", "mTvGood.getLeft()：" + mTvGood.getLeft() + ",,,," + "mTvGood.getTop():" + mTvGood.getTop());
                mPerLayout.addHeart(mTvGood, mLayoutParams);
//                sendEmptyMessageDelayed(0, 100);
//                mHandler.sendEmptyMessage(0);
//                TextView tv = new TextView(mContext);
//                tv.setText("hhhhhhh");
//                tv.setBackgroundColor(Color.parseColor("#ff0000"));
//                mPerLayout.addView(tv);
//                mPerLayout.addHeart();
            }
        });
    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }
}
