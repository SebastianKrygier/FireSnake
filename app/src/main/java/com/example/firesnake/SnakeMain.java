package com.example.firesnake;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeMain extends Game implements ApplicationListener {
    SpriteBatch batch;

    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private LeaderscoreScreen leaderscoreScreen;
    private String nickname;

    @Override
    public void create () {


        batch = new SpriteBatch();
        this.setScreen(new MenuScreen(this));
    }

    public void setGameScreen(String nickname) {
        gameScreen=new GameScreen(this,nickname);
        setScreen(gameScreen);

    }
    public void setMenuScreen()
    {
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }
    public void setLeaderscoreScreen(String nickname,int score)
    {
        leaderscoreScreen=new LeaderscoreScreen(this,nickname,score);
        setScreen(leaderscoreScreen);
    }

    @Override
    public void render () {
        super.render();
    }
    @Override
    public void dispose () {
        batch.dispose();
    }
}

