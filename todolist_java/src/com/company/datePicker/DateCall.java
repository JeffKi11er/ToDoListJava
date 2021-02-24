package com.company.datePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DateCall extends JButton implements MouseListener {
    private String date;
    private int x;
    private int y;

    public DateCall(String date, int x, int y) {
        this.date = date;
        this.x = x;
        this.y = y;
        addMouseListener(this);
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.fillRect(x,y,48,48);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(String.valueOf(date),x+16,y+30);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (this.contains(p)){
            System.out.println(date);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
