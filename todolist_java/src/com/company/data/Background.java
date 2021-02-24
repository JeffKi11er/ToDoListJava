package com.company.data;

import javax.swing.*;
import java.awt.*;

public class Background {
    private int x;
    private int y;
    private Image image = new ImageIcon(getClass().getResource("/res/white_6.jpg")).getImage();
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(image,0,0,null);
    }
}
