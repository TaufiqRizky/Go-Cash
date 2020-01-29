package com.unikom.go_cash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.unikom.go_cash.ViewModel.PemasukanViewModel;

import java.text.NumberFormat;
import java.util.Locale;

public class ProfileFragment extends Fragment {
    final int[] sumPemasukan = new int[1];
    final int[] sumPengeluaran = new int[1];
    private PemasukanViewModel viewModel;
    TextView txtPemasukan,txtPengeluaran,txtSaldo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile,container,false);
        txtPemasukan = view.findViewById(R.id.txtPemasukan);
        txtPengeluaran = view.findViewById(R.id.txtPengeluaran);
        txtSaldo = view.findViewById(R.id.txtSaldo);
        viewModel = ViewModelProviders.of(getActivity()).get(PemasukanViewModel.class);

        viewModel.getSumPengeluaran().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == null){
                    sumPengeluaran[0]=0;
                }else {
                    sumPengeluaran[0] = integer;
                }
                setText();
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
                setText();
            }
        });


        return view;
    }

    public void setText(){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        String a = formatRupiah.format((double) new Integer(sumPemasukan[0]));
        String b = formatRupiah.format((double) new Integer(sumPengeluaran[0]));
        String c = formatRupiah.format((double) new Integer(sumPemasukan[0]-sumPengeluaran[0]));

        txtPemasukan.setText(a);
        txtPengeluaran.setText(b);
        txtSaldo.setText(c);
    }
}
