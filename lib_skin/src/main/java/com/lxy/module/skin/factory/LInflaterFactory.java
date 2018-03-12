package com.lxy.module.skin.factory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

import com.lxy.module.skin.SkinManager;
import com.lxy.module.skin.dynamic.DynamicAttrWrapper;
import com.lxy.module.skin.listener.ILInflaterFactory;
import com.lxy.module.skin.listener.SkinUpdateCallback;
import com.lxy.module.skin.mediator.SkinViewMediator;
import com.lxy.module.skin.parse.LAttrParse;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class LInflaterFactory 
        implements
            ILInflaterFactory,
            SkinUpdateCallback
{
    
    private AppCompatActivity mActivity ;
    
    private ArrayList<SkinViewMediator> mViewMediators = new ArrayList<>();

    public LInflaterFactory(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        
        if (!LAttrFactory.isAttrEnableSkin(attrs)) return null;

        AppCompatDelegate delegate = mActivity.getDelegate();
        
        View view = delegate.createView(parent, name, context, attrs);

        if (view == null) {
            view = LViewFactory.createViewFromTag(context, name, attrs);
        }
        
        if (view != null) {
            parseSkinAttr(context, attrs, view);
        }
        
        return view;
    }

    @Override
    public void onResume() {
        SkinManager.getInstance().attachObs(this);
    }

    @Override
    public void onDestroy() {
        SkinManager.getInstance().detachObs(this);
        if (hasSkinView()){
            clearSkinView();
        }
    }

    @Override
    public void dynamicAddSkinView(Context context, View view, List<DynamicAttrWrapper> attrs) {
        if (LSkinUtils.obj_isNull(view)) return;
        if (LSkinUtils.array_isEmpty(attrs)) return;
        
        SkinViewMediator mediator = LAttrParse.parseAttrForDynamic(context, attrs, view);
        addMediator(mediator);

    }

    @Override
    public void dynamicRemoveSkinView(Context context, View view) {
        if (LSkinUtils.obj_isNull(view)) return;
        
        removeMediator(view);
        
    }

    @Override
    public void onSkinUpdate() {
        if (!hasSkinView()) return;

        for (SkinViewMediator mediator : mViewMediators) {
            mediator.apply();
        }
    }

    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        SkinViewMediator mediator = LAttrParse.parseAttrFormXml(context, attrs, view);
        addMediator(mediator);
    }
    
    private void addMediator(SkinViewMediator mediator){
        if (!LSkinUtils.obj_isNull(mediator)){
            mViewMediators.add(mediator);
        }
    }
    
    private synchronized void removeMediator(SkinViewMediator mediator){
        if (!LSkinUtils.obj_isNull(mediator)){
            mViewMediators.remove(mediator);
        }
    }
    
    private synchronized void removeMediator(View view){
        if (!LSkinUtils.obj_isNull(view)){
            
            Iterator<SkinViewMediator> iterator = mViewMediators.iterator();
            
            while (iterator.hasNext()){
                
                SkinViewMediator mediator = iterator.next();
                
                if (LSkinUtils.obj_equals(mediator.view, view)){
                    
                    iterator.remove();
                }
            }
        }
    }
    
    private boolean hasSkinView(){
        return !LSkinUtils.array_isEmpty(mViewMediators);
    }
    
    private void clearSkinView(){
        
        for (SkinViewMediator mediator : mViewMediators) {
            mediator.clean();
        }
        
        mViewMediators.clear();
        mViewMediators = null;
    }

}
