package com.company.data;

import javax.swing.*;
import java.awt.*;

public class ButtonScheduleItems extends Chosen{
    private int index = 0;
    private int count = 0;
    public ButtonScheduleItems(int x, int y) {
        super(x, y);
    }
    private Image[]img = {
            new ImageIcon(getClass().getResource("/res/button_schedule.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/button_schedule_clicked.png")).getImage()
    };
    private Image image;

    @Override
    public void draw(Graphics2D g2d) {
        image = img[index];
        g2d.drawImage(image,x,y,null);
    }
    public void move(){
        count++;
        if (count>=2){
            index++;
            if (index>=img.length){
                index=0;
            }
            count=0;
        }
    }
}
