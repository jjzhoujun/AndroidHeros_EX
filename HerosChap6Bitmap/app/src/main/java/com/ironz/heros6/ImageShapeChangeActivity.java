package com.ironz.heros6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.ironz.heros6.view.ImageMatrixView;

/**
 * 图像变换绘制
 * @author zhoujun
 * @date 19-5-8
 */
public class ImageShapeChangeActivity extends AppCompatActivity {

    private static final int ITEM_SIZE = 9;
    private GridLayout mGridLayout;
    private ImageMatrixView mImageMatrixView;
    private float[] mMatrixs = new float[ITEM_SIZE];
    private EditText[] mEts = new EditText[ITEM_SIZE];
    private int mItemWidth, mItemHeight;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_shape_change_layout);
        initView();
    }

    private void initView() {
        mGridLayout = findViewById(R.id.gl_matrix);
        mImageMatrixView = findViewById(R.id.iv_img);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test1);

        mGridLayout.post(new Runnable() {
            @Override
            public void run() {
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts() {
        mItemWidth = mGridLayout.getWidth() / 3;
        mItemHeight = mGridLayout.getHeight() / 3;
        for(int i=0; i<ITEM_SIZE; i++) {
            EditText et = new EditText(this);
            et.setGravity(Gravity.CENTER);
            mEts[i] = et;
            mGridLayout.addView(et, mItemWidth, mItemHeight);
        }
    }

    private void initMatrix() {
        for(int i=0; i<ITEM_SIZE; i++) {
            if(i % 4 == 0) {
                mEts[i].setText("1");
            } else {
                mEts[i].setText("0");
            }
        }
    }

    public void getImageMatrix() {
        for(int i=0; i<ITEM_SIZE; i++) {
            mMatrixs[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    public void btnChange(View v) {
        getImageMatrix();

        validImage();
    }

    public void btnReset(View v) {
        initMatrix();
        getImageMatrix();
        validImage();
    }

    private void validImage() {
        Matrix matrix = new Matrix();
        matrix.setValues(mMatrixs);

//        matrix.setRotate(45);
//        matrix.postTranslate(200, 200);

//        matrix.setTranslate(200, 200);
//        matrix.preRotate(45);

//        matrix.setScale(1, -1);
//        matrix.postRotate(45);
//        matrix.postTranslate(0, 200);

        mImageMatrixView.setImageAndMatrix(mBitmap, matrix);
        mImageMatrixView.invalidate();
    }

    public void btnMove(View v) {

    }

    public void btnStop(View v) {

    }


}
