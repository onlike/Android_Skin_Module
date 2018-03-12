package com.lxy.module.skin.parse;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;

import com.lxy.module.skin.SkinManager;
import com.lxy.module.skin.attr.BaseAttr;
import com.lxy.module.skin.dynamic.DynamicAttrWrapper;
import com.lxy.module.skin.factory.LAttrFactory;
import com.lxy.module.skin.mediator.SkinViewMediator;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2018/3/1.
 * 
 */
public class LAttrParse {

    public static SkinViewMediator parseAttrFormXml(Context context, AttributeSet attrs, View view) {

        SkinViewMediator mediator   = null;
        List<BaseAttr> viewAttrs    = new ArrayList<>();

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);

            if (!LAttrFactory.isSupportedAttr(attrName)) {
                continue;
            }

            if (attrValue.startsWith("@")) {
                try {
                    int id = Integer.parseInt(attrValue.substring(1));
                    String entryName = context.getResources().getResourceEntryName(id);
                    String typeName = context.getResources().getResourceTypeName(id);

                    BaseAttr skinAttr = LAttrFactory.get(id, attrName, entryName, typeName);
                    if (skinAttr != null) {
                        viewAttrs.add(skinAttr);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!LSkinUtils.array_isEmpty(viewAttrs)) {
            mediator        = new SkinViewMediator();
            mediator.view   = view;
            mediator.attrs  = viewAttrs;

            if (SkinManager.getInstance().isAlreadySkin()) {
                mediator.apply();
            }
        }

        return mediator;
    }

    public static SkinViewMediator parseAttrForDynamic(Context context, List<DynamicAttrWrapper> attrs, View view) {

        SkinViewMediator mediator   = null;
        List<BaseAttr> viewAttrs    = new ArrayList<>();

        for (DynamicAttrWrapper attr : attrs) {

            int attrId          = attr.targetAttrValueID;
            String attrName     = attr.targetAttrName;

            if (!LAttrFactory.isSupportedAttr(attrName)) {
                continue;
            }

            try {
                
                String entryName    = context.getResources().getResourceEntryName(attrId);
                String typeName     = context.getResources().getResourceTypeName(attrId);

                BaseAttr skinAttr = LAttrFactory.get(attrId, attrName, entryName, typeName);
                if (skinAttr != null) {
                    viewAttrs.add(skinAttr);
                }
                
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }

        if (!LSkinUtils.array_isEmpty(viewAttrs)) {
            mediator = new SkinViewMediator();
            mediator.view = view;
            mediator.attrs = viewAttrs;

            if (SkinManager.getInstance().isAlreadySkin()) {
                mediator.apply();
            }
        }

        return mediator;
    }
}
