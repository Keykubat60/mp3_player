package com.mp3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class decorate implements MouseListener, MouseMotionListener {
    private JPanel decorate;
    private JPanel closePanel;
    private JPanel minimizePanel;
    private JPanel mainPanel;
    private JLabel labelClose;
    private JLabel labelMaximize;
    private JLabel labelMinimize;
    private JLabel iconLabel;
    private int width;
    JFrame frame;
    int xMove;
    int yMove;


    public decorate(JFrame frame, int width){
        this.width = width;
        this.frame = frame;
        getLabelClose();
        getLabelMaximize();
        getLabelMinimize();
        getClosePanel();
        getMaximizePanel();
        getMinimizePanel();
        getIconLabel();
        getDecorate();
        action();


    }
    public void getDecorate() {

        decorate = new JPanel();
        decorate.setBounds(0, 0, width, 30);
        decorate.setBackground(new Color(0, 0, 0));
        // Border bright = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(46, 44, 45));
        //decorate.setBorder(bright);
        frame.add(decorate);
    }

    public void getLabelClose() {
        labelClose = new JLabel();
        labelClose.setBounds(width-38, 5, 31, 19);
        labelClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/decorate/close.png")));
        // labelClose.setFont(new Font("Arial",Font.BOLD,20));

        frame.add(labelClose);

    }

    public void getLabelMaximize() {
        labelMaximize = new JLabel();
        //labelMaximize.setFont(new Font("Arial",Font.BOLD,20));
        labelMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/decorate/max.png")));
        labelMaximize.setBounds(width - 68, 5, 31, 19);
        frame.add(labelMaximize);

    }

    public void getLabelMinimize() {
        labelMinimize = new JLabel();
        //labelMaximize.setFont(new Font("Arial",Font.BOLD,20));
        labelMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/decorate/min.png")));
        labelMinimize.setBounds(width -98, 5, 31, 19);
        frame.add(labelMinimize);

    }

    public void getMinimizePanel() {
        minimizePanel = new JPanel();
        minimizePanel.setBounds(width -98, 5, 31, 19);
        minimizePanel.setBackground(new Color(28, 26, 27));
        minimizePanel.setVisible(false);
        frame.add(minimizePanel);
    }
    public void getIconLabel(){
        iconLabel = new JLabel();
        iconLabel.setBounds(5,5,20,20);
        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/decorate/IconTheme.png")));
        frame.add(iconLabel);
    }

    public void getClosePanel() {
        closePanel = new JPanel();
        closePanel.setBounds(width-38, 5, 31, 19);
        closePanel.setBackground(new Color(156, 17, 26));
        closePanel.setVisible(false);
        frame.add(closePanel);
    }

    public void getMaximizePanel() {
        mainPanel = new JPanel();
        mainPanel.setBounds(width - 68, 5, 31, 19);
        mainPanel.setBackground(new Color(28, 26, 27));
        mainPanel.setVisible(true);
        frame.add(mainPanel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==labelClose){
            System.exit(0);
        }
        if(e.getSource()==labelMinimize){
            frame.setState(JFrame.ICONIFIED);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==decorate)
        {  xMove = e.getX();
        yMove = e.getY();}
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
if(e.getSource()==labelClose){
    closePanel.setVisible(true);
}
if(e.getSource()==labelMinimize){
    minimizePanel.setVisible(true);
}
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==labelClose){
            closePanel.setVisible(false);
        }
        if(e.getSource()==labelMinimize){
            minimizePanel.setVisible(false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getSource()==decorate)
        {   int xCordinate = e.getXOnScreen();
            int yCordinate = e.getYOnScreen();

            frame.setLocation(xCordinate-xMove,yCordinate-yMove);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public void action(){
        labelClose.addMouseListener(this);
        labelMinimize.addMouseListener(this);
        decorate.addMouseMotionListener(this);
        decorate.addMouseListener(this);
    }
}