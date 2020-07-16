package com.example.appquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.widget.RadioButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button logout;
    AlertDialog.Builder builder;
    RadioGroup radiogroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DatabaseHelper(this);

        logout = (Button)findViewById(R.id.btn_logout);


        Boolean checkSession = db.checkSession("ada");
        if (checkSession == false) {
            Intent loginIntent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }

        // logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updtSession = db.upgradeSession("kosong", 1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });
        //radiogroup inisialisasi
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);


    }

    //Memilih RadioButton

    public void onRadioButton(View view) {

        Boolean checked = ((RadioButton) view).isChecked();


        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    tampilDialog();
                break;

            case R.id.radioButton2:
                if (checked)
                    jawabanSalah();
                break;
            case R.id.radioButton3:
                if (checked)
                    jawabanSalah();
                break;
            case R.id.radioButton4:
                if (checked)
                    jawabanSalah();
                break;
        }


    }

    //menampilkan dialog
    public void tampilDialog() {

        builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Selamat !!!");
        builder.setMessage("Jawaban kamu benar : Real Marid");
        builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(HomeActivity.this, "Selamat", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("ULANGI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                radiogroup.clearCheck();
            }
        });

        builder.create().show();

    }
    //menampilkan toast text jawaban salah
    public void jawabanSalah(){

        Toast.makeText(this, "Jawaban kamu Salah", Toast.LENGTH_SHORT).show();

    }

}