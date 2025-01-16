package com.example.cookingclassapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class Timer extends Fragment {
    String dishTitle;
    double dishTimeInMinutes;
    TextView header, timerDisplay;
    Button startBtn, pauseBtn, restartBtn;
    long timeLeft, totalTime;
    ProgressBar progressBar;
    SeekBar seekBar;
    boolean isRunning = false;
    long dishTimeInMilli;

    private CountDownTimer countDownTimer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        seekBar = view.findViewById(R.id.timer_seekBar);
        seekBar.setEnabled(false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            dishTitle = bundle.getString("name");
            dishTimeInMinutes = bundle.getDouble("time");

            header = view.findViewById(R.id.timer_header);
            String finalHeader = dishTitle + "\n" + dishTimeInMinutes + " Minutes";
            header.setText(finalHeader);
            timerDisplay = view.findViewById(R.id.timer_remaining_time);
            progressBar = view.findViewById(R.id.timer_progressBar);

            dishTimeInMilli = (long) (dishTimeInMinutes * 60 * 1000);
            totalTime = dishTimeInMilli;
            timeLeft = totalTime;

            seekBar.setEnabled(true);
            seekBar.setMax((int) dishTimeInMinutes * 60);
            seekBar.setProgress(0);

            progressBar.setMax((int) dishTimeInMinutes * 60);
            progressBar.setProgress(0);

            startBtn = view.findViewById(R.id.timer_startBtn);
            pauseBtn = view.findViewById(R.id.timer_pauseBtn);
            restartBtn = view.findViewById(R.id.timer_restartBtn);

            startBtn.setEnabled(true);
            startBtn.setOnClickListener(e-> startTimer());
            pauseBtn.setOnClickListener(e-> pauseTime());
            restartBtn.setOnClickListener(e-> restartTime());

            updateTimerDisplay();

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    totalTime = (long) progress * 1000;
                    timeLeft = dishTimeInMilli - totalTime;
                    if(isRunning){
                        countDownTimer.cancel();
                        startTimer();
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        return view;
    }

    private void startTimer(){
        startBtn.setEnabled(false);
        pauseBtn.setEnabled(true);
        restartBtn.setEnabled(true);
        isRunning = true;

        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long timeFinished) {
                timeLeft = timeFinished;
                updateTimerDisplay();
                updateProgressBar();
                updateSeekBar();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                isRunning = false;
                startBtn.setEnabled(false);
                pauseBtn.setEnabled(false);
                restartBtn.setEnabled(true);
                progressBar.setProgress(0);
                seekBar.setProgress(0);
            }
        }.start();
    }
    private void updateTimerDisplay() {
        int seconds = (int) (timeLeft / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        String timeInString = minutes + ":" + seconds;
        timerDisplay.setText(timeInString);
    }
    private void pauseTime(){
        isRunning = false;
        countDownTimer.cancel();
        pauseBtn.setText(R.string.resume);
        pauseBtn.setOnClickListener(e->resumeTime());
    }

    public void resumeTime(){
        isRunning = true;
        countDownTimer.start();
        pauseBtn.setText(R.string.pause);
        pauseBtn.setOnClickListener(e->pauseTime());
    }

    private void restartTime(){
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
        timeLeft = dishTimeInMilli;
        progressBar.setProgress(0);
        seekBar.setProgress(0);
        if(!isRunning){
            startBtn.setEnabled(true);
            pauseBtn.setEnabled(false);
            restartBtn.setEnabled(false);
        }
        pauseBtn.setText(R.string.pause);
        pauseBtn.setOnClickListener(e->pauseTime());
        updateTimerDisplay();
    }
    private void updateProgressBar(){
        progressBar.setProgress((int) ((dishTimeInMilli - timeLeft) / 1000));
    }

    private void updateSeekBar(){
        seekBar.setProgress((int) ((dishTimeInMilli - timeLeft) / 1000));
    }

}