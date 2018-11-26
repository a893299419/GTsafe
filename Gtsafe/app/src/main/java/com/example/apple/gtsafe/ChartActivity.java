package com.example.apple.gtsafe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.gtsafe.DB.DBManger;
import com.example.apple.gtsafe.domain.Chart;
import com.example.apple.gtsafe.domain.JsonCallback;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartActivity extends AppCompatActivity {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;
    DBManger db;
    List<Map<String,String>> list2=new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chart);
        QMUIStatusBarHelper.translucent(this);

        //初始化状态栏
        View root = LayoutInflater.from(this).inflate(R.layout.activity_chart, null);
        ButterKnife.bind(this, root);
        initTopBar();
        setContentView(root);

        LineChart lineChart=(LineChart)findViewById(R.id.chart);
        db=new DBManger(ChartActivity.this);
        list2=db.querrychart();
        getChart(lineChart,list2);
        db.dbclose();
    }





    private void getChart(LineChart lineChart, List<Map<String,String>> list){

        LineChartManager lineChartManager = new LineChartManager(lineChart);
        ArrayList<Float> xValues0=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            xValues0.add((float)Integer.parseInt(list.get(i).get("day")));
        }

        List<List<Float>> yValues = new ArrayList<>();
        for (int i=0;i<1;i++){
            List<Float> yValue = new ArrayList<>();
            for(int j=0;j<list.size();j++){
                yValue.add((float)Integer.parseInt(list.get(j).get("point")));
            }
            yValues.add(yValue);
        }
//
//        //设置x轴的数据
//        ArrayList<Float> xValues0 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {//10组数据
//            xValues0.add((float) i);
//        }
//
//        //设置y轴的数据()
//        List<List<Float>> yValues = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            List<Float> yValue = new ArrayList<>();
//            for (int j = 0; j < 10; j++) {
//                yValue.add((float) (Math.random() * 80));
//            }
//            yValues.add(yValue);
//        }


        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");

        lineChartManager.showLineChart(xValues0,yValues.get(0),names.get(0),colors.get(0));
        lineChartManager.setYAxis(100,0,11);
        lineChartManager.setDescription("天数");
        lineChartManager.setHightLimitLine(80,"安全值",Color.GREEN);
    }




    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChartActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mTopBar.setTitle("图表");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db!=null){
            db.dbclose();
        }

    }
}
