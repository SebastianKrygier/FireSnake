package com.example.firesnake;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class LeaderscoreScreen implements Screen {

    private SnakeMain snakeMain;
    private Game game;
    private Stage stage;
    private String nickname;
    private int score;

    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));


    String[] tab = new String[50];


    public LeaderscoreScreen(SnakeMain sm, final String nickname, int score){
        snakeMain = sm;
        this.nickname = nickname;
        this.score = score;



        FirebaseDB db = new FirebaseDB(this.nickname, this.score);

        tab = db.pull();




        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        TextButton textButton = new TextButton("",skin);
        textButton.setBounds(250,300,600,200);
        textButton.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                snakeMain.setGameScreen(nickname);
            }
        });

        stage.addActor(textButton);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)0.7,(float)0.7,(float)0.7,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        spriteBatch.begin();
        font.setColor(0,0,0,1);
        font.getData().setScale((float)8,(float)6);
        font.draw(spriteBatch, "LEADERSCORE", 120, 1785);
        font.draw(spriteBatch, "RESTART", 290, 430);
        font.draw(spriteBatch,nickname + ": " + score , 120,700);

        spriteBatch.end();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
