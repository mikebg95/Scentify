package com.example.perfumefinder;

import android.media.Image;
import android.util.Range;

import java.io.Serializable;
import java.util.stream.IntStream;

public class Perfume implements Serializable {
    private String name;
    private String[] seasons;
    private Range ageRange;
    private int imageId;

    public Perfume(String name, String[] seasons, Range ageRange, int imageId) {
        this.name = name;
        this.seasons = seasons;
        this.ageRange = ageRange;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }
}
