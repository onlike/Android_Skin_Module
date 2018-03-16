package com.lxy.demo_applicaionskin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lxy.demo_applicaionskin.fragment.DynamicAddFragment;
import com.lxy.demo_applicaionskin.fragment.ListFragment;

import java.util.ArrayList;

/**
 * Created by lxy on 2018/3/16.
 * 
 */
public class FragmentAdapter extends FragmentStatePagerAdapter{
    
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String [] mTitles = new String[]{"列表","动态"};
    
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
    
    private void initFragment(){
        
        ListFragment listFragment = new ListFragment();
        mFragments.add(listFragment);
        
        DynamicAddFragment dynamicAddFragment = new DynamicAddFragment();
        mFragments.add(dynamicAddFragment);
    }
}
