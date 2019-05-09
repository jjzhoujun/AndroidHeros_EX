package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhoujun
 * @date 19-5-5
 */
public class LayerView extends View {
    private Paint mPaint;

    public LayerView(Context context) {
        super(context);
        initView(context);
    }

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, mPaint);

        canvas.saveLayerAlpha(0, 0, 400, 400, 127, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.restore();
    }
}
