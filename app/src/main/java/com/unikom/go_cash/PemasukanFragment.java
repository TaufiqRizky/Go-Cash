package com.unikom.go_cash;

import android.app.Dialog;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Adapter.PemasukanAdapter;
import com.unikom.go_cash.Model.Keuangan;

import java.util.ArrayList;


public class PemasukanFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private RecyclerView recyclerView;
    private PemasukanAdapter adapter;

    Dialog addDialog;
    private ArrayList<Keuangan> keuanganArrayList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_listpemasukan_adapter, container, false);

        Button btnapawelah = rootView.findViewById(R.id.btnTambah);

        btnapawelah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog = new Dialog(getActivity());
                ShowPopup(v);

            }
        });


        initView(rootView);
        return rootView;
    }

    public void ShowPopup(View v) {
        TextView txtExit;
        addDialog.setContentView(R.layout.fragment_tambah);
        txtExit = (TextView) addDialog.findViewById(R.id.txtExit);
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.dismiss();
            }
        });
        addDialog.show();
    }





    private void initView(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.rcpemasukan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        keuanganArrayList = new ArrayList<>();
        adapter = new PemasukanAdapter(this, keuanganArrayList);
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
}


