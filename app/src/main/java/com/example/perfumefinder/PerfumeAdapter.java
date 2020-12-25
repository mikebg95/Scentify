package com.example.perfumefinder;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfumeAdapter extends ArrayAdapter<Perfume> {
    private final static String TAG = "Adapter";
    private ArrayList<Perfume> perfumes;
    private Context mContext;
    int mResource;

    public PerfumeAdapter(@NonNull Context context, int resource, ArrayList<Perfume> perfumes) {
        super(context, resource, perfumes);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get perfume info
        String name = getItem(position).getName();
        String brand = getItem(position).getBrand();
        int imageId = getItem(position).getImageId();

        Perfume perfume = new Perfume(name, brand, imageId);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView lvImg = (ImageView) convertView.findViewById(R.id.lv_image);
        TextView lvName = (TextView) convertView.findViewById(R.id.lv_title);
        TextView lvBrand = (TextView) convertView.findViewById(R.id.lv_brand);

        lvImg.setImageResource(imageId);
        lvName.setText(name);
        lvBrand.setText(brand);

        return convertView;
    }
}
