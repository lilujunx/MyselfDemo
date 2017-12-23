package com.jiayou.myselfdemo.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.entity.TestEntity;
import com.jiayou.myselfdemo.ui.adapter.ExAdapter;
import com.myself.library.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity2 extends BaseActivity {


    //        @BindView(R.id.expandableList_view)
//        MyExpandableListView mExpandableListView;
    @BindView(R.id.expandableList_view)
    ExpandableListView mExpandableListView;


    private List<TestEntity> mTestEntities = new ArrayList<>();

    @Override
    public int initRootLayout() {
        return R.layout.activity_add_recording;
    }

    @Override
    public void initViews() {
        for (int i = 0; i < 50; i++) {
            TestEntity mTestEntity = new TestEntity();
            mTestEntity.setTitle("我是数据" + i);
            mTestEntities.add(mTestEntity);
        }
    }

    @Override
    public void initDatas() {
        View mInflate = LayoutInflater.from(mActivitySelf).inflate(R.layout.other, null);

        final ExAdapter mExAdapter = new ExAdapter(mActivitySelf, mTestEntities);
        mExAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestEntity entity = (TestEntity) v.getTag();
                CheckBox cb = (CheckBox) v;

                for (int i = 0; i < mTestEntities.size(); i++) {
                    mExpandableListView.collapseGroup(i);
                    mTestEntities.get(i).setCheck(false);
                    mExAdapter.notifyDataSetChanged();
                }
                if (cb.isChecked()) {
                    entity.setCheck(true);
                    mExpandableListView.expandGroup(mTestEntities.indexOf(entity));
                } else {
                    entity.setCheck(false);
                    mExpandableListView.collapseGroup(mTestEntities.indexOf(entity));
                }
            }
        });
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setAdapter(mExAdapter);
        mExpandableListView.addFooterView(mInflate);
    }

    @Override
    public void initOthers() {

    }


}
