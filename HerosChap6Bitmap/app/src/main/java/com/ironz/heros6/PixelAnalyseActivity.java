package com.ironz.heros6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * 图像处理像素块分析
 * @author zhoujun
 * @date 19-5-7
 */
public class PixelAnalyseActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixel_analyse_layout);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test2);
        mImageView = findViewById(R.id.iv_img);
        mImageView.setImageBitmap(mBitmap);
    }

    public void btnNegative(View v) {
        Bitmap bitmap = ImageHelper.handleImagePixelsType(mBitmap, ImageHelper.TYPE_NEGATIVE);
        mImageView.setImageBitmap(bitmap);
    }

    public void btnOldPic(View v) {
        Bitmap bitmap = ImageHelper.handleImagePixelsType(mBitmap, ImageHelper.TYPE_OLDPIC);
        mImageView.setImageBitmap(bitmap);

    }

    public void btnRelief(View v) {
        Bitmap bitmap = ImageHelper.handleImagePixelsType(mBitmap, ImageHelper.TYPE_RELIEF);
        mImageView.setImageBitmap(bitmap);
    }

    public void btnReset(View v) {
        mImageView.setImageBitmap(mBitmap);
    }


}
