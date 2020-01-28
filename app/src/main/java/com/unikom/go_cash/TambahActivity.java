package com.unikom.go_cash;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unikom.go_cash.Entity.Keuangan;
import com.unikom.go_cash.ViewModel.PemasukanViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TambahActivity extends AppCompatActivity {

    Button btnSubmit,btnreset;
    TextView txtExit ,txtjdl;
    EditText edttgl, edtuang, edtdesc,edtnama;
    Dialog dialog;
    String type;
    PemasukanViewModel viewModel;

    public  void  init(){
        Calendar calendar;
        SimpleDateFormat dateFormat;
        String date;


        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());


        btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        btnreset = (Button) dialog.findViewById(R.id.btnReset);
        edtnama = (EditText) dialog.findViewById(R.id.edtNama);
        edtuang = (EditText) dialog.findViewById(R.id.edtUang);
        edtdesc = (EditText) dialog.findViewById(R.id.edtDesc);
        edttgl = (EditText) dialog.findViewById(R.id.edtTanggal);
        txtExit = (TextView) dialog.findViewById(R.id.txtExit);
        txtjdl = (TextView) dialog.findViewById(R.id.txtCatatan);
        txtjdl.setText(this.type.toUpperCase());
        edttgl.setText(date);
    }
    public void popUp(String type, Dialog addDialog, PemasukanViewModel v){
        dialog=addDialog;
        this.type=type;
        this.viewModel=v;
        Log.d("asasas", "popUp: "+this.type);
        init();

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
                saveNote();
            }
        });

        //btn reset
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyDialog();
            }
        });
        addDialog.show();
    }

    private  void emptyDialog(){
        edtuang.setText("");
        edttgl.setText("");
        edtdesc.setText("");
        edtuang.setText("");
    }

    private void saveNote() {
        init();
        final String nama = edtnama.getText().toString();
        final String deskripsi = edtdesc.getText().toString();
        final String tanggal = edttgl.getText().toString();
        final String a = edtuang.getText().toString();


        if (nama.trim().isEmpty() || deskripsi.trim().isEmpty() || tanggal.trim().isEmpty() || a.trim().isEmpty()) {
            Toast.makeText(dialog.getContext(), "Pastikan Semua Data Terisi Dengan Benar!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            final int jml =Integer.parseInt(a);

            viewModel.insert(new Keuangan(tanggal,jml,deskripsi,nama,this.type));
            emptyDialog();
            dialog.dismiss();
            Toast.makeText(dialog.getContext(), "Berhasil Tambah Data", Toast.LENGTH_SHORT).show();
        }



//        Intent data = new Intent();
//        data.putExtra(EXTRA_UANG, jml);
//        data.putExtra(EXTRA_DESKRIPSI, deskripsi);
//        data.putExtra(EXTRA_TANGGAL, tanggal);
//        data.putExtra(EXTRA_NAMA, nama);
//
//        setResult(RESULT_OK, data);
//        finish();
    }


}
