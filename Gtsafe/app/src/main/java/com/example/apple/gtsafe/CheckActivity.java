package com.example.apple.gtsafe;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.Checklist;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.LogTpl;
import com.example.apple.gtsafe.domain.Loglist;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckActivity extends AppCompatActivity {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.listview_contact)
    ListView mListView_contact;
    private DBManger dbManger;
    private String url="http://10.0.2.2:8080/phone/log/list";


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            final List <Map<String,Object>> datalist = dbManger.querrylog();
            SimpleAdapter simpleAdapter = new SimpleAdapter(CheckActivity.this,
                    (List <Map<String,Object>>) msg.obj, R.layout.simple_list_item_2,
                    new String[]{"addTime","score","status"},
                    new int[]{R.id.textview_username,R.id.textview_phonenum,R.id.textview_gender});
            mListView_contact.setAdapter(simpleAdapter);
            mListView_contact.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @SuppressWarnings("unchecked")
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
//
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check);
        QMUIStatusBarHelper.translucent(this);

        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_check, null);
        ButterKnife.bind(this, root);
        initTopBar();

        getData();
//        dbManger = new DBManger(CheckActivity.this);
//        final List <Map<String,Object>> datalist = dbManger.querrylog();
//        SimpleAdapter simpleAdapter = new SimpleAdapter(CheckActivity.this,
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
////
//            }
//        });
        setContentView(root);
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mTopBar.setTitle("审核日志列表");
    }

    private void getData(){
        HttpParams params = new HttpParams();
        params.put("status",0);

        Type type = new TypeToken<List<Checklist>>() {}.getType();
        OkGo.<List<Checklist>>get(url)
                .tag(this)
                .params(params)
                .execute(new JsonCallback<List<Checklist>>(type){
                    @Override
                    public void onSuccess(Response<List<Checklist>> response) {

                        dbManger = new DBManger(CheckActivity.this);
                        dbManger.deletelogview();
                        dbManger.addcheck(response.body());
                        Message message = mHandler.obtainMessage();
                        message.what = 1;
                        message.obj = dbManger.querrylog();
                        mHandler.sendMessage(message);
                    }
                });
    }
}
