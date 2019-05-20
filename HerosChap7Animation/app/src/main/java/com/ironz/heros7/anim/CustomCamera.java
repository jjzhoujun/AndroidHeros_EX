package com.ironz.heros7.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author zhoujun
 * @date 19-5-20
 */
public class CustomCamera extends Animation {

    private Camera mCamera = new Camera();
    private int mCenterWidth, mCenterHeight;
    private int mRotateY;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(2000);
        setFillAfter(true);
        setInterpolator(new AccelerateInterpolator());
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
    }

    public void setRotateY(int rotateY) {
        mRotateY = rotateY;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        mCamera.save();
        mCamera.rotateY(mRotateY * interpolatedTime);
        mCamera.getMatrix(matrix);
        mCamera.restore();
        matrix.preTranslate(mCenterWidth, mCenterHeight);
        matrix.postTranslate(-mCenterWidth, -mCenterHeight);
    }
}
