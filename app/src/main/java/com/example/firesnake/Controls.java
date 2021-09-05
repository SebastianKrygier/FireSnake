package com.example.firesnake;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Rectangle;


public class Controls {

    private final static int up = 0;
    private final static int right = 1;
    private final static int down = 2;
    private final static int left = 3;

    private int currentDirection;
    private int nextDirection;

    private Vector2 touch = new Vector2();

    private Rectangle upBox = new Rectangle(235,265,130,135);
    private Rectangle downBox = new Rectangle(235,0,130,135);
    private Rectangle leftBox = new Rectangle(65,135,130,135);
    private Rectangle rightBox = new Rectangle(365,135,130,135);

    public int getDirection() {
        currentDirection = nextDirection;
        return nextDirection;
    }


    public void update(Viewport viewport) {
        if (Gdx.input.isTouched()) {
            touch.x = Gdx.input.getX();
            touch.y = Gdx.input.getY();
            viewport.unproject(touch);
        }

        if((Gdx.input.isKeyPressed(Input.Keys.UP) || upBox.contains(touch)) && currentDirection != down) nextDirection = up;

        else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightBox.contains(touch)) && currentDirection != left) nextDirection = right;

        else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || downBox.contains(touch)) && currentDirection != up) nextDirection = down;

        else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftBox.contains(touch)) && currentDirection != right) nextDirection = left;
    }

}
