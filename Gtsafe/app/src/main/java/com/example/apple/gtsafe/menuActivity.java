package com.example.apple.gtsafe;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import android.support.v4.content.ContextCompat;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;


import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class menuActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

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

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_channel.setChecked(true);

        Drawable drawable1=getResources().getDrawable(R.drawable.home_bg);
        Drawable drawable2=getResources().getDrawable(R.drawable.bianji_bg);
        Drawable drawable3=getResources().getDrawable(R.drawable.peizhi_bg);
        drawable1.setBounds(0,0,80,80);
        drawable2.setBounds(0,0,80,80);
        drawable3.setBounds(0,0,80,80);
        rb_channel.setCompoundDrawables(null,drawable1,null,null);
        rb_message.setCompoundDrawables(null,drawable2,null,null);
        rb_better.setCompoundDrawables(null,drawable3,null,null);
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

    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;

    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;


    private void bindViews() {

        rg_tab_bar = (RadioGroup) findViewById(R.id.main_radiogroup);
        rb_channel = (RadioButton) findViewById(R.id.main_rab_house);
        rb_message = (RadioButton) findViewById(R.id.main_rab_connect);
        rb_better = (RadioButton) findViewById(R.id.main_rab_wifi);

        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_rab_house:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.main_rab_connect:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.main_rab_wifi:
                vpager.setCurrentItem(PAGE_THREE);
                break;

        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_channel.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_message.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_better.setChecked(true);
                    break;
            }
        }
    }
}
