package com.example.cookingclassapp;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Home homeFragment = new Home();
    Recipes recipesFragment = new Recipes();
    Timer timerFragment = new Timer();
    Feedback feedbackFragment = new Feedback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, homeFragment, "homeFragment")
                .add(R.id.container, recipesFragment, "recipesFragment")
                .add(R.id.container, timerFragment, "timerFragment")
                .add(R.id.container, feedbackFragment, "feedbackFragment")
                .hide(recipesFragment)
                .hide(timerFragment)
                .hide(feedbackFragment)
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.home){
                getSupportFragmentManager().beginTransaction()
                        .hide(recipesFragment)
                        .hide(timerFragment)
                        .hide(feedbackFragment)
                        .show(homeFragment)
                        .commit();
                return true;
            }else if(id == R.id.recipes){
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(timerFragment)
                        .hide(feedbackFragment)
                        .show(recipesFragment)
                        .commit();
                return true;
            }else if(id == R.id.timer){
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(recipesFragment)
                        .hide(feedbackFragment)
                        .show(timerFragment)
                        .commit();
                return true;
            }else if(id == R.id.feedback){
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(recipesFragment)
                        .hide(timerFragment)
                        .show(feedbackFragment)
                        .commit();
                return true;
            }else{
                return false;
            }
        });
    }
    public void startHomeFragment(){
        bottomNavigationView.setSelectedItemId(R.id.home);
        getSupportFragmentManager().beginTransaction()
                .hide(recipesFragment)
                .hide(timerFragment)
                .hide(feedbackFragment)
                .show(homeFragment)
                .commit();
    }
    public void startTimer(String title, double num){
        Bundle bundle = new Bundle();
        bundle.putString("name", title);
        bundle.putDouble("time", num);

        getSupportFragmentManager().beginTransaction().remove(timerFragment).commit();

        timerFragment = new Timer();
        getSupportFragmentManager().beginTransaction().add(R.id.container, timerFragment, "timerFragment").commit();
        timerFragment.setArguments(bundle);

        bottomNavigationView.setSelectedItemId(R.id.timer);

        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(recipesFragment)
                .hide(feedbackFragment)
                .show(timerFragment)
                .commit();
    }
}