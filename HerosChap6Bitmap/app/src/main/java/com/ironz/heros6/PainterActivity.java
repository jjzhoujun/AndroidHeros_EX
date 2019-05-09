package com.ironz.heros6;

import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ironz.heros6.view.MixXfermodeView;
import com.ironz.heros6.view.RoundXfermodeView;

/**
 * 6.7 Painter: PorterDuffXfermode, Shader, PathEffect
 * @author zhoujun
 * @date 19-5-8
 */
public class PainterActivity extends AppCompatActivity {

    private final static Mode[] xModes = {Mode.CLEAR, Mode.SRC, Mode.DST, Mode.SRC_OVER, Mode.DST_OVER,
            Mode.SRC_IN, Mode.DST_IN, Mode.SRC_OUT, Mode.DST_OUT, Mode.SRC_ATOP, Mode.DST_ATOP,
            Mode.XOR, Mode.DARKEN, Mode.LIGHTEN, Mode.MULTIPLY, Mode.SCREEN, Mode.ADD, Mode.OVERLAY};
    private RoundXfermodeView mRoundView;
    private GridLayout mGdLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.painter_layout);
        mRoundView = (RoundXfermodeView)findViewById(R.id.iv_img);
        mGdLayout = findViewById(R.id.glayout);
        mGdLayout.post(new Runnable() {
            @Override
            public void run() {
                int size = xModes.length;
                int hightIndex = size / 4 + 1;
                int itemWidth = mGdLayout.getWidth() / 4;
                int itemHeight = mGdLayout.getHeight() / hightIndex;

                for(int i=0; i<size; i++) {
                    MixXfermodeView mixView = new MixXfermodeView(PainterActivity.this);
                    LayoutParams mixLp = new LayoutParams(itemWidth, itemHeight);
                    mixLp.setMargins(30, 30, 30, 30);
                    mixView.setLayoutParams(mixLp);
                    mixView.setXfermode(xModes[i]);
                    mGdLayout.addView(mixView, itemWidth, itemHeight);
                }
            }
        });
    }

    public void btnClear(View v) {
    }

    public void btnSrc(View v) {
    }

    public void btnDst(View v) {
    }


}
