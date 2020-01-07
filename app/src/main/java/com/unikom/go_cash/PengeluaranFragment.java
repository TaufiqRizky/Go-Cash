package com.unikom.go_cash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PengeluaranFragment extends Fragment {
    private RecyclerView rvPengeluaran;
    private ArrayList<PengeluaranModel> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_row_pengeluaran,container, false);
        rvPengeluaran = rvPengeluaran.findViewById(R.id.rv_pengeluaran);
        rvPengeluaran.setHasFixedSize(true);

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
    private void showRecyclerList() {
        ListPengeluaranAdapter lpd = new ListPengeluaranAdapter(list);
        rvPengeluaran.setAdapter(lpd);
    }
}
