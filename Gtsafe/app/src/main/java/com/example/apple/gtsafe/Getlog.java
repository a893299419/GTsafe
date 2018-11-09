package com.example.apple.gtsafe;

import android.content.Context;

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
    private String url="http://116.62.220.130:8080/gsaznew/logTpl/cateList";
    private contactconBean contactconbean;
    private contactcateBean contactcatebean;
    private contactattrBean contactattrbean;
    private contactdataBean contactdatabean;
    private contactlayeroneBean contactlayeronbean;

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
        Type type = new TypeToken<List<contactdataBean>>() {}.getType();
        OkGo.<List<LogTpl>>post(url)
                .tag(this)
                .execute(new JsonCallback<List<LogTpl>>(type){
                    @Override
                    public void onSuccess(Response<List<LogTpl>> response) {
                       dbManger.delete();

                        for(LogTpl logTpl :response.body())
                        {
                            for(LogTplcate logTplcate :logTpl.getChildren() )
                            {
//                                dbManger.addcontacttodatabase(logTpl.getChildren());
                            }
                        }
                    }
                });
    }
}
