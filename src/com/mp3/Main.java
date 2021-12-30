package com.mp3;

import com.mp3.Mp3PlayerGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
               new Runnable(){

                   @Override
                   public void run() {

                       Mp3PlayerGUI  mp3 = new Mp3PlayerGUI();
                       mp3.startMP3Player();
                       try {
                           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                       } catch (ClassNotFoundException e) {
                           e.printStackTrace();
                       } catch (InstantiationException e) {
                           e.printStackTrace();
                       } catch (IllegalAccessException e) {
                           e.printStackTrace();
                       } catch (UnsupportedLookAndFeelException e) {
                           e.printStackTrace();
                       }


                   }
               }

        );
    }
}
