package com.ironz.heroschap3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhoujun
 * @date 19-4-1
 * @email 3038382705@qq.com
 */
public class CircleProgress extends View {

    private Paint mCirclePaint, mArcPaint, mTextPaint;
    private float mRadius;
    private int mCircleXY, mSweepAngle;
    private RectF mArcRectF;
    private String mShowText = "HelloCircleProgress";
    private int mShowTextSize = 16;

    public CircleProgress(Context context) {
        super(context);
        init();
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Style.FILL);
        mCirclePaint.setColor(Color.GREEN);
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Style.STROKE);
        mArcPaint.setColor(Color.YELLOW);
        mArcPaint.setStrokeWidth(20);
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Align.CENTER);
        mTextPaint.setColor(Color.GRAY);

        int lenght = 300;
        mSweepAngle = 90;
        mCircleXY = lenght / 2;
        mRadius = (float)(lenght / 4);

        mArcRectF = new RectF((float)(lenght * 0.1), (float)(lenght * 0.1), (float)(lenght * 0.9), (float)(lenght * 0.9));
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArcPaint);
        canvas.drawText(mShowText, 0, mShowText.length(),
                mCircleXY, mCircleXY, mTextPaint);
    }
}
