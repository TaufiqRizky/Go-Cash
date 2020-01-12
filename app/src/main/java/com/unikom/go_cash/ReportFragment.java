package com.unikom.go_cash;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

import static com.unikom.go_cash.R.layout.activity__graph;

public class ReportFragment extends Fragment {
    BarChart barChart;
    Dialog grafikDialog;
    private Button btnGrafik;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_report,container,false);

        Spinner TahunSpin= view.findViewById(R.id.SpinTahun);
        ArrayAdapter<CharSequence> adapterThn = ArrayAdapter.createFromResource(getActivity() ,R.array.Tahun, android.R.layout.simple_spinner_item);
        adapterThn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TahunSpin.setAdapter(adapterThn);

        Spinner BulanSpin= view.findViewById(R.id.SpinBulan);
        ArrayAdapter<CharSequence> adapterBln = ArrayAdapter.createFromResource(getActivity() ,R.array.Bulan, android.R.layout.simple_spinner_item);
        adapterBln.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BulanSpin.setAdapter(adapterBln);

        grafikDialog = new Dialog(getActivity());
        btnGrafik = (Button) view.findViewById(R.id.btnGrafik);
        btnGrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowGrafik(v);
            }
        });





//        float groupspace = 0.1f;
//        float barspace=0.02f;
//        float barWidth= 0.43f;
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

    public void ShowGrafik(View v){
        TextView txtClose;
        grafikDialog.setContentView(activity__graph);
        txtClose = (TextView) grafikDialog.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grafikDialog.dismiss();
            }
        });
        BarChart barChart= grafikDialog.findViewById(R.id.barchart);
        Activity_Graph a = new Activity_Graph();
        a.buatGrafik(barChart);

        grafikDialog.show();
    }


}
