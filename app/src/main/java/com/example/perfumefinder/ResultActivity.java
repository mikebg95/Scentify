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
import android.widget.ImageView;
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
    ImageView backHome;

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
        backHome = findViewById(R.id.back_home2);

        addPerfumes();

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
            }
        });

        Intent intent = getIntent();
        answers = (HashMap<String, String>) intent.getSerializableExtra("answers");

        checkPerfumes(answers);

        for (int i = 0; i < resultPerfumes.size(); i++) {
            perfumeNames.add(resultPerfumes.get(i).getName());
        }

        // TODO: make custom lv rows with image and name
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameClicked = (String) parent.getItemAtPosition(position);

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
        Perfume p1 = new Perfume("Viktor & Rolf Spicebomb Extreme", new String[]{"fall", "winter"}, new Range(35, 120), R.drawable.viktor_rolf_spicebomb_extreme);
        Perfume p2 = new Perfume("Dolce & Gabbana The One EDP", new String[]{"fall", "winter"}, new Range(35, 120), R.drawable.dolce_gabbana_the_one_edp);
        Perfume p3 = new Perfume("Calvin Klein CK Shock For Him", new String[]{"fall", "winter"}, new Range(0, 34), R.drawable.calvin_klein_ck_shock_for_him);
        Perfume p4 = new Perfume("Emporio Armani Stronger With You", new String[]{"fall", "winter"}, new Range(0, 34), R.drawable.emporio_armani_stronger_with_you);
        Perfume p5 = new Perfume("Azzaro Wanted By Night", new String[]{"fall", "winter"}, new Range(35, 120), R.drawable.azzaro_wanted_by_night);
        Perfume p6 = new Perfume("Dolce & Gabbana Light Blue Intense", new String[]{"spring", "summer"}, new Range(0, 34), R.drawable.dolce_gabbana_light_blue_intense);
        Perfume p7 = new Perfume("Bvlgari Aqva Marine Pour Homme", new String[]{"spring", "summer"}, new Range(35, 120), R.drawable.bvlgari_aqva_marine_pour_homme);
        Perfume p8 = new Perfume("Montblanc Legend Spirit", new String[]{"spring", "summer"}, new Range(35, 120), R.drawable.montblanc_legend_spirit);
        Perfume p9 = new Perfume("Lacoste Blanc", new String[]{"spring", "summer"}, new Range(35, 120), R.drawable.lacoste_blanc);
        Perfume p10 = new Perfume("Versace Pour Homme", new String[]{"spring", "summer"}, new Range(0, 34), R.drawable.versace_pour_homme);
//        Perfume p11 = new Perfume("Anyone, Anytime & Anywhere", new String[]{"spring", "summer", "fall", "winter"}, new Range(0, 120), R.drawable);

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
//        perfumes.add(p11);
    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}