package com.ironz.heros7.anim;

import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author zhoujun
 * @date 19-5-20
 */
public class CustomTVAnim extends Animation {

    private int mCenterWidth, mCenterHeight;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(1000);
        setFillAfter(true);
        setInterpolator(new AccelerateInterpolator());
        mCenterWidth = width / 2;
        mCenterHeight = width / 2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);
    }
}
