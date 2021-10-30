package dev.farjana.guideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashAct extends AppCompatActivity {

    CircleImageView splashImageView;
    TextView appName, appDesc;
    Animation animation;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initializeAll();

        Thread td = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(7000);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                finally{
                    Intent intent = new Intent(SplashAct.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };td.start();



      /*  handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashAct.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 6000);*/


    }

    private void initializeAll() {

        splashImageView = findViewById(R.id.splash_image);
        appName = findViewById(R.id.appNameText);
        appDesc = findViewById(R.id.appDesc);
    }
}