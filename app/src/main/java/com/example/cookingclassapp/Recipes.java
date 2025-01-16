package com.example.cookingclassapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class Recipes extends Fragment {
    TextView first_title, second_title, third_title;
    ImageView first_image, second_image, third_image;
    TextView first_ingredients, second_ingredients, third_ingredients;
    TextView first_instructions, second_instructions, third_instructions;
    Button first_btn, second_btn, third_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        Recipe firstItem = new Recipe("Omelette",
                getString(R.string.omelette_image_url),
                getString(R.string.omelette_ingredients),
                getString(R.string.omelette_instructions_desc),
                10);

        Recipe secondItem = new Recipe("Tea",
                getString(R.string.tea_image_url),
                getString(R.string.tea_ingredients),
                getString(R.string.tea_instructions_desc),
                15);

        Recipe thirdItem = new Recipe("Oat Meal",
                getString(R.string.oatmeal_image_url),
                getString(R.string.oatmeal_ingredients),
                getString(R.string.oatmeal_instructions_desc),
                15);

        //FIRST ITEM
        first_title = view.findViewById(R.id.item1_title);
        first_image = view.findViewById(R.id.item1_image);
        first_ingredients = view.findViewById(R.id.item1_ingredients_desc);
        first_instructions = view.findViewById(R.id.item1_instructions_desc);
        first_btn = view.findViewById(R.id.item1_btn);

        first_title.setText(firstItem.getTitle());
        Glide.with(this).load(firstItem.getImageUrl()).into(first_image);
        first_ingredients.setText(firstItem.getIngredients());
        first_instructions.setText(firstItem.getCookingInstruction());
        first_btn.setOnClickListener(e-> submitBtn(firstItem.getTitle(), firstItem.getTime()));

        //SECOND ITEM
        second_title = view.findViewById(R.id.item2_title);
        second_image = view.findViewById(R.id.item2_image);
        second_ingredients = view.findViewById(R.id.item2_ingredients_desc);
        second_instructions = view.findViewById(R.id.item2_instructions_desc);
        second_btn = view.findViewById(R.id.item2_btn);

        second_title.setText(secondItem.getTitle());
        Glide.with(this).load(secondItem.getImageUrl()).into(second_image);
        second_ingredients.setText(secondItem.getIngredients());
        second_instructions.setText(secondItem.getCookingInstruction());
        second_btn.setOnClickListener(e-> submitBtn(secondItem.getTitle(), secondItem.getTime()));

        //THIRD ITEM
        third_title = view.findViewById(R.id.item3_title);
        third_image = view.findViewById(R.id.item3_image);
        third_ingredients = view.findViewById(R.id.item3_ingredients_desc);
        third_instructions = view.findViewById(R.id.item3_instructions_desc);third_btn = view.findViewById(R.id.item3_btn);

        third_title.setText(thirdItem.getTitle());
        Glide.with(this).load(thirdItem.getImageUrl()).into(third_image);
        third_ingredients.setText(thirdItem.getIngredients());
        third_instructions.setText(thirdItem.getCookingInstruction());
        third_btn.setOnClickListener(e-> submitBtn(thirdItem.getTitle(), thirdItem.getTime()));

        return view;
    }

    void submitBtn(String title, double num){
        if(getActivity() != null){
            MainActivity main = (MainActivity) getActivity();
            main.startTimer(title, num);
        }
    }

}