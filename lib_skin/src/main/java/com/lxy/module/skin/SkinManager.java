package com.lxy.module.skin;

import android.app.Application;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.lxy.module.skin.annotations.SkinLoadError;
import com.lxy.module.skin.attr.BaseAttr;
import com.lxy.module.skin.config.SkinConfig;
import com.lxy.module.skin.factory.LAttrFactory;
import com.lxy.module.skin.listener.IResourceParse;
import com.lxy.module.skin.listener.SkinLoadCallback;
import com.lxy.module.skin.listener.SkinUpdateCallback;
import com.lxy.module.skin.parse.LResourceParse;
import com.lxy.module.skin.util.LResourceProviderUtil;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.LinkedList;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class SkinManager {
    
    private static Context mContext;
    
    private LinkedList<SkinUpdateCallback> mSkinObservable ;
    
    private IResourceParse mDefResourceParse ;
    
    private Resources mSkinResource;
    
    private SkinManager() {
        mSkinObservable     = new LinkedList<>();
        mDefResourceParse   = new LResourceParse();
    }
    
    public static void init(Context context){
        if (context instanceof Application){
            mContext = context;
        }else {
            mContext = context.getApplicationContext();
        }
        
        SkinConfig.saveDefaultSkinPath(mContext);
        
        if (getInstance().isAlreadySkin()){
            getInstance().load();
        }
    }
    
    public static SkinManager getInstance(){
        if (LSkinUtils.obj_isNull(mContext)){
            throw new IllegalArgumentException("must init skin module !");
        }
        return SingletonHolder.INSTANCE;
    }
    
    public Context getContext(){
        return mContext;
    }


    public void load(){
        load(null);
    }

    public void load(SkinLoadCallback callback){
        String skinPath = isAlreadySkin() ? SkinConfig.readChangeSkinPath(getContext()) : SkinConfig.readDefaultSkinPath(getContext());
        load(skinPath, callback);
    }

    public void load(final String skinPath, final SkinLoadCallback callback){

        mDefResourceParse.load(getContext(), skinPath, new IResourceParse.ParseInvokeCallback() {

            @Override
            public void start() {
                if (!LSkinUtils.obj_isNull(callback)) callback.loadStart();
            }

            @Override
            public void error(@SkinLoadError int state) {
                if (!LSkinUtils.obj_isNull(callback)) callback.loadError(state);
            }

            @Override
            public void finish(Resources resources) {

                if (!LSkinUtils.obj_isNull(callback)) {
                    callback.loadSuccess();
                }

                SkinConfig.saveChangeSkinPath(getContext(), skinPath);

                setSkinResource(resources);

                applySkin();
            }
        });
    }

    public void addSkinAttrType(String attrName, Class<? extends BaseAttr> cls){
        LAttrFactory.addSupportSkinAttr(attrName, cls);
    }

    public void setResourceParse(IResourceParse parse){
        if (LSkinUtils.obj_isNull(parse)) return;
        this.mDefResourceParse = parse;
    }
    
    
    public void attachObs(SkinUpdateCallback factory){
        if (LSkinUtils.obj_isNull(mSkinObservable)) return;
        
        if (mSkinObservable.contains(factory)) return;
        
        mSkinObservable.add(factory);
    }
    
    public void detachObs(SkinUpdateCallback factory){
        if (LSkinUtils.obj_isNull(mSkinObservable)) return;
        
        if (!mSkinObservable.contains(factory)) return;
        
        mSkinObservable.remove(factory);
    }
    
    public void applySkin(){
        if (LSkinUtils.array_isEmpty(mSkinObservable)) return;
        
        for (SkinUpdateCallback listener : mSkinObservable) {
            listener.onSkinUpdate();
        }
    }

    public Resources getSkinResource() {
        return mSkinResource;
    }

    public void setSkinResource(Resources mSkinResource) {
        this.mSkinResource = mSkinResource;
    }

    public boolean isAlreadySkin(){
        return !isDefaultSkin() && !LSkinUtils.txt_isEmpty(SkinConfig.readChangeSkinPath(getContext()));
    }
    
    public boolean isDefaultSkin(){
        return LSkinUtils.txt_equals(SkinConfig.readDefaultSkinPath(getContext()), SkinConfig.readChangeSkinPath(getContext()));
    }
    
    public void clearSkin(){
        load(SkinConfig.readDefaultSkinPath(getContext()), null);
    }
    
    public int getColor(int resId){
        return LResourceProviderUtil.getColor(getContext(), getSkinResource(), resId);
    }
    
    public Drawable getDrawable(int resId){
        return LResourceProviderUtil.getDrawable(getContext(), getSkinResource(), resId);
    }
    
    public ColorStateList getColorList(int resId){
        return LResourceProviderUtil.getColorStateList(getContext(), getSkinResource(), resId);
    }

    private static class SingletonHolder {
        private static final SkinManager INSTANCE = new SkinManager();
    }
}
