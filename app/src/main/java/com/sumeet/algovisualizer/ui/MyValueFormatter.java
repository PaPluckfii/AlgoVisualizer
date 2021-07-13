package com.sumeet.algovisualizer.ui;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class MyValueFormatter extends ValueFormatter {

    @Override
    public String getFormattedValue(float value) {
        return String.valueOf((int) value);
    }
}

