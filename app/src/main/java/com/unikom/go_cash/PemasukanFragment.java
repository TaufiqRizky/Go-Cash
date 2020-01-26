package com.unikom.go_cash;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Adapter.PemasukanAdapter;
import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.ViewModel.PemasukanViewModel;

import java.util.List;


public class PemasukanFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private RecyclerView recyclerView;
    private PemasukanAdapter adapter;

    Dialog addDialog;
    private PemasukanViewModel viewModel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_listpemasukan_adapter, container, false);

        Button btnAdd = rootView.findViewById(R.id.btnTambah);
        // initiate pemanggilan Room database
//        db = Room.databaseBuilder(getActivity() ,
//                KeuanganDatabase.class, "Keuangan").build();

        btnAdd.setOnClickListener(new View.OnClickListener() {
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
        final String type = "Pemasukan";
        final String nama = "xxxx";

        Button btnSubmit;
        TextView txtExit;
        final EditText edttgl, edtuang, edtdesc;
        addDialog.setContentView(R.layout.fragment_tambah);

        btnSubmit = (Button) addDialog.findViewById(R.id.btnSubmit);
        edtuang = (EditText) addDialog.findViewById(R.id.edtUang);
        edtdesc = (EditText) addDialog.findViewById(R.id.edtDesc);
        edttgl = (EditText) addDialog.findViewById(R.id.edtTanggal);
        txtExit = (TextView) addDialog.findViewById(R.id.txtExit);

        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.dismiss();
            }
        });
        String temp = edtuang.getText().toString();
        int value = 0;
        if (!"".equals(temp)) {
            value = Integer.parseInt(temp);
        }
        final int finalValue = value;
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Keuangan b = new Keuangan();
//                b.setDesc(edtdesc.getText().toString());
//                b.setNama(nama);
//                b.setType(type);
//                b.setTgl(edttgl.getText().toString());
//                b.setUang(finalValue);
//                insertData(b);
//            }
//        });
        addDialog.show();
    }

    private void initView(View v) {
        adapter = new PemasukanAdapter();

        viewModel = ViewModelProviders.of(getActivity()).get(PemasukanViewModel.class);
        viewModel.getPemasukan().observe(getActivity(), new Observer<List<Keuangan>>() {
            @Override
            public void onChanged(List<Keuangan> keuangans) {
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                adapter.setData(keuangans);
            }
        });
        RecyclerView recyclerView = v.findViewById(R.id.rcpemasukan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

//    private void createListData() {
//        Keuangan keuangan = new Keuangan("19/08/2019", "Maemunah", "Uang Kas Mingguan", 2000);
//        keuanganArrayList.add(keuangan);
//        keuangan = new Keuangan("19/08/2019", "Epul", "Uang Kas Mingguan", 2000);
//        keuanganArrayList.add(keuangan);
//        keuangan = new Keuangan("19/08/2020", "Udin", "Uang Kas Mingguan", 3000);
//        keuanganArrayList.add(keuangan);
//        keuangan = new Keuangan("19/02/2019", "Siti", "Uang Kas Mingguan", 5000);
//        keuanganArrayList.add(keuangan);
//        keuangan = new Keuangan("14/07/2019", "Jubaedah", "Uang Kas Mingguan", 2500);
//        keuanganArrayList.add(keuangan);
//        keuangan = new Keuangan("9/02/2017", "Alip", "Uang Kas Mingguan", 500);
//        keuanganArrayList.add(keuangan);
//        keuangan = new Keuangan("1/08/2018", "Maemunah", "Uang Kas Mingguan", 2000);
//        keuanganArrayList.add(keuangan);
//
//        adapter.notifyDataSetChanged();
//    }
}


