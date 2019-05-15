package com.ironz.heros6;

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

    /**
     * 6.4章节以及之前
     * */
    public void onClickCanvas(View view) {
        Intent intent = new Intent();
        intent.setClass(this, CanvasSkillAcitivity.class);
        startActivity(intent);
    }

    /**
    * 6.5.1
    * **/
    public void btnImageSeekBar(View view) {
        Intent intent = new Intent(this, ImageEffSeekActivity.class);
        startActivity(intent);
    }

    /**
     * 6.5.2矩阵
     * */
    public void btnImageMatrix(View view) {
        Intent intent = new Intent(this, ImageEffMatrixActivity.class);
        startActivity(intent);
    }

    /**
     * 图像像素块处理颜色
     * */
    public void btnImagePiexels(View v) {
        Intent intent = new Intent(this, PixelAnalyseActivity.class);
        startActivity(intent);
    }

    /**
     * 图像变换
     * */
    public void btnImageShapeChange(View v) {
        Intent intent = new Intent(this, ImageShapeChangeActivity.class);
        startActivity(intent);
    }

    /**
     * 画笔-圆角-刮刮卡
     * */
    public void btnImagePainter(View v) {
        Intent intent = new Intent(this, PainterActivity.class);
        startActivity(intent);
    }

    public void btnShader(View v) {
        Intent intent = new Intent(this, ShaderActivity.class);
        startActivity(intent);
    }
}
