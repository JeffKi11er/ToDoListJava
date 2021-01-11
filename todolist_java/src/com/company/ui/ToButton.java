package com.company.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToButton extends JButton implements MouseListener {
    private ImageIcon imNormal;
    private ImageIcon imSelected;
    public ToButton(String Normal, String Selected){
        imNormal = new ImageIcon(getClass().getResource("/res/" + Normal));
        imSelected = new ImageIcon(getClass().getResource("/res/" + Selected));
        addMouseListener(this);
        setBorder(null);
        setIcon(imNormal);
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
        setIcon(imSelected);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        setIcon(imNormal);
    }
}
