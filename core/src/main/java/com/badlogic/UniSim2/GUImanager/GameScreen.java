package com.badlogic.UniSim2.GUImanager;

import com.badlogic.UniSim2.Main;
import com.badlogic.UniSim2.buildingmanager.Building;
import com.badlogic.UniSim2.mapmanager.Map;
import com.badlogic.UniSim2.resources.Consts;
import com.badlogic.UniSim2.resources.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.HashMap;

/**
 * This screen is used when the game is being played.
 */
public class GameScreen implements Screen {
    private Main game;
    private StretchViewport viewport;

    private Timer timer;

    private int money;
    private double satisfaction;
    private int score;

    private GameMenu menu; // Used to make and display the game menu

    boolean isPaused = false;

    // This variable is needed to stop a crash from occuring when the game ends.
    boolean hasEnded = false;

    ShapeRenderer shapeRenderer;

    private Map map;

    public GameScreen(Main game) {
        this.game = game;
        viewport = game.getViewport();
        timer = new Timer();
        money = 250;
        satisfaction = 0;
        score = 0;
        map = new Map(game);
        menu = new GameMenu(game, timer, money, satisfaction, map.getBuildingManager());

        SoundManager.playMusic();

        shapeRenderer = new ShapeRenderer();

    }

    public int getMoney() {
        return money;
    }

    public double getSatisfaction() {
        return satisfaction;
    }

    /**
     * Subtracts an amount of money if the current amount of money is greater than or equal to it
     * @param amount The amount of money to subtract
     * @return If the money got subtracted
     */
    public boolean subtractMoney(int amount) {
        if (this.money >= amount) {
            this.money -= amount;
            return true;
        }
        return false;
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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
            game.endGame(score, satisfaction);
            hasEnded = true;
        }

    }

    /**
     * Will update the timer or not (depending on whether the game is paused)
     * and will end the game if the timer has reached its max time.
     */
    private void update() {
        if (!isPaused) {
            timer.update();

            if (timer.hasWholeSecondPassed()) {
                // Money Calculations
                // Increase money by 10 until half time, then by 20
                if (timer.getElapsedTime() >= 150) {
                    money += 20;
                } else {
                    money += 10;
                }

                double satisfactionMultiplier = 1;
                double satisfactionToAdd = 0;

                // Satisfaction Calculations
                if (map.getBuildingManager().hasEveryType()) {
                    satisfactionToAdd ++;
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
                                default -> throw new IllegalStateException("Unexpected value in satisfaction switch: " + building.getType());
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
                                switch (proximityBuilding.getType()) {
                                    case Accommodation -> {}
                                    default -> {
                                        if (proximityRectangle.overlaps(proximityBuilding.getBoundingRectangle())) {
                                            nearbyBuildings.put(proximityBuilding.getType(), true);
                                        }
                                    }
                                }
                            }

                            if (nearbyBuildings.size() == Building.BuildingTypes.values().length - 1) {
                                satisfactionMultiplier += 1;
                            }
                            break;
                    }
//                    if (building.getType() == Building.BuildingTypes.Nature) {
//                        numNatures++;
//                        if (numNatures == 5) {
//                            break;
//                        }
//                    }
                }

                satisfactionMultiplier += numNatures * 0.5;

                System.out.println(satisfactionMultiplier);

                this.satisfaction += (satisfactionToAdd * satisfactionMultiplier);

//                shapeRenderer.end();
            }
            menu.updateMoney(money);
            menu.updateSatisfaction(satisfaction);

            if (timer.hasReachedMaxTime()) {
                calculateScore();
                game.endGame(score, satisfaction);
                hasEnded = true;
            }
        }
    }

    /**
     * Calculates the score of the player by the end of the game.
     */
    public void calculateScore(){
        score = (int)(satisfaction) + money/10;
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
    }

    @Override
    public void resume() {
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
