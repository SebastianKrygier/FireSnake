package com.example.firesnake;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class MenuScreen implements Screen {
    private SnakeMain snakeMain;
    private Game game;
    private Stage stage;
    private String nickname;

    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

    private TextField textField = new TextField("",skin);
    private TextButton textButton = new TextButton("",skin);

    private Texture snakeTexture = new Texture(Gdx.files.internal("snake.png"));
    private Sprite snakeSprite = new Sprite(snakeTexture,0,0,840,849);

    public MenuScreen(SnakeMain sm){
        snakeMain = sm;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        textButton.setBounds(250,300,600,200);
        textButton.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                nickname = textField.getText();
                snakeMain.setGameScreen(nickname);
            }
        });

        textField.setBounds(320,550,450,100);

        stage.addActor(textButton);
        stage.addActor(textField);

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
        font.draw(spriteBatch, "SNAKE", 335, 1785);
        font.draw(spriteBatch, "START", 355, 430);
        font.getData().setScale((float)4,(float)3);
        font.draw(spriteBatch, "NICKNAME:", 375, 710);

        snakeSprite.setPosition(135,800);
        snakeSprite.draw(spriteBatch,1);

        spriteBatch.end();
        //snakeMain.setGameScreen(); // go to game screen
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
