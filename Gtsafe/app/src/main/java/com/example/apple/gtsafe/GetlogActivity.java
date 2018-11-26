package com.example.apple.gtsafe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.gtsafe.DB.DBManger;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetlogActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    DBManger db;

    ScrollView scrollView;
    List<Map<String ,Object>> logtpllist;
    List<Map<String ,Object>> catelist;
    List<Map<String ,Object>> contactlist;
    List<Map<String ,Object>> attrlist;
    int logtplsize;
    int catesize;
    int attrsize;
    int contactsize;
    String type;
    List<RadioGroup> loginfogroup = new ArrayList<RadioGroup>();
    List<EditText> logtext = new ArrayList<EditText>();
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    Date date = new Date(System.currentTimeMillis());
    String addTime = format.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_get);
        QMUIStatusBarHelper.translucent(this);

        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_get, null);
        ButterKnife.bind(this, root);
        initTopBar();
        setContentView(root);

        init();
        //上下文
        LayoutInflater flater = LayoutInflater.from(GetlogActivity.this);
        //获取父控件
        LinearLayout addlog_item = (LinearLayout)findViewById(R.id.addlog_item);
        //获取数据
        db = new DBManger(GetlogActivity.this);
        logtpllist = db.querrylogtpl();
        catelist = db.querrycate();
        contactlist = db.querrycontact();
        attrlist = db.querryattr();

        logtplsize = logtpllist.size();
        catesize = catelist.size();

        attrsize = attrlist.size();
        contactsize = contactlist.size();
        //添加数据

        for(int i= 0;i<contactsize;i++)
        {
            //获取元数据模板
            View view = flater.inflate(R.layout.contact_singleitem,null);

            //添加触点值
            TextView textView = (TextView) view.findViewById(R.id.cs_contactname);
            textView.setText((i+1)+". "+contactlist.get(i).get("name").toString());//+"      "+contactlist.get(i).get("id").toString());
            //获取触点id
            String s = contactlist.get(i).get("id").toString();
            //初始化RadioGroup
            RadioGroup group = (RadioGroup)view.findViewById(R.id.radiogroup);
            group.setTag(contactlist.get(i).get("id").toString());
            loginfogroup.add(group);
            String type = contactlist.get(i).get("type").toString();
            if (type.equals("1"))
            {
                addlog_item.addView(view);
                for(int j=0;j<attrsize;j++)
                {
                    if(s.equals(attrlist.get(j).get("father").toString()))
                    {
                        RadioButton radioButton = new RadioButton(this);
                        radioButton.setTag(attrlist.get(j).get("id").toString());
                        radioButton.setText(attrlist.get(j).get("name").toString());//+"   "+radioButton.getTag());

                        group.addView(radioButton, 500, 50);
                        group.setPadding(10, 5, 0, 5);
                        group.setBackgroundColor(Color.rgb(222,222,222));
                    }
                }
                //addlog_item.addView(view);
            }
            else
            {
                addlog_item.addView(view);
                EditText editText = new EditText(this);
                addlog_item.addView(editText);
                logtext.add(editText);
            }
            //将元数据添加到父控件
            //addlog_item.addView(view);
        }
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetlogActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mTopBar.addRightTextButton("保存",R.id.qmuidemo).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        mTopBar.setTitle("填写日志");
    }

    public void init()
    {
        scrollView = (ScrollView)findViewById(R.id.scrollView);
    }

    @Override
    protected void onDestroy() {
        db.dbclose();
        super.onDestroy();
    }
}
