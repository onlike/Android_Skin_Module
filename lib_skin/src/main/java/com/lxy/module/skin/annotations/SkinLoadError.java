package com.lxy.module.skin.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.lxy.module.skin.config.SkinConfig.LOAD_ERROR_FILE_INTERNAL_ERROR;
import static com.lxy.module.skin.config.SkinConfig.LOAD_ERROR_FILE_NOT_EXIT;
import static com.lxy.module.skin.config.SkinConfig.LOAD_ERROR_ILLEGAL_PACKAGE;
import static com.lxy.module.skin.config.SkinConfig.LOAD_ERROR_ILLEGAL_SIGNATURE;


/**
 * Created by lxy on 2018/3/1.
 * 
 */
@IntDef({
        LOAD_ERROR_FILE_NOT_EXIT,
        LOAD_ERROR_ILLEGAL_SIGNATURE,
        LOAD_ERROR_ILLEGAL_PACKAGE,
        LOAD_ERROR_FILE_INTERNAL_ERROR
})
@Retention(RetentionPolicy.SOURCE)
public @interface SkinLoadError {

}
