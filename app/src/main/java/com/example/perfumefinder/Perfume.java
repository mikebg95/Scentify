package com.example.perfumefinder;

import android.util.Range;

import java.util.stream.IntStream;

public class Perfume {
    private String name;
    private String[] seasons;
    private Range ageRange;

    public Perfume(String name, String[] seasons, Range ageRange) {
        this.name = name;
        this.seasons = seasons;
        this.ageRange = ageRange;
    }

    public String getName() {
        return name;
    }

    public String[] getSeasons() {
        return seasons;
    }

    public Range getAgeRange() {
        return ageRange;
    }
}
