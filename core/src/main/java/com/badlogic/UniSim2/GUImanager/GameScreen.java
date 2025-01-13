package com.badlogic.UniSim2.GUImanager;

import com.badlogic.UniSim2.Main;
import com.badlogic.UniSim2.buildingmanager.Building;
import com.badlogic.UniSim2.mapmanager.Map;
import com.badlogic.UniSim2.resources.Consts;
import com.badlogic.UniSim2.resources.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.HashMap;
import java.util.Objects;

/**
 * This screen is used when the game is being played.
 */
public class GameScreen implements Screen {
    private boolean headless = false;

    private final Main game;
    private final StretchViewport viewport;

    private final Timer timer;

    private int money;
    private double satisfaction;
    private int score;

    private GameMenu menu; // Used to make and display the game menu

    boolean isPaused = false;

    // This variable is needed to stop a crash from occurring when the game ends.
    boolean hasEnded = false;

    ShapeRenderer shapeRenderer;

    private Map map;

    /**
     * @param game the game to use for the {@link GameScreen}
     */
    public GameScreen (Main game) {
        this(game, false);
    }

    /**
     * @param game the game to use for the {@link GameScreen}
     * @param headless whether the {@link GameScreen} is being used headless in a test
     */
    public GameScreen(Main game, boolean headless) {
        this.game = game;
        viewport = game.getViewport();
        timer = new Timer();
        money = 250;
        satisfaction = 0;
        score = 0;

        if (!headless) {
            map = new Map(game);
            menu = new GameMenu(game, timer, money, satisfaction, map.getBuildingManager());

            SoundManager.playMusic();

            shapeRenderer = new ShapeRenderer();
        } else this.headless = true;
    }

    /**
     * @return current amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @return current amount of satisfaction
     */
    public double getSatisfaction() {
        return satisfaction;
    }

    /**
     * Subtracts an amount of money if the current amount of money is greater than or equal to it
     *
     * @param amount The amount of money to subtract
     */
    public void subtractMoney(int amount) {
        if (this.money >= amount) {
            this.money -= amount;
        }
    }

    @Override
    public void show() {
        menu.activate();
    }

    @Override
    public void render(float delta) {
        input();
        update();
        if (hasEnded) return;
        draw();
    }

    /**
     * Processes input. Will pause/resume the game if the space is pressed.
     */
    private void input() {
        menu.input();
        map.input();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (isPaused) {
                isPaused = false;
                menu.resume();
            } else {
                isPaused = true;
                menu.pause();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            calculateScore();
            game.endGame(score, satisfaction, menu.returnBuildingCount());
            hasEnded = true;
        }

    }

    /**
     * Will update the timer or not (depending on whether the game is paused)
     * and will end the game if the timer has reached its max time.
     */
    public void update() {
        if (!isPaused) {
            timer.update();

            if (timer.hasWholeSecondPassed()) {
                // Money Calculations
                // Increase money by 10 until half-time, then by 20
                if (timer.getElapsedTime() >= 150) {
                    money += 20;
                } else {
                    money += 10;
                }

                if (!headless) {
                    double satisfactionMultiplier = 1;
                    double satisfactionToAdd = 0;

                    // Satisfaction Calculations
                    if (map.getBuildingManager().hasEveryType()) {
                        satisfactionToAdd++;
                    }

                    int numNatures = 0;

                    Rectangle proximityRectangle;

                    for (Building building : new Array.ArrayIterator<>(map.getBuildingManager().getBuildings())) {
                        if (!building.getIsPlaced()) continue;
                        switch (building.getType()) {
                            case Nature:
                                if (numNatures <= 4) numNatures++;
                                break;
                            case LectureHall:
                            case FoodZone:
                            case Library:
                                // Check for {LectureHall, FoodZone, Library} within 3 tiles of a {Course, Recreational, LectureHall}

                                // Create a rectangle 3 tiles bigger on each side to check for overlaps
                                proximityRectangle = new Rectangle(building.getBoundingRectangle());
                                proximityRectangle.set(proximityRectangle.x - (Consts.CELL_SIZE * 3), proximityRectangle.y - (Consts.CELL_SIZE * 3), proximityRectangle.width + (Consts.CELL_SIZE * 6), proximityRectangle.height + (Consts.CELL_SIZE * 6));

                                // Match current building type to one to match to
                                Building.BuildingTypes matchType = switch (building.getType()) {
                                    case LectureHall -> Building.BuildingTypes.Course;
                                    case FoodZone -> Building.BuildingTypes.Recreational;
                                    case Library -> Building.BuildingTypes.LectureHall;
                                    default ->
                                        throw new IllegalStateException("Unexpected value in satisfaction switch: " + building.getType());
                                };

                                // Find the matching buildings and check if they overlap
                                for (Building proximityBuilding : new Array.ArrayIterator<>(map.getBuildingManager().getBuildings())) {
                                    if (!proximityBuilding.getIsPlaced()) continue;

                                    if (proximityBuilding.getType() == matchType) {
                                        if (proximityRectangle.overlaps(proximityBuilding.getBoundingRectangle())) {
                                            satisfactionMultiplier += 0.2;
                                            break;
                                        }
                                    }
                                }
                                break;
                            case Accommodation:
                                proximityRectangle = new Rectangle(building.getBoundingRectangle());
                                proximityRectangle.set(proximityRectangle.x - (Consts.CELL_SIZE * 10), proximityRectangle.y - (Consts.CELL_SIZE * 10), proximityRectangle.width + (Consts.CELL_SIZE * 20), proximityRectangle.height + (Consts.CELL_SIZE * 20));

                                HashMap<Building.BuildingTypes, Boolean> nearbyBuildings = new HashMap<>();

                                for (Building proximityBuilding : new Array.ArrayIterator<>(map.getBuildingManager().getBuildings())) {
                                    if (!proximityBuilding.getIsPlaced()) continue;
                                    if (Objects.requireNonNull(proximityBuilding.getType()) != Building.BuildingTypes.Accommodation) {
                                        if (proximityRectangle.overlaps(proximityBuilding.getBoundingRectangle())) {
                                            nearbyBuildings.put(proximityBuilding.getType(), true);
                                        }
                                    }
                                }

                                if (nearbyBuildings.size() == Building.BuildingTypes.values().length - 1) {
                                    satisfactionMultiplier += 1;
                                }
                                break;
                        }
                    }

                    satisfactionMultiplier += numNatures * 0.5;

                    this.satisfaction += (satisfactionToAdd * satisfactionMultiplier);
                    menu.updateSatisfactionPerSecond(satisfactionToAdd * satisfactionMultiplier);
                }

                gameEvents();
            }

            if (!headless) {
                menu.updateMoney(money);
                menu.updateSatisfaction(satisfaction);
            }

            if (timer.hasReachedMaxTime()) {
                calculateScore();
                game.endGame(score, satisfaction, menu.returnBuildingCount());
                hasEnded = true;
            }
        }
    }

    /**
     * Adds events to the game.
     */
    private void gameEvents(){
        if ((timer.getElapsedTime() > 59.5 && timer.getElapsedTime() < 60.5) ||
            (timer.getElapsedTime() > 119.5 && timer.getElapsedTime() < 120.5) ||
            (timer.getElapsedTime() > 179.5 && timer.getElapsedTime() < 180.5) ||
            (timer.getElapsedTime() > 239.5 && timer.getElapsedTime() < 240.5)) {
            if (Math.random() < 0.5){
                menu.updateEventLabel("You won the lottery!!! Have an extra $500");
                money += 500;
            }
            else {
                menu.updateEventLabel("Building fine - $300 has been confiscated :((");
                money -= 300;
                if (money < 0){
                    money = 0;
                }
            }
        }
        if (timer.getElapsedTime() > 69.5 && timer.getElapsedTime() < 70.5||
            (timer.getElapsedTime() > 129.5 && timer.getElapsedTime() < 130.5) ||
            (timer.getElapsedTime() > 189.5 && timer.getElapsedTime() < 190.5) ||
            (timer.getElapsedTime() > 249.5 && timer.getElapsedTime() < 250.5)) {
            menu.updateEventLabel("");
        }
    }

    /**
     * Calculates the score of the player by the end of the game.
     */
    public void calculateScore(){
        score = (int)(satisfaction) + money/10;
    }

    /**
     * @return the calculated score. {@link GameScreen#calculateScore()} must be called first
     */
    public int getScore() {
        return score;
    }

    /**
     * Draws the game. This means drawing the game menu, building menu and game
     * map.
     */
    private void draw() {
        viewport.apply();
        ScreenUtils.clear(Consts.BACKGROUND_COLOR);
        map.draw();
        menu.draw();
    }


    @Override
    public void resize(int width, int height) {
        map.resize(width, height);
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        map.dispose();
        menu.dispose();
    }
}
