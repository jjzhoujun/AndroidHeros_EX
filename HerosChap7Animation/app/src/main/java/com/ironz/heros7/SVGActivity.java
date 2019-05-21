package com.ironz.heros7;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * @author zhoujun
 * @date 19-5-21
 */
public class SVGActivity extends AppCompatActivity {

    private final String TAG = SVGActivity.class.getSimpleName();
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.svg_anim_layout);
        mImageView = findViewById(R.id.iv_img);

    }

    public void btnStart(View v) {
        Drawable drawable = mImageView.getDrawable();
        Log.d(TAG, "===>>>drawable == " + drawable);
        //todo 这里没动画效果出来。。
        if(drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
