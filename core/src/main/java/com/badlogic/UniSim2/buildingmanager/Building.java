package com.badlogic.UniSim2.buildingmanager;

import com.badlogic.UniSim2.GUImanager.BuildingMenu;
import com.badlogic.UniSim2.mapmanager.Map;
import com.badlogic.UniSim2.resources.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * A class which represents a building that can be placed on the map.
 * Each building can be stored in the {@link BuildingManager}.
 */
public abstract class Building extends Sprite {

    // Textures for when a building is placed, colliding, dragged
    private final Texture placedTexture;
    private final Texture collisionTexture;
    private final Texture draggingTexture;

    private boolean isPlaced; // True when a building is placed on the grid

    private final int width;
    private final int height;

    private final int cost;

    /**
     * Stores different types of buildings
     */
    public enum BuildingTypes {
        Accommodation,
        LectureHall,
        Library,
        Course,
        FoodZone,
        Recreational,
        Nature
    }

    private final BuildingTypes type;

    /**
     * @param placedTexture Texture to use for a placed building
     * @param collisionTexture Texture to use during a collision
     * @param draggingTexture Texture to use when dragging the building
     * @param width Width of the building
     * @param height Height of the building
     * @param type {@link BuildingTypes} of the building
     * @param cost How much the building should cost to build
     */
    public Building(Texture placedTexture, Texture collisionTexture, Texture draggingTexture, int width, int height, BuildingTypes type, int cost) {

        this.placedTexture = placedTexture;
        this.collisionTexture = collisionTexture;
        this.draggingTexture = draggingTexture;
        this.width = width;
        this.height = height;
        this.type = type;
        this.cost = cost;
        isPlaced = false;

        setSize(width, height);
        setRegion(placedTexture); // By default, a buildings texture is its placed texture
    }

    /**
     * Updates the position of the building to the specified mousePos. Will
     * ensure that the building is snapped to the grid.
     *
     * @param mousePos The position of the mouse in world coords.
     */
    private void updatePosition(Vector2 mousePos) {
        // Puts the centre of the building where the mouse is
        float x = mousePos.x - getWidth() / 2f;
        float y = mousePos.y - getHeight() / 2f;

        // Ensures that a building moves within the grid by snapping it to the grid
        int cellSize = Consts.CELL_SIZE;
        float snapX = MathUtils.round(x / cellSize) * cellSize;
        float snapY = MathUtils.round(y / cellSize) * cellSize;
        setPosition(snapX, snapY);
    }

    /**
     * Should be called when is building is being dragged. Will update the
     * position to the mousePos and will set use the colliding texture
     * if colliding is set to true.
     *
     * @param mousePos  The mouse position in world coords.
     * @param colliding true if the building is colliding with something in
     *                  {@link Map#collidableSprites} and false otherwise.
     */
    public void handleDragging(Vector2 mousePos, boolean colliding) {
        updatePosition(mousePos); // Sets the position of the building to the mouse position
        setDraggingTexture(colliding); // Sets texture to colliding if true, dragging if false
    }

    /**
     * Should be called when this building is selected. Will set
     * the texture to dragging.
     */
    public void selectBuilding() {
        setDraggingTexture(false); // Sets the texture to dragging
    }

    /**
     * Should be called when the building is placed. Will play the build
     * placing sound, deselect the building and change the texture to the
     * placed texture.
     */
    public void placeBuilding() {
        incrementCount();
        isPlaced = true;
        setRegion(placedTexture);
        SoundManager.playClick();
    }

    private void incrementCount() {
        int index = type.ordinal();
        BuildingMenu.buildingCounts[index]++;
        BuildingMenu.updateCountLabel(index); // Increments the building count label by 1 and displays
    }


    /**
     * Sets the texture to collision or dragging depending on if it is colliding
     * with another collidable in {@link Map#collidableSprites}.
     *
     * @param collision true when the building is colliding and false otherwise.
     */
    private void setDraggingTexture(boolean collision) {
        setRegion(collision ? collisionTexture : draggingTexture);
    }

    /**
     * Ensures that the building stays within the boundaries set by {@link Consts#MAP_MIN_X_BOUNDARY},
     * {@link Consts#MAP_MAX_X_BOUNDARY}, {@link Consts#MAP_MIN_Y_BOUNDARY} and
     * {@link Consts#MAP_MAX_Y_BOUNDARY}.
     */
    public void clampPosition() {
        setX(MathUtils.clamp(getX(), Consts.MAP_MIN_X_BOUNDARY, Consts.MAP_MAX_X_BOUNDARY - width));
        setY(MathUtils.clamp(getY(), Consts.MAP_MIN_Y_BOUNDARY, Consts.MAP_MAX_Y_BOUNDARY - height));
    }

    /**
     * Returns the column of the bottom left square of the building.
     *
     * @return column of the bottom left square of the building
     */
    public int getCol() {
        return (int) (getX() / Consts.CELL_SIZE);
    }

    /**
     * Returns the row of the bottom left square of the building.
     *
     * @return row of the bottom left square of the building
     */
    public int getRow() {
        return (int) (Consts.GRID_ROWS - getY() / Consts.CELL_SIZE);
    }

    /**
     * Get placed status of building
     * @return whether the building is placed
     */
    public boolean getIsPlaced() {
        return isPlaced;
    }

    /**
     * Get building width
     * @return building width
     */
    public int getBuildingWidth() {
        return width;
    }

    /**
     * Get building height
     * @return building height
     */
    public int getBuildingHeight() {
        return height;
    }

    /**
     * Return the {@link BuildingTypes} of the building
     * @return type of building
     */
    public BuildingTypes getType() {
        return type;
    }

    /**
     * @return cost of the building
     */
    public int getCost() {
        return cost;
    }

    /**
     * Disposes of textures to free up memory
     */
    public void dispose() {
        placedTexture.dispose();
        collisionTexture.dispose();
        draggingTexture.dispose();
    }
}
