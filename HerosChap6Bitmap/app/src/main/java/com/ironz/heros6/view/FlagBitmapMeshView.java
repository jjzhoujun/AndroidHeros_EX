package com.ironz.heros6.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.ironz.heros6.R;

/**
 * 旗帜飘扬
 * @author zhoujun
 * @date 19-5-8
 */
public class FlagBitmapMeshView extends View {

    private final int WIDTH = 200;
    private final int HEIGHT = 200;
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);
    private float[] verts = new float[COUNT * 2];
    private float[] org = new float[COUNT * 2];
    private Bitmap bitmap;
    private float A;
    private float k = 1;

    public FlagBitmapMeshView(Context context) {
        super(context);
        initView(context);
    }

    public FlagBitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FlagBitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setFocusable(true);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test3);
        float bitmapWidth = bitmap.getWidth();
        float bitmapHeight = bitmap.getHeight();
        int index = 0;
        for(int y=0; y<=HEIGHT; y++) {
            float fy = bitmapHeight * y / HEIGHT;
            for(int x = 0; x<=WIDTH; x++) {
                float fx = bitmapWidth * x / WIDTH;
                org[index*2] = verts[index*2] = fx;
                org[index*2+1] = verts[index*2+1] = fy + 100;
                index += 1;

            }
        }
        A = 50;
    }

    private void flagWave() {
        for(int j=0; j<=HEIGHT; j++) {
            for(int i=0; i<WIDTH; i++) {
                verts[(j*(WIDTH+1) + i) * 2 + 0] += 0;
                float offsetY = (float)Math.sin((float)i / WIDTH * 2 * Math.PI + Math.PI * k);
                verts[(j*(WIDTH+1) + i) * 2 + 1] =
                        org[(j*WIDTH+i) * 2 + 1] + offsetY * A;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        flagWave();
        k += 0.1F;
        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
        invalidate();
    }
}
