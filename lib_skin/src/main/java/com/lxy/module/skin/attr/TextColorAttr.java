package com.lxy.module.skin.attr;

import android.view.View;
import android.widget.TextView;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class TextColorAttr extends BaseAttr{
    
    @Override
    public void invoke(View view) {
        
        if (view instanceof TextView){

            TextView textView = (TextView) view;

            if (isColorType()){

                textView.setTextColor(getColorList());
            }
        }
    }
}
