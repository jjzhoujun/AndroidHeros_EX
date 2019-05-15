package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ironz.heros6.R;

/**
 * 刮刮卡效果
 * @author zhoujun
 * @date 19-5-15
 */
public class ScratchCardView extends View {

    private static final String TAG = ScratchCardView.class.getSimpleName();

    private Bitmap mBgBitmap, mFgBitmap;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;

    public ScratchCardView(Context context) {
        super(context);
        init();
    }

    public ScratchCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScratchCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(50);

        mPath = new Path();
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test1);
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(), mBgBitmap.getHeight(), Config.ARGB_8888);
        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBgBitmap, 0, 0, null);
        canvas.drawBitmap(mFgBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
            default:
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }
}
