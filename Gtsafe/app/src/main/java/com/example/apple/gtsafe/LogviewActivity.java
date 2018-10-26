package com.example.apple.gtsafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.Loglist;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogviewActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    @BindView(R.id.listview_contact)
    ListView mListView_contact;
    private DBManger dbManger;
    private String url="http://116.62.220.130:8080/gsaznew/safeLog/list";
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
//        initListView();
        getData();
        dbManger = new DBManger(LogviewActivity.this);
        SimpleAdapter simpleAdapter = new SimpleAdapter(LogviewActivity.this,
                dbManger.querrylog(), R.layout.simple_list_item_2,
                new String[]{"id","addTime","status"},
                new int[]{R.id.textview_username,R.id.textview_phonenum,R.id.textview_gender});
        mListView_contact.setAdapter(simpleAdapter);


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

//    private void initListView() {
//        String[] listItems = new String[]{
//                "舒淇", "周杰伦", "古天乐", "姚明", "刘德华", "谢霆锋",
//                "朱时茂", "朱军", "周迅", "赵忠祥", "赵薇", "张国立",
//                "赵本山", "章子怡", "张艺谋", "张卫健", "张铁林", "袁泉",
//                "俞小凡", "杨丽萍", "杨澜", "汪峰", "唐国强", "孙楠",
//                "宋祖英", "斯琴高娃", "撒贝宁", "秦海璐", "任泉", "周杰"
//        };
//        mListView_contact.setAdapter(new ArrayAdapter<String>(
//                LogviewActivity.this,
//                android.R.layout.simple_list_item_1, listItems
//        ));
//    }
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

                        dbManger.add(response.body().rows);
                    }
                });
    }
}
