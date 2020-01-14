package com.unikom.go_cash;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.unikom.go_cash.Adapter.CardAdapter;
import com.unikom.go_cash.Model.Keuangan;

import java.util.ArrayList;

import static com.unikom.go_cash.R.layout.activity__graph;

public class ReportFragment extends Fragment {
    BarChart barChart;
    Dialog grafikDialog;
    private Button btnGrafik;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Keuangan> keuanganArrayList;

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
        keuanganArrayList = new ArrayList<>();
        adapter = new CardAdapter(this, keuanganArrayList);
        recyclerView.setAdapter(adapter);
        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        createListData();
    }

    private void createListData() {
        Keuangan keuangan = new Keuangan("19/08/2019", "Maemunah", "Uang Kas Mingguan", 2000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("19/08/2019", "Epul", "Uang Kas Mingguan", 2000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("19/08/2020", "Udin", "Uang Kas Mingguan", 3000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("19/02/2019", "Siti", "Uang Kas Mingguan", 5000);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("14/07/2019", "Jubaedah", "Uang Kas Mingguan", 2500);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("9/02/2017", "Alip", "Uang Kas Mingguan", 500);
        keuanganArrayList.add(keuangan);
        keuangan = new Keuangan("1/08/2018", "Maemunah", "Uang Kas Mingguan", 2000);
        keuanganArrayList.add(keuangan);

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
