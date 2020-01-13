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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.unikom.go_cash.Adapter.CardAdapter;
import com.unikom.go_cash.Model.Planet;

import java.util.ArrayList;

import static com.unikom.go_cash.R.layout.activity__graph;

public class ReportFragment extends Fragment {
    BarChart barChart;
    Dialog grafikDialog;
    private Button btnGrafik;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Planet> planetArrayList;

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

        initView(view);





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

    private void initView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        planetArrayList = new ArrayList<>();
        adapter = new CardAdapter(this, planetArrayList);
        recyclerView.setAdapter(adapter);
        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        createListData();
    }

    private void createListData() {
        Planet planet = new Planet("Earth", 150, 10, 12750);
        planetArrayList.add(planet);
        planet = new Planet("Jupiter", 778, 26, 143000);
        planetArrayList.add(planet);
        planet = new Planet("Mars", 228, 4, 6800);
        planetArrayList.add(planet);
        planet = new Planet("Pluto", 5900, 1, 2320);
        planetArrayList.add(planet);
        planet = new Planet("Venus", 108, 9, 12750);
        planetArrayList.add(planet);
        planet = new Planet("Saturn", 1429, 11, 120000);
        planetArrayList.add(planet);
        planet = new Planet("Mercury", 58, 4, 4900);
        planetArrayList.add(planet);
        planet = new Planet("Neptune", 4500, 12, 50500);
        planetArrayList.add(planet);
        planet = new Planet("Uranus", 2870, 9, 52400);
        planetArrayList.add(planet);
        adapter.notifyDataSetChanged();
    }

    public void ShowGrafik(View v){
        TextView txtClose;
        grafikDialog.setContentView(activity__graph);
//        txtClose = (TextView) grafikDialog.findViewById(R.id.txtClose);
//        txtClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                grafikDialog.dismiss();
//            }
//        });
        BarChart barChart= grafikDialog.findViewById(R.id.barchart);
        Activity_Graph a = new Activity_Graph();
        a.buatGrafik(barChart);

        grafikDialog.show();
    }


}
