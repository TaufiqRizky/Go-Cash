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
    int id;
    PemasukanViewModel viewModel;

    public  void  init(){






        btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        btnreset = (Button) dialog.findViewById(R.id.btnReset);
        edtnama = (EditText) dialog.findViewById(R.id.edtNama);
        edtuang = (EditText) dialog.findViewById(R.id.edtUang);
        edtdesc = (EditText) dialog.findViewById(R.id.edtDesc);
        edttgl = (EditText) dialog.findViewById(R.id.edtTanggal);
        txtExit = (TextView) dialog.findViewById(R.id.txtExit);
        txtjdl = (TextView) dialog.findViewById(R.id.txtCatatan);
        txtjdl.setText(this.type.toUpperCase());

    }
    public void popUp(String type, Dialog addDialog, PemasukanViewModel v){
        dialog=addDialog;
        this.type=type;
        this.viewModel=v;
        Log.d("asasas", "popUp: "+this.type);
        init();

        Calendar calendar;
        SimpleDateFormat dateFormat;
        String date;
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());

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
                saveNote("in");
            }
        });

        //btn reset
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyDialog();
            }
        });
        edttgl.setText(date);
        addDialog.show();
    }

    public void popUpEdit(String type, Dialog addDialog, PemasukanViewModel v,String[] dataa){
        dialog=addDialog;
        this.type=type;
        this.viewModel=v;
        Log.d("asasas", "popUp: "+this.type);
        init();
        edtnama.setText(dataa[0]);
        edttgl.setText(dataa[1]);
        edtdesc.setText(dataa[2]);
        edtuang.setText(dataa[3]);
        id=new Integer(dataa[4]);
        Log.d("tes woii", "popUpEdit: "+id);
        TextView judul = (TextView) dialog.findViewById(R.id.txtJudulDial) ;
        judul.setText("EDIT CATATAN");

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
                saveNote("up");
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
        edtnama.setText("");
        edttgl.setText("");
        edtdesc.setText("");
        edtuang.setText("");
    }

    private void saveNote(String x) {
        init();
        final String nama = edtnama.getText().toString();
        final String deskripsi = edtdesc.getText().toString();
        final String tanggal = edttgl.getText().toString();
        final String a = edtuang.getText().toString();


        if (nama.trim().isEmpty() || deskripsi.trim().isEmpty() || tanggal.trim().isEmpty() || a.trim().isEmpty()) {
            Toast.makeText(dialog.getContext(), "Pastikan Semua Data Terisi Dengan Benar!", Toast.LENGTH_SHORT).show();

        }else{
            final int jml =Integer.parseInt(a);
            if (x == "in"){
                viewModel.insert(new Keuangan(tanggal,jml,deskripsi,nama,this.type));
                emptyDialog();
                dialog.dismiss();
                Toast.makeText(dialog.getContext(), "Berhasil Tambah Data", Toast.LENGTH_SHORT).show();
            }else{
                Keuangan keuangan =new Keuangan(tanggal,jml,deskripsi,nama,this.type);
                keuangan.setId(id);
                viewModel.update(keuangan);
                Log.d("Coba", "edit: " + tanggal);
                emptyDialog();
                dialog.dismiss();
                Toast.makeText(dialog.getContext(), "Berhasil Edit Data", Toast.LENGTH_SHORT).show();
            }

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
