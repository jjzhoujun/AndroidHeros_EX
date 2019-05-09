package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhoujun
 * @date 19-4-30
 */
public class InstrumentView extends View {

    private Paint mCirclePaint;
    private Paint mLinePaint;
    private Paint mPointerPaint;
    private float mWidth, mHeight;

    public InstrumentView(Context context) {
        super(context);
        initView(context);
    }

    public InstrumentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public InstrumentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Style.STROKE);
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(10);
        mLinePaint = new Paint();
        mLinePaint.setStyle(Style.STROKE);
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setTextSize(30);
        mPointerPaint = new Paint();
        mPointerPaint.setStyle(Style.STROKE);
        mPointerPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 3, mCirclePaint);

        canvas.save();
        canvas.translate(mWidth/2, mHeight/2);
        float lStartY = -mWidth/3;
        for(int i=0; i<24; i++) {
            if(i % 6 == 0) {
                mLinePaint.setStrokeWidth(6);
                canvas.drawLine(0, lStartY, 0, lStartY + 80, mLinePaint);
                canvas.drawText(String.valueOf(i), -10, lStartY + 110, mLinePaint);
            } else {
                mLinePaint.setStrokeWidth(3);
                canvas.drawLine(0, lStartY, 0, lStartY + 40, mLinePaint);
                canvas.drawText(String.valueOf(i), -10, lStartY + 70, mLinePaint);
            }
            canvas.rotate(15);
        }
        canvas.restore();
        canvas.save();
        canvas.translate(mWidth/2, mHeight/2);
        mPointerPaint.setStrokeWidth(20);
        canvas.drawLine(0, 0, 50, 50, mPointerPaint);
        mPointerPaint.setStrokeWidth(10);
        canvas.drawLine(0, 0, 50, 130, mPointerPaint);
        canvas.restore();
    }
}
