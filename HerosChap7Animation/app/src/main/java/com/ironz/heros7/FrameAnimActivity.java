package com.ironz.heros7;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * @author zhoujun
 * @date 19-5-16
 */
public class FrameAnimActivity extends AppCompatActivity {

    private ImageView mIvAnim;
    private AnimationDrawable mAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_anim_layout);
        mIvAnim = findViewById(R.id.iv_img);
    }

    public void btnShowAnim(View view) {
        mIvAnim.setImageResource(R.drawable.frame_anim);
        mAnim = (AnimationDrawable)mIvAnim.getDrawable();
        mAnim.start();
    }

    public void btnStopAnim(View view) {
        mAnim.stop();
    }


}
