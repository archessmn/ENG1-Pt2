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
    private Main game;
    private StretchViewport viewport;
    private Stage stage;
    private Label scoreLabel;
    private Label scoreNum;
    private final Skin skin;
    private final int score;
    private double satisfaction;
    private final boolean didWin;
    private Scanner scanBoard;

    SpriteBatch spriteBatch = new SpriteBatch();

    public EndScreen(Main game, int score, double satisfaction){
        this.game = game;
        this.viewport = game.getViewport();
        this.stage = new Stage(this.viewport);
        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.score = score;
        this.satisfaction = satisfaction;

        didWin = satisfaction >= 500;


        addToScoreBoard();
        createScoreBoard();
        createScoreLabel();
        stage.setDebugAll(true);
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
        ArrayList<Integer> boardList = new ArrayList<Integer>();
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
        for (int i = 0; i < boardList.size(); i++) {
            currentScore = (Integer) boardList.get(i);
            if (currentScore < score) {
                boardList.add(i, score);
                break;
            }
        }
        try{
            BufferedWriter writeBoard = new BufferedWriter(new FileWriter(leaderboard));
            for (int i = 0; i < boardList.size(); i++){
                writeBoard.write(boardList.get(i) + "\r\n");
            }
            writeBoard.flush();
            writeBoard.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

        Integer varStringHeight = Consts.SCOREBOARD_LABEL_Y;

        File leaderboard = new File("Leaderboard.txt");
        try {
            scanBoard = new Scanner(leaderboard);

            for (int i = 0; i<5; i++){
                varStringHeight -= Consts.LABEL_GAP;
                if(scanBoard.hasNext()){
                    String string = scanBoard.next();
                    scoreNum = new Label(string, skin);
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
