package com.example.apple.gtsafe;

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
            HttpParams params = new HttpParams();
            params.put("account",username);
            params.put("password",password);

            OkGo.<Login>post(url)
                    .tag(this)
                    .params(params)
                    .execute(new JsonCallback<Login>() {
                        @Override
                        public void onSuccess(Response<Login> response) {
                                if(response.body().status==1)
                                Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }



    public void  init()
    {
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new LoginListener());
        eusername = (EditText)findViewById(R.id.logined_username);
        epassword = (EditText)findViewById(R.id.logined_epassword);
    }
}
