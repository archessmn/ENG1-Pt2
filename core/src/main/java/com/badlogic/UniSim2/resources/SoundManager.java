package com.badlogic.UniSim2.resources;

public class SoundManager {

    private SoundManager(){}

    /**
     * Starts the looping music in the background
     */
    public static void playMusic(){
        Assets.music.setVolume(.3f);
        Assets.music.setLooping(true);
        Assets.music.play();
    }

    /**
     * Plays the click sound effect when buildings are placed
     */
    public static void playClick(){
        Assets.click.play();
    }
}
