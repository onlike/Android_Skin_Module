package com.lxy.module.skin.annotations;

import android.support.annotation.StringDef;

import com.lxy.module.skin.config.SkinConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lxy on 2018/3/6.
 * 
 */
@StringDef({
        SkinConfig.ATTR_NAME_BACKGROUND,
        SkinConfig.ATTR_NAME_SRC,
        SkinConfig.ATTR_NAME_TEXTCOLOR,
        SkinConfig.ATTR_NAME_LIST_SELECTOR,
        SkinConfig.ATTR_NAME_DIVIDER
})
@Retention(RetentionPolicy.SOURCE)
public @interface SkinAttrName {
}
