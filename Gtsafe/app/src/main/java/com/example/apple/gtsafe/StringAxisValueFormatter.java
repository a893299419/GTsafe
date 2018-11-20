package com.example.apple.gtsafe;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/*
 * Description: x轴数据转换类
 */

public class StringAxisValueFormatter implements IAxisValueFormatter {

    private List<String> xValues;

    public StringAxisValueFormatter(List<String> xValues) {
        this.xValues = xValues;
    }

    @Override
    public String getFormattedValue(float v, AxisBase axisBase) {
        try {
            if (v < 0 || v > (xValues.size() - 1)) {//使得两侧柱子完全显示
                return "";
            }
            return xValues.get((int) v);
        } catch (Exception e) {
            return "";
        }
    }
}
