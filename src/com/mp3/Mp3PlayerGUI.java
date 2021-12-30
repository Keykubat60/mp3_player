package com.mp3;

import de.vdheide.mp3.MP3Properties;
import de.vdheide.mp3.NoMP3FrameException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Mp3PlayerGUI extends JFrame {



    static JFrame frame;
    static JLabel start_pauseLabel;
    static JLabel backLabel;
    static JLabel nextLabel;
    static JLabel loopLabel;
    static JLabel fileOpener;
    static JLabel labelFrame;
    static JLabel leftTimeLabel;
    static boolean isPlaying = false;
    static double i;
    static Timer timer;
    static JLabel totalTimeLabel;
    static JLabel display;
    static JLabel songList;
    static JLabel totalSongList;
    private JPanel panel;
    private long pauseLocation;
    private long songTotalLength;
    private String fileLocation;
    public static FileInputStream fis;
    MP3Properties song;
    static BufferedInputStream bis;
    static Player player;
    private int width = 300;
    private int height = 230;
    static double s;
    public Mp3PlayerGUI() {


    }

    public void startMP3Player() {


        createJFrame();
        createStartPause();
        createNext();
        createBack();
        createLoop();
        createDisplay();
        createCerceve();
        creatTimeLabel();
        creatTotalTimeLabel();
        createSongList();
        createTotalSongList();



        createFileOpener();
        new decorate(frame, width + 50);
        createPanel(0, 0, width + 50, 40, new Color(28, 26, 27, 220));
        createPanel(0, 40, 40, 40, new Color(28, 26, 27, 220));
        createPanel(310, 40, 40, 40, new Color(28, 26, 27, 220));
        createPanel(0, 80, width + 50, 110, new Color(28, 26, 27, 220));
        new ActionHandler();

    }

    public void createJFrame() {
        frame = new JFrame("MF_MP3Player");
        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setSize(width + 50, height - 40);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(23, 21, 22, 200));
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setIconImage(new ImageIcon(getClass().getResource("/icons/decorate/IconTheme.png")).getImage());
    }

    public void createStartPause() {
        start_pauseLabel = new JLabel();
        start_pauseLabel.setBounds(126 + 25, 160 - 40, 45, 45);
        start_pauseLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Start1.png")));
        start_pauseLabel.setFocusable(true);
        start_pauseLabel.requestFocusInWindow();
        frame.add(start_pauseLabel);
    }

    public void createNext() {
        nextLabel = new JLabel();
        nextLabel.setBounds(191 + 25, 170 - 40, 21, 23);
        nextLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png")));

        frame.add(nextLabel);
    }

    public void createBack() {
        backLabel = new JLabel();
        backLabel.setBounds(85 + 25, 170 - 40, 21, 23);
        backLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Back.png")));

        frame.add(backLabel);
    }

    public void createLoop() {
        loopLabel = new JLabel();
        loopLabel.setBounds(25 + 25, 172 - 40, 40, 23);
        loopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Loop.png")));

        frame.add(loopLabel);
    }

    public void createFileOpener() {
        fileOpener = new JLabel();
        fileOpener.setBounds(232 + 25, 172 - 40, 40, 18);
        fileOpener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/File121.png")));

        frame.add(fileOpener);
    }

    public void createPanel(int x, int y, int width, int height, Color col) {
        panel = new JPanel();
        panel.setBackground(col);
        panel.setBounds(x, y, width, height);

        frame.add(panel);
    }

    public void createDisplay() {
        display = new JLabel();

        display.setBounds(40, 50, 220 + 40, 20);
        display.setFont(new Font("Arial", Font.BOLD, 10));
        display.setForeground(new Color(213, 213, 23));
        display.setVerticalAlignment(JLabel.CENTER);
        display.setHorizontalAlignment(JLabel.CENTER);

        frame.add(display);


    }

    public void createCerceve() {
        labelFrame = new JLabel();
        labelFrame.setBounds(32, 36, 284, 49);
        labelFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cerceve.png")));
        frame.add(labelFrame);


    }

    public void creatTimeLabel() {
        leftTimeLabel = new JLabel("00:00");
        leftTimeLabel.setFont(new Font("Arial", 0, 15));
        leftTimeLabel.setBounds(52, 90, 50, 20);
        leftTimeLabel.setForeground(new Color(213, 213, 23));
        frame.add(leftTimeLabel);
    }

    public void creatTotalTimeLabel() {
        totalTimeLabel = new JLabel("00:00");
        totalTimeLabel.setFont(new Font("Arial", 0, 15));
        totalTimeLabel.setBounds(259, 90, 50, 20);
        totalTimeLabel.setForeground(new Color(213, 213, 23));
        frame.add(totalTimeLabel);
    }


    public void createSongList (){
        songList = new JLabel("0");
        songList.setFont(new Font("Arial", 0, 15));
        songList.setBounds(143-3, 90, 30, 20);
        songList.setHorizontalAlignment(SwingConstants.RIGHT);
        songList.setForeground(new Color(213, 213, 23));
        frame.add(songList);

    }
    public void createTotalSongList (){
        totalSongList = new JLabel("/0");
        totalSongList.setFont(new Font("Arial", 0, 15));
        totalSongList.setBounds(174-3, 90, 30, 20);
        totalSongList.setForeground(new Color(213, 213, 23));
        frame.add(totalSongList);

    }

    public JLabel getStart_pauseLabel() {
        return start_pauseLabel;
    }
    public void stop() {
        if (player != null) {
            player.close();

            songTotalLength = 0;
            pauseLocation = 0;
            isPlaying = false;

        }
    }

    public void Play(String path) {
        try {
            songList.setText(""+(ActionHandler.songIs+1));
            isPlaying = true;
            fis = new FileInputStream(path);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            songTotalLength = fis.available();
            fileLocation = path + "";

            song = new MP3Properties(ActionHandler.fl[ActionHandler.songIs]);
            i = song.getLength();


        } catch (JavaLayerException | IOException | NoMP3FrameException ignored) {

        }
        new Thread(() -> {
            try {


                player.play();
                if (player.isComplete() && ActionHandler.isLoop) {
                    if (Mp3PlayerGUI.timer != null) {
                        Mp3PlayerGUI.timer.cancel();
                    }
                  //  Thread.sleep(1500);
                    Play(fileLocation);
                } else if (player.isComplete()) {
                    timer.cancel();
                    stop();

                  //  Thread.sleep(1500);

                    ActionHandler.songIs++;
                    ActionHandler.songIs = ActionHandler.songIs % ActionHandler.fl.length;

                    Mp3PlayerGUI.display.setText(ActionHandler.fl[ActionHandler.songIs % ActionHandler.fl.length].getName());
                    Play(ActionHandler.fl[ActionHandler.songIs].getAbsolutePath());
                }

            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
        i = song.getLength();
        getTimer();

    }

    public void getTimer() {
        if (Mp3PlayerGUI.timer != null) {
            Mp3PlayerGUI.timer.cancel();
        }
        timer = new Timer();
        s = (song.getLength());
        int p1 = (int) s % 60;
        int p2 = (int) s / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        String str1 = p3 + "", str = p1 + "";
        if (p1 < 10) {
            str = "0" + p1;
        }
        if (p3 < 10) {
            str1 = "0" + p3;

        }
        totalTimeLabel.setText(str1 + ":" + str);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isPlaying) {
                    i--;
                    double s = (song.getLength() - i);
                    int p1 = (int) s % 60;


                    int p2 = (int) s / 60;
                    int p3 = p2 % 60;
                    p2 = p2 / 60;
                    String str1 = p3 + "", str = p1 + "";
                    if (p1 < 10) {
                        str = "0" + p1;
                    }
                    if (p3 < 10) {
                        str1 = "0" + p3;
                        leftTimeLabel.setText(str1 + ":" + str);

                    }

                } else {
                    timer.cancel();
                    timer.cancel();


                }
            }
        }, 1 * 1000, 1000);
    }

    public void pause() {
        if (player != null) {
            try {
                isPlaying = false;
                pauseLocation = fis.available();
            } catch (IOException ignored) {

            }

            player.close();

        }
    }

    public void resume() {
        try {
            isPlaying = true;
            getTimer();
            fis = new FileInputStream(fileLocation);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
            fis.skip(songTotalLength - pauseLocation);

        } catch (JavaLayerException | IOException ignored) {

        }
        new Thread(() -> {
            try {
                player.play();
                if (player.isComplete() && ActionHandler.isLoop) {
                    if (Mp3PlayerGUI.timer != null) {
                        Mp3PlayerGUI.timer.cancel();
                    }
                    //  Thread.sleep(1500);
                    Play(fileLocation);
                } else if (player.isComplete()) {
                    timer.cancel();
                    stop();

                    //  Thread.sleep(1500);

                    ActionHandler.songIs++;
                    ActionHandler.songIs = ActionHandler.songIs % ActionHandler.fl.length;

                    Mp3PlayerGUI.display.setText(ActionHandler.fl[ActionHandler.songIs % ActionHandler.fl.length].getName());
                    Play(ActionHandler.fl[ActionHandler.songIs].getAbsolutePath());
                }
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();



    }

    public JLabel getLoopLabel() {
        return loopLabel;
    }

    public JLabel getNextLabel() {
        return nextLabel;
    }

    public JLabel getBackLabel() {
        return backLabel;
    }

    public JLabel getFileOpener() {
        return fileOpener;
    }

}