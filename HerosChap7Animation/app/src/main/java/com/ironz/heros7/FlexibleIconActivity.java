package com.ironz.heros7;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author zhoujun
 * @date 19-5-21
 */
public class FlexibleIconActivity extends AppCompatActivity implements OnClickListener {

    private ImageView[] mImageViews;
    private boolean mIsExpandFlag = false;

    private LinearLayout mHiddenLayout;
    private int mHiddenHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flex_icon_layout);
        mImageViews = new ImageView[5];
        ImageView iv0 = findViewById(R.id.iv_mid);
        ImageView iv1 = findViewById(R.id.iv_left);
        ImageView iv2 = findViewById(R.id.iv_right);
        ImageView iv3 = findViewById(R.id.iv_top);
        ImageView iv4 = findViewById(R.id.iv_bottom);
        mImageViews[0] = iv0;
        mImageViews[1] = iv1;
        mImageViews[2] = iv2;
        mImageViews[3] = iv3;
        mImageViews[4] = iv4;
        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);

        mHiddenLayout = findViewById(R.id.ll_hidden);
        mHiddenHeight = (int)(getResources().getDisplayMetrics().density * 40 + 0.5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mid:
                if(!mIsExpandFlag) {
                    expandAnim();
                } else {
                    closeAnim();
                }
                break;
            default:
                Toast.makeText(this, "The click id = " + v.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void expandAnim() {
        mIsExpandFlag = true;
        ObjectAnimator animMid = ObjectAnimator.ofFloat(
                mImageViews[0],
                "alpha",
                1F,
                0.5F);
        ObjectAnimator animLeft = ObjectAnimator.ofFloat(
                mImageViews[1],
                "translationX",
                -200F);
        ObjectAnimator animRight = ObjectAnimator.ofFloat(
                mImageViews[2],
                "translationX",
                200F);
        ObjectAnimator animTop = ObjectAnimator.ofFloat(
                mImageViews[3],
                "translationY",
                -200F);
        ObjectAnimator animBottom = ObjectAnimator.ofFloat(
                mImageViews[4],
                "translationY",
                200F);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animMid, animLeft, animRight, animTop, animBottom);
        set.start();
    }

    public void closeAnim() {
        mIsExpandFlag = false;
        ObjectAnimator animMid = ObjectAnimator.ofFloat(
                mImageViews[0],
                "alpha",
                0.5F,
                1F);
        ObjectAnimator animLeft = ObjectAnimator.ofFloat(
                mImageViews[1],
                "translationX",
                -200F,
                0F);
        ObjectAnimator animRight = ObjectAnimator.ofFloat(
                mImageViews[2],
                "translationX",
                200F,
                0F);
        ObjectAnimator animTop = ObjectAnimator.ofFloat(
                mImageViews[3],
                "translationY",
                -200F,
                0F);
        ObjectAnimator animBottom = ObjectAnimator.ofFloat(
                mImageViews[4],
                "translationY",
                200F,
                0F);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animMid, animLeft, animRight, animTop, animBottom);
        set.start();
    }

    public void llClick(View v) {
        if(mHiddenLayout.getVisibility() == View.GONE) {
            showAnim(mHiddenLayout);
        } else {
            hideAnim(mHiddenLayout);
        }
    }

    private void showAnim(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mHiddenHeight);
        animator.start();
    }

    private void hideAnim(final View view) {
        int orgHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, orgHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(1000);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer)animation.getAnimatedValue();
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                lp.height = value;
                view.setLayoutParams(lp);
            }
        });
        return animator;
    }
}
