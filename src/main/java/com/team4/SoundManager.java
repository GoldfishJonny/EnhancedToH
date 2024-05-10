package com.team4;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {

    private static final String PICK_UP_SOUND_FILE = "src/main/resources/pick_up.wav";
    private static final String DROP_SOUND_FILE = "src/main/resources/drop.wav";

    public static void playPickUpSound() {
        playSound(PICK_UP_SOUND_FILE);
    }

    public static void playDropSound() {
        playSound(DROP_SOUND_FILE);
    }

    private static void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}

