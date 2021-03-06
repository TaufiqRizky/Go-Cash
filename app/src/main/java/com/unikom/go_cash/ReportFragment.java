package com.unikom.go_cash;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.unikom.go_cash.Adapter.CardAdapter;
import com.unikom.go_cash.Adapter.PemasukanAdapter;
import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.ViewModel.PemasukanViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.unikom.go_cash.R.layout.activity__graph;

public class ReportFragment extends Fragment {
    BarChart barChart;
    Dialog grafikDialog;
    private Button btnGrafik;
    private RecyclerView recyclerView;
    private PemasukanAdapter adapter;
    private PemasukanViewModel viewModel;
    final int[] sumPemasukan = new int[1];
    final int[] sumPengeluaran = new int[1];

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_report,container,false);
        initView(view);
//        Spinner TahunSpin= view.findViewById(R.id.SpinTahun);
//        viewModel.getTahun().observe(Objects.requireNonNull(getActivity()), new Observer<String[]>() {
//            @Override
//            public void onChanged(String[] strings) {
//
//                    ArrayAdapter<String> adapterThn =new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
//                            android.R.layout.simple_spinner_item,
//                            strings);
//                    adapterThn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    TahunSpin.setAdapter(adapterThn);
//
//            }
//        });


//        Spinner BulanSpin= view.findViewById(R.id.SpinBulan);
//        ArrayAdapter<CharSequence> adapterBln = ArrayAdapter.createFromResource(getActivity() ,R.array.Bulan, android.R.layout.simple_spinner_item);
//        adapterBln.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        BulanSpin.setAdapter(adapterBln);

        grafikDialog = new Dialog(getActivity());
        btnGrafik = (Button) view.findViewById(R.id.btnGrafik);
        btnGrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowGrafik(v);
            }
        });



        viewModel.getSumPengeluaran().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == null){
                    sumPengeluaran[0]=0;
                }else {
                    sumPengeluaran[0] = integer;
                }
            }
        });
        viewModel.getSumPemasukan().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == null){
                    sumPemasukan[0]=0;
                }else {
                    sumPemasukan[0] = integer;
                }
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

    private void initView(View v) {
        adapter = new PemasukanAdapter();

        viewModel = ViewModelProviders.of(getActivity()).get(PemasukanViewModel.class);
        viewModel.getLaporan().observe(getActivity(), new Observer<List<Keuangan>>() {
            @Override
            public void onChanged(List<Keuangan> keuangans) {
                Log.d(TAG, "onChanged: " + keuangans);
                adapter.setData(keuangans);
            }
        });
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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

        //Log.d(TAG, "aass  "+x.toString());
        BarChart barChart= grafikDialog.findViewById(R.id.barchart);
        Activity_Graph a = new Activity_Graph();
        a.buatGrafik(barChart,sumPemasukan[0],sumPengeluaran[0]);

        grafikDialog.show();
    }


}
