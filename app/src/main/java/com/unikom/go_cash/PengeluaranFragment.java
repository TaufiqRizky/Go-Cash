package com.unikom.go_cash;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unikom.go_cash.Adapter.PengeluaranAdapter;
import com.unikom.go_cash.Model.Pengeluaran;

import java.util.ArrayList;

public class PengeluaranFragment extends Fragment {
    private RecyclerView rvPengeluaran;
    private ArrayList<Pengeluaran> list = new ArrayList<>();
    private Button btnAdd;
    Dialog addDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pengeluaran,container, false);
        rvPengeluaran = view.findViewById(R.id.rv_pengeluaran);
        rvPengeluaran.setHasFixedSize(true);
        addDialog = new Dialog(getActivity());
        btnAdd= (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(v);
            }
        });
//        btnAdd = view.findViewById(R.id.btnAdd);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopUp();
//            }
//        });
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(v);
            }
        });

        list.addAll(PengeluaranData.getListData());
        showRecyclerList();
        return view;
    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        rvPengeluaran = rvPengeluaran.findViewById(R.id.rv_pengeluaran);
//        rvPengeluaran.setHasFixedSize(true);
//
//        list.addAll(PengeluaranData.getListData());
//        showRecyclerList();
//    }
//
//
    public  void  ShowPopup(View v){
        TextView txtExit;
        addDialog.setContentView(R.layout.fragment_tambah_pengeluaran);
        txtExit = (TextView) addDialog.findViewById(R.id.txtExit);
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.dismiss();
            }
        });
        addDialog.show();
    }

    private void showRecyclerList() {
        rvPengeluaran.setLayoutManager(new LinearLayoutManager(getActivity()));
        PengeluaranAdapter lpd = new PengeluaranAdapter(list);
        rvPengeluaran.setAdapter(lpd);
    }
}
