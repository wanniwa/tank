package com.wanniwa.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tank {


    private static final int speed = 5;

    public static int WIDTH;
    public static int HEIGHT;


    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean moving = false;
    private final TankFrame tankFrame;

    private boolean living = true;

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        //Color color = g.getColor();
        //g.setColor(Color.YELLOW);
        //g.fillRect(x,y,50,50);
        //g.setColor(color);
        if (!living) {
            tankFrame.tanks.remove(this);
        }

        BufferedImage bufferedImage;
        switch (dir) {
            case LEFT:
                bufferedImage = ResourceMgr.tankL;
                break;
            case UP:
                bufferedImage = ResourceMgr.tankU;

                break;
            case DOWN:
                bufferedImage = ResourceMgr.tankD;

                break;
            case RIGHT:
                bufferedImage = ResourceMgr.tankR;
                break;
            default:
                bufferedImage = null;
        }
        HEIGHT = bufferedImage.getHeight();
        WIDTH = bufferedImage.getWidth();
        g.drawImage(bufferedImage, x, y, null);
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
    }

    public void fire() {
        int bx = this.x + WIDTH / 2;
        int by = this.y + HEIGHT / 2;

        switch (dir) {
            case LEFT:
                bx -= WIDTH / 2;
                break;
            case UP:
                by -= HEIGHT / 2;
                break;
            case DOWN:
                by += HEIGHT / 2;
                break;
            case RIGHT:
                bx += WIDTH / 2;
                break;
        }
        tankFrame.bullets.add(new Bullet(bx, by, this.dir, this.tankFrame));
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void die() {
        this.living = false;
    }
}
