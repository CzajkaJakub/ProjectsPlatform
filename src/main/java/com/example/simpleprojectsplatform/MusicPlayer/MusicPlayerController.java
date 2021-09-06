package com.example.simpleprojectsplatform.MusicPlayer;

import com.example.simpleprojectsplatform.PathsTitlesFiles;
import com.example.simpleprojectsplatform.MainPlatform.PlatformController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MusicPlayerController implements Initializable, PathsTitlesFiles {

    @FXML
    Button playButton, stopButton, resetButton, nextMusicButton, previousSongButton, backToMain,
            playRandomSongButton, showFilesButton;

    @FXML
    Label songNameLabel;

    @FXML
    ComboBox<String> musicSpeeds;

    @FXML
    Slider volumeSlider;

    @FXML
    ProgressBar songProgressBar;

    private ArrayList<File> songs;
    private int songNumber = 0;
    private final Integer[] songSpeeds = {25, 50, 75, 100, 125, 150, 175, 200};

    private Timer timer;

    private MediaPlayer mediaPlayer;
    private Media media;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBox();
        createSongsList();
        if(notEmptyDirectory()){
            createSongPlayer();
            changeLabelText();
            createVolumeSlider();
            createProgressBar();
        }
    }

    private void fillComboBox() {
        for(Integer speed : songSpeeds){
            musicSpeeds.getItems().add(speed.toString() + "%");
        }
    }

    private void createSongsList() {
        songs = new ArrayList<>();
        File directory = new File(musicFilesPath);
        File[] files = directory.listFiles();
        if(Objects.requireNonNull(files).length != 0){
            Collections.addAll(songs, files);
        }
    }

    private void createSongPlayer() {
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    private void createProgressBar() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                double endTime = media.getDuration().toSeconds();
                double progress = currentTime/endTime;
                songProgressBar.setProgress(progress);
                if(progress == 1){
                    cancelTheTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void cancelTheTimer() {
        timer.cancel();
    }

    private void changeLabelText() {
        String songName = songs.get(songNumber).getName();
        songNameLabel.setText(songName.split("\\.")[0]);
    }

    private void createVolumeSlider() {
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        volumeSlider.valueProperty().addListener(event -> mediaPlayer.setVolume(volumeSlider.getValue() * 0.01));
    }


    @FXML
    private void backToMain() throws IOException {
        if(notEmptyDirectory()){
            mediaPlayer.stop();
            cancelTheTimer();
        }
        PlatformController.changeScene(platformPath, mainTitle, platformIconPath);
    }

    @FXML
    private void playMusic(){
        if(notEmptyDirectory()){
            createProgressBar();
            mediaPlayer.play();
        }
    }

    @FXML
    private void stopMusic(){
        if(notEmptyDirectory()){
            cancelTheTimer();
            mediaPlayer.pause();
        }
    }

    @FXML
    private void resetMusic(){
        if(notEmptyDirectory()){
            songProgressBar.setProgress(0);
            mediaPlayer.seek(Duration.millis(0));
        }
    }

    @FXML
    private void playNextMusic(){
        if(notEmptyDirectory()){
            if(songNumber < songs.size() - 1){
                songNumber++;
            }else{
                songNumber = 0;
            }
            mediaPlayer.stop();
            createSongPlayer();
            changeLabelText();
            playMusic();
        }
    }

    @FXML
    private void playPreviousSong(){
        if(notEmptyDirectory()){
            if(songNumber > 0){
                songNumber--;
            }else{
                songNumber = songs.size() - 1;
            }
            mediaPlayer.stop();
            createSongPlayer();
            changeLabelText();
            playMusic();
        }
    }

    @FXML
    private void playRandomSong(){
        if(notEmptyDirectory()){
            Random random = new Random();
            songNumber = random.nextInt(songs.size());
            mediaPlayer.stop();
            createSongPlayer();
            changeLabelText();
            playMusic();
        }
    }

    @FXML
    private void changeSpeedSong(){
        if(notEmptyDirectory()){
            String value = musicSpeeds.getValue();
            if(value == null){
                mediaPlayer.setRate(1);
            }else{
                double speed = Double.parseDouble(value.substring(0, value.length() - 1));
                mediaPlayer.setRate(speed * 0.01);
            }
        }
    }

    @FXML
    private void showFiles() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(musicFilesPath));
    }

    private boolean notEmptyDirectory(){
        return songs.size() != 0;
    }
}


