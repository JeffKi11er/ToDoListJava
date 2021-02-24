package com.company.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ToFrame extends javax.swing.JFrame implements WindowListener {
    public static final int W = 350;
    public static final int H = 500;
    public static final int generalFieldWid = ToFrame.W-40;
    public static final int getGeneralFieldHei = 20;
    ToPanel toPanel;
    public ToFrame(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(W,H);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("TodoList");
        addWindowListener(this);
        init();
        dispose();
    }

    private void init() {
        toPanel = new ToPanel();
        add(toPanel);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int result = JOptionPane.showConfirmDialog(null,"Do you want to save?","Exit Task",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (result==JOptionPane.YES_OPTION){
            toPanel.save();
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
