package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

import com.ironz.heros6.R;

/**
 * @author zhoujun
 * @date 19-5-15
 */
public class ReflectView extends View {

    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;


    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ReflectView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test1);
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0,
                mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);
        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0,
                mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 2,
                0xdd000000, 0x10000000, TileMode.CLAMP));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        mPaint.setXfermode(mXfermode);
        canvas.drawRect(0, mSrcBitmap.getHeight(),
                mRefBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
    }
}
