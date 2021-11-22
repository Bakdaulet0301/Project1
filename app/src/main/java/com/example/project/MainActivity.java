package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class MainActivity extends AppCompatActivity {

    TextView wel,my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wel=findViewById(R.id.textview1);
        my=findViewById(R.id.textview2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,Login_Activity.class);
                startActivity(i);
                finish();
            }
        },2000);
        Animation myanimation=  AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation2);
        wel.startAnimation( myanimation);

        Animation myanimation2=  AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation1);
        my.startAnimation( myanimation2);
    }
}