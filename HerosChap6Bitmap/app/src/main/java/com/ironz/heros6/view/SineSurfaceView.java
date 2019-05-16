package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author zhoujun
 * @date 19-5-16
 */
public class SineSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final String TAG = SineSurfaceView.class.getSimpleName();
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    // 子线程标志位
    private boolean mIsDrawing;

    public SineSurfaceView(Context context) {
        super(context);
        initView();
    }

    public SineSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SineSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
//        mHolder.setFormat(PixelFormat.OPAQUE);

        mPaint = new Paint();
        mPaint.setStyle(Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.GREEN);
        mPath = new Path();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        Log.d(TAG, "surfaceCreated==>>>mIsDrawing = " + mIsDrawing);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged==>>>mIsDrawing = " + mIsDrawing);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
        Log.d(TAG, "surfaceDestroyed==>>>mIsDrawing = " + mIsDrawing);
    }

    @Override
    public void run() {
        while(mIsDrawing) {
            draw();
            x += 2;
            y = (int)(100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
            if(x >= mWidth) {
                x = 0;
                mPath.reset();
            }

        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
    }

    private int mWidth;
    private int x, y = 0;
    private Path mPath;
    private Paint mPaint;

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            // draw something
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {

        } finally {
            if(mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
