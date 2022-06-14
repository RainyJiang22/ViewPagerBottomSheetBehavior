package com.base.custom_bottomsheet;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Method;


public class ViewPagerUtils {

    public static View getCurrentView(ViewPager viewPager) {
        final int currentItem = viewPager.getCurrentItem();
        for (int i = 0; i < viewPager.getChildCount(); i++) {
            final View child = viewPager.getChildAt(i);
            final ViewPager.LayoutParams layoutParams = (ViewPager.LayoutParams) child.getLayoutParams();

            String propertyName = "position";
            // 1.根据属性名称就可以获取其get方法
            String getMethodName = "get"
                    + propertyName.substring(0, 1).toUpperCase()
                    + propertyName.substring(1);
            //2.获取方法对象
            Class c = layoutParams.getClass();
            int position = 0;

            try {
                //get方法都是public的且无参数
                Method m = c.getMethod(getMethodName);
                //3 通过方法的反射操作方法
                Object value = m.invoke(layoutParams);
                position = (int)value;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            if (!layoutParams.isDecor && currentItem == position) {
                return child;
            }
        }
        return null;
    }

}
