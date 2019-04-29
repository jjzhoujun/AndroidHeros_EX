package com.ironz.heroschap3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @author zhoujun
 * @date 19-4-1
 * @email 3038382705@qq.com
 */
public class LineTextView extends android.support.v7.widget.AppCompatTextView {

    private final static String TAG = "LineTextView";

    private Paint mPaintOutside, mPaintInside;

    public LineTextView(Context context) {
        super(context);
        init();
    }

    public LineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintOutside = new Paint();
        mPaintOutside.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaintOutside.setStyle(Paint.Style.FILL);
        mPaintInside = new Paint();
        mPaintInside.setColor(Color.YELLOW);
        mPaintInside.setStyle(Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "wid = " + widthMeasureSpec + "; height = " + heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        Log.d(TAG, "mode = " + specMode + "; size = " + specSize);

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        Log.d(TAG, "mode = " + specMode + "; size = " + specSize);

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 100;
            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0,
                getMeasuredWidth(), getMeasuredHeight(), mPaintOutside);
        canvas.drawRect(10, 10,
                getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaintInside);
        canvas.save();
        canvas.translate(10 , 0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
