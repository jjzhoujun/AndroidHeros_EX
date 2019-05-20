package com.ironz.heros7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * @author zhoujun
 * @date 19-5-16
 */
public class TweenAnimActivity extends AppCompatActivity {

    private ImageView mIvAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tween_anim_layout);
        mIvAnim = findViewById(R.id.iv_img);
    }

    public void btnAlpha(View v) {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        mIvAnim.startAnimation(aa);
    }

    public void btnRotate(View v) {
//        RotateAnimation ra = new RotateAnimation(0, 360, 100, 100);
        RotateAnimation ra = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F,
                RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        ra.setDuration(1000);
        mIvAnim.startAnimation(ra);
    }

    public void btnTranslate(View v) {
        TranslateAnimation ta = new TranslateAnimation(0, 200, 0, 300);
        ta.setDuration(1000);
        mIvAnim.startAnimation(ta);
    }

    public void btnScale(View v) {
//        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F);
        sa.setDuration(1000);
        mIvAnim.startAnimation(sa);
    }

    public void btnAnimationSet(View v) {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(3000);
        as.addAnimation(aa);
        TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 200);
        ta.setDuration(1000);
        as.addAnimation(ta);
        mIvAnim.startAnimation(as);
    }
}
