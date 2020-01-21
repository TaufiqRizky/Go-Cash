package com.unikom.go_cash;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Activity_Graph extends AppCompatActivity {
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__graph);


        barChart= findViewById(R.id.barchart);
        buatGrafik(barChart);

    }

    public void  buatGrafik(BarChart b){
        b.setDrawBarShadow(true);
        b.setDrawValueAboveBar(true);
        b.setMaxVisibleValueCount(50);
        b.setPinchZoom(true);
        b.setDrawGridBackground(true);
        b.setDrawValueAboveBar(true);
        b.animateY(1500);


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1,200000));
        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(2,340000));


        BarDataSet barDataSet = new BarDataSet(barEntries,"Pemasukan");
        barDataSet.setColor(Color.rgb(46,204,113));

        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"Pengeluaran");
        barDataSet2.setColor(Color.rgb(235, 77, 75));

        BarData data = new BarData(barDataSet,barDataSet2);

        b.setData(data);
    }
}
