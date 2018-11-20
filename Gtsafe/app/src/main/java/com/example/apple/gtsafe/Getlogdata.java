package com.example.apple.gtsafe;

import android.content.Context;
import android.widget.Toast;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.LogTpl;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.lang.reflect.Type;
import java.util.List;

public class Getlogdata {
    private DBManger dbManger;
    private Context context;
    private String url="http://10.0.2.2:8080/phone/log/data?logId=";
    private int logId;

    public Getlogdata(Context context,int logId)
    {
        dbManger = new DBManger(context);
        this.context = context;
        this.logId=logId;
    }

    public void getData(){
        final QMUITipDialog tipDialog;
        tipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("下载中···请稍候···")
                .create();
//        tipDialog.show();
        HttpParams params = new HttpParams();
//        params.put("logId",logId);
        System.out.println(logId);
        Type type = new TypeToken<List<LogTpl>>() {}.getType();
        OkGo.<List<LogTpl>>get(url+logId)
                .tag(this)
                .execute(new JsonCallback<List<LogTpl>>(type){
                    @Override
                    public void onSuccess(Response<List<LogTpl>> response) {
//                        tipDialog.cancel();
                        dbManger.delete();

                        dbManger.addlogtpldatabase(response.body());

                        Toast.makeText(context,response.body().get(0).getSubContactCateList().get(0).getContactList().get(0).getName()+"",Toast.LENGTH_LONG).show();
                        dbManger.dbclose();
                    }
                });
    }
}
