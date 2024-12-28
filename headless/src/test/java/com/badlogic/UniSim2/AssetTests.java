package com.badlogic.UniSim2;

import com.badlogic.UniSim2.resources.Assets;
import com.badlogic.gdx.Gdx;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssetTests extends AbstractHeadlessGdxTest {


    @Test
    public void testMusicExists() {
        assertTrue(Gdx.files.internal("sounds/music.mp3").exists());
    }

    @Test
    public void testClickExists() {
        assertTrue(Gdx.files.internal("sounds/click.mp3").exists());
    }

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
    public void testAccomodationButtonUpTextureEists() {
        assertTrue(Gdx.files.internal("textures/buttons/accomodationButtonUp.png").exists());
    }

    @Test
    public void testLectureHallButtonUpTextureEists() {
        assertTrue(Gdx.files.internal("textures/buttons/lectureHallButtonUp.png").exists());
    }

    @Test
    public void testLibraryButtonUpTextureEists() {
        assertTrue(Gdx.files.internal("textures/buttons/libraryButtonUp.png").exists());
    }

    @Test
    public void testCourseButtonUpTextureEists() {
        assertTrue(Gdx.files.internal("textures/buttons/courseButtonUp.png").exists());
    }

    @Test
    public void testFoodZoneButtonUpTextureEists() {
        assertTrue(Gdx.files.internal("textures/buttons/foodZoneButtonUp.png").exists());
    }

    @Test
    public void testRecreationalButtonUpTextureEists() {
        assertTrue(Gdx.files.internal("textures/buttons/recreationalButtonUp.png").exists());
    }

    @Test
    public void testNatureButtonUpTextureEists() {
        assertTrue(Gdx.files.internal("textures/buttons/natureButtonUp.png").exists());
    }


    /**
     * Button Down Assets
     */
    @Test
    public void testAccomodationButtonDownTextureExists() {
        assertTrue(Gdx.files.internal("textures/buttons/accomodationButtonDown.png").exists());
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
     * Building Assets
     */
    @Test
    public void testAccomodationPlacedTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/accomodationPlaced.png").exists());
    }

    @Test
    public void testAccomodationCollisionTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/accomodationCollision.png").exists());
    }

    @Test
    public void testAccomodationDraggingTextureExists() {
        assertTrue(Gdx.files.internal("textures/buildings/accomodationDragging.png").exists());
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
