package com.badlogic.UniSim2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractHeadlessGdxTest {
    @BeforeEach
    public void setup() {
        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        Gdx.graphics = mock(Graphics.class);

        HeadlessLauncher.main(new String[0]);
    }
}
