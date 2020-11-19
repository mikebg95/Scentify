package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class PerfumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfume);

        Intent intent = getIntent();
        Perfume perfumeClicked = (Perfume) intent.getSerializableExtra("perfumeClicked");
        makeToast(perfumeClicked.getName());
    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}