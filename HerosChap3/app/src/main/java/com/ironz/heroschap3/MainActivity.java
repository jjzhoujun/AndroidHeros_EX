package com.ironz.heroschap3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.ironz.heroschap3.view.TopBar;
import com.ironz.heroschap3.view.TopBar.TopBarClickListener;

public class MainActivity extends AppCompatActivity {

    private TopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopBar = findViewById(R.id.topbar);
        mTopBar.setListener(new TopBarClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(MainActivity.this, "Click Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(MainActivity.this, "Click Right", Toast.LENGTH_SHORT).show();
            }
        });
//        mTopBar.setLeftVisible(false);
        findViewById(R.id.btn_scrollview).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustScrollActivity.class);
                startActivity(intent);
            }
        });

    }
}
