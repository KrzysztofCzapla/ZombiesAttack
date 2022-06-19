package com.example.zombiesattack;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Zombie extends GameObject{

    public String direction;
    public int v = 5;
    public int health = 10;
    public int maxHealth = 10;

    public Zombie(float x, float y, int width, int height, String direction, Bitmap bm) {
        super(x, y, width, height);
        this.direction = direction;
        this.bm = bm;
    }
    public void draw(Canvas canvas){
        update();

        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(30,100,20));


        canvas.drawBitmap(this.bm,this.getX(),this.getY(),null);
        if(health>0) {
            canvas.drawRect(this.x, this.y - 20, this.x + this.getWidth() * health / maxHealth, this.y - 5, myPaint);
        }
    }

    public void update() {
        if(direction.equals("left")){
            this.x -= this.v;
        }
        if(direction.equals("right")){
            this.x += this.v;
        }
    }
}
