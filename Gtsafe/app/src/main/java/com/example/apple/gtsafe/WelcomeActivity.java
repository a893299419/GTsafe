package com.example.apple.gtsafe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.apple.gtsafe.utils.ImgUtils;


public class WelcomeActivity extends Activity {
    private String url = "https://img.zcool.cn/community/0111b457ea419d0000012e7eaa231d.jpg@1280w_1l_2o_100sh.jpg";
    private ImageView imageView;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.welcome);

        imageView = (ImageView) findViewById(R.id.adImage);

        /*异步加载*/
        new FetchDataTask().execute(url);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
                WelcomeActivity.this.finish();
            }
        }, 3000);
    }

    /**
     * 异步加载
     */
    public class FetchDataTask extends AsyncTask<String, Void, Bitmap> {

        //执行前调用
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //执行后台任务
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                bitmap = ImgUtils.getBitmap(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        //任务完成时调用
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
