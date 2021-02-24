package com.company.datePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridAdapter implements MouseListener {
    private List<Date>dates;
    private Calendar currentDate;
    private int count;
    private ArrayList<DateCall>dateCalls = new ArrayList<>();

    public GridAdapter(List<Date> dates, Calendar currentDate) {
        this.dates = dates;
        this.currentDate = currentDate;
    }

    public DateCall drawSingleCell(int position,int i,int j,Graphics2D graphics2D) {
        int x = 51*i + 10;
        int y = 50*(j)+30;
        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int dayNumber = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int currentMonth = currentDate.get(Calendar.MONTH)+1;
        int currentYear = currentDate.get(Calendar.YEAR);
        int displayYear = dateCalendar.get(Calendar.YEAR);
        if (displayMonth == currentMonth && displayYear == currentYear){
            graphics2D.setColor(Color.GREEN);
        }else {
            graphics2D.setColor(Color.WHITE);
        }
        DateCall dateCall = new DateCall(String.valueOf(dayNumber),x,y);
        dateCalls.add(dateCall);
        dateCall.draw(graphics2D);
        dateCall.addMouseListener(this);
//        graphics2D.fillRect(x,y,48,48);
//        graphics2D.setColor(Color.BLACK);
//        graphics2D.drawString(String.valueOf(dayNumber),x+16,y+30);
        return dateCall;
    }

    public int getCount() {
        return dates.size();
    }
    public int getPosition(Object item){
        return dates.indexOf(item);
    }
    public Object getItem(int position){
        return dates.get(position);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        for (int i = 0; i < dateCalls.size() ; i++) {
            if (dateCalls.get(i).contains(p)){
                System.out.println(dates.get(i));
            }
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
