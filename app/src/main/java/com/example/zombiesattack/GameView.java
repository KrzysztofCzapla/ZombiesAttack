package com.example.zombiesattack;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameView extends View {
    private Handler handler;
    private Runnable r;
    public int height = (Constants.SCREEN_HEIGHT-450);
    public Player player;
    public UpgradeSystem upgradeSystem;
    public Zombie zombie;

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Zombie> zombies = new ArrayList<>();
    public static ArrayList<Coin> coins = new ArrayList<>();

    public static int zombiesMaxNumber  = 2;
    public static int zombiesNumber;



    public static int wave = 0;

    public boolean start;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initPlayer();
        initUpgradeSystem();


        start = false;



        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    public void initPlayer(){
        player = new Player(Constants.SCREEN_WIDTH/2,height,50*Constants.SCREEN_WIDTH/1080,73*Constants.SCREEN_WIDTH/1080);
        player.setBm(BitmapFactory.decodeResource(this.getResources(),R.drawable.player1));
        player.setBasicbm(BitmapFactory.decodeResource(this.getResources(),R.drawable.player1));
        player.hearth = (BitmapFactory.decodeResource(this.getResources(),R.drawable.hearth));

    }

    public void initUpgradeSystem(){
        upgradeSystem = new UpgradeSystem(Constants.SCREEN_WIDTH-100*Constants.SCREEN_WIDTH/1080,100*Constants.SCREEN_WIDTH/1080,100*Constants.SCREEN_WIDTH/1080,100*Constants.SCREEN_WIDTH/1080,20*Constants.SCREEN_WIDTH/1080);

        upgradeSystem.coin = (BitmapFactory.decodeResource(this.getResources(),R.drawable.coin));


    }

    public void initZombies(int zombiesnum){
        Random ran = new Random();
        for(int i = 0;i<zombiesnum;i++) {

            if(i>zombiesnum/2) {
                zombies.add(new Zombie(-55 * i*(ran.nextInt(4)+4), height, 72*Constants.SCREEN_WIDTH/1080, 80*Constants.SCREEN_WIDTH/1080, "right", BitmapFactory.decodeResource(this.getResources(), R.drawable.zombie2)));
            }else{
                zombies.add(new Zombie(Constants.SCREEN_WIDTH+100*i*(ran.nextInt(4)+4), height, 72*Constants.SCREEN_WIDTH/1080, 80*Constants.SCREEN_WIDTH/1080, "left", BitmapFactory.decodeResource(this.getResources(), R.drawable.zombie2)));
            }

        }


    }

    public void handleZombies(Canvas canvas){

        zombiesNumber = (zombies.size()-zombiesMaxNumber*3);
        MainActivity.testtext.setText("Wave "+wave);

        List<Zombie> zombiesDead = new ArrayList<>();
        List<Bullet> bulletFound = new ArrayList<>();
        for(Zombie zombie: zombies){
            for(Bullet bullet: bullets){
                if(Collisions.checkCollision(zombie,bullet)){
                    zombie.health -= player.damage+4;
                    bulletFound.add(bullet);
                }
            }
            if(Collisions.checkCollision(player,zombie)){
                zombiesDead.add(zombie);
                player.healthPoints--;

            }
            if(zombie.health<1){
                zombiesDead.add(zombie);
                coins.add(new Coin(zombie.x,zombie.y+zombie.height-100,100,100));
            }



            zombie.draw(canvas);
        }
        zombies.removeAll(zombiesDead);
        bullets.removeAll(bulletFound);



    }


    public void handleBullets(Canvas canvas){
        List<Bullet> found = new ArrayList<>();
        for(Bullet bullet: bullets){
            bullet.bm = BitmapFactory.decodeResource(this.getResources(),R.drawable.bullet2);
            bullet.draw(canvas);

            if(bullet.x>Constants.SCREEN_WIDTH || bullet.x < 0){
                found.add(bullet);
            }
        }
        bullets.removeAll(found);
    }

    public void handleCoins(Canvas canvas){
        List<Coin> coinsFound = new ArrayList<>();
        for(Coin coin: coins){
            coin.bm = (BitmapFactory.decodeResource(this.getResources(),R.drawable.coin));
            coin.draw(canvas);
            if(Collisions.checkCollision(coin,player)){
                coinsFound.add(coin);
                upgradeSystem.coinNumber++;
            }
        }
        coins.removeAll(coinsFound);
    }

    public void draw(Canvas canvas) {
        if(start) {
            if (zombies.size() < 1) {
                wave++;
                initZombies(zombiesMaxNumber * wave);
            }
            super.draw(canvas);
            handleBullets(canvas);
            if(player.healthPoints<1){
                start = false;


                MainActivity.rl_HUD.setVisibility(INVISIBLE);
                MainActivity.rl_gameover.setVisibility(VISIBLE);
            }
            handleCoins(canvas);
            player.draw(canvas, upgradeSystem);

            handleZombies(canvas);

            upgradeSystem.draw(canvas);



        }
        handler.postDelayed(r, 10);
    }

    public void reset(){
        wave = 0;
        coins.removeAll(coins);
        initPlayer();
        initUpgradeSystem();

    }

}
