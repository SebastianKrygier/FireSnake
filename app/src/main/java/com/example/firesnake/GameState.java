package com.example.firesnake;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameState {

    private SnakeMain snakeMain;

    private int score = 0;
    private float mTimer = 0;
    private int boardSize = 30;
    private int yOffset = 400;
    private int snakeLength = 3;
    private String nickname;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Queue<Bodypart> mBody =  new Queue<>();
    private Controls controls = new Controls();
    private Food mFood = new Food(boardSize);


    //font
    private BitmapFont font = new BitmapFont();

    private SpriteBatch spriteBatch = new SpriteBatch();
    private CharSequence str;

    private Texture arrow = new Texture(Gdx.files.internal("arrow.png"));
    private Sprite rightArrowSprite = new Sprite(arrow,0,0,512,512);
    private Sprite leftArrowSprite = new Sprite(arrow,0,0,512,512);
    private Sprite upArrowSprite = new Sprite(arrow,0,0,512,512);
    private Sprite downArrowSprite = new Sprite(arrow,0,0,512,512);

    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private TextField textField = new TextField("Nickname",skin);


    public GameState(SnakeMain sm,String nickname) {
        snakeMain = sm;
        this.nickname = nickname;

        mBody.addLast(new Bodypart(15,15,boardSize)); //head
        mBody.addLast(new Bodypart(15,14,boardSize));
        mBody.addLast(new Bodypart(15,13,boardSize));


        rightArrowSprite.setPosition(685,315);
        rightArrowSprite.setSize(200,200);

        leftArrowSprite.rotate((float)180);
        leftArrowSprite.setPosition(-120,0);
        leftArrowSprite.setSize(200,200);

        upArrowSprite.rotate((float)90);
        upArrowSprite.setPosition(130,605);
        upArrowSprite.setSize(200,200);

        downArrowSprite.rotate((float)270);
        downArrowSprite.setPosition(440,-280);
        downArrowSprite.setSize(200,200);

    }

    public void upadte(float delta, Viewport viewport){
        mTimer += delta;
        if (mTimer > 0.1f) {
            mTimer = 0;
            advance();
        }
        controls.update(viewport);
    }

    private void isDead() { if(mBody.first().getDead()){snakeMain.setLeaderscoreScreen(nickname,score);} }

    private void advance() {
        int headX = mBody.first().getX();
        int headY = mBody.first().getY();

        //moving
        switch (controls.getDirection()) {
            case 0:
                mBody.addFirst(new Bodypart(headX, headY+1,boardSize));
                isDead();
                break;
            case 1:
                mBody.addFirst(new Bodypart(headX+1, headY,boardSize));
                isDead();
                break;
            case 2:
                mBody.addFirst(new Bodypart(headX, headY-1,boardSize));
                isDead();
                break;
            case 3:
                mBody.addFirst(new Bodypart(headX-1, headY,boardSize));
                isDead();
                break;
            default:
                mBody.addFirst(new Bodypart(headX, headY+1,boardSize));
                isDead();
                break;
        }

        //ate himself - END
        for (int i = 1; i<mBody.size; i++) {
            if (mBody.get(i).getX() == mBody.first().getX() && mBody.get(i).getY() == mBody.first().getY()) {snakeMain.setLeaderscoreScreen(nickname,score);}
        }

        //ate food
        if (mBody.first().getX() == mFood.getX() && mBody.first().getY() == mFood.getY()) {
            snakeLength++;
            score++;
            mFood.randomisePos(boardSize);
        }
        if (mBody.size - 1 >= snakeLength) {mBody.removeLast();}

        while (mBody.size - 1 >= snakeLength) {mBody.removeLast();}
    }

    public void draw(int width, int height, OrthographicCamera camera){
        str = String.valueOf(score);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //drawing board
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0, yOffset, width, width);
        shapeRenderer.setColor((float)0.1,(float)0.5,(float)0.1,1);
        shapeRenderer.rect(0+5, yOffset+5, width-5*2, width-5*2);


        // drawing snake
        shapeRenderer.setColor((float)0.8,(float)0.7,(float)0.05,1);
        float scaleSnake = (float)width/boardSize;
        for (Bodypart bp : mBody) {
            shapeRenderer.rect(bp.getX()*scaleSnake, bp.getY()*scaleSnake + yOffset, scaleSnake, scaleSnake);
        }

        //drawing food
        shapeRenderer.setColor(0,1,0,1);
        shapeRenderer.rect(mFood.getX() * scaleSnake, mFood.getY()*scaleSnake + yOffset, scaleSnake, scaleSnake);

        shapeRenderer.setColor(0,0,0, 1);
        shapeRenderer.rect(235, 265, 130, 135);
        shapeRenderer.rect(235, 0, 130, 135);
        shapeRenderer.rect(105,135,130,130);
        shapeRenderer.rect(365,135,130,130);

        shapeRenderer.setColor((float)0.8,(float)0.8,(float)0.8,1);
        shapeRenderer.rect(237, 266, 126, 134);
        shapeRenderer.rect(237, 2, 126, 132);
        shapeRenderer.rect(107,138,126,124);
        shapeRenderer.rect(367,137,126,126);

        shapeRenderer.end();


        spriteBatch.begin();

        //frameSprite.draw(spriteBatch);

        rightArrowSprite.draw(spriteBatch);
        leftArrowSprite.draw(spriteBatch);
        upArrowSprite.draw(spriteBatch);
        downArrowSprite.draw(spriteBatch);


        font.setColor(0,0,0,1);
        font.getData().setScale((float)6,(float)5);
        font.draw(spriteBatch, "Score: " + str, 35, 785);
        font.draw(spriteBatch, nickname, 670, 785);
        spriteBatch.end();

    }
}
