package com.badlogic.UniSim2;

import com.badlogic.UniSim2.GUImanager.EndScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test methods for the {@link EndScreen}
 */
public class EndScreenTests extends AbstractHeadlessGdxTest {

	@Test
	public void testLowSatisfaction() {
        EndScreen endScreen = new EndScreen(new Main(), 0, 50, true, new int[] {});

        Assertions.assertFalse(endScreen.hasWon());
	}

    @Test
    public void test499Satisfaction() {
        EndScreen endScreen = new EndScreen(new Main(), 0, 499, true, new int[] {});

        Assertions.assertFalse(endScreen.hasWon());
    }

    @Test
    public void test500Satisfaction() {
        EndScreen endScreen = new EndScreen(new Main(), 0, 500, true, new int[] {});

        Assertions.assertTrue(endScreen.hasWon());
    }

    @Test
    public void testHighSatisfaction() {
        EndScreen endScreen = new EndScreen(new Main(), 0, 5000, true, new int[] {});

        Assertions.assertTrue(endScreen.hasWon());
    }
}
