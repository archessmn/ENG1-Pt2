package com.badlogic.UniSim2.buildingmanager;

import com.badlogic.UniSim2.resources.*;

/**
 * A building which represents a place where students can eat food.
 * @see Building
 */
public class FoodZone extends Building{
    /**
     * Initialises an {@link com.badlogic.UniSim2.buildingmanager.Building.BuildingTypes#FoodZone} building
     */
    public FoodZone(){
        super(
            Assets.foodZonePlacedTexture,
            Assets.foodZoneCollisionTexture,
            Assets.foodZoneDraggingTexture,
            Consts.FOODZONE_WIDTH,
            Consts.FOODZONE_HEIGHT,
            BuildingTypes.FoodZone,
            Consts.FOODZONE_COST
        );
    }
}
