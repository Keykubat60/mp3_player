package com.mp3;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;


public class ActionHandler implements MouseListener,  KeyListener {
Mp3PlayerGUI mp3;
static int count = 0;
static int count1=0;
static  int count2=0;
static boolean isLoop=false;
static boolean isPlay = false;
static boolean isSong = false;
static int songIs=0;
private String path ;
static File[]fl;
static String name;
static  float changedCalc;
  private  Timer  timer;

    public ActionHandler(){

        mp3 =new Mp3PlayerGUI();
        mp3.getStart_pauseLabel().addMouseListener(this);
        mp3.getNextLabel().addMouseListener(this);
        mp3.getBackLabel().addMouseListener(this);
        mp3.getLoopLabel().addMouseListener(this);
        mp3.getFileOpener().addMouseListener(this);
        Mp3PlayerGUI.frame.addKeyListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(path!=null) {
    if (e.getSource() == mp3.getStart_pauseLabel()) {
                                           //Start_Resume
        getStart();

    }
    if(e.getSource()== mp3.getNextLabel()){                     //NextSong

        getNext();
    }
    if(e.getSource()==mp3.getBackLabel()){                      //BackSong

        getBack();
    }
    if(e.getSource()==mp3.getLoopLabel()){                      //LoopSong
        getLoop();
    }
}
        if(e.getSource()== mp3.getFileOpener()){                //OpenSong
            getChooser();

        }
            }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()== mp3.getNextLabel()){

            mp3.getNextLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/Next1.png")));

        }
        if(e.getSource()== mp3.getBackLabel()){

            mp3.getBackLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/Back1.png")));

        }
        if((e.getSource()== mp3.getLoopLabel())&&!isLoop){

            mp3.getLoopLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/Loop12.png")));

        }
        if(e.getSource()== mp3.getFileOpener()){
            mp3.getFileOpener().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/File122.png")));

        }
    }


    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == mp3.getNextLabel()) {

            mp3.getNextLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/next.png")));

        }
        if (e.getSource() == mp3.getBackLabel()) {

            mp3.getBackLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/Back.png")));

        }
        if ((e.getSource() == mp3.getLoopLabel()) && !isLoop) {

            mp3.getLoopLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/Loop.png")));

        }
        if (e.getSource() == mp3.getFileOpener()) {
            mp3.getFileOpener().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/File121.png")));

        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(path!=null) {
            if ((e.getKeyCode()!=KeyEvent.VK_RIGHT)||(e.getKeyCode()!=KeyEvent.VK_LEFT)){
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    getStart();
                }
        }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){

                getNext();


            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                getBack();
            }
            if(e.getKeyCode()==KeyEvent.VK_L){
               getLoop();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            getChooser();

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
           volumeDownControl(0.1);

        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
           volumeDownControl(-0.1);
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            count2++;int on = count2 % 2;count2 = on;
            if(count2==1){
                    volumeDownControl(true);

            }
            else{
                volumeDownControl(false);
            }


        }
        }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void getStart(){


                count++;
                int on = count % 2;
                count = on;


                if (count == 1) {

                    if (isSong) {

try {
    mp3.Play(fl[songIs].getAbsolutePath());

}
catch(ArrayIndexOutOfBoundsException ignore){

}
                        isSong = false;
                        mp3.getStart_pauseLabel().setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/icons/stop.png")));
                        isPlay = true;


                    } else {

                        //Resume

                        mp3.getStart_pauseLabel().setIcon(
                                new javax.swing.ImageIcon(getClass().getResource("/icons/stop.png")));
                        mp3.resume();
                        isPlay = true;


                    }

                } else {

                    //Pause
                    mp3.pause();
                    mp3.getStart_pauseLabel().setIcon(
                            new javax.swing.ImageIcon(getClass().getResource("/icons/Start1.png")));
                    isPlay = false;


                }


    }
    public void getNext(){
        if(isPlay) {
            Mp3PlayerGUI.isPlaying=false;
            if(Mp3PlayerGUI.timer!=null) {Mp3PlayerGUI.timer.cancel();}

            mp3.stop();
            songIs++;
            songIs=songIs % fl.length;

            createScrollingText();


            mp3.Play(fl[songIs].getAbsolutePath());
        }
        else if(!isPlay){
            Mp3PlayerGUI.isPlaying=false;
            songIs++;
            Mp3PlayerGUI.songList.setText(""+(songIs+1));
            mp3.stop();

            createScrollingText();

            isSong = true;
        }

    }
    public void getBack(){
        if(songIs!=0) {                 //BackStart
            if (isPlay) {
                if(Mp3PlayerGUI.timer!=null) {Mp3PlayerGUI.timer.cancel();}
                songIs--;

                mp3.stop();
                createScrollingText();


                mp3.Play(fl[songIs % fl.length].getAbsolutePath());

            } else if (!isPlay) {
                songIs--;//BackPause
                Mp3PlayerGUI.songList.setText(""+(songIs+1));

                mp3.stop();
                createScrollingText();



                isSong=true;
            }
        }
        else {                  //if its the first song
            if (isPlay) {

            songIs = 0;
            mp3.stop();
            mp3.Play(fl[songIs %fl.length].getAbsolutePath());

        }
        else {
            songIs = 0;
        }
        }
    }
    public void getLoop(){
        count1++;int on = count1 % 2;count1 = on;
        if(count1==1){
            mp3.getLoopLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/Loop12.png")));
            isLoop=true;
        }
        else{
            mp3.getLoopLabel().setIcon(
                    new javax.swing.ImageIcon(getClass().getResource("/icons/Loop.png")));
            isLoop=false;
        }
    }
    public void getChooser(){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files","mp3","mpeg3");
        JFileChooser chooser = new JFileChooser();
        chooser.setDragEnabled(true);
        chooser.setFileFilter(filter);
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(filter);
        chooser.setMultiSelectionEnabled(true);
       chooser.setDialogTitle("Choose MP3 Files");


        int returnVal = chooser.showOpenDialog(null);
        if(returnVal==JFileChooser.APPROVE_OPTION) {


            fl = chooser.getSelectedFiles();
            mp3.stop();
            Mp3PlayerGUI.display.setText("");
            songIs = 0;
            String song = fl[songIs] + "";
            //  name =  fl[songIs].getName();


            // Mp3PlayerGUI.display.setText(name);
            path = song;
            isSong = true;
            Mp3PlayerGUI.totalSongList.setText("/" + (fl.length));
            Mp3PlayerGUI.songList.setText("" + (songIs + 1));
            createScrollingText();


        }

        }

    public void volumeDownControl(Double valueToPlusMinus) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                     changedCalc = (float) ((float) currentVolume - (double) volumeToCut);



                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);


                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }

    }
    public void volumeDownControl(Boolean mute) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);



if (mute) {
    // Now Set This Changed Value Into Volume Line.
    volControl.setValue(0);
}
else{
    volControl.setValue(0.5f);
}

                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }
    public void createScrollingText(){
        if(timer!=null) {timer.cancel();}

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count =0;
            String j =  "      "+( fl[songIs].getName()) +"";
            @Override
            public void run() {
                count++;
                j = j.substring(1) + j.substring(0, 1);
                Mp3PlayerGUI.display.setText(j);

            }
        },0,110);
    }


}
