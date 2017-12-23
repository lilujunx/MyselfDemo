package com.myself.library.control;


import com.myself.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FragmentControl {
    private static List<BaseFragment> mBaseFragments=new ArrayList<>();
    
    public static void add(BaseFragment baseFragment){
        mBaseFragments.add(baseFragment);
        
    }
    
    public static void remove(BaseFragment baseFragment){
        mBaseFragments.remove(baseFragment);
        
    }
    
    public static BaseFragment getFragment(Class baseFragment){
        for (BaseFragment base : mBaseFragments) {
            if (base.getClass()==baseFragment) {
                return base;
            }
        }
        return null;
    }
}
