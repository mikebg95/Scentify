package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PerfumeActivity extends AppCompatActivity {

    // TODO: finish layout!

    TextView name, season, age;
    ImageView img, backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfume);

        name = findViewById(R.id.name);
        season = findViewById(R.id.season);
        age = findViewById(R.id.age);
        img = findViewById(R.id.img);
        backHome = findViewById(R.id.back_home3);

//        backHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.
//                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
//            }
//        });

        String nameClicked = getIntent().getStringExtra("nameClicked");
        Perfume perfumeClicked = null;
        for (int i = 0; i < ResultActivity.resultPerfumes.size(); i++) {
            if (ResultActivity.resultPerfumes.get(i).getName().equals(nameClicked)) {
                perfumeClicked = ResultActivity.resultPerfumes.get(i);
            }
        }

        int imageId = perfumeClicked.getImageId();
        String pName = perfumeClicked.getName();
        String[] seasonsList = perfumeClicked.getSeasons();
        String pSeasons = "";
        if (seasonsList.length == 4) {
            pSeasons = "all year round";
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
        String pAge = "";
        if (pAgeRange.equals("[0, 34]")) {
            pAge = "0-34 years old";
        }
        else if (pAgeRange.equals("[35, 120]")) {
            pAge = "35+ years old";
        }
        else {
            pAge = "all ages";
        }

        name.setText(pName);
        season.setText(pSeasons);
        age.setText(pAge);
        img.setImageResource(imageId);


    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}