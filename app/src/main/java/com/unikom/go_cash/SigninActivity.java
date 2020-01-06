package com.unikom.go_cash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SigninActivity extends AppCompatActivity {
    Dialog regisDialog;
    private Button btnSignUp, btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        regisDialog = new Dialog(this);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(v);
            }
        });
    }

    public  void  Home(View view){

        Intent intent = new Intent(this, MainActivity.class);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        startActivity(intent);
    }
    public  void  ShowPopup(View v){
        TextView txtClose;
        EditText edtName,edtUsername,edtPassword;
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

        regisDialog.show();
    }
}

