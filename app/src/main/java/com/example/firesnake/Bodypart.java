package com.example.firesnake;

public class Bodypart {
    private int x;
    private int y;
    private boolean dead;

    public Bodypart(int x, int y, int boardSize) {

        if (x>=0 && y>=0 && x<boardSize && y<boardSize) dead = false;
        else dead = true;

        this.x = x;
        this.y = y;

    }

    public int getX() {return x;}
    public int getY() {return y;}
    public boolean getDead() {return dead;}
}
