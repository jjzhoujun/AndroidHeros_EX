package com.ironz.heroschap3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Android 7以上，会滑动不了。// todo 需要处理
 * @author zhoujun
 * @date 19-4-29
 * https://blog.csdn.net/qinjuning/article/details/7247126
 * https://blog.csdn.net/vipzjyno1/article/details/24577023
 * https://blog.csdn.net/rebirth_love/article/details/51442359
 */
public class CustScrollView extends ViewGroup {

    private static final String TAG = CustScrollView.class.getSimpleName();

    private int mScreenHeight;
    private Scroller mScroller;
    private int mLastY;
    private int mStart;
    private int mEnd;

    public CustScrollView(Context context) {
        this(context, null);
    }

    public CustScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        // 设置ViewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);
        for(int i=0; i<childCount; i++) {
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE) {
                child.layout(l, i*mScreenHeight,
                        r, (i+1)*mScreenHeight);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for(int i=0; i<count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();  // 第一次点击时候是0，表示离视图起始位置的y垂直方向的偏移量
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                int sy = getScrollY();
                Log.d(TAG, "===getScrollY = " + sy + "; height = " + getHeight() + "; lastY = " + mLastY + "; y = " + y + "; dy = " + dy);
                if(sy < 0) {
                    dy = 0;
                }
                if(sy > getHeight() - mScreenHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if(dScrollY > 0) {
                    if(dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(),
                                0, mScreenHeight - dScrollY);
                    }
                } else {
                    if(-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(),
                                0, -mScreenHeight - dScrollY);
                    }
                }
                break;
            default:
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            Log.d(TAG, "getCurrY = " + mScroller.getCurrY());
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
