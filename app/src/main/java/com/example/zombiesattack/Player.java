package com.example.zombiesattack;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Player extends GameObject{


    public String direction; // "left" or "right"



    public Bitmap basicbm;
    public boolean walkingleft;
    public boolean walkingright;
    public float v = 10;
    public boolean shoots;
    public int shootcount = 0;
    public int shootcountmax = 20;
    public Bitmap hearth;
    public int healthPoints = 3;

    public int damage = 0;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        direction = "right";


    }
    public void draw(Canvas canvas, UpgradeSystem upgradeSystem){
        update(upgradeSystem);




        canvas.drawBitmap(this.bm,this.getX(),this.getY(),null);
        for (int i = 0;i<healthPoints;i++){
            canvas.drawBitmap(this.hearth,20+i*190,20,null);
       }
    }





    public void setBasicbm(Bitmap basicbm) {
        this.basicbm = basicbm;
    }

    public void update(UpgradeSystem upgradeSystem){

        this.v = upgradeSystem.speedLevel*3+20;
        this.shootcountmax = 20-upgradeSystem.reloadLevel*2;
        this.damage = upgradeSystem.damageLevel/4;



        if(shootcount>0){
            shootcount-=1;
        }
        if(shoots && shootcount==0){
            shootcount = shootcountmax;
            createBullet();
        }
        if(direction.equals("right")){
            if(walkingright&& this.x<=Constants.SCREEN_WIDTH){
                this.x += this.v;
            }

            Matrix matrix = new Matrix();


            this.bm = Bitmap.createBitmap(this.basicbm,0,0,this.basicbm.getWidth(),this.basicbm.getHeight(),matrix,true);
        }
        if(direction.equals("left")){
            if(walkingleft && this.x>=0){
                this.x -= this.v;
            }
            Matrix matrix = new Matrix();
            matrix.preScale(-1.0f,1.0f);

            this.bm = Bitmap.createBitmap(this.basicbm,0,0,this.basicbm.getWidth(),this.basicbm.getHeight(),matrix,true);

        }
    }

    public void createBullet(){
        GameView.bullets.add(new Bullet(this.x+this.getWidth()*2/3,this.getY()+this.getHeight()*1/2,1000,20,this.direction));
    }



}
