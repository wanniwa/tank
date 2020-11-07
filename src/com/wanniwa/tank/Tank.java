package com.wanniwa.tank;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Random;

public class Tank {

    private static final int speed = 2;

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private final Random random = new Random();
    public Rectangle rect;
    private Group group;
    FireStrategy fireStrategy;

    private int x, y;
    private Dir dir;
    private boolean moving = false;
    private final TankFrame tf;

    private boolean living = true;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        this.rect = new Rectangle(x, y, Tank.WIDTH - 5, Tank.HEIGHT - 5);
        if (group == Group.BAD) {
            moving = true;
        }
        this.fireStrategy = group == Group.GOOD ? new FourDirFireStrategy() : new DefaultFireStrategy();
    }

    public void paint(Graphics g) {
        //Color color = g.getColor();
        //g.setColor(Color.YELLOW);
        //g.fillRect(x,y,50,50);
        //g.setColor(color);
        if (!living) {
            tf.tanks.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
        if (!living) return;
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

        if (this.getGroup() == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        //随机方向
        if (this.getGroup() == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fireStrategy.fire(this);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void die() {
        this.living = false;
    }

    public TankFrame getTf() {
        return tf;
    }
}
