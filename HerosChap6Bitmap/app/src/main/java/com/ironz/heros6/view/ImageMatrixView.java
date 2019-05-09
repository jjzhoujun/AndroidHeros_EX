package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ironz.heros6.R;

/**
 * 变换图片
 * @author zhoujun
 * @date 19-5-8
 */
public class ImageMatrixView extends View {

    private static final String TAG = ImageMatrixView.class.getSimpleName();

    private Matrix mMatrix;
    private Bitmap mBitmap;

    public ImageMatrixView(Context context) {
        super(context);
        initView();
    }

    public ImageMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImageMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test1);
        setImageAndMatrix(bitmap, new Matrix());
    }

    public void setImageAndMatrix(Bitmap bm, Matrix matrix) {
        mBitmap = bm;
        mMatrix = matrix;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "==>>>>onDraw = " + canvas + "; bitmap = " + mBitmap);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }
}
