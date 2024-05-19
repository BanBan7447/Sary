package com.example.sary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class Page_Sign_In extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_sign_in);

        Button btnSignIn = findViewById(R.id.Intro_SignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_SignIn();
            }
        });

    }

    public void Dialog_SignIn(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Page_Sign_In.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View viewSignIn = layoutInflater.inflate(R.layout.ui_dialog_sign_in, null);
        builder.setView(viewSignIn);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.radius_dialog));
        alertDialog.show();

    }
}