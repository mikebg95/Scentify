package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Range;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private final static String TAG = "GAMEPLAY";

    ArrayList<Perfume> perfumes;
    public static ArrayList<Perfume> resultPerfumes;

//    ArrayList<String> perfumeNames;
//    ArrayAdapter<String> adapter;

    PerfumeAdapter adapter;

    ListView lv;
    ImageView backHome;

    HashMap<String, String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // references to views
        lv = findViewById(R.id.lv);
        backHome = findViewById(R.id.back_question);

        // add all perfumes to list
        perfumes = new ArrayList<>();
        addPerfumes();

        // get answers from intent
        Intent intent = getIntent();
        answers = (HashMap<String, String>) intent.getSerializableExtra("answers");

        // get perfumes from answers
        resultPerfumes = new ArrayList<>();
        checkPerfumes(answers);

        // create custom adapter
        adapter = new PerfumeAdapter(this, R.layout.list_item, resultPerfumes);

        // set adapter to listview
        lv.setAdapter(adapter);


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
                startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
            }
        });





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Perfume perfumeClicked = (Perfume) parent.getItemAtPosition(position);
                int clickedId = perfumeClicked.getId();

                Intent intent = new Intent(getApplicationContext(), PerfumeActivity.class);
                intent.putExtra("clickedId", clickedId);
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
        Perfume p1 = new Perfume(1, "Viktor & Rolf",  "Spicebomb Extreme", new String[]{"fall", "winter"}, new Range(35, 120), R.drawable.viktor_rolf_spicebomb_extreme);
        Perfume p2 = new Perfume(2, "Dolce & Gabbana", "The One EDP", new String[]{"fall", "winter"}, new Range(35, 120), R.drawable.dolce_gabbana_the_one_edp);
        Perfume p3 = new Perfume(3, "Calvin Klein", "Shock For Him", new String[]{"fall", "winter"}, new Range(0, 34), R.drawable.calvin_klein_ck_shock_for_him);
        Perfume p4 = new Perfume(4, "Emporio Armani", "Stronger With You", new String[]{"fall", "winter"}, new Range(0, 34), R.drawable.emporio_armani_stronger_with_you);
        Perfume p5 = new Perfume(5, "Azzaro", "Wanted By Night", new String[]{"fall", "winter"}, new Range(35, 120), R.drawable.azzaro_wanted_by_night);
        Perfume p6 = new Perfume(6, "Dolce & Gabbana", "Light Blue Intense", new String[]{"spring", "summer"}, new Range(0, 34), R.drawable.dolce_gabbana_light_blue_intense);
        Perfume p7 = new Perfume(7, "Bvlgari", "Aqva Marine Pour Homme", new String[]{"spring", "summer"}, new Range(35, 120), R.drawable.bvlgari_aqva_marine_pour_homme);
        Perfume p8 = new Perfume(8, "Montblanc", "Legend Spirit", new String[]{"spring", "summer"}, new Range(35, 120), R.drawable.montblanc_legend_spirit);
        Perfume p9 = new Perfume(9, "Lacoste", "Lacoste Blanc", new String[]{"spring", "summer"}, new Range(35, 120), R.drawable.lacoste_blanc);
        Perfume p10 = new Perfume(10, "Versace", "Versace Pour Homme", new String[]{"spring", "summer"}, new Range(0, 34), R.drawable.versace_pour_homme);

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

    private boolean isInside(View v, MotionEvent e) {
        return !(e.getX() < 0 || e.getY() < 0 || e.getX() > v.getMeasuredWidth() || e.getY() > v.getMeasuredHeight());
    }
}