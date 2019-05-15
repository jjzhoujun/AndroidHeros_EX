package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

import com.ironz.heros6.R;

/**
 * @author zhoujun
 * @date 19-5-15
 */
public class ShaderGradientView extends View {

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private Paint mPaint, mGraPaint;

    public ShaderGradientView(Context context) {
        super(context);
        init();
    }

    public ShaderGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShaderGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test2);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_test);
//        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, TileMode.CLAMP);
        mBitmapShader = new BitmapShader(mBitmap, TileMode.REPEAT, TileMode.REPEAT);
        mPaint = new Paint();
        mPaint.setShader(mBitmapShader);

        mGraPaint = new Paint();
        mGraPaint.setShader(new LinearGradient(0, 0, 100, 100,
                Color.BLUE, Color.YELLOW, TileMode.REPEAT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200, 200, 200, mPaint);
//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        canvas.drawRect(400, 200, 600, 400, mGraPaint);
    }
}
