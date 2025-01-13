package com.badlogic.UniSim2.buildingmanager;

import com.badlogic.UniSim2.resources.*;

/**
 * A building which represents a place where lectures can take place.
 * @see Building
 */
public class LectureHall extends Building{
    /**
     * Initialises an {@link com.badlogic.UniSim2.buildingmanager.Building.BuildingTypes#LectureHall} building
     */
    public LectureHall(){
        super(
            Assets.lectureHallPlacedTexture,
            Assets.lectureHallCollisionTexture,
            Assets.lectureHallDraggingTexture,
            Consts.LECTUREHALL_WIDTH,
            Consts.LECTUREHALL_HEIGHT,
            BuildingTypes.LectureHall,
            Consts.LECTUREHALL_COST
        );
    }

}
