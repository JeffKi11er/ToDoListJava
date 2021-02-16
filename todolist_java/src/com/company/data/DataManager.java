package com.company.data;

import com.company.ui.ToFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class DataManager {
    private ArrayList<Item> items;
    private Chosen chosen;
    private Player player;
    private int mode;
    private int page;
    private boolean pageIncrease;
    private boolean pageDecrease;
    private Theme colorSet;
    private ButtonPagePrevious buttonPrevious;
    private ButtonPageNext buttonPageNext;
    private ButtonScheduleItems buttonScheduleItems;
//    private Background background;
    public void setColorSet(Theme colorSet) {
        this.colorSet = colorSet;
    }

    public void setPageIncrease(boolean pageIncrease) {
        this.pageIncrease = pageIncrease;
    }

    public void setPageDecrease(boolean pageDecrease) {
        this.pageDecrease = pageDecrease;
    }

    public int getPage() {
        return page;
    }

    private ArrayList<Rectangle> rectangleSensing;

    public void initChosen(int x, int y) {
        items = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setIntersect(1);
        }
//        background = new Background();
        chosen = new Chosen(x, y);
        player = new Player(100, 10);
        buttonPrevious = new ButtonPagePrevious(ToFrame.W * 1 / 2 + 48, ToFrame.H * 2 / 3);
        buttonPageNext = new ButtonPageNext(buttonPrevious.getX() + 50, buttonPrevious.getY());
        buttonScheduleItems = new ButtonScheduleItems(buttonPrevious.getX()-200,buttonPrevious.getY());
        //eyes = new Eyes(120,ToFrame.H*3/4);
    }

    public Chosen getChosen() {
        return chosen;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public DataManager(ArrayList<Item> items, int mode, int page) {
        this.items = items;
        this.mode = mode;
        this.page = page;
        pageIncrease = false;
        pageDecrease = false;
    }
    private void memes(int kind) {
        switch (kind) {
            case 1:
                Random stress = new Random();
                int rotate1 = 1 + stress.nextInt(4);
                switch (rotate1) {
                    case 1:
                        Icon icon1 = new ImageIcon(getClass().getResource("/res/stress.gif"));
                        JOptionPane.showMessageDialog(null, "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon1);
                        break;
                    case 2:
                        Icon icon2 = new ImageIcon(getClass().getResource("/res/stress_2.gif"));
                        JOptionPane.showMessageDialog(null, "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon2);
                        break;
                    case 3:
                        Icon icon3 = new ImageIcon(getClass().getResource("/res/stress_3.gif"));
                        JOptionPane.showMessageDialog(null, "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon3);
                        break;
                    case 4:
                        Icon icon4 = new ImageIcon(getClass().getResource("/res/stress_4.gif"));
                        JOptionPane.showMessageDialog(null, "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon4);
                        break;
                }
                break;
            case 2:
                Random kill = new Random();
                int rotate2 = 1 + kill.nextInt(2);
                switch (rotate2) {
                    case 1:
                        Icon iconk1 = new ImageIcon(getClass().getResource("/res/kill_it.gif"));
                        JOptionPane.showMessageDialog(null, "", "Killing task!", JOptionPane.INFORMATION_MESSAGE, iconk1);
                        break;
                    case 2:
                        Icon iconk2 = new ImageIcon(getClass().getResource("/res/kill_it_2.gif"));
                        JOptionPane.showMessageDialog(null, "", "Killing task!", JOptionPane.INFORMATION_MESSAGE, iconk2);
                        break;
                }
                break;
            case 3:
                Random cheers = new Random();
                int rotate3 = 1 + cheers.nextInt(5);
                switch (rotate3) {
                    case 1:
                        Icon iconC1 = new ImageIcon(getClass().getResource("/res/cheer_1.gif"));
                        JOptionPane.showMessageDialog(null, "", "Cheers!", JOptionPane.INFORMATION_MESSAGE, iconC1);
                        break;
                    case 2:
                        Icon iconC2 = new ImageIcon(getClass().getResource("/res/cheer_2.gif"));
                        JOptionPane.showMessageDialog(null, "", "Congratulation!", JOptionPane.INFORMATION_MESSAGE, iconC2);
                        break;
                    case 3:
                        Icon iconC3 = new ImageIcon(getClass().getResource("/res/cheer_3.gif"));
                        JOptionPane.showMessageDialog(null, "", "Let's dance!", JOptionPane.INFORMATION_MESSAGE, iconC3);
                        break;
                    case 4:
                        Icon iconC4 = new ImageIcon(getClass().getResource("/res/cheer_4.gif"));
                        JOptionPane.showMessageDialog(null, "", "That's nothing!", JOptionPane.INFORMATION_MESSAGE, iconC4);
                        break;
                    case 5:
                        Icon iconC5 = new ImageIcon(getClass().getResource("/res/laugh.gif"));
                        JOptionPane.showMessageDialog(null, "", "Who laughing now?", JOptionPane.INFORMATION_MESSAGE, iconC5);
                        break;
                }
                break;
        }
    }
    public void setItems(ArrayList<Item> items, int mode, int page) {
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setIntersect(1);
        }
        this.mode = mode;
        this.page = page;
    }
    public int getDoneItems(){
        int k;
        k = 0;
        for (int j = 0; j < items.size(); j++) {
            if (items.get(j).isDone()){
                ++k;
            }
        }
        return k;
    }
    public void drawChosen(Graphics2D g2D) {
//        background.draw(g2D);
        chosen.draw(g2D);
        player.draw(g2D);
        //eyes.draw(g2D);
        buttonPrevious.draw(g2D);
        buttonPageNext.draw(g2D);
        buttonScheduleItems.draw(g2D);
    }

    public void drawTask(Graphics2D g2d) {
        if (items.size()>0){
            int k;
            k = 0;
            for (int i = 8*page; i < items.size(); i++) {
                if (i<8*(page+1)){
                    Item item = items.get(i);
                    if (item.getIntersect() > 0 && colorSet != null) {
                        g2d.setColor(colorSet.setColorFont());
                    } else {
                        g2d.setColor(colorSet.setColorSelected());
                    }
                    if (!item.isDone()) {
                        g2d.setFont(new Font(null, Font.PLAIN, 15));
                    } else {
                        Font font = new Font("helvetica", Font.PLAIN, 15);
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                        Font newFont = new Font(attributes);
                        g2d.setFont(newFont);
                    }
                    int x = 20;
                    int y = 25 * k + 150;
                    item.setX(x);
                    item.setY(y);
                    String str = item.getDescription().substring(0, 25);
                    g2d.drawString(str, x, y);
                    g2d.drawString(item.getDate(), ToFrame.W * 1 / 2 + 55, y);
                    ++k;
                }

            }
        }
    }

    public void btnNext() {
        if (pageIncrease) {
            setPage(page + 1);
            pageIncrease = false;
        }
    }

    public void btnPre() {
        if (pageDecrease) {
            setPage(page + 1);
            pageDecrease = false;
        }
    }

    public void moveChosen(int newOrient) {
        chosen.move(310,145,5,250);
        player.move(310,145,5,250);
        player.changeOrient(newOrient);
        chosen.changeOrient(newOrient);
//        buttonPrevious.move();
//        buttonPageNext.move();
    }

    public void buttonNextMove() {
        buttonPageNext.move();
    }

    public void buttonPreviousMove() {
        buttonPrevious.move();
    }
    public  void buttonScheduleMove(){
        buttonScheduleItems.move();
    }

    public void setItemValue(int value, int i) {
        items.get(i).setIntersect(value);
    }
}
