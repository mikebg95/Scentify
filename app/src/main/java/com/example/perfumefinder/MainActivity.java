package com.example.perfumefinder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    ArrayList<Perfume> perfumes;
    private final static String TAG = "GAMEPLAY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perfumes = new ArrayList<>();
        addPerfumes();

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