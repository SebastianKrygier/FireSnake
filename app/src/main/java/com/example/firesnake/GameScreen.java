package com.example.firesnake;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private String nickname;
    private SnakeMain game;
    private int width = 600;
    private int height = 1000;
    private OrthographicCamera camera = new OrthographicCamera(width, height);
    private Viewport viewport;
    private GameState gameState;

    public GameScreen(SnakeMain game, String nickname) {
        this.game = game;
        this.nickname = nickname;
        gameState = new GameState(game,nickname);

        camera.setToOrtho(false,width,height);
        viewport = new FitViewport(width,height,camera);
        viewport.apply();
    }

    @Override
    public void render(float delta) {
        camera.update();
        viewport.apply();

        gameState.upadte(delta, viewport);

        Gdx.gl.glClearColor((float)0.7,(float)0.7,(float)0.7,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameState.draw(width, height, camera);
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
