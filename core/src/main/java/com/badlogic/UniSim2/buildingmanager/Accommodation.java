package com.badlogic.UniSim2.buildingmanager;

import com.badlogic.UniSim2.resources.*;

/**
 * A building which represents student accommodation.
 * @see Building
 */
public class Accommodation extends Building{

    public Accommodation(){
        super(
            Assets.accommodationPlacedTexture,
            Assets.accommodationCollisionTexture,
            Assets.accommodationDraggingTexture,
            Consts.ACCOMMODATION_WIDTH,
            Consts.ACCOMMODATION_HEIGHT,
            BuildingTypes.Accommodation,
            Consts.ACCOMMODATION_COST
        );
    }
}
