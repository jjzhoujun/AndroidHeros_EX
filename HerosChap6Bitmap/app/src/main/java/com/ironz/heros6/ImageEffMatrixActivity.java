package com.ironz.heros6;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * 图像处理--矩阵更改
 * @author zhoujun
 * @date 19-5-6
 */
public class ImageEffMatrixActivity extends AppCompatActivity {

    private static final int ITEM_SIZE = 20;
    // 灰度效果
    private static final float[] MATRIX_GRAY = {0.33F, 0.59F, 0.11F, 0, 0,
                                                0.33F, 0.59F, 0.11F, 0, 0,
                                                0.33F, 0.59F, 0.11F, 0, 0,
                                                0, 0, 0, 1, 0};
    // 图像反转
    private static final float[] MATRIX_REVERSE = {-1, 0, 0, 1, 1,
                                                    0, -1, 0, 1, 1,
                                                    0, 0, -1, 1, 1,
                                                    0, 0, 0, 1, 0};
    // 怀旧效果
    private static final float[] MATRIX_OLDPIC = {0.393F, 0.769F, 0.189F, 0, 0,
                                                  0.349F, 0.686F, 0.168F, 0, 0,
                                                  0.272F, 0.534F, 0.131F, 0, 0,
                                                  0, 0, 0, 1, 0};

    // 去色效果
    private static final float[] MATRIX_NOCOLOR = {1.5F, 1.5F, 1.5F, 0, -1,
                                                   1.5F, 1.5F, 1.5F, 0, -1,
                                                   1.5F, 1.5F, 1.5F, 0, -1,
                                                   0, 0, 0, 1, 0};

    // 高饱和度
    private static final float[] MATRIX_HIGH_SAT = {1.438F, -0.122F, -0.016F, 0, -0.03F,
                                                    -0.062F, 1.378F, -0.016F, 0, 0.05F,
                                                    -0.062F, -0.122F, 1.483F, 0, -0.02F,
                                                    0, 0, 0, 1, 0};

    private ImageView mImageView;
    private GridLayout mGridLayout;
    private Bitmap mBitmap;
    private Button mBtnApply, mBtnReset;
    private EditText[] mEditTexts;
    private int mEtWidth, mEtHeight;
    private float[] mColorMatrix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_eff_matrix);

        initView();
        mEditTexts = new EditText[ITEM_SIZE];
        mColorMatrix = new float[ITEM_SIZE];
        mGridLayout.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGridLayout.getWidth() / 5;
                mEtHeight = mGridLayout.getHeight() / 4;
                addEtItems();
                initMatrix();
            }
        });
    }

    private void addEtItems() {
        for(int i=0; i<ITEM_SIZE; i++) {
            EditText editText = new EditText(this);
            mEditTexts[i] = editText;
            mGridLayout.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix() {
        for(int i=0; i<ITEM_SIZE; i++) {
            if(i % 6 == 0) {
                mEditTexts[i].setText("1");
            } else {
                mEditTexts[i].setText("0");
            }
        }
    }

    private void initView() {
        mImageView = findViewById(R.id.iv_src);
        mGridLayout = findViewById(R.id.glayout);
        mBtnApply = findViewById(R.id.btn_apply);
        mBtnApply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getMatrix();
                setImageMatrix();
            }
        });

        mBtnReset = findViewById(R.id.btn_reset);
        mBtnReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                initMatrix();
                getMatrix();
                setImageMatrix();
            }
        });
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test1);
        mImageView.setImageBitmap(mBitmap);
    }

    private void getMatrix() {
        for(int i=0; i<ITEM_SIZE; i++) {
            mColorMatrix[i] = Float.valueOf(mEditTexts[i].getText().toString());
        }
    }

    private void setMatrix(float[] matrixs) {
        int length = matrixs.length;
        if(length != ITEM_SIZE) {
            return ;
        }
        for(int i=0; i<length; i++) {
            mEditTexts[i].setText(String.valueOf(matrixs[i]));
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    private void setSpecialColor(float[] value) {
        setMatrix(value);
        getMatrix();
        setImageMatrix();
    }

    public void btnGray(View v) {
        setSpecialColor(MATRIX_GRAY);
    }

    public void btnReverse(View v) {
        setSpecialColor(MATRIX_REVERSE);
    }

    public void btnOldPic(View v) {
        setSpecialColor(MATRIX_OLDPIC);
    }

    public void btnNoColor(View v) {
        setSpecialColor(MATRIX_NOCOLOR);
    }

    public void btnHighSaturation(View v) {
        setSpecialColor(MATRIX_HIGH_SAT);
    }
}
