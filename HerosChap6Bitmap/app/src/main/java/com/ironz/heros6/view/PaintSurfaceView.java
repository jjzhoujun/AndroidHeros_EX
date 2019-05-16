package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author zhoujun
 * @date 19-5-16
 */
public class PaintSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private final static String TAG = PaintSurfaceView.class.getSimpleName();
    private Paint mPaint;
    private Path mPath;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private SurfaceHolder mHolder;

    public PaintSurfaceView(Context context) {
        super(context);
        initView();
    }

    public PaintSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PaintSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mHolder.setKeepScreenOn(true);

        mCanvas = new Canvas();
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Log.d(TAG, "runnable ===, mIsDrawing = " + mIsDrawing + "; startTime = " + start);
        while(mIsDrawing) {
            draw();
        }
        long end = System.currentTimeMillis();
        Log.d(TAG, "runnable ===,after mIsDrawing = " + mIsDrawing + "; endTime = " + end);

    }

    private void draw() {
        try{
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        }catch (Exception e) {

        }finally {
            if(mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
