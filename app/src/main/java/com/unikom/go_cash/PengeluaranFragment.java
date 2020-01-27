package com.unikom.go_cash;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class PengeluaranFragment extends Fragment {
    Dialog addDialog;
    private Button btnAdd;
    private RecyclerView rvPengeluaran;
    private PemasukanAdapter adapter;
    private PemasukanViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pengeluaran,container, false);

        addDialog = new Dialog(getActivity());
        btnAdd= (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(v);
            }
        });

        initView(view);
        return view;
    }

    public  void  ShowPopup(View v){
        addDialog.setContentView(R.layout.fragment_tambah);
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
        RecyclerView recyclerView = v.findViewById(R.id.rvPengeluaran);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }
}
