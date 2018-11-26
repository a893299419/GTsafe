package com.example.apple.gtsafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.apple.gtsafe.DB.DBManger;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class LogFragment extends Fragment  {
    private String url="http://10.0.2.2:8080/phone/log/check/verify";
    private DBManger dbManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button) getActivity().findViewById(R.id.button_check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkverify();
            }
        });
    }






    private void checkverify(){
        dbManger=new DBManger(getActivity());
        String username;
        username=dbManger.querrylogin().get(0).get("account").toString();

        HttpParams params=new HttpParams();
        params.put("account",username);
        OkGo.<String>get(url)
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().equals("true")){
                            Intent intent = new Intent(getActivity(),CheckActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(getContext(),"您的账号不能进行此操作",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(Response<String> response){
                        Toast.makeText(getContext(),"服务器IP地址错误或网络连接错误",Toast.LENGTH_LONG).show();
                    }
                });
        dbManger.dbclose();
    }

    private void fillverify(){
        dbManger=new DBManger(getActivity());
        String username;
        username=dbManger.querrylogin().get(0).get("account").toString();

        HttpParams params=new HttpParams();
        params.put("account",username);
        OkGo.<String>get(url)
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body().equals("true")){
                            Intent intent = new Intent(getActivity(),CheckActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(getContext(),"您的账号不能进行此操作",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(Response<String> response){
                        Toast.makeText(getContext(),"服务器IP地址错误或网络连接错误",Toast.LENGTH_LONG).show();
                    }
                });
        dbManger.dbclose();
    }
}
