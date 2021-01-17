package com.example.perfumefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
                intent.putExtra("answers", answers);
                intent.putExtra("clickedId", clickedId);
                startActivity(intent);

            }
        });
    }

    public void checkPerfumes(HashMap<String, String> answers) {

        boolean isSeason, isAge, isLifestyle, isType;

        // answers chosen by user
        String season = answers.get("season").toLowerCase();
        String ageString = answers.get("age").toLowerCase();
        String lifestyle = answers.get("lifestyle").toLowerCase();
        String type = answers.get("type").toLowerCase();

        int age;
        if (ageString.equals("0-30 years old")) {
            age = 10;
        }
        else if (ageString.equals("30+ years old")) {
            age = 80;
        }
        else {
            age = 999;
        }

        for (int i = 0; i < perfumes.size(); i++) {
            Perfume perfume = perfumes.get(i);

            // iterating over each perfume in list
            String[] perfumeSeasons = perfume.getSeasons();
            Range ageRange = perfume.getAgeRange();
            String perfumeLifestyle = perfume.getLifestyle().toLowerCase();
            String perfumeType = perfume.getType().toLowerCase();

            // filter based on seasons
            if (Arrays.asList(perfumeSeasons).contains(season) || season.equals("doesn't matter") || (season.equals("all seasons") && perfumeSeasons.length == 4)) {
                isSeason = true;
            }
            else {
                isSeason = false;
            }

            Log.d(TAG, "Perfume: " + i);
            Log.d(TAG, ": ");

            // filter based on age
            if (ageRange.contains(age) || ageString.equals("doesn't matter")) {
                isAge = true;
            }
            else if (ageRange.toString().equals("[0, 120]") && ageString.equals("all ages")) {
                isAge = true;
            }
            else {
                isAge = false;
            }

            // select based on lifestyle
            if (lifestyle.equals("doesn't matter") || perfumeLifestyle.equals(lifestyle)) {
                isLifestyle = true;
            }
            else {
                isLifestyle = false;
            }

            // select based on type
            if (type.equals("doesn't matter") || perfumeType.equals(type)) {
                isType = true;
            }
            else {
                isType = false;
            }

            if (isSeason && isAge && isLifestyle && isType) {
                resultPerfumes.add(perfume);
            }

        }
    }

    public void addPerfumes() {
        Perfume p1 = new Perfume(1, "Viktor & Rolf",  "Spicebomb Extreme", new String[]{"fall", "winter"}, new Range(30, 120), "Business", "Fearless", R.drawable.viktor_rolf_spicebomb_extreme);
        Perfume p2 = new Perfume(2, "Giorgio Armani",  "Acqua di Gio Profumo", new String[]{"fall", "winter", "summer", "spring"}, new Range(30, 120), "Business", "Fearless", R.drawable.giorgio_armani_acqua_di_gio_profumo);
        Perfume p3 = new Perfume(3, "Giorgio Armani",  "Armani Code Colonia", new String[]{"spring", "summer"}, new Range(0, 29), "Student", "Sweet", R.drawable.giorgio_armani_code_colonia);
        Perfume p4 = new Perfume(4, "Giorgio Armani",  "Armani Code Absolu", new String[]{"fall", "winter"}, new Range(0, 29), "Student", "Sweet", R.drawable.giorgio_armani_code_absolu);
        Perfume p5 = new Perfume(5, "Giorgio Armani",  "Armani Code A-List", new String[]{"fall", "winter"}, new Range(30, 120), "Business", "Sweet", R.drawable.giorgio_armani_code_a_list);
        Perfume p6 = new Perfume(6, "Giorgio Armani",  "Stronger With You", new String[]{"fall", "winter"}, new Range(0, 29), "Traveler", "Sweet", R.drawable.emporio_armani_stronger_with_you);
        Perfume p7 = new Perfume(7, "Chanel",  "Bleu de Chanel", new String[]{"fall", "winter", "summer", "spring"}, new Range(30, 120), "Business", "Fresh", R.drawable.chanel_bleu_de_chanel);
        Perfume p8 = new Perfume(8, "Chanel",  "Allure Homme Sport Eau Extreme", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Athlete", "Fresh", R.drawable.chanel_allure_homme_sport_eau_extreme);
        Perfume p9 = new Perfume(9, "Chanel",  "Platinum Egoïste", new String[]{"fall", "winter", "summer", "spring"}, new Range(30, 120), "Business", "Fearless", R.drawable.chanel_platinum_egoiste);
        Perfume p10 = new Perfume(10, "Dolce & Gabbana", "Light Blue Eau Intense", new String[]{"spring", "summer"}, new Range(0, 29), "Traveler", "Fresh", R.drawable.dolce_gabbana_light_blue_intense);
        Perfume p11 = new Perfume(11, "Dolce & Gabbana", "The One Eau de Parfum", new String[]{"fall", "winter"}, new Range(0, 29), "Business", "Sweet", R.drawable.dolce_gabbana_the_one_edp);
        Perfume p12 = new Perfume(12, "Yves Saint Laurent", "L'Homme", new String[]{"fall", "winter"}, new Range(30, 120), "Traveler", "Sweet", R.drawable.yves_saint_laurent_lhomme);
        Perfume p13 = new Perfume(13, "Yves Saint Laurent", "La Nuit de L'Homme", new String[]{"fall", "winter"}, new Range(30, 120), "Business", "Sweet", R.drawable.yves_saint_laurent_la_nuit_de_lhomme);
        Perfume p14 = new Perfume(14, "Yves Saint Laurent", "Y Eau de Parfum", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Student", "Fresh", R.drawable.yves_saint_laurent_y_eau_de_parfum);
        Perfume p15 = new Perfume(15, "Christian Dior", "Sauvage", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Business", "Fresh", R.drawable.christian_dior_sauvage);
        Perfume p16 = new Perfume(16, "Christian Dior", "Fahrenheit", new String[]{"fall", "winter"}, new Range(30, 120), "Nature", "Fearless", R.drawable.christian_dior_fahrenheit);
        Perfume p17 = new Perfume(17, "Christian Dior", "Homme", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Business", "Sweet", R.drawable.christian_dior_homme);
        Perfume p18 = new Perfume(18, "Jean Paul Gaultier", "Le Male Le Parfum", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Business", "Sweet", R.drawable.jean_paul_gaultier_le_male_le_parfum);
        Perfume p19 = new Perfume(19, "Jean Paul Gaultier", "Le Male", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Student", "Sweet", R.drawable.jean_paul_gaultier_le_male);
        Perfume p20 = new Perfume(20, "Jean Paul Gaultier", "Le Beau", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Athlete", "Fresh", R.drawable.jean_paul_gaultier_le_beau);
        Perfume p21 = new Perfume(21, "Givenchy", "Gentleman", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Student", "Sweet", R.drawable.givenchy_gentleman);
        Perfume p22 = new Perfume(22, "Lacoste", "L'Homme Intense", new String[]{"spring", "summer"}, new Range(30, 120), "Traveler", "Fresh", R.drawable.jean_paul_gaultier_le_beau);
        Perfume p23 = new Perfume(23, "Lacoste", "Blanc", new String[]{"spring", "summer"}, new Range(0, 29), "Student", "Fresh", R.drawable.lacoste_blanc);
        Perfume p24 = new Perfume(24, "Montblanc", "Legend Spirit", new String[]{"spring", "summer"}, new Range(0, 29), "Athlete", "Fresh", R.drawable.montblanc_legend_spirit);
        Perfume p25 = new Perfume(25, "Montblanc", "Explorer", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Traveler", "Fresh", R.drawable.montblanc_explorer);
        Perfume p26 = new Perfume(26, "Montblanc", "Emblem Intense", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Business", "Sweet", R.drawable.montblanc_emblem_intense);
        Perfume p27 = new Perfume(27, "Prada", "L'Homme'", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Business", "Fresh", R.drawable.prada_l_homme);
        Perfume p28 = new Perfume(28, "Prada", "L'Homme Intense'", new String[]{"fall", "winter"}, new Range(0, 29), "Business", "Sweet", R.drawable.prada_l_homme_intense);
        Perfume p29 = new Perfume(29, "Prada", "Amber Pour Homme", new String[]{"fall", "winter"}, new Range(30, 120), "Business", "Fearless", R.drawable.jean_paul_gaultier_le_beau);
        Perfume p30 = new Perfume(30, "Prada", "Luna Rossa Carbon", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Athlete", "Fresh", R.drawable.prada_luna_rossa_carbon);
        Perfume p31 = new Perfume(31, "Bvlgari", "Man Wood", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Business", "Fearless", R.drawable.bvlgari_man_wood);
        Perfume p32 = new Perfume(32, "Bvlgari", "Man in Black", new String[]{"fall", "winter"}, new Range(30, 120), "Business", "Fearless", R.drawable.bvlgari_man_in_black);
        Perfume p33 = new Perfume(33, "Bvlgari", "Aqva Marine Pour Homme", new String[]{"spring", "summer"}, new Range(0, 29), "Traveler", "Fresh", R.drawable.bvlgari_aqva_marine_pour_homme);
        Perfume p34 = new Perfume(34, "Bvlgari", "Aqva Pour Homme Atlantique", new String[]{"spring", "summer"}, new Range(30, 120), "Traveler", "Fresh", R.drawable.bvlgari_aqva_pour_homme_atlantique);
        Perfume p35 = new Perfume(35, "Versace", "Dylan Blue", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Student", "Fresh", R.drawable.versace_dylan_blue);
        Perfume p36 = new Perfume(36, "Versace", "Pour Homme", new String[]{"spring", "summer"}, new Range(0, 29), "Athlete", "Fresh", R.drawable.versace_pour_homme);
        Perfume p37 = new Perfume(37, "Versace", "Eros", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Student", "Sweet", R.drawable.versace_eros);
        Perfume p38 = new Perfume(38, "Versace", "Oud Noir", new String[]{"fall", "winter"}, new Range(30, 120), "Traveler", "Fearless", R.drawable.versace_oud_noir);
        Perfume p39 = new Perfume(39, "Hermes", "Terre d'Hermes", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Business", "Fearless", R.drawable.hermes_terre_d_hermes);
        Perfume p40 = new Perfume(40, "Thierry Mugler", "A*Men", new String[]{"fall", "winter", "spring", "summer"}, new Range(0, 29), "Traveler", "Fresh", R.drawable.thierry_mugler_a_men);
        Perfume p41 = new Perfume(41, "Thierry Mugler", "A*Men Pure Malt", new String[]{"fall", "winter"}, new Range(0, 29), "Traveler", "Fearless", R.drawable.thierry_mugler_a_men_pure_malt);
        Perfume p42 = new Perfume(42, "Thierry Mugler", "A*Men Pure Havane", new String[]{"fall", "winter"}, new Range(30, 120), "Traveler", "Fearless", R.drawable.thierry_mugler_a_men_pure_havanne);
        Perfume p43 = new Perfume(43, "Paco Rabanne", "Invictus", new String[]{"spring", "summer"}, new Range(0, 29), "Student", "Sweet", R.drawable.paco_rabanne_invictus);
        Perfume p44 = new Perfume(44, "Paco Rabanne", "Pure XS", new String[]{"fall", "winter"}, new Range(0, 29), "Athlete", "Sweet", R.drawable.paco_rabanne_pure_xs);
        Perfume p45 = new Perfume(45, "John Varvatos", "Artisan", new String[]{"spring", "summer"}, new Range(30, 120), "Traveler", "Fresh", R.drawable.john_varvatos_artisan);
        Perfume p46 = new Perfume(46, "John Varvatos", "Artisan Pure", new String[]{"spring", "summer"}, new Range(0, 29), "Athlete", "Fresh", R.drawable.john_varvatos_artisan_pure);
        Perfume p47 = new Perfume(47, "Salvatore Ferregamo", "F by Ferregamo Black", new String[]{"fall", "winter", "spring", "summer"}, new Range(30, 120), "Business", "Fresh", R.drawable.salvatore_ferregamo_f_by_ferregamo_black);
        Perfume p48 = new Perfume(48, "Nautica", "Voyage", new String[]{"spring", "summer"}, new Range(0, 29), "Student", "Fresh", R.drawable.nautica_voyage);
        Perfume p49 = new Perfume(49, "Azzaro", "Wanted By Night", new String[]{"fall", "winter"}, new Range(30, 120), "Traveler", "Fearless", R.drawable.azzaro_wanted_by_night);
        Perfume p50 = new Perfume(50, "Azzaro", "Chrome", new String[]{"spring", "summer"}, new Range(0, 29), "Traveler", "Fresh", R.drawable.azzaro_chrome);
        Perfume p51 = new Perfume(51, "Azzaro", "Pour Homme Intense", new String[]{"fall", "winter"}, new Range(30, 120), "Athlete", "Sweet", R.drawable.azzaro_pour_homme_intense);
        Perfume p52 = new Perfume(52, "Calvin Klein", "CK One Shock For Him", new String[]{"fall", "winter"}, new Range(0, 29), "Student", "Sweet", R.drawable.calvin_klein_ck_shock_for_him);


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
        perfumes.add(p12);
        perfumes.add(p13);
        perfumes.add(p14);
        perfumes.add(p15);
        perfumes.add(p16);
        perfumes.add(p17);
        perfumes.add(p18);
        perfumes.add(p19);
        perfumes.add(p20);
        perfumes.add(p21);
        perfumes.add(p22);
        perfumes.add(p23);
        perfumes.add(p24);
        perfumes.add(p25);
        perfumes.add(p26);
        perfumes.add(p27);
        perfumes.add(p28);
        perfumes.add(p29);
        perfumes.add(p30);
        perfumes.add(p31);
        perfumes.add(p32);
        perfumes.add(p33);
        perfumes.add(p34);
        perfumes.add(p35);
        perfumes.add(p36);
        perfumes.add(p37);
        perfumes.add(p38);
        perfumes.add(p39);
        perfumes.add(p40);
        perfumes.add(p41);
        perfumes.add(p42);
        perfumes.add(p43);
        perfumes.add(p44);
        perfumes.add(p45);
        perfumes.add(p46);
        perfumes.add(p47);
        perfumes.add(p48);
        perfumes.add(p49);
        perfumes.add(p50);
        perfumes.add(p51);
        perfumes.add(p52);

    }

    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private boolean isInside(View v, MotionEvent e) {
        return !(e.getX() < 0 || e.getY() < 0 || e.getX() > v.getMeasuredWidth() || e.getY() > v.getMeasuredHeight());
    }
}