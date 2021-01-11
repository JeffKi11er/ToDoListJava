package com.company.data;

import com.company.ui.ToFrame;

import java.awt.*;
import java.util.ArrayList;

public class Chosen {
    public static final int UP = 0;
    public static final int DOWN = 1;

    protected int x;
    protected int y;
    protected int orient;
    protected int speed = 30;
    public Chosen(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics2D g2d){
        g2d.fillRect(x-10,y, ToFrame.W-40,3);
    }
    public void move(ArrayList<Item>items){
        int xR = x;
        int yR = y;
        switch (orient){
            case UP:
                y-=1;
                break;
            case DOWN:
                y+=1;
                break;
        }
        boolean check = checkLimit(items);
        if (check == false){
            x = xR;
            y = yR;
        }
    }

    private boolean checkLimit(ArrayList<Item>items) {
        return true; 
    }
    public void changeOrient(int newOrient){
        orient = newOrient;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
