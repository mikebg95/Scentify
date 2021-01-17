package com.example.perfumefinder;

import android.media.Image;
import android.util.Range;

import java.io.Serializable;
import java.util.stream.IntStream;

public class Perfume implements Serializable {
    private int id;
    private String brand;
    private String name;
    private String[] seasons;
    private Range ageRange;
    private String lifestyle;
    private String type;
    private int iconId;
    private int imageId;

    public Perfume(int id, String brand, String name, String[] seasons, Range ageRange, String lifestyle, String type, int imageId) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.seasons = seasons;
        this.ageRange = ageRange;
        this.lifestyle = lifestyle;
        this.type = type;
        this.imageId = imageId;
    }

//    public Perfume(int id, String brand, String name, String[] seasons, Range ageRange, int iconId, int imageId) {
//        this.id = id;
//        this.brand = brand;
//        this.name = name;
//        this.seasons = seasons;
//        this.ageRange = ageRange;
//        this.iconId = iconId;
//        this.imageId = imageId;
//    }

    public Perfume(String name, String brand, int imageId) {
        this.brand = brand;
        this.name = name;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
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

    public String getLifestyle() {
        return lifestyle;
    }

    public String getType() {
        return type;
    }

    public int getImageId() {
        return imageId;
    }
}
