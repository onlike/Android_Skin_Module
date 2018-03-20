package com.lxy.demo_applicaionskin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.demo_applicaionskin.R;
import com.lxy.demo_applicaionskin.adapter.ListAdapter;
import com.lxy.module.skin.base.BaseSkinFragment;

/**
 * Created by lxy on 2018/3/16.
 * 
 */
public class ListFragment extends BaseSkinFragment{
    
    private RecyclerView rvCore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initVariable();
        
        initView(view);
        
        bindView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void initVariable() {

    }

    private void initView(View rootView) {
        rvCore = rootView.findViewById(R.id.fra_list_rv);
    }

    private void bindView() {
        rvCore.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCore.setAdapter(new ListAdapter(getActivity()));
    }
    
}
