package com.jiayou.myselfdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.entity.TestEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemLvAdapter extends BaseAdapter {

    private List<TestEntity> mEntities = new ArrayList<TestEntity>();

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ItemLvAdapter(Context context, List<TestEntity> entities) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
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
            convertView = mLayoutInflater.inflate(R.layout.item_lv, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((TestEntity) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(TestEntity entity, final ViewHolder holder, int pos) {
        //TODO implement
        holder.title.setText(entity.getTitle());

        holder.edit.setText(entity.getContent());
        holder.edit.setTag(entity);
        holder.edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    EditText ed = (EditText) v;
                    TestEntity mTestEntity = (TestEntity) ed.getTag();
                    mTestEntity.setContent(ed.getText().toString());
                }
            }
        });

    }

    protected class ViewHolder {
        private TextView title;
        private EditText edit;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            edit = (EditText) view.findViewById(R.id.edit);
        }
    }
}
