package com.badlogic.UniSim2.buildingmanager;

import com.badlogic.UniSim2.resources.*;

/**
 * A building which represents a library.
 * @see Building
 */
public class Library extends Building{
    /**
     * Initialises an {@link com.badlogic.UniSim2.buildingmanager.Building.BuildingTypes#Library} building
     */
    public Library(){
        super(
            Assets.libraryPlacedTexture,
            Assets.libraryCollisionTexture,
            Assets.libraryDraggingTexture,
            Consts.LIBRARY_WIDTH,
            Consts.LIBRARY_HEIGHT,
            BuildingTypes.Library,
            Consts.LIBRARY_COST
        );
    }
}
