package com.company.datePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DatePickerPanel extends JPanel implements Runnable, ActionListener, MouseListener {
    private String contentMessage;
    private JButton btnPre;
    private JButton btnNex;
    private String dateAndTime;
    private Calendar calendar = Calendar.getInstance();
    private List<Date> dates = new ArrayList<>();
    private ArrayList<DateCall>jDate = new ArrayList<>();
    private static final int MAX_CALENDAR_DAYS = 42;

    public DatePickerPanel() {
        setLayout(null);
        initDatePicker();
        Thread t = new Thread(this);
        t.start();
        setFocusable(true);
        requestFocusInWindow();
        setFocusTraversalKeysEnabled(false);
    }

    private void initDatePicker() {
        btnNex = new JButton();
        btnNex.setText("Next");
        btnNex.setSize(90, 30);
        btnNex.setLocation(273, 10);
        btnPre = new JButton();
        btnPre.setText("Previous");
        btnPre.setSize(90, 30);
        btnPre.setLocation(10, 10);
        add(btnPre);
        add(btnNex);
        btnPre.addActionListener(this);
        btnNex.addActionListener(this);
    }

    public DatePickerPanel(String contentMessage) {
        this.contentMessage = contentMessage;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.RED);
        Font font = new Font("Comic Sans MS", Font.BOLD, 15);
        g2d.setFont(font);
        g2d.drawString(dateAndTime, 130, 30);
        drawDayByDay(g2d);
        GridAdapter adapter = new GridAdapter(dates,calendar);
        int count1,count2;
        count1 = 0;
        count2 = 1;
        for (int i = 0; i < dates.size(); i++) {
            if (count1>5){
                add(adapter.drawSingleCell(i,count1,count2,g2d));
                ++count2;
                count1=0;
            }else {
                add(adapter.drawSingleCell(i,count1,count2,g2d));
                ++count1;
            }
        }
    }

    private void drawDayByDay(Graphics2D g2d) {
        String[] dayByDay = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < dayByDay.length; i++) {
            g2d.setColor(Color.ORANGE);
            g2d.fillRect(51 * i + 10, 50, 48, 20);
            g2d.setColor(Color.BLACK);
            Font font = new Font("Comic Sans MS", Font.BOLD, 15);
            g2d.setFont(font);
            g2d.drawString(dayByDay[i], 51 * i + 15, 65);
        }
//        GridAdapter adapter = new GridAdapter(dates,calendar);
//        JButton jButton = adapter.drawSingleCell(29,1,4);
//        add(jButton);
    }

    @Override
    public void run() {
        while (true) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM yyyy");
            dateAndTime = simpleDateFormat.format(calendar.getTime());
            Calendar monthCalendar = (Calendar) calendar.clone();
            monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
            int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 2;
            monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth);
            while (dates.size() < MAX_CALENDAR_DAYS) {
                dates.add(monthCalendar.getTime());
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            addMouseListener(this);
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(btnNex)) {
            dates.clear();
            calendar.add(Calendar.MONTH, 1);
        } else if (actionEvent.getSource().equals(btnPre)) {
            dates.clear();
            calendar.add(Calendar.MONTH, -1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

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
