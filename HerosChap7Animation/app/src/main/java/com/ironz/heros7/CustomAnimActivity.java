package com.ironz.heros7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ironz.heros7.anim.CustomCamera;
import com.ironz.heros7.anim.CustomTVAnim;

/**
 * @author zhoujun
 * @date 19-5-20
 */
public class CustomAnimActivity extends AppCompatActivity {

    private ImageView mIvPic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_anim_layout);
        mIvPic = findViewById(R.id.iv_img);
    }

    public void btnCustomTV(View v) {
        CustomTVAnim anim = new CustomTVAnim();
        mIvPic.startAnimation(anim);
    }

    public void btnCustomCamera(View v) {
        CustomCamera anim = new CustomCamera();
        anim.setRotateY(30);
        mIvPic.startAnimation(anim);
    }
}
