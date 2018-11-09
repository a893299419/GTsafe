package com.example.apple.gtsafe;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;

import okhttp3.OkHttpClient;

public class GApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();



        initOkGo();
    }

    private void initOkGo(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
        OkGo.getInstance().init(this).setOkHttpClient(builder.build());
    }
}
