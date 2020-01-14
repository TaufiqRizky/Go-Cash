package com.unikom.go_cash;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InputPemasukan extends Fragment {
    private RecyclerView rcPemasukan;
    private ArrayList<PemasukanModel> list = new ArrayList<>();

    public InputPemasukan(){
        //constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pemasukan, container, false);


        final Button tb_pemasukan = (Button)view.findViewById(R.id.tb_pemasukan);
//        tb_pemasukan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pindah = new Intent(getActivity(), ListpemasukanAdapter.class);
//                startActivity(pindah);
//                Toast.makeText(getActivity(),"test",Toast.LENGTH_SHORT).show();
//
//
//
//            }
//            //}
//        });
        return view;
    }


}



