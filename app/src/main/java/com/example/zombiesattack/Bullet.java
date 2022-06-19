package com.example.zombiesattack;

import android.graphics.Canvas;

public class Bullet extends GameObject{
        public String direction;
        public int v = 50;
        public boolean shouldDelete = false;
    public Bullet(float x, float y, int width, int height, String direction) {
        super(x, y, width, height);
        this.direction = direction;

    }

    public void update() {
        if (direction != null) {


        if (direction.equals("left")) {
            this.x -= this.v;
        }
        if (direction.equals("right")) {
            this.x += this.v;
        }}
    }
    public void draw(Canvas canvas){
        update();



        canvas.drawBitmap(this.bm,this.getX(),this.getY(),null);
    }

}
