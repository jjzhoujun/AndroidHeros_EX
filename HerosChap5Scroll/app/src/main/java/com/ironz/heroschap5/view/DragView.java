package com.ironz.heroschap5.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * @author zhoujun
 * @date 19-4-30
 */
public class DragView extends View {

    private static final String TAG = DragView.class.getSimpleName();
    private int mLastX, mLastY;
    private Scroller mScroller;

    public DragView(Context context) {
        super(context);
        init(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
//        int x = (int)event.getRawX();
//        int y = (int)event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                Log.d(TAG, "mLastX = " + mLastX + "; x = " + x + "; mLastY = " + mLastY + "; y = " + y);
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);

                //                offsetLeftAndRight(offsetX);
                //                offsetTopAndBottom(offsetY);

//                MarginLayoutParams lp = (MarginLayoutParams)getLayoutParams();
//                lp.leftMargin = getLeft() + offsetX;
//                lp.topMargin = getTop() + offsetY;
//                setLayoutParams(lp);

                // 移动的是View的内容，所以取父view， 并且取负数，因为是类似盖板挪，画布在下面不动
                ((View)getParent()).scrollBy(-offsetX, -offsetY);

//                mLastX = x;
//                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View)getParent();
                mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(),
                        -viewGroup.getScrollX(), -viewGroup.getScrollY());
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
