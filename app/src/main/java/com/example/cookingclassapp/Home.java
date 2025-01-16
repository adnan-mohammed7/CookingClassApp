package com.example.cookingclassapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Home extends Fragment {
    ImageView firstImage, secondImage, thirdImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firstImage = view.findViewById(R.id.home_firstImage);
        secondImage = view.findViewById(R.id.home_secondImage);
        thirdImage = view.findViewById(R.id.home_thirdImage);

        Glide.with(this).load(getString(R.string.omelette_image_url)).into(firstImage);
        Glide.with(this).load(getString(R.string.oatmeal_image_url)).into(secondImage);
        Glide.with(this).load(getString(R.string.tea_image_url)).into(thirdImage);

        return view;
    }
}