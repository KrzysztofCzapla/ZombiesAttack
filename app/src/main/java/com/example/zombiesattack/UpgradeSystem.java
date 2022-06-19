package com.example.zombiesattack;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class UpgradeSystem {
    public int x;
    public int y;
    public int width;
    public int height;
    public int spacing;


    public int speedLevel = 0;
    public int reloadLevel = 0;
    public int damageLevel = 0;
    public Bitmap coin;
    public int coinNumber = 0;

    public UpgradeSystem(int x, int y, int width, int height, int spacing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.spacing = spacing;
    }



    public void draw(Canvas canvas){
        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(30,10,20));
        for(int i = 0;i<speedLevel;i++){
            canvas.drawRect(this.x+30*i,this.y+10,this.x+30*i+20,this.y+20+10,myPaint);
        }
        for(int i = 0;i<reloadLevel;i++){
            canvas.drawRect(this.x+30*i,this.y+230,this.x+30*i+20,this.y+20+230,myPaint);
        }
        for(int i = 0;i<damageLevel;i++){
            canvas.drawRect(this.x+30*i,this.y+440,this.x+30*i+20,this.y+20+440,myPaint);
        }

        for(int i = 0; i<coinNumber;i++) {
            canvas.drawBitmap(this.coin, 20+50*i, 400, null);
        }










    }


}
