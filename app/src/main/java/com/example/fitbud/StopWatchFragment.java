package com.example.fitbud;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitbud.Model.ExerciseClass;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class StopWatchFragment extends Fragment {

    private Integer seconds = 0;
    private Boolean running = false;
    private Boolean wasRunning = false;
    private Button startStopwatch;
    private Activity activity;
    private TextView timer;
    private Boolean breakTime = false;
    private Integer breakSeconds = 2;
    private ArrayList<ExerciseClass> exerciseClassArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        activity = getActivity();
        exerciseClassArrayList = requireArguments().getParcelableArrayList("exercises");

        timer = view.findViewById(R.id.time_view);
        startStopwatch = activity.findViewById(R.id.btn_start_workout);

        startStopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running) {
                    running = true;
                    startStopwatch.setText("Tap When Done");
                    startStopwatch.setTextSize(14);
                }
                else if(running && !breakTime){
                    breakSeconds = 45;
                    breakTime = true;
                    startStopwatch.setText("Skip Break");
                    startStopwatch.setTextSize(14);

                }
                else if(running && breakTime){
                    breakTime = false;
                    startStopwatch.setText("Tap When Done");
                    if(exerciseClassArrayList.size() > 0) {
                        exerciseClassArrayList.remove(0);
                    }
                    else {
                        Toast.makeText(getContext(), "Workout Completed", Toast.LENGTH_LONG).show();
                    }
                    activity.recreate();

                }
            }

        });
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer(timer);
        breakTime(timer);
        return  view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }


    public void onClickStop(View view){
        running = false;
    }

    private void runTimer(TextView timer){
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);

                timer.setText(time);

                if(running){
                    seconds++;
                    if(breakTime == false){
                        timer.setText(time);
                    }
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    private void breakTime(TextView timer){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int secs = breakSeconds % 60;

                String time = String.format(Locale.getDefault(),"%02d", secs );

                if(breakTime){
                    timer.setText(time);
                    breakSeconds--;
                    if(breakSeconds == 0){
                        breakTime = false;
                        startStopwatch.setText("Tap when done");
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}