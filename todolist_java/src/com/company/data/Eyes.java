package com.company.data;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Eyes extends Chosen{
    private Random rd = new Random();
    protected Image img;
    public Eyes(int x, int y) {
        super(x, y);
        orient = DOWN;
    }
    private Image[] arrImg = {
            new ImageIcon(getClass().getResource("/res/m0.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/m1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/m2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/m3.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/m4.png")).getImage()
    };

    @Override
    public void draw(Graphics2D g2d) {
        img = arrImg[orient];
        g2d.drawImage(img, x, y, null);
    }

    @Override
    public void move(ArrayList<Item> items) {
        generateOrient();
    }

    private void generateOrient() {
        int percent = rd.nextInt(101);
        if (percent >95){
            orient = rd.nextInt(4);
        }
    }
}
