package com.badlogic.UniSim2;

import com.badlogic.UniSim2.resources.Assets;
import com.badlogic.gdx.Gdx;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 // Test that all the asset files exist and are loaded,
 // there's likely better ways to do it but at least this gives a granular view of what's wrong.
 */
public class AssetTests extends AbstractHeadlessGdxTest {
    /**
     * Test Sound files
     */
    @Test
    public void testMusicExists() {
        assertTrue(Gdx.files.internal("sounds/music.mp3").exists());
    }

    @Test
    public void testClickExists() {
        assertTrue(Gdx.files.internal("sounds/click.mp3").exists());
    }

    /**
     * Test generic textures
     */
    @Test
    public void testStartBackgroundTextureExists() {
        assertTrue(Gdx.files.internal("startBackground.png").exists());
    }

    @Test
    public void testStartButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("startButtonUp.png").exists());
    }

    @Test
    public void testStartButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("startButtonDown.png").exists());
    }

    @Test
    public void testBackgroundTextureExists() {
        assertTrue(Gdx.files.internal("background.png").exists());
    }

    @Test
    public void testPathTextureExists() {
        assertTrue(Gdx.files.internal("path.png").exists());
    }

    @Test
    public void testMenuBarTextureExists() {
        assertTrue(Gdx.files.internal("menuBar.png").exists());
    }

    /**
     * Button Up Assets
     */
    @Test
    public void testAccommodationButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/accommodationButtonUp.png").exists());
    }

    @Test
    public void testLectureHallButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/lectureHallButtonUp.png").exists());
    }

    @Test
    public void testLibraryButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/libraryButtonUp.png").exists());
    }

    @Test
    public void testCourseButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/courseButtonUp.png").exists());
    }

    @Test
    public void testFoodZoneButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/foodZoneButtonUp.png").exists());
    }

    @Test
    public void testRecreationalButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/recreationalButtonUp.png").exists());
    }

    @Test
    public void testNatureButtonUpTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/natureButtonUp.png").exists());
    }


    /**
     * Button Down Assets
     */
    @Test
    public void testAccommodationButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/accommodationButtonDown.png").exists());
    }

    @Test
    public void testLectureHallButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/lectureHallButtonDown.png").exists());
    }

    @Test
    public void testLibraryButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/libraryButtonDown.png").exists());
    }

    @Test
    public void testCourseButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/courseButtonDown.png").exists());
    }

    @Test
    public void testFoodZoneButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/foodZoneButtonDown.png").exists());
    }

    @Test
    public void testRecreationalButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/recreationalButtonDown.png").exists());
    }

    @Test
    public void testNatureButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/natureButtonDown.png").exists());
    }

    /**
     * Building Assets (Placed, Collision and Dragging for each type)
     */
    @Test
    public void testAccommodationPlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/accommodationPlaced.png").exists());
    }

    @Test
    public void testAccommodationCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/accommodationCollision.png").exists());
    }

    @Test
    public void testAccommodationDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/accommodationDragging.png").exists());
    }

    @Test
    public void testLectureHallPlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/lectureHallPlaced.png").exists());
    }

    @Test
    public void testLectureHallCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/lectureHallCollision.png").exists());
    }

    @Test
    public void testLectureHallDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/lectureHallDragging.png").exists());
    }

    @Test
    public void testLibraryPlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/libraryPlaced.png").exists());
    }

    @Test
    public void testLibraryCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/libraryCollision.png").exists());
    }

    @Test
    public void testLibraryDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/libraryDragging.png").exists());
    }

    @Test
    public void testCoursePlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/coursePlaced.png").exists());
    }

    @Test
    public void testCourseCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/courseCollision.png").exists());
    }

    @Test
    public void testCourseDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/courseDragging.png").exists());
    }

    @Test
    public void testFoodZonePlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/foodZonePlaced.png").exists());
    }

    @Test
    public void testFoodZoneCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/foodZoneCollision.png").exists());
    }

    @Test
    public void testFoodZoneDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/foodZoneDragging.png").exists());
    }

    @Test
    public void testRecreationalPlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/recreationalPlaced.png").exists());
    }

    @Test
    public void testRecreationalCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/recreationalCollision.png").exists());
    }

    @Test
    public void testRecreationalDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/recreationalDragging.png").exists());
    }

    @Test
    public void testNaturePlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/naturePlaced.png").exists());
    }

    @Test
    public void testNatureCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/natureCollision.png").exists());
    }

    @Test
    public void testNatureDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/natureDragging.png").exists());
    }
}
