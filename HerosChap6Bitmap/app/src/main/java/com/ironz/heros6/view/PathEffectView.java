package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathDashPathEffect;
import android.graphics.PathDashPathEffect.Style;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhoujun
 * @date 19-5-15
 */
public class PathEffectView extends View {

    private PathEffect[] mEffects = new PathEffect[6];
    private Path mPath;
    private Paint mPaint;

    public PathEffectView(Context context) {
        super(context);
        init();
    }

    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.moveTo(0, 0);
        for(int i=0; i<=30; i++) {
            mPath.lineTo(i * 35, (float)(Math.random() * 100));
        }
        mEffects[0] = null;
        mEffects[1] = new CornerPathEffect(30);
        mEffects[2] = new DiscretePathEffect(3.0F, 5.0F);
        mEffects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Direction.CCW);
        mEffects[4] = new PathDashPathEffect(path, 12, 0, Style.ROTATE);
        mEffects[5] = new ComposePathEffect(mEffects[3], mEffects[1]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0; i<mEffects.length; i++) {
            mPaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath, mPaint);
            canvas.translate(0, 200);
        }
    }
}
