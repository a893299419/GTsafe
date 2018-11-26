package com.example.apple.gtsafe;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.Notice;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    TextView tView;
    TextView titleView;
    private DBManger dbManger;
    private String url="http://10.0.2.2:8080/phone/config?sName=message";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            List <Map<String ,Object>> datalist=(List <Map<String ,Object>>)msg.obj;
            String ssText=datalist.get(0).get("value").toString();
            String tText=datalist.get(0).get("title").toString();
            tView.setText(Html.fromHtml(ssText));
            tView.setClickable(true);
            tView.setMovementMethod(LinkMovementMethod.getInstance());
            titleView.setText(Html.fromHtml(tText));
            titleView.setClickable(true);
            titleView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message);
        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_message, null);
        ButterKnife.bind(this, root);
        initTopBar();
        setContentView(root);
        init();
        getDate();

    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mTopBar.setTitle("联系人");
    }

    private void init() {
        tView = (TextView) findViewById(R.id.message);
        titleView = (TextView) findViewById(R.id.title);
    }

    private void getDate(){

        OkGo.<com.example.apple.gtsafe.domain.Message>get(url)
                .tag(this)
                .execute(new JsonCallback<com.example.apple.gtsafe.domain.Message>(){
                    @Override
                    public void onSuccess(Response<com.example.apple.gtsafe.domain.Message> response) {
                        dbManger =new DBManger(MessageActivity.this);
                        dbManger.deletemessage();
                        dbManger.addmessage(response.body());

                        android.os.Message message = mHandler.obtainMessage();
                        message.what = 1;
                        message.obj = dbManger.querrymessage();
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
