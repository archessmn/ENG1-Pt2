package com.badlogic.UniSim2;

import com.badlogic.UniSim2.GUImanager.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TimerTests {
    @Test
    public void testUpdate() {
        Timer timer = new Timer();

        Gdx.graphics = Mockito.mock(Graphics.class);

        Mockito.when(Gdx.graphics.getDeltaTime()).thenReturn(0.5f);

        timer.update();

        assertEquals(0.5f, timer.getElapsedTime());
    }

    @Test
    public void testWholeSecondPassing() {
        Timer timer = new Timer();

        Gdx.graphics = Mockito.mock(Graphics.class);

        Mockito.when(Gdx.graphics.getDeltaTime()).thenReturn(0.5f);

        timer.update();

        assertFalse(timer.hasWholeSecondPassed());

        timer.update();

        assertTrue(timer.hasWholeSecondPassed());

        timer.update();

        assertFalse(timer.hasWholeSecondPassed());
    }

    @Test
    public void testReachedMaxTime() {
        Timer timer = new Timer();

        Gdx.graphics = Mockito.mock(Graphics.class);

        Mockito.when(Gdx.graphics.getDeltaTime()).thenReturn(150f);

        timer.update();

        assertFalse(timer.hasReachedMaxTime());

        timer.update();

        assertTrue(timer.hasReachedMaxTime());
    }
}
