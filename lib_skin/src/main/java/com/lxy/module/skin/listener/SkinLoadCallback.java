package com.lxy.module.skin.listener;

import com.lxy.module.skin.annotations.SkinLoadError;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public interface SkinLoadCallback {
    void loadStart();
    void loadSuccess();
    void loadError(@SkinLoadError int state);
}
