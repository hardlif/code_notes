package com.zwpra.TankGame;

import java.awt.*;
import java.util.Vector;

public class Tank {
    private int x;
    private int y;
    private Color color;
    private char direction;
    public Vector<Bullet> bullets;
    public boolean isTankAlive = true;

    public Tank(int x, int y, Color color, char direction) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.direction = direction;
        bullets=new Vector<>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
