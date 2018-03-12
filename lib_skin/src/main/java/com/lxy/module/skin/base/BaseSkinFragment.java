package com.lxy.module.skin.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.module.skin.dynamic.DynamicAttrWrapper;
import com.lxy.module.skin.util.LSkinUtils;

import java.util.List;

/**
 * Created by lxy on 2018/3/6.
 * 
 */
public class BaseSkinFragment extends Fragment {

    @Override
    public void onDestroyView() {
        removeSkinViewOnDestroy(getView());
        super.onDestroyView();
    }

    private void removeSkinViewOnDestroy(View rootView) {

        if (rootView instanceof ViewGroup) {

            ViewGroup root = (ViewGroup) rootView;

            for (int i = 0; i < root.getChildCount(); i++) {
                removeSkinViewOnDestroy(rootView);
            }

            dynamicRemoveSkinView(rootView);

        } else {

            dynamicRemoveSkinView(rootView);
        }

    }

    protected void dynamicAddSkinView(View view, List<DynamicAttrWrapper> attrs) {

        if (LSkinUtils.obj_isNull(getActivity())) return;
        if (getActivity().isDestroyed()) return;
        if (getActivity().isFinishing()) return;

        if (getActivity() instanceof BaseSkinActivity) {
            ((BaseSkinActivity) getActivity()).dynamicAddSkinView(view, attrs);
        }

    }

    protected void dynamicRemoveSkinView(View view) {

        if (LSkinUtils.obj_isNull(getActivity())) return;

        if (getActivity() instanceof BaseSkinActivity) {
            ((BaseSkinActivity) getActivity()).dynamicRemoveSkinView(view);
        }
    }
}
