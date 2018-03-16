package com.lxy.demo_applicaionskin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxy.demo_applicaionskin.R;
import com.lxy.module.skin.base.BaseSkinFragment;
import com.lxy.module.skin.config.SkinConfig;
import com.lxy.module.skin.dynamic.DynamicAttrWrapper;

import java.util.ArrayList;

/**
 * Created by lxy on 2018/3/16.
 * 
 */
public class DynamicAddFragment extends BaseSkinFragment{
    
    private LinearLayout mContainer;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initView(view);
        
        bindView();
    }

    private void initView(View view) {
        mContainer = view.findViewById(R.id.linearlayout);
    }

    private void bindView() {
        
        LinearLayout.LayoutParams layoutParams ;
        TextView textView ;
        ArrayList<DynamicAttrWrapper> attrWrappers;

        for (int i = 0; i < 15; i++) {

            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 15, 10, 15);
            
            textView = new TextView(getActivity());
            textView.setText("动态添加："+ (i+1));
            textView.setTextColor(getContext().getResources().getColor(R.color.skin_white));
            textView.setBackgroundColor(getContext().getResources().getColor(R.color.skin_main));
            textView.setLayoutParams(layoutParams);

            attrWrappers = new ArrayList<>();
            attrWrappers.add(new DynamicAttrWrapper(SkinConfig.ATTR_NAME_BACKGROUND, R.color.skin_main));
            attrWrappers.add(new DynamicAttrWrapper(SkinConfig.ATTR_NAME_TEXTCOLOR, R.color.skin_white));
            
            dynamicAddSkinView(textView,attrWrappers);
            
            mContainer.addView(textView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
