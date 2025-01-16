package com.example.cookingclassapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

public class Feedback extends Fragment {
    RatingBar ratingBar;
    EditText editText;
    CheckBox checkBox;
    Button submitBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        submitBtn = view.findViewById(R.id.feedback_submitBtn);
        ratingBar = view.findViewById(R.id.feedback_ratingBar);
        editText = view.findViewById(R.id.feedback_notes);
        checkBox = view.findViewById(R.id.feedback_communityCheck);
        submitBtn.setOnClickListener(v -> submitFeedback());

        return view;
    }

    void submitFeedback() {
        if(getActivity() != null){
            ratingBar.setRating(4);
            editText.setText("");
            checkBox.setChecked(false);

            MainActivity main = (MainActivity) getActivity();
            main.startHomeFragment();
        }
    }
}