package com.example.zombiesattack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.FileObserver;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static RelativeLayout rl_HUD,rl_gameover;
    public static ImageButton leftBtn, rightBtn, actionBtn, speed, reload, damage;
    public static Button btnStart;
    public static TextView testtext,txt_gameover1,txt_gameover2;
    private GameView gv;

    public static String curBtnLeft = "none";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_main);
        gv = findViewById(R.id.gv);
        leftBtn = findViewById(R.id.LeftButton);
        rightBtn = findViewById(R.id.RightBtn);
        actionBtn = findViewById(R.id.ActionBtn);
        speed = findViewById(R.id.speed);
        reload = findViewById(R.id.reload);
        damage = findViewById(R.id.damage);
        testtext = findViewById(R.id.textView);
        rl_HUD = findViewById(R.id.rl_HUD);
        rl_gameover = findViewById(R.id.rl_gameover);
        btnStart = findViewById(R.id.btn_start);
        txt_gameover1 = findViewById(R.id.txt_game_over1);
        txt_gameover2 = findViewById(R.id.txt_game_over2);



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.start = true;
                gv.reset();
                rl_HUD.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);
            }
        });
        rl_gameover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setVisibility(View.VISIBLE);
                rl_gameover.setVisibility(View.INVISIBLE);
            }
        });

        speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gv.upgradeSystem.speedLevel<4 && gv.upgradeSystem.coinNumber>0){
                    gv.upgradeSystem.speedLevel++;
                    gv.upgradeSystem.coinNumber--;
                }

            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gv.upgradeSystem.reloadLevel<4 && gv.upgradeSystem.coinNumber>0){
                gv.upgradeSystem.reloadLevel++;
                    gv.upgradeSystem.coinNumber--;
            }}
        });
        damage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gv.upgradeSystem.damageLevel<4 && gv.upgradeSystem.coinNumber>0){
                gv.upgradeSystem.damageLevel++;
                    gv.upgradeSystem.coinNumber--;
            }}
        });







        leftBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        gv.player.direction = "left";
                        gv.player.walkingleft = true;


                    }else if(event.getAction() == MotionEvent.ACTION_UP){
                    gv.player.walkingleft = false;
                }





                return false;
            }
        });

        rightBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    gv.player.direction = "right";
                    gv.player.walkingright = true;

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    gv.player.walkingright = false;
                }

                return false;
            }
        });
        actionBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    gv.player.shoots = true;

                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    gv.player.shoots = false;
                }

                return false;
            }
        });







    }
}