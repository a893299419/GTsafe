package com.example.apple.gtsafe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.Message;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PolicyActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    private TextView pView;

    private DBManger dbManger;
    private String url="http://10.0.2.2:8080/phone/config?sName=policy";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            String ssText= (String)msg.obj;
            pView.setText(Html.fromHtml(ssText));
            pView.setClickable(true);
            pView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_policy);
        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_policy, null);
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
                Intent intent = new Intent(PolicyActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mTopBar.setTitle("措施");
    }

    private void init() {
        pView = (TextView) findViewById(R.id.policy);
    }

    private void getDate(){

        OkGo.<com.example.apple.gtsafe.domain.Message>get(url)
                .tag(this)
                .execute(new JsonCallback<Message>(){
                    @Override
                    public void onSuccess(Response<Message> response) {
                        dbManger =new DBManger(PolicyActivity.this);
                        dbManger.deletepolicy();
                        dbManger.addpolicy(response.body());
//                        System.out.println(response.body().getValue());
                        android.os.Message message = mHandler.obtainMessage();
                        message.what = 1;
                        message.obj = dbManger.querrypolicy().get(0).get("value").toString();
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
