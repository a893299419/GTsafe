package com.example.apple.gtsafe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.Login;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText eusername;
    private EditText epassword;
    private String username;
    private String password;
    private String url="http://116.62.220.130:8080/gsaznew/public/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        init();
    }

    private class LoginListener implements View

            .OnClickListener{
        public void onClick(View v){
            username = eusername.getText().toString().trim();
            password = epassword.getText().toString().trim();
            if(username==null||username.length()<=0)
            {
                eusername.requestFocus();
                eusername.setError("请输入用户名");
                return;
            }
            else if (password==null||password.length()<=0)
            {
                epassword.requestFocus();
                epassword.setError("请输入密码");
                return;
            }
            else {
                final ProgressDialog logindialog = new ProgressDialog(MainActivity.this);
                logindialog.show(MainActivity.this,"提示","正在登录中请稍后",true,true);
                HttpParams params = new HttpParams();
                params.put("account",username);
                params.put("password",password);

                OkGo.<Login>post(url)
                        .tag(this)
                        .params(params)
                        .execute(new JsonCallback<Login>() {
                            @Override
                            public void onSuccess(Response<Login> response) {
                                if(response.body().status==1) {
                                    logindialog.cancel();
//                                    Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, menuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "账号密码错误", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Response<Login> response) {
                                super.onError(response);
                                Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        }
    }



    public void  init()
    {
        login = (Button)findViewById(R.id.loginBtn);
        login.setOnClickListener(new LoginListener());
        eusername = (EditText)findViewById(R.id.logined_username);
        epassword = (EditText)findViewById(R.id.logined_epassword);
    }
}
