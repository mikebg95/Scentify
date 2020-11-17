package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class ResultActivity extends AppCompatActivity {

    private final static String TAG = "GAMEPLAY";

    ArrayList<Perfume> perfumes;
    ArrayList<Perfume> resultPerfumes;
    ArrayList<String> perfumeNames;
    ArrayAdapter<String> adapter;
    ListView lv;

    Hashtable<String, String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        perfumes = new ArrayList<>();
        resultPerfumes = new ArrayList<>();
        perfumeNames = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, perfumeNames);
        lv = findViewById(R.id.lv);

        addPerfumes();

        // TODO: get answers hashtable from intent! (via JSON??)
        Intent intent = getIntent();
//        answers = intent.

//        checkPerfumes(answers);

        for (int i = 0; i < resultPerfumes.size(); i++) {
            perfumeNames.add(resultPerfumes.get(i).getName());
        }

        lv.setAdapter(adapter);


    }

    public void checkPerfumes(ArrayList<String> answers) {

        String season = answers.get(0);
        String ageString = answers.get(1);
        int age;
        makeToast(ageString);
        if (ageString.equals("0-35 years old")) {
            age = 10;
        }
        else {
            age = 80;
        }

        for (int i = 0; i < perfumes.size(); i++) {
            boolean isInSeasons;
            Perfume perfume = perfumes.get(i);
            String[] seasons = perfume.getSeasons();
            Range ageRange = perfume.getAgeRange();


            if (Arrays.asList(seasons).contains(season) && ageRange.contains(age)) {
                resultPerfumes.add(perfume);
            }
        }
    }

    public void addPerfumes() {
        Perfume p1 = new Perfume("Viktor & Rolf Spicebomb Extreme", new String[]{"fall", "winter"}, new Range(35, 120));
        Perfume p2 = new Perfume("Dolce & Gabbana The One EDP", new String[]{"fall", "winter"}, new Range(35, 120));
        Perfume p3 = new Perfume("Calvin Klein CK Shock For Him", new String[]{"fall", "winter"}, new Range(0, 34));
        Perfume p4 = new Perfume("Emporio Armani Stronger With You", new String[]{"fall", "winter"}, new Range(0, 34));
        Perfume p5 = new Perfume("Azzaro Wanted By Night", new String[]{"fall", "winter"}, new Range(35, 120));
        Perfume p6 = new Perfume("Dolce & Gabbana Light Blue Intense", new String[]{"spring", "summer"}, new Range(0, 34));
        Perfume p7 = new Perfume("Bvlgari Aqva Marine Pour Homme", new String[]{"spring", "summer"}, new Range(35, 120));
        Perfume p8 = new Perfume("Mont Blanc Legend Spirit", new String[]{"spring", "summer"}, new Range(35, 120));
        Perfume p9 = new Perfume("Lacoste Blanc", new String[]{"spring", "summer"}, new Range(35, 120));
        Perfume p10 = new Perfume("Versace Pour Homme", new String[]{"spring", "summer"}, new Range(0, 34));

        perfumes.add(p1);
        perfumes.add(p2);
        perfumes.add(p3);
        perfumes.add(p4);
        perfumes.add(p5);
        perfumes.add(p6);
        perfumes.add(p7);
        perfumes.add(p8);
        perfumes.add(p9);
        perfumes.add(p10);
    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}