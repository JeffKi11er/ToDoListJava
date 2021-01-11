package com.company.ui;

import com.company.data.*;
import com.sun.jdi.event.ExceptionEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ToPanel extends javax.swing.JPanel implements KeyListener, Runnable, Theme, ActionListener {
    private JTextField tfInput;
    private JButton btnTheme;
    private JButton btnKeypad;
//    private ToButton btnRemove;
    private JLabel jImage;
    private DataManager manager;
    private ArrayList<Item> items = new ArrayList<>();
    private boolean[] flag = new boolean[256];
    private int label = 0;
    private int mode;
    private int page;
    private JLabel jChrc;
    private Background background;
    private String path = "F:/task/task.txt";
    int i;
    int x_c;
    int y_c;
    int ch;
    int test;
    public ToPanel() {
        setLayout(null);
        mode = 0;
        page = 0;
        ch = 1;
        test = 0;
        initComps();
        load();
        Thread t = new Thread(this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setFocusTraversalKeysEnabled(false);
    }
    private void memes(int kind) {
        switch (kind) {
            case 1:
                Random stress = new Random();
                int rotate1 = 1 + stress.nextInt(4);
                switch (rotate1) {
                    case 1:
                        Icon icon1 = new ImageIcon(getClass().getResource("/res/stress.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon1);
                        break;
                    case 2:
                        Icon icon2 = new ImageIcon(getClass().getResource("/res/stress_2.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon2);
                        break;
                    case 3:
                        Icon icon3 = new ImageIcon(getClass().getResource("/res/stress_3.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon3);
                        break;
                    case 4:
                        Icon icon4 = new ImageIcon(getClass().getResource("/res/stress_4.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Moving On!", JOptionPane.INFORMATION_MESSAGE, icon4);
                        break;
                }
                break;
            case 2:
                Random kill = new Random();
                int rotate2 = 1 + kill.nextInt(2);
                switch (rotate2) {
                    case 1:
                        Icon iconk1 = new ImageIcon(getClass().getResource("/res/kill_it.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Killing task!", JOptionPane.INFORMATION_MESSAGE, iconk1);
                        break;
                    case 2:
                        Icon iconk2 = new ImageIcon(getClass().getResource("/res/kill_it_2.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Killing task!", JOptionPane.INFORMATION_MESSAGE, iconk2);
                        break;
                }
                break;
            case 3:
                Random cheers = new Random();
                int rotate3 = 1 + cheers.nextInt(5);
                switch (rotate3) {
                    case 1:
                        Icon iconC1 = new ImageIcon(getClass().getResource("/res/cheer_1.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Cheers!", JOptionPane.INFORMATION_MESSAGE, iconC1);
                        break;
                    case 2:
                        Icon iconC2 = new ImageIcon(getClass().getResource("/res/cheer_2.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Congratulation!", JOptionPane.INFORMATION_MESSAGE, iconC2);
                        break;
                    case 3:
                        Icon iconC3 = new ImageIcon(getClass().getResource("/res/cheer_3.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Let's dance!", JOptionPane.INFORMATION_MESSAGE, iconC3);
                        break;
                    case 4:
                        Icon iconC4 = new ImageIcon(getClass().getResource("/res/cheer_4.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "That's nothing!", JOptionPane.INFORMATION_MESSAGE, iconC4);
                        break;
                    case 5:
                        Icon iconC5 = new ImageIcon(getClass().getResource("/res/laugh.gif"));
                        JOptionPane.showMessageDialog(getComponentPopupMenu(), "", "Who laughing now?", JOptionPane.INFORMATION_MESSAGE, iconC5);
                        break;
                }
                break;
        }
    }

    private void getP(int p) {
        page = p;
    }

    private void initComps() {
        manager = new DataManager(items, 0, page);
        manager.setColorSet(this);
        //-------------------------------------
        background = new Background();
        //-------------------------------------
        btnKeypad = new JButton();
        btnKeypad.setText("Syntax");
        btnKeypad.setSize(100,30);
        btnKeypad.setLocation(ToFrame.W*1/3+20,ToFrame.H*2/3+60);
        add(btnKeypad);
        btnKeypad.addActionListener(this);
        //-------------------------------------
        btnTheme = new JButton();
        btnTheme.setText("Edit sample");
        btnTheme.setSize(100,30);
        btnTheme.setLocation(20,ToFrame.H*2/3+60);
        add(btnTheme);
        btnTheme.addActionListener(this);
        //-------------------------------------
        tfInput = new JTextField();
        tfInput.setSize(ToFrame.W - 40, 25);
        int y = 10 + 80 + 10;
        tfInput.setLocation(10, y);
        tfInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                flag[keyEvent.getKeyCode()] = true;
                if (flag[KeyEvent.VK_UP]) {
                    manager.moveChosen(Chosen.UP);
                } else if (flag[KeyEvent.VK_DOWN]) {
                    manager.moveChosen(Chosen.DOWN);
                } else if (flag[KeyEvent.VK_ENTER]) {
                    String task = tfInput.getText();
                    if (task.isEmpty()) {
//                        Icon icon = new ImageIcon(getClass().getResource("/res/laugh.gif"));
//                        JOptionPane.showMessageDialog(getComponentPopupMenu(),"","Cheers!",JOptionPane.INFORMATION_MESSAGE,icon);
                        return;
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    Item item = new Item(task, simpleDateFormat.format(date), false);
                    item.setX(x_c);
                    item.setY(y_c);
                    items.add(item);
                    tfInput.setText("");
                    if (items.size() > 8 * ch) {
                        ++page;
                        ++ch;
                    }
                    manager.setItems(items, mode, page);
                    int o = items.size();
                    if (o%4==0){
                        memes(1);
                    }
                } else if (flag[KeyEvent.VK_SPACE]) {
                    for (Item item : items) {
                        System.out.println(item.toString());
                    }
                } else if (flag[KeyEvent.VK_NUMPAD1]) {
                    for (int i = page * 8; i < items.size(); i++) {
                        if (i < 8 * (page + 1)) {
                            if (checkIntersects(items.get(i), manager.getChosen())&&!items.get(i).isDone()) {
                                items.get(i).setDone(true);
                            }else if (checkIntersects(items.get(i), manager.getChosen())&&items.get(i).isDone()){
                                items.get(i).setDone(false);
                            }
                        }
                    }
                    manager.setItems(items, mode, page);
//                    if (manager.getDoneItems()%3==0){
//                        Random rd = new Random();
//                        int roll = 1+rd.nextInt(2);
//                        if (roll==1){
//                            memes(2);
//                        }else {
//                            memes(3);
//                        }
//                    }
                } else if (flag[KeyEvent.VK_NUMPAD9]) {
                    manager.buttonNextMove();
                    i = page + 1;
                    manager.setPage(i);
//                getP(page+1);
////                manager.setPageIncrease(true);
//                manager.btnNext();
                    page = i;
                    i = 0;
//                    if (checkDoneTaskMe()){
//                        memes(3);
//                    }
                } else if (flag[KeyEvent.VK_NUMPAD3]) {
                    manager.buttonPreviousMove();
                    if (manager.getPage() > 0) {
                        i = manager.getPage() - 1;
                        manager.setPage(i);
//                    getP(page-1);
////                    manager.setPageDecrease(true);
//                    manager.btnPre();
                    }
                    page = i;
                    i = 0;
//                    if (checkDoneTaskMe()){
//                        memes(3);
//                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                flag[keyEvent.getKeyCode()] = false;
            }
        });
        add(tfInput);
        label = 10 + tfInput.getY() + tfInput.getHeight();
        x_c = 20;
        y_c = label + 20;
        manager.initChosen(x_c, y_c - 10);
        //-------------------------------------
//        ImageIcon background = new ImageIcon("D:/javaTransfer/todolist_java/src/res/clocks.jpg");
//        jChrc = new JLabel("",background,JLabel.CENTER);
//        jChrc.setLocation(0,0);
//        jChrc.setSize(ToFrame.W,ToFrame.H);
//        jChrc.setForeground(Color.RED);
//        jChrc.setFont(new Font(null,Font.BOLD,25));
//        add(jChrc);
    }
    private boolean checkDoneTaskMe(){
        int task_Done;
        task_Done = 0;
        int siz = items.size();
        for (int j = 8*page; j < items.size(); j++) {
            if (items.get(j).isDone()&& siz<8*(page+1)){
                ++task_Done;
            }
        }
        if (task_Done%2==0){
            return true;
        }
        return false;
    }
    private void checkFile() {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                boolean created = file.createNewFile();
                System.out.println(created);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        String script = "\n";
        for (Item item : items) {
            if (!item.isDone()) {
                script += item.toString();
            }
        }
        try {
            File file = new File(path);
            if (file.exists()) {
                Files.deleteIfExists(Paths.get(path));
            }
            checkFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(script.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        memes(3);
    }

    private void load() {
        checkFile();
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] b = new byte[1024];
            int count = fileInputStream.read(b);
            StringBuilder builder = new StringBuilder();
            while (count != -1) {
                String s = new String(b, 0, count);
                builder.append(s);
                count = fileInputStream.read(b);
            }
            String t[] = builder.toString().split("\n");
//            System.out.println(t.length);
            for (int i = 0; i < t.length; i++) {
                String each[] = t[i].split("-");
                if (each.length > 1) {
                    items.add(new Item(each[0], each[1], Boolean.valueOf(each[2])));
                }
//                System.out.println(t[i]);
            }
            manager.setItems(items, mode, page);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        memes(2);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnTheme)){
            String theme[] = {"Normal", "High Light", "Computer", "Dark"};
            JComboBox comboBox = new JComboBox(theme);
            int input;
            input = JOptionPane.showConfirmDialog(getComponentPopupMenu(), comboBox, "Select theme", JOptionPane.DEFAULT_OPTION);
            if (input == JOptionPane.OK_OPTION) {
                String theme_c = (String) comboBox.getSelectedItem();
                if (theme_c.equals("Normal")) {
                    mode = 1;
                } else if (theme_c.equals("High Light")) {
                    mode = 2;
                } else if (theme_c.equals("Computer")) {
                    mode = 3;
                } else {
                    mode = 4;
                }
            }
            manager.setItems(items, mode, page);
        }else if(actionEvent.getSource().equals(btnKeypad)){
            JOptionPane.showMessageDialog(getComponentPopupMenu(),"- Use UP/DOWN to move\n"+
                    "- Use LEFT/RIGHT to arrange\n"+
                    "- Use NUMPAD1 to check Done/Not Done\n"+
                    "- Use NUMPAD3/NUMPAD9 to move previous/next pages","Guide",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        flag[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        flag[keyEvent.getKeyCode()] = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
//        int y = 0 ;
//        for (int i = 0; i < items.size(); i++) {
//            g2d.setColor(Color.BLACK);
//            g2d.setFont(new Font(null,Font.BOLD,15));
//            Item item = items.get(i);
//            g2d.drawString(item.getDescription()+" : "+item.getDate(),item.getX(),item.getY()+y);
//            y+=30;
//        }
        background.draw(g2d);
        g2d.setColor(setColorBorder());
        g2d.fillRect(10, tfInput.getHeight() + tfInput.getY() + 5, ToFrame.W - 40, ToFrame.H * 2 / 5);
        g2d.setColor(setColorForm());
        g2d.fillRect(10 + 1, tfInput.getHeight() + tfInput.getY() + 5 + 1, ToFrame.W - 40 - 2, ToFrame.H * 2 / 5 - 2);
        manager.drawChosen(g2d);
//        for (int i = 0;i< items.size();i++){
//            g2d.setColor(Color.GREEN);
//            g2d.fillRect(items.get(i).getX()-10,items.get(i).getY()-15,ToFrame.generalFieldWid,ToFrame.getGeneralFieldHei);
////            Rectangle rectangle = new Rectangle(items.get(i).getX()-10,items.get(i).getY()-15,ToFrame.generalFieldWid,ToFrame.getGeneralFieldHei);
////            Rectangle check = rectangle;
////            if (check.intersects(new Rectangle(manager.getChosen().getX(),manager.getChosen().getY(),ToFrame.generalFieldWid,ToFrame.getGeneralFieldHei))){
////                items.get(i).setIntersect(0);
////            }
//        }
        manager.drawTask(g2d);
//        buttonPrevious.draw(g2d);
//        buttonPageNext.draw(g2d);
    }

    private boolean checkIntersects(Item item, Chosen chosen) {
        Rectangle rectangle = new Rectangle(item.getX() - 10, item.getY(), ToFrame.generalFieldWid, ToFrame.getGeneralFieldHei * 1 / 3);
        Rectangle check = rectangle;
        if (check.intersects(new Rectangle(chosen.getX(), chosen.getY(), ToFrame.generalFieldWid, ToFrame.getGeneralFieldHei))) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < items.size(); i++) {
//            g2d.setColor(Color.ORANGE);
//            g2d.fillRect(items.get(i).getX()-10,items.get(i).getY()-15,ToFrame.generalFieldWid,ToFrame.getGeneralFieldHei);
//                Rectangle rectangle = new Rectangle(items.get(i).getX()-10,items.get(i).getY(),ToFrame.generalFieldWid,ToFrame.getGeneralFieldHei*1/3);
//                Rectangle check = rectangle;
                if (checkIntersects(items.get(i), manager.getChosen())) {
                    items.get(i).setIntersect(0);
                } else {
                    items.get(i).setIntersect(1);
                }
            }
            if (flag[KeyEvent.VK_UP]) {
                manager.moveChosen(Chosen.UP);
            } else if (flag[KeyEvent.VK_DOWN]) {
                manager.moveChosen(Chosen.DOWN);
            } else if (flag[KeyEvent.VK_LEFT]) {
                manager.moveChosen(Player.LEFT);
                for (int i = 0; i < items.size(); i++) {
                    if (checkIntersects(items.get(i), manager.getChosen()) && i > 0) {
                        int k = i - 1;
                        Item item1 = items.get(i);
                        Item item2 = items.get(k);
                        String desCurrent = item1.getDescription();
                        String dateCurrent = item1.getDate();
                        boolean doneCurrent = item1.isDone();
                        String desConvert = item2.getDescription();
                        String dateConvert = item2.getDate();
                        boolean doneConver = item2.isDone();
                        item1.setDescription(desConvert);
                        item1.setDate(dateConvert);
                        item1.setDone(doneConver);
                        item2.setDescription(desCurrent);
                        item2.setDate(dateCurrent);
                        item2.setDone(doneCurrent);
                    }
                }
            } else if (flag[KeyEvent.VK_RIGHT]) {
                manager.moveChosen(Player.RIGHT);
                for (int i = 0; i < items.size(); i++) {
                    if (checkIntersects(items.get(i), manager.getChosen()) && i < items.size() - 1) {
                        int k = i + 1;
                        Item item1 = items.get(i);
                        Item item2 = items.get(k);
                        String desCurrent = item1.getDescription();
                        String dateCurrent = item1.getDate();
                        boolean doneCurrent = item1.isDone();
                        String desConvert = item2.getDescription();
                        String dateConvert = item2.getDate();
                        boolean doneConver = item2.isDone();
                        item1.setDescription(desConvert);
                        item1.setDate(dateConvert);
                        item1.setDone(doneConver);
                        item2.setDescription(desCurrent);
                        item2.setDate(dateCurrent);
                        item2.setDone(doneCurrent);
                    }
                }
            }
//            else if (flag[KeyEvent.VK_NUMPAD0]){
//                String theme[]={"Normal","High Light","Computer","Dark"};
//                JComboBox comboBox = new JComboBox(theme);
//                int input;
//                input = JOptionPane.showConfirmDialog(getComponentPopupMenu(),comboBox,"Select theme",JOptionPane.DEFAULT_OPTION);
//                if (input==JOptionPane.OK_OPTION){
//                    String theme_c = (String)comboBox.getSelectedItem();
//                    if (theme_c.equals("Normal")){
//                        mode = 1;
//                    }else if (theme_c.equals("High Light")){
//                        mode = 2;
//                    }else if (theme_c.equals("Computer")){
//                        mode = 3;
//                    }else {
//                        mode = 4;
//                    }
//                }
//                manager.setItems(items,mode,page);
//            }
//            System.out.println(manager.getDoneItems());
            repaint();
            try {
                Thread.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Color setColorBorder() {
        Color border;
        switch (mode) {
            case 1:
                border = Color.RED;
                break;
            case 2:
                border = Color.BLACK;
                break;
            case 3:
                border = Color.WHITE;
                break;
            default:
                border = Color.GRAY;
                break;
        }
        return border;
    }

    @Override
    public Color setColorFont() {
        Color font;
        switch (mode) {
            case 1:
                font = Color.WHITE;
                break;
            case 2:
                font = Color.BLUE;
                break;
            case 3:
                font = Color.yellow;
                break;
            default:
                font = Color.BLACK;
                break;
        }
        return font;
    }

    @Override
    public Color setColorForm() {
        Color form;
        switch (mode) {
            case 1:
                form = Color.GRAY;
                break;
            case 2:
                form = Color.ORANGE;
                break;
            case 3:
                form = Color.BLUE;
                break;
            default:
                form = Color.WHITE;
                break;
        }
        return form;
    }

    @Override
    public Color setColorBackground() {
        Color background;
        switch (mode) {
            case 1:
                background = Color.LIGHT_GRAY;
                break;
            case 2:
                background = Color.BLUE;
                break;
            case 3 :
                background = Color.LIGHT_GRAY;
                break;
            default:
                background = null;
                break;
        }
        return background;
    }

    @Override
    public Color setColorSelected() {
        Color selected;
        switch (mode) {
            case 1:
                selected = Color.GREEN;
                break;
            case 2:
                selected = Color.WHITE;
                break;
            case 3:
                selected = Color.GREEN;
                break;
            default:
                selected = Color.BLUE;
                break;
        }
        return selected;
    }

}
