package com.badlogic.UniSim2;

import com.badlogic.UniSim2.GUImanager.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods for the {@link GameScreen}
 */
public class GameScreenTests extends AbstractHeadlessGdxTest {

    @Test
    public void testInitialMoney() {
        GameScreen screen = new GameScreen(new Main(), true);

        assertEquals(250, screen.getMoney());
    }

    @Test
    public void testInitialSatisfaction() {
        GameScreen screen = new GameScreen(new Main(), true);

        assertEquals(0, screen.getSatisfaction());
    }

    @Test
    public void testSubtractMoney() {
        GameScreen screen = new GameScreen(new Main(), true);

        screen.subtractMoney(100);

        assertEquals(150, screen.getMoney());
    }

    @Test
    public void testUpdate() {
        GameScreen screen = new GameScreen(new Main(), true);

        Gdx.graphics = Mockito.mock(Graphics.class);
        Mockito.when(Gdx.graphics.getDeltaTime()).thenReturn(0.5f);

        screen.update();

        assertEquals(250, screen.getMoney());
        assertEquals(0, screen.getSatisfaction());

        screen.update();

        assertEquals(260, screen.getMoney());
        assertEquals(0, screen.getSatisfaction());
    }

    @Test
    public void testPausedUpdate() {
        GameScreen screen = new GameScreen(new Main(), true);

        Gdx.graphics = Mockito.mock(Graphics.class);
        Mockito.when(Gdx.graphics.getDeltaTime()).thenReturn(0.5f);

        screen.pause();

        screen.update();

        assertEquals(250, screen.getMoney());
        assertEquals(0, screen.getSatisfaction());

        screen.update();

        assertEquals(250, screen.getMoney());
        assertEquals(0, screen.getSatisfaction());
    }

    @Test
    public void testScore() {
        GameScreen screen = new GameScreen(new Main(), true);

        screen.subtractMoney(-440);

        screen.calculateScore();

        assertEquals(69, screen.getScore());
    }
}
