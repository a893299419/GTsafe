package com.example.apple.gtsafe;

import android.content.Context;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.Chart;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class GetChartData {
    private DBManger dbManger;
    private Context context;
    private String url="http://116.62.220.130:8080/gsaznew/api/getGTSafeInfo";
    public GetChartData(Context context)
    {
        dbManger = new DBManger(context);
        this.context = context;
    }

    public void getData(){

        OkGo.<Chart>get(url)
                .tag(this)
                .execute(new JsonCallback<Chart>() {

                    @Override
                    public void onSuccess(Response<Chart> response) {
                        dbManger.delete();
//                        System.out.print("获取到的数据："+response.body());

                        dbManger.addchart(response.body().targetPoint);
//                        Toast.makeText(getApplicationContext(),list2.get(10).point+"",Toast.LENGTH_LONG).show();
                        dbManger.dbclose();
                    }

                    @Override
                    public void onError(Response<Chart> response){

                    }
                });

    }
}
