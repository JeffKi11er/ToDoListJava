package com.company.data;

import javax.swing.*;
import java.awt.*;

public class ButtonScheduleItems extends JButton{
    private int index = 0;
    private int count = 0;
    private int x;
    private int y;
    public ButtonScheduleItems(int x, int y) {
        this.x = x;
        this.y = y;
    }
    private Image[]img = {
            new ImageIcon(getClass().getResource("/res/button_schedule.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/button_schedule_clicked.png")).getImage()
    };
    private Image image;

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
