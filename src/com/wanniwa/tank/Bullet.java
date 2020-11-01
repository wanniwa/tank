package com.wanniwa.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private static final int SPEED = 10;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x, y;
    private Dir dir;
    private boolean living = true;
    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tf;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bullets.remove(this);
        }

        //Color color = g.getColor();
        //g.setColor(Color.RED);
        //g.fillOval(x,y,WIDTH,HEIGHT);
        //g.setColor(color);
        BufferedImage bufferedImage;
        switch (dir) {
            case LEFT:
                bufferedImage =  ResourceMgr.bulletL;
                break;
            case UP:
                bufferedImage =  ResourceMgr.bulletU;
                break;
            case DOWN:
                bufferedImage =  ResourceMgr.bulletD;
                break;
            case RIGHT:
                bufferedImage = ResourceMgr.bulletR;
                break;
            default:
                bufferedImage = null;
        }
        HEIGHT = bufferedImage.getHeight();
        WIDTH = bufferedImage.getWidth();
        g.drawImage(bufferedImage, x-WIDTH/2, y-HEIGHT/2, null);
        move();
    }

    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }

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

    public void collideWith(Tank tank) {
        Rectangle rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect.intersects(rect2)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        living = false;
    }
}
