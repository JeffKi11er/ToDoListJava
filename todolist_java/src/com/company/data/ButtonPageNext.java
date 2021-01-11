package com.company.data;

import javax.swing.*;
import java.awt.*;

public class ButtonPageNext extends Chosen {
    private int index = 0;
    private String previous;
    private String click;
    private int count = 0;
    public ButtonPageNext(int x, int y) {
        super(x, y);
    }

    protected Image[]img = {
            new ImageIcon(getClass().getResource("/res/button_next.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/button_next_click.png")).getImage()
    };
    private Image image;
    @Override
    public void draw(Graphics2D g2d) {
        image = img[index];
        g2d.drawImage(image,x,y,null);
    }
    public void move() {
        count++;
        if (count>=2){
            index++;
            if (index>=img.length){
                index=0;
            }
            count=0;
        }
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }
}
