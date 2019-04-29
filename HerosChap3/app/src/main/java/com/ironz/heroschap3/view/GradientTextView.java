package com.ironz.heroschap3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author zhoujun
 * @date 19-4-1
 * @email 3038382705@qq.com
 */
public class GradientTextView extends AppCompatTextView {

    private final static String TAG = "GradientTextView";

    private Paint mPaint;
    private int mViewWidth = 0, mTranslate = 0;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;

    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "w = " + w + "; h = " + h + "; oldw = " + oldw + "; oldh = " + oldh);
        if(mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.BLUE, 0xffffffff, Color.BLUE},
                        null, TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw... mTranslate = " + mTranslate);
        super.onDraw(canvas);
        if(mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if(mTranslate > 2*mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);

        }
    }
}
