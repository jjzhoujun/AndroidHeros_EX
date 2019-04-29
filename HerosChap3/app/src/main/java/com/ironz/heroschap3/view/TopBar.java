package com.ironz.heroschap3.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ironz.heroschap3.R;

/**
 * @author zhoujun
 * @date 19-4-1
 * @email 3038382705@qq.com
 */
public class TopBar extends RelativeLayout {

    private String mTitle, mLeftText, mRightText;
    private float mTitleTextSize;
    private int mTitleColor, mLeftTextColor, mRightTextColor;
    private Drawable mLeftBackground, mRightBackground;

    private Button mLeftBtn, mRightBtn;
    private TextView mTitleView;
    private LayoutParams mLeftParams, mRightParams, mTitleParams;
    private TopBarClickListener mListener;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_title);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        ta.recycle();

        mLeftBtn = new Button(context);
        mRightBtn = new Button(context);
        mTitleView = new TextView(context);
        mLeftBtn.setText(mLeftText);
        mLeftBtn.setTextColor(mLeftTextColor);
        mLeftBtn.setBackground(mLeftBackground);
        mRightBtn.setText(mRightText);
        mRightBtn.setTextColor(mRightTextColor);
        mRightBtn.setBackground(mRightBackground);
        mTitleView.setText(mTitle);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setTextColor(mTitleColor);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftBtn, mLeftParams);
        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightBtn, mRightParams);
        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    mListener.onLeftClick();
                }
            }
        });

        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    mListener.onRightClick();
                }
            }
        });
    }


    public interface TopBarClickListener {
        void onLeftClick();
        void onRightClick();
    }


    public void setListener(TopBarClickListener listener) {
        mListener = listener;
    }

    public void setLeftVisible(boolean isVisible) {
        if(isVisible) {
            mLeftBtn.setVisibility(View.VISIBLE);
        } else {
            mLeftBtn.setVisibility(View.GONE);
        }
    }
}
