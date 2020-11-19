package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private final static String TAG = "GAMEPLAY";

    ArrayList<Perfume> perfumes;
    public static ArrayList<Perfume> resultPerfumes;
    ArrayList<String> perfumeNames;
    ArrayAdapter<String> adapter;
    ListView lv;

    HashMap<String, String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        perfumes = new ArrayList<>();
        resultPerfumes = new ArrayList<>();
        perfumeNames = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, perfumeNames);
        lv = findViewById(R.id.lv);

        addPerfumes();

        // TODO: get answers hashtable from intent! (via JSON??)
        Intent intent = getIntent();
        answers = (HashMap<String, String>) intent.getSerializableExtra("answers");

        checkPerfumes(answers);

        for (int i = 0; i < resultPerfumes.size(); i++) {
            perfumeNames.add(resultPerfumes.get(i).getName());
        }

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameClicked = (String) parent.getItemAtPosition(position);

                // TODO: pass clicked perfume object via intent to PerfumeActivity!
                Intent intent = new Intent(getApplicationContext(), PerfumeActivity.class);
                intent.putExtra("nameClicked", nameClicked);
                startActivity(intent);

            }
        });


    }

    public void checkPerfumes(HashMap<String, String> answers) {
        boolean isSeason;
        boolean isAge;

        // check season
        String season = answers.get("season").toLowerCase();

        String ageString = answers.get("age").toLowerCase();

        int age;
        if (ageString.equals("0-35 years old")) {
            age = 10;
        }
        else if (ageString.equals("35+ years old")) {
            age = 80;
        }
        else {
            age = 999;
        }

        for (int i = 0; i < perfumes.size(); i++) {
            Perfume perfume = perfumes.get(i);

            String[] perfumeSeasons = perfume.getSeasons();

            Range ageRange = perfume.getAgeRange();

            if (Arrays.asList(perfumeSeasons).contains(season) || season.equals("doesn't matter") || (season.equals("all seasons") && perfumeSeasons.length == 4)) {
                isSeason = true;
            }
            else {
                isSeason = false;
            }

            if (ageRange.contains(age) || ageString.equals("doesn't matter")) {
                isAge = true;
            }
            else if (ageRange.toString().equals("[0, 120]") && ageString.equals("all ages")) {
                isAge = true;
            }
            else {
                isAge = false;
            }

            if (isSeason && isAge) {
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
        Perfume p11 = new Perfume("Anyone, Anytime & Anywhere", new String[]{"spring", "summer", "fall", "winter"}, new Range(0, 120));

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
        perfumes.add(p11);
    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}