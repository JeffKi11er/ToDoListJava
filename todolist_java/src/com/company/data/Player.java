package com.company.data;

import com.company.ui.ToFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player extends Chosen {
    private int index = 0;
    private int count = 0;
    public static final  int LEFT = 2;
    public static final  int RIGHT = 3;
    private Image[] imgLeft={
            new ImageIcon(getClass().getResource("/res/tile004.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile005.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile006.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile007.png")).getImage(),
    };
    private Image[]imgRight={
            new ImageIcon(getClass().getResource("/res/tile008.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile009.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile010.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile011.png")).getImage(),
    };
    private Image[]imgUp={
            new ImageIcon(getClass().getResource("/res/tile012.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile013.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile014.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile015.png")).getImage(),
    };
    private Image[]imgDown={
            new ImageIcon(getClass().getResource("/res/tile000.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile001.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile002.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/tile003.png")).getImage(),
    };
    private Image[][]images = {imgLeft, imgRight,imgUp, imgDown};
    public Player(int x, int y) {
        super(x, y);
        orient = DOWN;
    }
    protected Image img;
    public void changeOrient(int newOrient){
        orient = newOrient;
    }
    @Override
    public void draw(Graphics2D g2d) {
        img = images[orient][index];
        g2d.drawImage(img, x, y, null);
    }
    @Override
    public void move(int y_max,int y_min,int x_min, int x_max) {
        int xR = x;
        count++;
        if (count >= 4){
            index++;
            if (index >= images[orient].length){
                index = 0;
            }
            count = 0;
        }
        switch (orient) {
            case UP:
                x -= 1;
                break;
            case DOWN:
                x += 1;
                break;
            case LEFT:
                y -= 0;
                break;
            case RIGHT:
                y += 0;
                break;
        }
        if (x>= x_max||x<=x_min){
            x = xR;
        }
    }
}
