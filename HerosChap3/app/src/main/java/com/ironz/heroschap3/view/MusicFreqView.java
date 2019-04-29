package com.ironz.heroschap3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * @author zhoujun
 * @date 19-4-29
 * @email 3038382705@qq.com
 */
public class MusicFreqView extends View {

    private static final String TAG = MusicFreqView.class.getSimpleName();

    private int mWidth, mHeight;
    private int mRectCount = 10;
    private int mRectWidth, mRectHeight;
    private int offset = 3;
    private Paint mPaint;
    private double mRandom;

    public MusicFreqView(Context context) {
        this(context, null);
    }

    public MusicFreqView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicFreqView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Style.FILL);
//        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        Log.d(TAG, "width = " + mWidth + "; mHeight = " + mHeight
                + "; getWidth = " + getWidth() + "; getHeight = " + getHeight());
//        mRectWidth = mWidth / 40;
//        mRectHeight = mRectWidth * 16;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0; i<mRectCount; i++) {
            mRandom = Math.random();
            float currentHeight = (float)(mRectHeight * mRandom);
            canvas.drawRect((float)(mWidth * 0.4 / 2 + mRectWidth * i + offset),
                    currentHeight,
                    (float)(mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint);
        }
        postInvalidateDelayed(300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChange, getWidth = " + getWidth() + "; getHeight = " + getHeight());
//        int width = getWidth();
//        int height = getHeight();
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int)(mWidth * 0.6 / mRectCount);
        LinearGradient gradient = new LinearGradient(0, 0, mRectWidth, mRectHeight,
                Color.YELLOW, Color.BLUE, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);

    }
}
