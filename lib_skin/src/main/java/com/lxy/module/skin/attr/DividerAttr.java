package com.lxy.module.skin.attr;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

/**
 * Created by lxy on 2018/3/9.
 * 
 */
public class DividerAttr extends BaseAttr{
    
    @Override
    public void invoke(View view) {
        
        if (view instanceof ListView){

            ListView lv = (ListView) view;

            int dividerHeight = lv.getDividerHeight();

            if (isDrawableType()) {

                lv.setDivider(getDrawable());
            } else if (isColorType()) {

                lv.setDivider(new ColorDrawable(getColor()));
                lv.setDividerHeight(dividerHeight);
            }
        }
    }
}
