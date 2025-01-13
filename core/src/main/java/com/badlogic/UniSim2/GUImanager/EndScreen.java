package com.badlogic.UniSim2.GUImanager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.UniSim2.Main;
import com.badlogic.UniSim2.resources.Assets;
import com.badlogic.UniSim2.resources.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.Align;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.util.ArrayList;

/**
 * This screen should be shown when the game ends.
 */
public class EndScreen implements Screen {
    private final StretchViewport viewport;
    private Stage stage;
    private Label scoreLabel;
    private final Skin skin;
    private int score;
    private final double satisfaction;
    private final boolean didWin;
    private final int[] buildingCounts;
    private Scanner scanBoard;

    private SpriteBatch spriteBatch;

    /**
     * @param game to use for the viewport
     * @param score calculated score at the end of the game
     * @param satisfaction satisfaction at the end of the game
     * @param headless whether the {@link EndScreen} is being used headless in a test
     * @param counts counts of each different building type
     */
    public EndScreen (Main game, int score, double satisfaction, boolean headless, int[] counts) {
        this.viewport = game.getViewport();

        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.score = score;
        this.satisfaction = satisfaction;
        this.buildingCounts = counts;

        didWin = hasWon();

        if (!headless) {
            this.stage = new Stage(this.viewport);
            spriteBatch = new SpriteBatch();

            calculateAchievements();
            addToScoreBoard();
            createScoreBoard();
            createScoreLabel();
        }
    }

    public EndScreen(Main game, int score, double satisfaction, int[] counts){
        this(game, score, satisfaction, false, counts);
    }

    // Adds achievement label to screen and adds to score if necessary
    private void calculateAchievements(){
        Label achievementLabel = new Label("", skin);
        int change = 0;

        if (satisfaction == 0) {
            achievementLabel = new Label ("""
                ACHIEVEMENT: game completed with 0 satisfaction\

                 \

                 \

                 \

                 \

                 \

                 \

                Reward: +50 points""", skin);
            change = 50;
        }

        int overallCount = 0;
        for (int buildingCount : buildingCounts) {
            overallCount += buildingCount;
        }
        if (overallCount > 50){
            achievementLabel = new Label ("""
                ACHIEVEMENT: over fifty buildings placed\

                 \

                 \

                 \

                 \

                 \

                 \

                Reward: +100 points""", skin);
            change = 100;
            }

        int nature = buildingCounts[6];
        if (nature >= 25){
            achievementLabel = new Label ("""
                ACHIEVEMENT: nature lover - over 25 bushes placed\

                 \

                 \

                 \

                 \

                 \

                 \

                Reward: +250 points""", skin);
            change = 250;
        }

        score += change;

        achievementLabel.setFontScale(4);
        achievementLabel.setAlignment(Align.center);
        achievementLabel.setAlignment(Align.top);
        achievementLabel.setColor(Consts.TIMER_COLOR);
        // Position the label at the top center of the screen
        achievementLabel.setPosition(Consts.ACHIEVEMENT_LABEL_X, Consts.ACHIEVEMENT_LABEL_Y, Align.center);

        // Add the label to the stage
        stage.addActor(achievementLabel);
    }

    // Adds a label to the middle of the screen displaying the score that the player
    // managed to get throughout the game.
    private void createScoreLabel() {
        // Initialize scoreLabel
        scoreLabel = new Label("You " + (didWin ? "Won!!!" : "Lost :(") +
            "\nScore : " + score +
            "\nSatisfaction: " + (int) satisfaction + (!didWin ? "\n^ 500 needed to win." : ""), skin);
        scoreLabel.setFontScale(3);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setAlignment(Align.top);
        scoreLabel.setColor(Consts.TIMER_COLOR);

        // Position the label at the top center of the screen
        scoreLabel.setPosition(Consts.GAME_STATUS_LABEL_X, Consts.GAME_STATUS_LABEL_Y, Align.center);

        // Add the label to the stage
        stage.addActor(scoreLabel);
    }

    // Checks if score is high enough be added to leaderboard
    private void addToScoreBoard(){
        File leaderboard = new File("Leaderboard.txt");
        try {
            if (leaderboard.createNewFile()) {
                newScoreBoard(leaderboard);
                return;
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        ArrayList<Integer> boardList = new ArrayList<>();
        try {
            scanBoard = new Scanner(leaderboard);
            for (int i = 0; i<5; i++) {
                if (scanBoard.hasNext()) {
                    String string = scanBoard.next();
                    boardList.add(Integer.valueOf(string));
                }
            }
            scanBoard.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Integer currentScore;
        boolean appended = false;
        for (int i = 0; i < boardList.size(); i++) {
            currentScore = boardList.get(i);
            if (currentScore < score) {
                boardList.add(i, score);
                appended = true;
                break;
            }
        }
        if ((boardList.size() < 5) && (!appended)){
            boardList.add(score);
        }
        try{
            BufferedWriter writeBoard = new BufferedWriter(new FileWriter(leaderboard));
            for (Integer integer : boardList) {
                writeBoard.write(integer + "\r\n");
            }
            writeBoard.flush();
            writeBoard.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Initialises the scoreboard
    private void newScoreBoard(File leaderboard){
        ArrayList<Integer> boardList = new ArrayList<>();
        boardList.add(score);
        try{
            BufferedWriter writeBoard = new BufferedWriter(new FileWriter(leaderboard));
            for (Integer integer : boardList) {
                writeBoard.write(integer + "\r\n");
            }
            writeBoard.flush();
            writeBoard.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Displays the scoreboard
    private void createScoreBoard() {
        // Initialize scoreLabel
        scoreLabel = new Label("Scoreboard:", skin);
        scoreLabel.setFontScale(3);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setColor(Consts.TIMER_COLOR);

        // Position the label at the top center of the screen
        scoreLabel.setPosition(Consts.SCOREBOARD_LABEL_X, Consts.SCOREBOARD_LABEL_Y, Align.center);

        // Add the label to the stage
        stage.addActor(scoreLabel);

        int varStringHeight = Consts.SCOREBOARD_LABEL_Y;

        File leaderboard = new File("Leaderboard.txt");
        try {
            scanBoard = new Scanner(leaderboard);

            for (int i = 0; i<5; i++){
                varStringHeight -= Consts.LABEL_GAP;
                if(scanBoard.hasNext()){
                    String string = scanBoard.next();
                    Label scoreNum = new Label(string, skin);
                    scoreNum.setFontScale(3);
                    scoreNum.setAlignment(Align.center);
                    scoreNum.setColor(Consts.TIMER_COLOR);
                    scoreNum.setPosition(Consts.SCOREBOARD_LABEL_X, varStringHeight, Align.center);
                    stage.addActor(scoreNum);
                }
            }

            scanBoard.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    // Draws the background of the start menu
    private void drawBackground(){
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(Assets.startBackgroundTexture, 0, 0, Consts.WORLD_WIDTH, Consts.WORLD_HEIGHT);
        spriteBatch.end();

    }

    public boolean hasWon(double satisfaction) {
        return satisfaction >= 500;
    }

    public boolean hasWon() {
        return hasWon(satisfaction);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        ScreenUtils.clear(Consts.BACKGROUND_COLOR);
        drawBackground();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}

