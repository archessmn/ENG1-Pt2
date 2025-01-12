package com.badlogic.UniSim2.GUImanager;

import com.badlogic.UniSim2.Main;
import com.badlogic.UniSim2.buildingmanager.BuildingManager;
import com.badlogic.UniSim2.resources.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

/**
 * This is the game menu that is shown by the {@link GameScreen}. It contains
 * the {@link Timer timer} for the game and {@link BuildingMenu the building menu}
 * which can be used to place new buildings.
 */
public class GameMenu {
    private Stage stage;
    private final Skin skin;
    private BuildingManager buildingManager;
    private BuildingMenu buildingMenu;
    private Timer timer;
    private int money;
    private double satisfaction;
    private double satisfactionPerSecond;
    private Label timerLabel;
    private Label moneyLabel;
    private Label satisfactionLabel;
    private Label eventLabel;
    private boolean isPaused;

    public GameMenu(Main game, Timer timer, int money, double satisfaction, BuildingManager buildings){
        stage = new Stage(game.getViewport());
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        buildingMenu = new BuildingMenu(stage, buildings);
        this.timer = timer;
        this.money = money;
        this.satisfaction = satisfaction;
        this.buildingManager = buildings;
        isPaused = false;
        createMenu();
    }

    /**
     * Activates the input processor needed for the menu to use inputs.
     */
    public void activate() {
        Gdx.input.setInputProcessor(stage);
    }

    public int[] returnBuildingCount(){
        return buildingMenu.returnBuildingCount();
    }

    private void createMenu(){
        buildingMenu.createBuildingMenu();
        createTimerLabel();
        createMoneyLabel();
        createSatisfactionLabel();
        createEventLabel();
    }

    // Adds a label at the top of the screen displaying the time
    private void createTimerLabel(){

        // Initialize timerLabel
        timerLabel = new Label("00:00", skin);
        timerLabel.setFontScale(3);
        timerLabel.setAlignment(Align.center);
        timerLabel.setColor(Consts.TIMER_COLOR);

        // Position the label at the top center of the screen
        timerLabel.setPosition(Consts.TIMER_X, Consts.TIMER_Y, Align.center);

        // Add the label to the stage
        stage.addActor(timerLabel);
    }

    /**
     * Updates the time shown on the label to the elapsed time got from
     * the timer.
     */
    private void updateTimerLabel(){
        float elapsedTime = timer.getElapsedTime();
        int minutes = (int) (elapsedTime / 60);
        int seconds = (int) (elapsedTime % 60);
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    // Adds a label at the top of the screen displaying the time
    private void createMoneyLabel(){

        // Initialize timerLabel
        moneyLabel = new Label(String.format("$%d", money), skin);
        moneyLabel.setFontScale(2f);
        moneyLabel.setAlignment(Align.center);
        moneyLabel.setColor(Consts.TIMER_COLOR);

        // Position the label at the top center of the screen
        moneyLabel.setPosition(Consts.MONEY_X, Consts.MONEY_Y, Align.center);

        // Add the label to the stage
        stage.addActor(moneyLabel);
    }

    /**
     * Updates the time shown on the label to the elapsed time got from
     * the timer.
     */
    private void updateMoneyLabel(){
        moneyLabel.setText(String.format("$%d", money));
    }

    private void createSatisfactionLabel(){
        satisfactionLabel = new Label(String.format("Satisfaction:\n%,.1f\n%,.1f/s", satisfaction, satisfactionPerSecond), skin);
        satisfactionLabel.setFontScale(1.5f);
        satisfactionLabel.setAlignment(Align.center);
        satisfactionLabel.setColor(Consts.TIMER_COLOR);

        satisfactionLabel.setPosition(Consts.SATISFACTION_X, Consts.SATISFACTION_Y, Align.center);

        stage.addActor(satisfactionLabel);
    }

    private void updateSatisfactionLabel(){
        satisfactionLabel.setText(String.format("Satisfaction:\n%,.1f\n%,.1f/s", satisfaction, satisfactionPerSecond));
    }

    private void createEventLabel(){
        eventLabel = new Label("", skin);
        eventLabel.setFontScale(2.5f);
        eventLabel.setAlignment(Align.center);
        eventLabel.setColor(Consts.TIMER_COLOR);

        eventLabel.setPosition(Consts.EVENT_LABEL_X, Consts.EVENT_LABEL_Y, Align.center);

        stage.addActor(eventLabel);
    }

    public void updateEventLabel(String text){
        eventLabel.setText(text);
    }

    /**
     * Should be called when the game is paused.
     */
    public void pause() {
        timerLabel.setText("PAUSED");
        isPaused = true;
    }

    /**
     * Should be called when the game is resumed.
     */
    public void resume() {
        updateTimerLabel();
        updateMoneyLabel();
        isPaused = false;
    }

    /**
     * Processes any input.
     */
    public void input(){
        stage.act(Gdx.graphics.getDeltaTime());
    }

    /**
     * Updates and draws the menu.
     */
    public void draw(){
        if (isPaused == false) {
            updateTimerLabel();
            updateMoneyLabel();
            updateSatisfactionLabel();
        }
        buildingMenu.draw();
        stage.draw();
    }

    /**
     * @return true if the menu is paused and false if not.
     */
    public boolean getPaused(){
        return isPaused;
    }

    public void updateMoney(int money) {
        this.money = money;
    }

    public void updateSatisfaction(double satisfaction) {
        this.satisfaction = satisfaction;
    }

    public void updateSatisfactionPerSecond(double satisfactionPerSecond) {
        this.satisfactionPerSecond = satisfactionPerSecond;
    }

    /**
     * Gets rid the all textures. This method should be called when the menu is
     * not going to be used anymore.
     */
    public void dispose(){
        buildingMenu.dispose();
        stage.dispose();
        skin.dispose();
    }
}
