package com.example.onlineshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Animatable;

public class SplashScreen extends AppCompatActivity {

    //variable
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo;

        /** Duration of wait **/


        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.splash_screen);

            //animation
            topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
            bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

            //Hooks
            image=findViewById(R.id.app_logo);
            logo=findViewById(R.id.app_slogan);

            image.setAnimation(topAnim);
            logo.setAnimation(bottomAnim);




            Thread myThread =new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
        }
    }

