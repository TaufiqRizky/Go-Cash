package com.unikom.go_cash;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unikom.go_cash.Adapter.PemasukanAdapter;
import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.ViewModel.PemasukanViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;


public class PemasukanFragment extends Fragment  {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private RecyclerView recyclerView;
    private PemasukanAdapter adapter;

    Dialog addDialog;
    private PemasukanViewModel viewModel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_listpemasukan_adapter, container, false);

        FloatingActionButton btnAdd = rootView.findViewById(R.id.btnTambah);

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
        TextView txtExit ,txtjdl;
        final EditText edttgl, edtuang, edtdesc;
        addDialog.setContentView(R.layout.fragment_tambah);

        btnSubmit = (Button) addDialog.findViewById(R.id.btnSubmit);
        edtuang = (EditText) addDialog.findViewById(R.id.edtUang);
        edtdesc = (EditText) addDialog.findViewById(R.id.edtDesc);

        txtExit = (TextView) addDialog.findViewById(R.id.txtExit);
        txtjdl = (TextView) addDialog.findViewById(R.id.txtCatatan);

        txtjdl.setText(type.toUpperCase());


        //btn close
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.dismiss();
            }
        });

        //btn submit
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Keuangan b = new Keuangan( );
//                b.setDesc(edtdesc.getText().toString());
//                b.setNama(nama);
//                b.setType(type);
//                b.setTgl(edttgl.getText().toString());
//                b.setUang(finalValue);
//                insertData(b);
            }
        });
        addDialog.show();
    }

    private void initView(View v) {
        adapter = new PemasukanAdapter();

        viewModel = ViewModelProviders.of(getActivity()).get(PemasukanViewModel.class);
        viewModel.getPemasukan().observe(getActivity(), new Observer<List<Keuangan>>() {
            @Override
            public void onChanged(List<Keuangan> keuangans) {
                if (keuangans != null)
                adapter.setData(keuangans);
            }
        });

        RecyclerView recyclerView = v.findViewById(R.id.rcpemasukan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Keuangan myWord = adapter.getUangat(position);
                Toast.makeText(getActivity(), "Deleting " +
                        myWord.getNama(), Toast.LENGTH_LONG).show();

                // Delete the word
                //viewModel.deleteTest(myWord.getId());
                viewModel.delete(adapter.getUangat(viewHolder.getAdapterPosition()));
                Log.d(TAG, "onSwiped: "+myWord.getId());

                Toast.makeText(getActivity(),"Berhasil Delete", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


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


