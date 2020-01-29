package com.unikom.go_cash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unikom.go_cash.Database.KeuanganDatabase;
import com.unikom.go_cash.Database.UserDao;
import com.unikom.go_cash.Database.UserDatabase;
import com.unikom.go_cash.Database.keuanganDAO;
import com.unikom.go_cash.Model.User;
import com.unikom.go_cash.ViewModel.PemasukanViewModel;

public class SigninActivity extends AppCompatActivity {
    Dialog regisDialog;
    EditText edtName,edtUsername,edtPassword;
    private ProgressDialog progressDialog;
    private PemasukanViewModel viewModel;
    private keuanganDAO dao;
    private UserDao dao2;
    public UserDatabase database;


    private Button btnSignUp, btnSignIn;
    private EditText edtusname,edtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(SigninActivity.this).get(PemasukanViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        database = Room.databaseBuilder(this, UserDatabase.class, "user.db")
                .allowMainThreadQueries()
                .build();

        dao2 = database.getUserDao();

        setContentView(R.layout.activity_signin);
        regisDialog = new Dialog(this);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username,password;
                edtusname = (EditText) findViewById(R.id.edtUsernamel);
                edtpass = (EditText) findViewById(R.id.edtPassl);
                username=edtusname.getText().toString();
                password=edtpass.getText().toString();
                progressDialog.setMessage("Login in...");
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();

                }else{

                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            User user = dao2.getUser(username,password);
                            if(user!=null){
                                Intent i = new Intent(SigninActivity.this, MainActivity.class);
                                i.putExtra("User", user);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(SigninActivity.this, "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    }, 1000);
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Registering...");
                ShowPopup(v);
            }
        });

    }

    public  void  ShowPopup(View v){
        TextView txtClose;
        Button btnRegister;
        regisDialog.setContentView(R.layout.activity_register);
        txtClose = (TextView) regisDialog.findViewById(R.id.txtClose);
        edtName = (EditText) regisDialog.findViewById(R.id.edtName);
        edtUsername = (EditText) regisDialog.findViewById(R.id.edtUsername);
        edtPassword = (EditText) regisDialog.findViewById(R.id.edtPassword);
        btnRegister = (Button) regisDialog.findViewById(R.id.btnRegister);



        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regisDialog.dismiss();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama,username,password;
                nama= edtName.getText().toString();
                username= edtUsername.getText().toString();
                password= edtPassword.getText().toString();

                if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Pastikan Semua Data Terisi Dengan Benar!", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SigninActivity.this,"test",Toast.LENGTH_SHORT).show();
                            User user = new User(nama, username, password);
                            dao2.insert(user);
                            progressDialog.dismiss();
                            startActivity(new Intent(SigninActivity.this, MainActivity.class));
                        }
                    }, 1000);


                }
            }
        });

        regisDialog.show();
    }

}

