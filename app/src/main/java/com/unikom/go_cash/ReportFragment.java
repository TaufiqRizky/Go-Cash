package com.unikom.go_cash;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportFragment extends Fragment {
    BarChart barChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_report,container,false);

        barChart= (BarChart) view.findViewById(R.id.barchart);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(true);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1,200000));


        ArrayList<BarEntry> barEntries2 = new ArrayList<>();

        barEntries2.add(new BarEntry(2,340000));


        BarDataSet barDataSet = new BarDataSet(barEntries,"Pemasukan");
        barDataSet.setColor(Color.rgb(46,204,113));

        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"Pengeluaran");
        barDataSet2.setColor(Color.rgb(235, 77, 75));

        BarData data = new BarData(barDataSet,barDataSet2);

//        float groupspace = 0.1f;
//        float barspace=0.02f;
//        float barWidth= 0.43f;

        barChart.setData(data);
//        data.setBarWidth(barWidth);
//        barChart.groupBars(1, groupspace, barspace);



//        String[] bulan = new String[]{"Jan","Feb","Mar","April","May","Jun"};
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setValueFormatter(new MyAxisValueFormater(bulan));
//        xAxis.setPosition((XAxis.XAxisPosition.BOTH_SIDED));
//        xAxis.setGranularity(1);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setAxisMinimum(1);


        return view;

    }

//    public class MyAxisValueFormater extends ValueFormatter implements IAxisValueFormatter{
//        private  String[] mValues;
//        public  MyAxisValueFormater(String [] values){
//            this.mValues=values;
//        }
//        @Override
//        public String getFormattedValue(float value, AxisBase axis) {
//            return mValues[(int)value];
//        }
//    }


}
