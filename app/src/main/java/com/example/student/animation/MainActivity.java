package com.example.student.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView hello;


    private float startX = 0;
    private float endX = 800;
    private int DURATION = 1000;

    private ValueAnimator toRight = new ValueAnimator().setDuration(DURATION);
    private ValueAnimator toLeft = new ValueAnimator().setDuration(DURATION);

    private AnimatorSet setToRight = new AnimatorSet();
    private AnimatorSet setToLeft = new AnimatorSet();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.hello);

        startX = hello.getX();
        toRight.setFloatValues(startX,endX);
        toLeft.setFloatValues(endX,startX);

        //toRight.setInterpolator(new LinearInterpolator());
        toRight.setInterpolator(new Interpolator(){
            @Override
            public float getInterpolation(float v) {
                return v*v;
            }
        });

        toRight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                //Log.d("happy", "onAnumationUpdate: " + value);
                hello.setX(value);
            }
        });

        toLeft.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float value = (float) valueAnimator.getAnimatedValue();
                //Log.d("happy", "onAnumationUpdate: " + value);
                hello.setX(value);
            }
        });

        ObjectAnimator toTransparent = ObjectAnimator.ofFloat(hello,
                "Alpha",1,0);
        toTransparent.setDuration(1500);

        setToRight.playTogether(toRight, toTransparent);

        ObjectAnimator toOpaque = ObjectAnimator.ofFloat(hello,
                "Alpha",0,1);
        toOpaque.setDuration(1500);

        setToLeft.playTogether(toLeft,toOpaque);
    }

    public void startRight(View view) {

        toRight.setFloatValues(startX,endX);
       // toRight.start();
        setToRight.start();

    }

    public void startLeft(View view) {

        toLeft.setFloatValues(endX,startX);
        //toLeft.start();
        setToLeft.start();
    }
}
