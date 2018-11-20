package com.example.apple.gtsafe;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.apple.gtsafe.DB.DBManger;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment{


    //定义图标数组
    private int[] imageRes = {
            R.drawable.zhuye,
            R.drawable.rizhi,
            R.drawable.lianxiren,
            R.drawable.bugao,
            R.drawable.tubiao,
            R.drawable.shezhi
    };

    //定义图标下方的名称数组
    private String[] name = {
            "首页",
            "日志",
            "联系人",
            "布告",
            "图表",
            "设置"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        int length = imageRes.length;

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageRes[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(getContext(),
                lstImageItem,//数据来源
                R.layout.item,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.img_shoukuan, R.id.txt_shoukuan});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        Getlog getlog=new Getlog(getActivity());
                        getlog.getData();
                        intent = new Intent(getActivity(),GetlogActivity.class);
                        startActivity(intent);

                        break;
                    case 1:

                        intent = new Intent(getActivity(), LogviewActivity.class);
                        startActivity(intent);

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        GetChartData getChartData=new GetChartData(getActivity());
                        getChartData.getData();
                        intent = new Intent(getActivity(), ChartActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        break;
                }


            }
        });


        return view;
    }

}
