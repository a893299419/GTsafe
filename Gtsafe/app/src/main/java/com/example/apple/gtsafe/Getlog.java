package com.example.apple.gtsafe;

import android.content.Context;
import android.widget.Toast;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.contactBean.contactattrBean;
import com.example.apple.gtsafe.contactBean.contactcateBean;
import com.example.apple.gtsafe.contactBean.contactconBean;
import com.example.apple.gtsafe.contactBean.contactdataBean;
import com.example.apple.gtsafe.contactBean.contactlayeroneBean;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.example.apple.gtsafe.domain.LogTpl;
import com.example.apple.gtsafe.domain.LogTplcate;
import com.example.apple.gtsafe.domain.Loglist;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.lang.reflect.Type;
import java.util.List;

public class Getlog {
    private DBManger dbManger;
    private Context context;
    private String url="http://10.0.2.2:8080/phone/cateList?type=default";


    public Getlog(Context context)
    {
        dbManger = new DBManger(context);
        this.context = context;
    }

    public void getData(){
        final QMUITipDialog tipDialog;
        tipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("下载中···请稍候···")
                .create();
        tipDialog.show();
        Type type = new TypeToken<List<LogTpl>>() {}.getType();
        OkGo.<List<LogTpl>>get(url)
                .tag(this)
                .execute(new JsonCallback<List<LogTpl>>(type){
                    @Override
                    public void onSuccess(Response<List<LogTpl>> response) {
                        tipDialog.dismiss();
                       dbManger.delete();

                       dbManger.addlogtpldatabase(response.body());
//                        Toast.makeText(context,response.body().get(0).getSubContactCateList().get(0).getContactList().get(0).getName()+"",Toast.LENGTH_LONG).show();
                       dbManger.dbclose();
                    }
                });
    }
}
