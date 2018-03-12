package com.lxy.module.skin.attr;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by lxy on 2018/3/9.
 * 
 */
public class ListSelectorAttr extends BaseAttr{

    @Override
    public void invoke(View view) {
        
        if (view instanceof AbsListView){
            
            AbsListView alv = (AbsListView) view;
            
            if (isDrawableType()){
                
                alv.setSelector(getDrawable());
            }else if (isColorType()){
                
                alv.setSelector(getColor());
            }
        }
    }
}
