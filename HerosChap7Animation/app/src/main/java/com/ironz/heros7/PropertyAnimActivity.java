package com.ironz.heros7;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author zhoujun
 * @date 19-5-16
 */
public class PropertyAnimActivity extends AppCompatActivity {

    private static final String TAG = PropertyAnimActivity.class.getSimpleName();

    private LinearLayout mRootView;
    private ImageView mIvFish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_anim_layout);
        mRootView = findViewById(R.id.ll_root);
        mIvFish = findViewById(R.id.iv_img);
    }

    public void btnTrans(View v) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(
//                mIvFish, "translationX", 1000);
//        animator.setDuration(300);
//        animator.start();
        WrapperView wrapperView = new WrapperView(mIvFish);
        // 用的反射
        ObjectAnimator.ofInt(wrapperView, "width", 500).setDuration(1000).start();
    }

    public void btnHolder(View v) {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("translationX", 300F);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1F, 0, 1F);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1F, 0, 1F);
        ObjectAnimator.ofPropertyValuesHolder(mIvFish, pvh1, pvh2, pvh3)
                .setDuration(1000).start();
    }

    public void btnValue(View v) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.setTarget(mIvFish);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float)animation.getAnimatedValue();
                // Todo value
                Log.d(TAG, "value====>>>>" + value);
            }
        });
    }

    public void btnAnimator(View v) {
        ObjectAnimator ba1 = ObjectAnimator.ofFloat(mIvFish, "translationX", 300F);
        ObjectAnimator ba2 = ObjectAnimator.ofFloat(mIvFish, "scaleX", 1F, 0, 1F);
        ObjectAnimator ba3 = ObjectAnimator.ofFloat(mIvFish, "scaleY", 1F, 0, 1F);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
//        set.playTogether(ba1, ba2, ba3);
        set.playSequentially(ba1, ba2, ba3);
        set.start();
    }

    public void btnAnimate(View v) {
        mIvFish.animate()
                .alpha(0.4F)
                .y(300)
                .setDuration(1000)
                .start();
    }


    public void btnAnimFromXML(View v) {
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.proper_anim);
        anim.setTarget(mIvFish);
        anim.start();
    }


    private static class WrapperView {
        private View mTarget;
        public WrapperView(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }

    public void btnLayoutAnim(View v) {
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LayoutParams(300, 300));
        ll.setBackgroundColor(Color.GREEN);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);
        LayoutAnimationController lac = new LayoutAnimationController(sa, 0.5F);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        ll.setLayoutAnimation(lac);
        // 作用在layout里面的元素的
        Button button = new Button(this);
        button.setText("anim");
        button.setTextColor(Color.BLACK);
        ll.addView(button);
        mRootView.addView(ll);

    }
}
