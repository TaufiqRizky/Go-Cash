package com.unikom.go_cash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unikom.go_cash.Database.UserDao;
import com.unikom.go_cash.Database.UserDatabase;
import com.unikom.go_cash.Model.User;

public class SigninActivity extends AppCompatActivity {
    Dialog regisDialog;
    EditText edtName,edtUsername,edtPassword,edtEmail;
    private ProgressDialog progressDialog;
    private UserDao userDao;
    private Button btnSignUp, btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        setContentView(R.layout.activity_signin);
        regisDialog = new Dialog(this);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(v);
            }
        });
        userDao = Room.databaseBuilder(this, UserDatabase.class, "keuangan.db")
                .allowMainThreadQueries()
                .build()
                .getUserDao();
    }

    public  void  Home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        startActivity(intent);
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
                if (!isEmpty()) {

                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(edtName.getText().toString(), edtUsername.getText().toString(),
                                    edtEmail.getText().toString(), edtPassword.getText().toString());
                            userDao.insert(user);
                            progressDialog.dismiss();
                            Toast.makeText(SigninActivity.this, "berhasil masuk boi", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        }
                    }, 1000);

                } else {
                    Toast.makeText(SigninActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        regisDialog.show();
    }
    private boolean isEmpty() {
        if (TextUtils.isEmpty(edtName.getText().toString()) ||
                TextUtils.isEmpty(edtPassword.getText().toString()) ||
                TextUtils.isEmpty(edtUsername.getText().toString()) ||
                TextUtils.isEmpty(edtEmail.getText().toString())
        ) {
            return true;
        } else {
            return false;
        }
    }
}

