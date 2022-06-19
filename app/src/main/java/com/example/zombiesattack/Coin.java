package com.example.zombiesattack;

import android.graphics.Canvas;

public class Coin extends GameObject{
    public Coin(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(this.bm,this.x,this.y,null);
    }
}
