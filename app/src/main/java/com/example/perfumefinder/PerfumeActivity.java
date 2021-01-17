package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class PerfumeActivity extends AppCompatActivity {

    HashMap<String, String> answers;

    TextView name, season, age, brand;
    ImageView img, backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfume);

        Intent intent = getIntent();
        answers = (HashMap<String, String>) intent.getSerializableExtra("answers");

        name = findViewById(R.id.name);
        season = findViewById(R.id.season);
        age = findViewById(R.id.age);
        brand = findViewById(R.id.brand);

        img = findViewById(R.id.img);
        backHome = findViewById(R.id.back_home3);

        backHome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                backHome.setBackgroundResource(R.drawable.back_clicked);
                if (!isInside(backHome, event)) {
                    backHome.setBackgroundResource(R.drawable.back);
                }
                return false;
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome.setBackgroundResource(R.drawable.back);
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("answers", answers);
                startActivity(intent);
            }
        });

        int clickedId = getIntent().getIntExtra("clickedId", 0);
        Perfume perfumeClicked = null;
        for (int i = 0; i < ResultActivity.resultPerfumes.size(); i++) {
            if (ResultActivity.resultPerfumes.get(i).getId() == clickedId) {
                perfumeClicked = ResultActivity.resultPerfumes.get(i);
            }
        }

        int imageId = perfumeClicked.getImageId();
        String pName = perfumeClicked.getName();
        String[] seasonsList = perfumeClicked.getSeasons();
        String pSeasons = "";
        if (seasonsList.length == 4) {
            pSeasons += "All year round";
        }
        else {
            for (int i = 0; i < seasonsList.length; i++) {
                pSeasons += seasonsList[i];
                if (i != seasonsList.length - 1) {
                    pSeasons += ", ";
                }
            }
        }
        String pAgeRange = perfumeClicked.getAgeRange().toString();
        String pAge;
        if (pAgeRange.equals("[0, 34]")) {
            pAge = "0-29 years old";
        }
        else if (pAgeRange.equals("[35, 120]")) {
            pAge = "30+ years old";
        }
        else {
            pAge = "All ages";
        }

        String pBrand = perfumeClicked.getBrand();

        // capitalize first letter of seasons string
        pSeasons = pSeasons.substring(0, 1).toUpperCase() + pSeasons.substring(1);

        name.setText(pName);
        season.setText(pSeasons);
        age.setText(pAge);
        brand.setText(pBrand);
        img.setImageResource(imageId);
    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private boolean isInside(View v, MotionEvent e) {
        return !(e.getX() < 0 || e.getY() < 0 || e.getX() > v.getMeasuredWidth() || e.getY() > v.getMeasuredHeight());
    }
}