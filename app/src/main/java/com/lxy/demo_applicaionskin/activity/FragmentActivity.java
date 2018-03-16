package com.lxy.demo_applicaionskin.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.lxy.demo_applicaionskin.R;
import com.lxy.demo_applicaionskin.adapter.FragmentAdapter;
import com.lxy.module.skin.base.BaseSkinActivity;

/**
 * Created by lxy on 2018/3/16.
 * 
 */
public class FragmentActivity extends BaseSkinActivity {
    
    private FragmentAdapter mFragmentAdapter;
    
    private TabLayout tabLayout;
    
    private ViewPager viewPager;
    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initVariable();
        initView();
        bindView();
    }

    private void initVariable() {
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }


    private void bindView() {
        viewPager.setAdapter(mFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
