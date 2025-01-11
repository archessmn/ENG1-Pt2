package com.badlogic.UniSim2;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.UniSim2.Main;

/** Launches the headless application. Can be converted into a utilities project or a server application. */
public class HeadlessLauncher {
    public static void main(String[] args) {
        createApplication();
    }

    public static Application createApplication() {
        // Note: you can use a custom ApplicationListener implementation for the headless project instead of Gemo.
        return new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() { // Pass null or a mock game instance if necessary
            }
            @Override
            public void resize(int width, int height) {}
            @Override
            public void render() {
            }
            @Override
            public void pause() {}
            @Override
            public void resume() {}
            @Override
            public void dispose() {
            }
        }, getDefaultConfiguration());
    }

    private static HeadlessApplicationConfiguration getDefaultConfiguration() {
        HeadlessApplicationConfiguration configuration = new HeadlessApplicationConfiguration();
        configuration.updatesPerSecond = -1; // When this value is negative, Gemo#render() is never called.
        //// If the above line doesn't compile, it is probably because the project libGDX version is older.
        //// In that case, uncomment and use the below line.
        //configuration.renderInterval = -1f; // When this value is negative, Gemo#render() is never called.
        return configuration;
    }
}
