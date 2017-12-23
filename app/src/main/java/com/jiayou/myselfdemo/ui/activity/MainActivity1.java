package com.jiayou.myselfdemo.ui.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.jiayou.myselfdemo.R;
import com.jiayou.myselfdemo.entity.TestEntity;
import com.jiayou.myselfdemo.ui.adapter.ExAdapter;
import com.myself.library.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity1 extends BaseActivity {
    @BindView(R.id.lv)
    ListView mLv;
    @BindView(R.id.rg_main)
    RadioGroup mRgMain;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    @BindView(R.id.exLv)
    ExpandableListView mExLv;
//    使用代码设置drawableleft
//Drawable drawable = getResources().getDrawable(
//        R.drawable.ic_qaa_top_icon);
//    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
//    drawable.getMinimumHeight());
//    // / 这一步必须要做,否则不会显示.
//    getTv_title().setCompoundDrawables(null, null, drawable, null);

    private List<TestEntity> mTestEntities = new ArrayList<>();

    @Override
    public int initRootLayout() {
        return R.layout.activity_main;
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
//        ItemLvAdapter mItemLvAdapter = new ItemLvAdapter(mActivitySelf, mTestEntities);
//        mLv.setAdapter(mItemLvAdapter);
        final ExAdapter mExAdapter = new ExAdapter(mActivitySelf, mTestEntities);
        mExAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestEntity entity = (TestEntity) v.getTag();
                CheckBox cb = (CheckBox) v;
                for (int i = 0; i < mTestEntities.size(); i++) {
                    mExLv.collapseGroup(i);
                    mTestEntities.get(i).setCheck(false);
                    mExAdapter.notifyDataSetChanged();
                }
                if (cb.isChecked()) {
                    entity.setCheck(true);
                    mExLv.expandGroup(mTestEntities.indexOf(entity));
                } else {
                    entity.setCheck(false);
                    mExLv.collapseGroup(mTestEntities.indexOf(entity));
                }
            }
        });
        mExLv.setGroupIndicator(null);
        mExLv.setAdapter(mExAdapter);
    }

    @Override
    public void initOthers() {

    }


}
