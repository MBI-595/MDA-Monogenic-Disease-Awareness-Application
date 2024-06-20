package com.example.monogenicdiseases;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Charts extends AppCompatActivity {
    BarChart barChart;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        barChart=findViewById(R.id.bar_chart);
        pieChart=findViewById(R.id.pie_chart);

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        ArrayList<PieEntry> pieEntries=new ArrayList<>();

        for(int i=1;i<10;i++){
            float value=(float)(i*10.0);
            BarEntry barEntry=new BarEntry(i,value);
            PieEntry pieEntry=new PieEntry(i, value);

            barEntries.add(barEntry);
            pieEntries.add(pieEntry);
        }

        BarDataSet barDataSet=new BarDataSet(barEntries,"Time Spent");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(5000);
        barChart.getDescription().setText("Time Spent Chart");
        barChart.getDescription().setTextColor(Color.BLUE);

        PieDataSet pieDataSet=new PieDataSet(pieEntries, "Work");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateXY(5000,5000);
        pieChart.getDescription().setEnabled(false);

    }
}