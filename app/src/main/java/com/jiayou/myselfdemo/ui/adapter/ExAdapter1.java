package com.jiayou.myselfdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.entity.TestEntity;

import java.util.List;

/**
 * Created by Malik J on 2017/12/18.
 */

public class ExAdapter1 extends BaseExpandableListAdapter {

    private Context mContext;
    private List<TestEntity> mTestEntities;
    private LayoutInflater mLayoutInflater;

    private View.OnClickListener mOnClickListener;


    public ExAdapter1 setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    public ExAdapter1(Context mContext, List<TestEntity> mTestEntities) {
        this.mContext = mContext;
        this.mTestEntities = mTestEntities;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getGroupCount() {
        return mTestEntities.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public TestEntity getGroup(int groupPosition) {
        return mTestEntities.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_ex_group, null);
            convertView.setTag(new GroupViewHolder(convertView));
        }
        GroupViewHolder mGroupViewHolder = (GroupViewHolder) convertView.getTag();
        TestEntity mTestEntity = getGroup(groupPosition);
        mGroupViewHolder.mTv1.setText(mTestEntity.getTitle());
        mGroupViewHolder.mTv3.setChecked(mTestEntity.isCheck());
        mGroupViewHolder.mTv3.setTag(mTestEntity);
        if (mOnClickListener != null) {
            mGroupViewHolder.mTv3.setOnClickListener(mOnClickListener);
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_lv, null);
            convertView.setTag(new ChildViewHolder(convertView));
        }
        ChildViewHolder mChildViewHolder = (ChildViewHolder) convertView.getTag();
        mChildViewHolder.mTitle.setText("我是：" + groupPosition + "组的" + childPosition + "个");
        TestEntity mTestEntity = getGroup(groupPosition);
        mChildViewHolder.mEdit.setText(mTestEntity.getContent());
        mChildViewHolder.mEdit.setTag(mTestEntity);
        mChildViewHolder.mEdit.clearFocus();
        mChildViewHolder.mEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText ed = (EditText) v;
                TestEntity mEntity = (TestEntity) ed.getTag();
                mEntity.setContent(ed.getText().toString());
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    protected class GroupViewHolder {
        private TextView mTv1;
        private TextView mTv2;
        private CheckBox mTv3;

        public GroupViewHolder(View mView) {
            mTv1 = (TextView) mView.findViewById(R.id.tv1);
            mTv2 = (TextView) mView.findViewById(R.id.tv2);
            mTv3 = (CheckBox) mView.findViewById(R.id.tv3);

        }
    }


    protected class ChildViewHolder {
        private TextView mTitle;
        private EditText mEdit;


        public ChildViewHolder(View mView) {
            mTitle = (TextView) mView.findViewById(R.id.title);
            mEdit = (EditText) mView.findViewById(R.id.edit);

        }
    }
}
