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
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File("D:/javaTransfer/todolist_java/src/res/clocks.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Set the frames content pane to use a JLabel
//        // whose icon property has been set to use the image
//        // we just loaded
//        setContentPane(new JLabel(new ImageIcon(img)));
//
//        // Supply a layout manager for the body of the content
//        setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
        toPanel = new ToPanel();
        add(toPanel);
//        pack();
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
