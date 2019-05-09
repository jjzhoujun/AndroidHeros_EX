package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * 混合模式自定义View
 * @author zhoujun
 * @date 19-5-9
 */
public class MixXfermodeView extends View {

    // SRC是正方形； DST是圆形
    private Paint mSrcPaint, mDstPaint;
    private float mWidth, mHeight, mMidWidth, mMidHeight;
    private PorterDuff.Mode mode;
    private String label;
    private Bitmap mSrcBmp, mDstBmp;


    public MixXfermodeView(Context context) {
        super(context);
        initView();
    }

    public MixXfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MixXfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mSrcPaint = new Paint();
        mDstPaint = new Paint();
        mSrcPaint.setAntiAlias(true);
        mDstPaint.setAntiAlias(true);
        setLayerType(View.LAYER_TYPE_SOFTWARE, mSrcPaint);
        setLayerType(View.LAYER_TYPE_SOFTWARE, mDstPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mMidWidth = w / 2F;
        mMidHeight = h / 2F;

        //DST 圆形
        mDstBmp = Bitmap.createBitmap((int)mMidWidth, (int)mMidWidth, Config.ARGB_8888);
        Canvas dstCanvas = new Canvas(mDstBmp);
        // todo 如果有颜色的话， 那么SRC_IN的范围就会带这块
//        dstCanvas.drawColor(Color.YELLOW);
        dstCanvas.drawColor(Color.TRANSPARENT);
        mDstPaint.setColor(Color.GREEN);
        dstCanvas.drawCircle(mMidWidth / 2, mMidWidth / 2, mMidWidth / 2, mDstPaint);

        // SRC 正方形
        mSrcBmp = Bitmap.createBitmap((int)mMidWidth, (int)mMidWidth, Config.ARGB_8888);
        Canvas canvas = new Canvas(mSrcBmp);
//        canvas.drawColor(Color.CYAN);
        canvas.drawColor(Color.TRANSPARENT);
        mSrcPaint.setColor(Color.BLUE);
        canvas.drawRect(mMidWidth / 3, mMidWidth / 3, mMidWidth, mMidWidth, mSrcPaint);

    }

    public void setXfermode(PorterDuff.Mode mode) {
        this.mode = mode;
        this.label = mode.name();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(mDstBmp, 0, 0, mDstPaint);
        mSrcPaint.setXfermode(new PorterDuffXfermode(mode));
//        canvas.drawBitmap(mSrcBmp, mMidWidth / 2, mMidHeight / 2, mSrcPaint);
        canvas.drawBitmap(mSrcBmp, 0, 0, mSrcPaint);
        mSrcPaint.setXfermode(null);
        mSrcPaint.setColor(Color.BLACK);
        mSrcPaint.setTextSize(40);
        canvas.drawText(label, mMidWidth - mWidth / 4, mHeight - 20, mSrcPaint);
//        // DST
//        mPaint.setColor(Color.GREEN);
//        canvas.drawCircle(mMidWidth - 20, mMidHeight - 20, mWidth / 4, mPaint);
//
//        // SRC
//        mPaint.setXfermode(new PorterDuffXfermode(mode));
//        mPaint.setColor(Color.BLUE);
//        float offset = mWidth / 5;
//        canvas.drawRect(mMidWidth - offset * 0.8F, mMidHeight - offset * 0.8F,
//                mMidWidth + offset * 1.2F, mMidHeight + offset * 1.2F, mPaint);
//        mPaint.setXfermode(null);
//
//        mPaint.setColor(Color.BLACK);
//        mPaint.setTextSize(40);
//        canvas.drawText(label, mMidWidth - offset, mHeight - 20, mPaint);

    }
}
