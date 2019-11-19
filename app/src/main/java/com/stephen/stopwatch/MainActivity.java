package com.stephen.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnFinish;
    Chronometer chronometer;
    ImageView imageView;
    long pause;
    Animation rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnFinish = findViewById(R.id.btnFinish);
        chronometer = findViewById(R.id.chronometer);
        imageView = findViewById(R.id.image);
        rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotation);

        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                imageView.startAnimation(rotate);
                chronometer.setFormat("%s");
                chronometer.setBase(SystemClock.elapsedRealtime()-pause);
                chronometer.start();
                btnStart.setVisibility(View.INVISIBLE);
                btnFinish.setVisibility(View.VISIBLE);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                pause=0;
                rotate.cancel();
                imageView.setAnimation(rotate);
                btnStart.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);
            }
        });

    }
}
