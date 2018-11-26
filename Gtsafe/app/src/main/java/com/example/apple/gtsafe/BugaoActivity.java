package com.example.apple.gtsafe;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.LogTpl;
import com.example.apple.gtsafe.domain.Notice;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BugaoActivity extends AppCompatActivity {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    TextView ttView;
    private DBManger dbManger;
    private String url="http://10.0.2.2:8080/phone/notice/notice";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            String ssText=(String)msg.obj;
            ttView.setText(Html.fromHtml(ssText));
            ttView.setClickable(true);
            ttView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bugao);
        QMUIStatusBarHelper.translucent(this);

        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_bugao, null);
        ButterKnife.bind(this, root);
        initTopBar();
        setContentView(root);

        init();
        getNoticeDate();

    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BugaoActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mTopBar.setTitle("布告");
    }

    private void init() {
        ttView = (TextView) findViewById(R.id.bugao);
    }


    private void getNoticeDate(){
        Type type = new TypeToken<List<Notice>>() {}.getType();
        OkGo.<List<Notice>>get(url)
                .tag(this)
                .execute(new JsonCallback<List<Notice>>(type){
                    @Override
                    public void onSuccess(Response<List<Notice>> response) {
                       dbManger =new DBManger(BugaoActivity.this);
                       dbManger.deletenotice();
                       dbManger.addnotice(response.body());
                       android.os.Message message = mHandler.obtainMessage();
                       message.what = 1;
                       message.obj = dbManger.querrynotice().get(0).get("content").toString();
                       mHandler.sendMessage(message);
                       dbManger.dbclose();
                    }
                });

    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        if(dbManger!=null){
            dbManger.dbclose();
        }
    }
}
