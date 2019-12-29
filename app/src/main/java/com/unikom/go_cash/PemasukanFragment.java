package com.unikom.go_cash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PemasukanFragment extends Fragment {

    public  PemasukanFragment(){
        //constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pemasukan,container,false);

        Button tb_pemasukan = (Button)view.findViewById(R.id.tb_pemasukan);
        tb_pemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent in = new Intent(getActivity(),TablePemasukan.class);
            startActivity(in);
            }
        });
            return view;
    }

    }


