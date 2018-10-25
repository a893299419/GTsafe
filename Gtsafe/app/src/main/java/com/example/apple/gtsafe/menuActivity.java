package com.example.apple.gtsafe;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;


import butterknife.BindView;
import butterknife.ButterKnife;


public class menuActivity extends AppCompatActivity{

    @BindView(R.id.topbar) QMUITopBar mTopBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
        // 沉浸式状态栏

        QMUIStatusBarHelper.translucent(this);

        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_menu, null);
        ButterKnife.bind(this, root);
        initTopBar();
        setContentView(root);


    }


    private void initTopBar() {

//        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
//            }
//        });

        mTopBar.setTitle("首页");

    }
}
