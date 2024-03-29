package com.suprun.seaBattleGame.entity;

public class Ship {
    private int size;
    private int x;
    private int y;
    private boolean rotate;

    public Ship(int size, int x, int y, boolean rotate) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.rotate = rotate;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isRotate() {
        return rotate;
    }

    @Override
    public String toString() {
        return "com.suprun.seaBattleGame.entity.Ship{" +
                "size=" + size +
                ", x=" + x +
                ", y=" + y +
                ", rotate=" + rotate +
                '}';
    }
}
