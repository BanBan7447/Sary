package com.example.sary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sary.DAO.NguoiDungDAO;

public class Page_Sign_In extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_sign_in);

        EditText edtSignin = findViewById(R.id.SignIn_MaSo);
        EditText edtPass = findViewById(R.id.SignIn_PassWord);
        Button btnSignIn = findViewById(R.id.Intro_SignIn);

         nguoiDungDAO = new NguoiDungDAO(this);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_SignIn();
                String user = edtSignin.getText().toString();
                String pass = edtPass.getText().toString();

                boolean check = nguoiDungDAO.KiemTraDangNhap(user, pass);

                if (check){
                    startActivity(new Intent(Page_Sign_In.this, Page_Home.class));
                }else {
                    Toast.makeText(Page_Sign_In.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                }
            }
        });

            }

            public void Dialog_SignIn() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_Sign_In.this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View viewSignIn = layoutInflater.inflate(R.layout.ui_dialog_sign_in, null);
                builder.setView(viewSignIn);
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.radius_dialog));
                alertDialog.show();

                Button btnSignIn_Home = viewSignIn.findViewById(R.id.btn_SignIn);
                btnSignIn_Home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Page_Sign_In.this, Page_Home.class));
                    }
                });

    }
};