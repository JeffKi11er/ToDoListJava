package com.company.datePicker;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

public class DatePickerFrame extends JFrame {
    public static final int Width = 390;
    public static final int Height = 470;
    private static final long serialVersionID = 1L;
    private String intentMessageContent;
    JFormattedTextField textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));

    public String getIntentMessageContent() {
        return intentMessageContent;
    }

    public void setIntentMessageContent(String intentMessageContent) {
        this.intentMessageContent = intentMessageContent;
    }

    public DatePickerFrame(String intentMessageContent){
        this.intentMessageContent = intentMessageContent;
    }

    public DatePickerFrame(){
        setSize(Width,Height);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Date Picker");
        setLocationRelativeTo(null);
        DatePickerPanel panel = new DatePickerPanel();
        add(panel);
    }
}
