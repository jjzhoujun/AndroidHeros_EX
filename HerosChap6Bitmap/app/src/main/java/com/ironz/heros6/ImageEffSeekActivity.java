package com.ironz.heros6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * 图像处理--SeekBar动态实时更改
 * @author zhoujun
 * @date 19-5-6
 */
public class ImageEffSeekActivity extends AppCompatActivity implements OnSeekBarChangeListener {

    private static final String TAG = ImageEffSeekActivity.class.getSimpleName();
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    private ImageView mImageView;
    private SeekBar mHueSeekBar, mSaturationSeekBar, mLumSeekBar;
    private float mHue, mSaturation, mLum;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_eff_seek);
        mImageView = findViewById(R.id.iv_img);
        mHueSeekBar = findViewById(R.id.seek_hue);
        mSaturationSeekBar = findViewById(R.id.seek_saturation);
        mLumSeekBar = findViewById(R.id.seek_lum);

        mHueSeekBar.setMax(MAX_VALUE);
        mSaturationSeekBar.setMax(MAX_VALUE);
        mLumSeekBar.setMax(MAX_VALUE);
        mHueSeekBar.setProgress(MID_VALUE);
        mSaturationSeekBar.setProgress(MID_VALUE);
        mLumSeekBar.setProgress(MID_VALUE);
        mHueSeekBar.setOnSeekBarChangeListener(this);
        mSaturationSeekBar.setOnSeekBarChangeListener(this);
        mLumSeekBar.setOnSeekBarChangeListener(this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test3);
        mHue = 0F;
        mSaturation = 1F;
        mLum = 1F;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek_hue:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seek_saturation:
                mSaturation = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seek_lum:
                mLum = progress * 1.0F / MID_VALUE;
                break;
            default:
                break;
        }
        Log.d(TAG, "==>>>>progress = " + progress + "; mHue = " + mHue + "; mSaturation = " + mSaturation + "; mLum = " + mLum);
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(mBitmap, mHue, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
