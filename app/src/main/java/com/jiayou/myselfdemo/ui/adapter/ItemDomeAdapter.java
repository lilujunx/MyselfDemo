package com.jiayou.myselfdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.entity.TestEntity;
import com.myself.library.view.love.PeriscopeLayout;

import java.util.ArrayList;
import java.util.List;

public class ItemDomeAdapter extends BaseAdapter {

    private List<TestEntity> mEntities = new ArrayList<TestEntity>();

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ItemDomeAdapter(Context context,List<TestEntity> entities) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public TestEntity getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_dome, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((TestEntity)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(TestEntity entity, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private PeriscopeLayout perLayout;
    private LinearLayout ll;
    private TextView tv;
    private Button tvDel;
    private Button tvGood;

        public ViewHolder(View view) {
            perLayout = (PeriscopeLayout) view.findViewById(R.id.perLayout);
            ll = (LinearLayout) view.findViewById(R.id.ll);
            tv = (TextView) view.findViewById(R.id.tv);
            tvDel = (Button) view.findViewById(R.id.tv_del);
            tvGood = (Button) view.findViewById(R.id.tv_good);
        }
    }
}
