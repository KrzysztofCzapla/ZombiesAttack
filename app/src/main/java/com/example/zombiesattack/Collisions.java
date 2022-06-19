package com.example.zombiesattack;

public class Collisions {

    public static boolean checkCollision(GameObject object1, GameObject object2){

        if(object1.x >= object2.x && object1.x <= object2.x+object2.getWidth()
                &&
           object1.y >= object2.y && object1.y <= object2.y+object2.getHeight()){
            return true;
        }
        if(object2.x >= object1.x && object2.x <= object1.x+object1.getWidth()
                &&
                object2.y >= object1.y && object2.y <= object1.y+object1.getHeight()){
            return true;
        }

        return false;
    }
}
