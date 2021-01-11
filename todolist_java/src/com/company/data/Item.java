package com.company.data;

import com.company.ui.ToFrame;

import java.awt.*;
import java.util.Date;

public class Item {
    private String description;
    private String date;
    private boolean done;
    private int x;
    private int y;
    private int intersect;
    public Item(String description, String date, boolean done) {
        this.description = description;
        this.date = date;
        this.done = done;
    }

    public int getIntersect() {
        return intersect;
    }

    public void setIntersect(int intersect) {
        this.intersect = intersect;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font(null,Font.BOLD,15));
    }

    @Override
    public String toString() {
        return getDescription()+"-"+getDate()+"-"+isDone()+"\n";
    }
}
