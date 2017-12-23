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
import com.jiayou.myselfdemo.utils.TLog;

import java.util.List;

/**
 * Created by Malik J on 2017/12/18.
 */

public class ExAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<TestEntity> mTestEntities;
    private LayoutInflater mLayoutInflater;

    private View.OnClickListener mOnClickListener;


    public ExAdapter setOnClickListener(View.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    public ExAdapter(Context mContext, List<TestEntity> mTestEntities) {
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
        TLog.error("getGroupView-----------"+groupPosition+"----");
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_group_expandablelistview, null);
            convertView.setTag(new GroupViewHolder(convertView));
        }

        GroupViewHolder mGroupViewHolder = (GroupViewHolder) convertView.getTag();
        TestEntity mTestEntity = getGroup(groupPosition);
        mGroupViewHolder.mUserNameTv.setText(mTestEntity.getTitle());
        mGroupViewHolder.mCb.setChecked(mTestEntity.isCheck());

        mGroupViewHolder.mCb.setTag(mTestEntity);
        if (mOnClickListener != null) {
            mGroupViewHolder.mCb.setOnClickListener(mOnClickListener);
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TLog.error("-----------getChildView"+groupPosition+"----"+childPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_child_expandablelistview, null);
            convertView.setTag(new ChildViewHolder(convertView));
        }
        ChildViewHolder mChildViewHolder = (ChildViewHolder) convertView.getTag();
        TestEntity mTestEntity = getGroup(groupPosition);
        mChildViewHolder.mRemarksEt.setText(mTestEntity.getContent());
        mChildViewHolder.mRemarksEt.setTag(mTestEntity);

        mChildViewHolder.mRemarksEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText ed = (EditText) v;
                if (!hasFocus) {
                    TestEntity mEntity = (TestEntity) ed.getTag();
                    mEntity.setContent(ed.getText().toString());

                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    protected class GroupViewHolder {
        private TextView mUserNameTv;
        private TextView mRelationTv;
        private TextView mMobileTv;
        private CheckBox mCb;


        public GroupViewHolder(View mView) {
            mUserNameTv = (TextView) mView.findViewById(R.id.user_name_tv);
            mRelationTv = (TextView) mView.findViewById(R.id.relation_tv);
            mMobileTv = (TextView) mView.findViewById(R.id.mobile_tv);
            mCb = (CheckBox) mView.findViewById(R.id.cb);

        }
    }


    protected class ChildViewHolder {

        private EditText mRemarksEt;




        public ChildViewHolder(View mView) {

            mRemarksEt = (EditText) mView.findViewById(R.id.remarks_et);

        }
    }
}
