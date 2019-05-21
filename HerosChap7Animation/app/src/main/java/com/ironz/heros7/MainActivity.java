package com.ironz.heros7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnFrameAnim(View v) {
        Intent intent = new Intent(this, FrameAnimActivity.class);
        startActivity(intent);
    }

    public void btnTweenAnim(View v) {
        Intent intent = new Intent(this, TweenAnimActivity.class);
        startActivity(intent);
    }

    public void btnPropertyAnim(View v) {
        Intent intent = new Intent(this, PropertyAnimActivity.class);
        startActivity(intent);
    }

    public void btnCustomAnim(View v) {
        Intent intent = new Intent(this, CustomAnimActivity.class);
        startActivity(intent);
    }

    public void btnSVGAnim(View v) {
        Intent intent = new Intent(this, SVGActivity.class);
        startActivity(intent);
    }

    public void btnFlexIcon(View v) {
        Intent intent = new Intent(this, FlexibleIconActivity.class);
        startActivity(intent);
    }
}
