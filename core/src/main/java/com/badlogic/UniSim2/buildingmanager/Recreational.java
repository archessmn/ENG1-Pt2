package com.badlogic.UniSim2.buildingmanager;

import com.badlogic.UniSim2.resources.*;

/**
 * A building which represents a recreational building where students can have
 * fun.
 * @see Building
 */
public class Recreational extends Building{
    /**
     * Initialises an {@link com.badlogic.UniSim2.buildingmanager.Building.BuildingTypes#Recreational} building
     */
    public Recreational(){
        super(
            Assets.recreationalPlacedTexture,
            Assets.recreationalCollisionTexture,
            Assets.recreationalDraggingTexture,
            Consts.RECREATIONAL_WIDTH,
            Consts.RECREATIONAL_HEIGHT,
            BuildingTypes.Recreational,
            Consts.RECREATIONAL_COST
        );
    }
}
