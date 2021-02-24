package com.company.ui;

import com.company.data.*;
import com.company.datePicker.DatePickerFrame;
import com.sun.jdi.event.ExceptionEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Visibility;
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
    private String datePicked;
    private String taskName;
    private JLabel jImage;
    private DataManager manager;
    private ArrayList<Item> items = new ArrayList<>();
    private boolean[] flag = new boolean[256];
    private int label = 0;
    private int mode;
    private int page;
    private JLabel jChrc;
    private String path = "F:/task/task.txt";
    int i;
    int x_c;
    int y_c;
    int ch;
    int test;
    private int maxCharacter = 25;

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

    public String getDatePicked() {
        return datePicked;
    }

    public void setDatePicked(String datePicked) {
        this.datePicked = datePicked;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
        btnKeypad = new JButton();
        btnKeypad.setText("Syntax");
        btnKeypad.setSize(100, 30);
        btnKeypad.setLocation(ToFrame.W * 1 / 3 + 20, ToFrame.H * 2 / 3 + 60);
        add(btnKeypad);
        btnKeypad.addActionListener(this);
        //-------------------------------------
        btnTheme = new JButton();
        btnTheme.setText("Edit sample");
        btnTheme.setSize(100, 30);
        btnTheme.setLocation(20, ToFrame.H * 2 / 3 + 60);
        add(btnTheme);
        btnTheme.addActionListener(this);
        //-------------------------------------
        tfInput = new JTextField();
        tfInput.setSize(ToFrame.W - 117, 25);
        int y = 10 + 80 + 10;
        tfInput.setLocation(85, y);
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
                        return;
                    }
                    System.out.println(task.length());
                    int le = task.length();
                    if (le < 25) {
                        char[] synchronize = new char[25];
                        for (int j = 0; j < task.length(); j++) {
                            synchronize[j] = task.charAt(j);
                        }
                        String result = String.valueOf(synchronize);
                        int count = 1;
                        while (count <= 25 - le) {
                            result = result + " ";
                            ++count;
                        }
                        task = result;
                    }
                    if (datePicked==null){
                        pickDate(task);
                    }else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        Item item = new Item(taskName, simpleDateFormat.format(date), false);
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
                        if (o % 4 == 0) {
                            memes(1);
                        }
                    }
                } else if (flag[KeyEvent.VK_SPACE]) {
                    for (Item item : items) {
                        System.out.println(item.toString());
                    }
                } else if (flag[KeyEvent.VK_NUMPAD1]) {
                    for (int i = page * 8; i < items.size(); i++) {
                        if (i < 8 * (page + 1)) {
                            if (checkIntersects(items.get(i), manager.getChosen()) && !items.get(i).isDone()) {
                                items.get(i).setDone(true);
                            } else if (checkIntersects(items.get(i), manager.getChosen()) && items.get(i).isDone()) {
                                items.get(i).setDone(false);
                            }
                        }
                    }
                    manager.setItems(items, mode, page);
                } else if (flag[KeyEvent.VK_NUMPAD9]) {
                    manager.buttonNextMove();
                    i = page + 1;
                    manager.setPage(i);
                    page = i;
                    i = 0;
                } else if (flag[KeyEvent.VK_NUMPAD3]) {
                    manager.buttonPreviousMove();
                    if (manager.getPage() > 0) {
                        i = manager.getPage() - 1;
                        manager.setPage(i);
                    }
                    page = i;
                    i = 0;
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
    }
    private void pickDate(String taskName){
        DatePickerFrame datePickerFrame = new DatePickerFrame();
        datePickerFrame.setVisible(true);
        SwingUtilities.getWindowAncestor(this).dispose();
    }
    private boolean checkDoneTaskMe() {
        int task_Done;
        task_Done = 0;
        int siz = items.size();
        for (int j = 8 * page; j < items.size(); j++) {
            if (items.get(j).isDone() && siz < 8 * (page + 1)) {
                ++task_Done;
            }
        }
        if (task_Done % 2 == 0) {
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
            for (int i = 0; i < t.length; i++) {
                String each[] = t[i].split("-");
                if (each.length > 1) {
                    items.add(new Item(each[0], each[1], Boolean.valueOf(each[2])));
                }
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
        if (actionEvent.getSource().equals(btnTheme)) {
            String theme[] = {"Bright", "Dark"};
            JComboBox comboBox = new JComboBox(theme);
            int input;
            input = JOptionPane.showConfirmDialog(getComponentPopupMenu(), comboBox, "Select theme", JOptionPane.DEFAULT_OPTION);
            if (input == JOptionPane.OK_OPTION) {
                String theme_c = (String) comboBox.getSelectedItem();
                if (theme_c.equals("Bright")) {
                    mode = 1;
                } else{
                    mode = 2;
                }
            }
            manager.setItems(items, mode, page);
        } else if (actionEvent.getSource().equals(btnKeypad)) {
            JOptionPane.showMessageDialog(getComponentPopupMenu(), "- Use UP/DOWN to move\n" +
                    "- Use LEFT/RIGHT to arrange\n" +
                    "- Use NUMPAD1 to check Done/Not Done\n" +
                    "- Use NUMPAD3/NUMPAD9 to move previous/next pages", "Guide", JOptionPane.INFORMATION_MESSAGE);
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
        manager.drawChosen(g2d);
        g2d.drawImage(imgBackground(),0,0,null);
        manager.drawEntity(g2d);
        manager.drawTask(g2d);
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
            try {
                int delay = 0;
                for (int i = 0; i < items.size(); i++) {
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
                            String desCurrent = item1.getHeap();
                            String dateCurrent = item1.getDate();
                            boolean doneCurrent = item1.isDone();
                            String desConvert = item2.getHeap();
                            String dateConvert = item2.getDate();
                            boolean doneConver = item2.isDone();
                            item1.setHeap(desConvert);
                            item1.setDate(dateConvert);
                            item1.setDone(doneConver);
                            item2.setHeap(desCurrent);
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
                            String desCurrent = item1.getHeap();
                            String dateCurrent = item1.getDate();
                            boolean doneCurrent = item1.isDone();
                            String desConvert = item2.getHeap();
                            String dateConvert = item2.getDate();
                            boolean doneConver = item2.isDone();
                            item1.setHeap(desConvert);
                            item1.setDate(dateConvert);
                            item1.setDone(doneConver);
                            item2.setHeap(desCurrent);
                            item2.setDate(dateCurrent);
                            item2.setDone(doneCurrent);
                        }
                    }
                }
                for (int j = 0; j < items.size(); j++) {
                    if (checkIntersects(items.get(j), manager.getChosen())) {
                        String newStr = items.get(j).getDescription();
                        newStr = newStr.charAt(newStr.length() - 1) + newStr.substring(0, newStr.length() - 1);
                        items.get(j).setDescription(newStr);
                        Thread.sleep(24);
                    }else {
                        String locatedMemory = items.get(j).getHeap();
                        items.get(j).setDescription(locatedMemory);
                    }
                }
                repaint();
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
                font = Color.BLACK;
                break;
            default:
                font = Color.WHITE;
                break;
        }
        return font;
    }

    @Override
    public Image imgBackground() {
        Image image;
        switch (mode){
            case 1:
                image = new ImageIcon(getClass().getResource("/res/white_6.jpg")).getImage();
                break;
            default:
                image = new ImageIcon(getClass().getResource("/res/black_5.jpg")).getImage();
        }
        return image;
    }

    @Override
    public Color setColorSelected() {
        Color selected;
        switch (mode) {
            case 1:
                selected = Color.BLUE;
                break;
            default:
                selected = Color.PINK;
                break;
        }
        return selected;
    }

}
