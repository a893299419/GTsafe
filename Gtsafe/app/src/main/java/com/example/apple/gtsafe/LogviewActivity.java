package com.example.apple.gtsafe;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.Loglist;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogviewActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.listview_contact)
    ListView mListView_contact;
    private DBManger dbManger;
    private String url="http://116.62.220.130:8080/gsaznew/safeLog/list";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            final List <Map<String,Object>> datalist = (List <Map<String,Object>>)msg.obj;
            SimpleAdapter simpleAdapter = new SimpleAdapter(LogviewActivity.this,
                    dbManger.querrylog(), R.layout.simple_list_item_2,
                    new String[]{"addTime","score","status"},
                    new int[]{R.id.textview_username,R.id.textview_phonenum,R.id.textview_gender});
            mListView_contact.setAdapter(simpleAdapter);
            mListView_contact.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @SuppressWarnings("unchecked")
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Getlogdata getlogdata=new Getlogdata(LogviewActivity.this,Integer.parseInt(datalist.get(position).get("id").toString()));
                    getlogdata.getData();
                    Intent intent = new Intent(LogviewActivity.this,LogdataActivity.class);
                    startActivity(intent);
                    finish();
//                System.out.println(datalist.get(position).get("id"));
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_logview);
        // 沉浸式状态栏

        QMUIStatusBarHelper.translucent(this);

        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_logview, null);
        ButterKnife.bind(this, root);
        initTopBar();
        getData();

//        dbManger = new DBManger(LogviewActivity.this);
//        final List <Map<String,Object>> datalist = dbManger.querrylog();
//        SimpleAdapter simpleAdapter = new SimpleAdapter(LogviewActivity.this,
//                dbManger.querrylog(), R.layout.simple_list_item_2,
//                new String[]{"addTime","score","status"},
//                new int[]{R.id.textview_username,R.id.textview_phonenum,R.id.textview_gender});
//        mListView_contact.setAdapter(simpleAdapter);
//        mListView_contact.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @SuppressWarnings("unchecked")
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Getlogdata getlogdata=new Getlogdata(LogviewActivity.this,Integer.parseInt(datalist.get(position).get("id").toString()));
//                getlogdata.getData();
//                Intent intent = new Intent(LogviewActivity.this,LogdataActivity.class);
//                startActivity(intent);
//                finish();
////                System.out.println(datalist.get(position).get("id"));
//            }
//        });

        setContentView(root);

    }

    private void initTopBar() {

        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
//                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
                Intent intent = new Intent(LogviewActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mTopBar.setTitle("日志列表");

    }


    private void getData(){
        HttpParams params = new HttpParams();
        params.put("page",1);
        params.put("rows",10000);

        OkGo.<Loglist>post(url)
                .tag(this)
                .params(params)
                .execute(new JsonCallback<Loglist>(){
                    @Override
                    public void onSuccess(Response<Loglist> response) {

                        dbManger = new DBManger(LogviewActivity.this);
                        dbManger.deletelogview();
                        dbManger.add(response.body().rows);
                        Message message = mHandler.obtainMessage();
                        message.what = 1;
                        message.obj = dbManger.querrylog();
                        mHandler.sendMessage(message);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbManger!=null) {
            dbManger.dbclose();
        }

    }
}
