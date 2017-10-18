package com.ovwvwvo.wallpaper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Copyright ©2017 by rawer
 * <p>
 * fix bug : 图片缩放时: java.lang.IllegalArgumentException: pointerIndex out of range
 * <p>
 * {@link "http://blog.csdn.net/nnmmbb/article/details/28419779"}
 */

public class ViewPageFixed extends android.support.v4.view.ViewPager {

    public ViewPageFixed(Context context) {
        super(context);
    }

    public ViewPageFixed(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
