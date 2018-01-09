package com.jiayou.myselfdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.entity.TestEntity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Malik J on 2018/1/9.  import android.support.v7.widget.RecyclerView;
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();
    private Context mContext;
    private List<TestEntity> mList;
    private OnItemClickListener mListener;

    public MyAdapter(Context context, List<TestEntity> list, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mList = list;
        this.mListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_dome, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TestEntity item = mList.get(position);

        //TODO setup views

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(TestEntity item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //TODO Bind views
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}