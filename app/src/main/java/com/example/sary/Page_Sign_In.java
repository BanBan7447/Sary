package com.example.sary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sary.DAO.NguoiDungDAO;

public class Page_Sign_In extends AppCompatActivity {
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_sign_in);

        Button btnSignIn = findViewById(R.id.Intro_SignIn);

        nguoiDungDAO = new NguoiDungDAO(this);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_SignIn();
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

        EditText SignIn_MaSo = viewSignIn.findViewById(R.id.SignIn_MaSo);
        EditText SignIn_PassWord = viewSignIn.findViewById(R.id.SignIn_PassWord);

        Button btnSignIn_Home = viewSignIn.findViewById(R.id.Btn_SignIn);
        btnSignIn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = SignIn_MaSo.getText().toString();
                String pass = SignIn_PassWord.getText().toString();

                boolean check = nguoiDungDAO.KiemTraDangNhap(user, pass);
                if (user.length() == 0 || pass.length() == 0){
                    Toast.makeText(Page_Sign_In.this, "Phải nhập đẩy đủ", Toast.LENGTH_SHORT).show();
                } else if (nguoiDungDAO.KiemTraDangNhap(user, pass)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("ThongTin", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("maThuThu", user);
                    editor.commit();
                    Toast.makeText(Page_Sign_In.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Page_Sign_In.this, Page_Home.class));
                }else {
                    Toast.makeText(Page_Sign_In.this, "Mã số hoặc mật khẩu bị sai", Toast.LENGTH_SHORT).show();
                }



//                if (user.length() == 0 || pass.length() == 0){
//                    Toast.makeText(Page_Sign_In.this, "Phải nhập đẩy đủ", Toast.LENGTH_SHORT).show();
//                } else if (check) {
//                    startActivity(new Intent(Page_Sign_In.this, Page_Home.class));
//                    Toast.makeText(Page_Sign_In.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(Page_Sign_In.this, "Mã số hoặc mật khẩu bị sai", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
};