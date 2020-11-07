package com.wanniwa.tank;

import com.wanniwa.tank.abstractfactory.BaseExplode;

import java.awt.*;

public class Explode extends BaseExplode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;
    private final TankFrame tf;
    private int step = 0;

    public Explode(int x, int y,  TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        //爆炸结束后从list中去除
        if (step >= ResourceMgr.explodes.length) {
            tf.explodes.remove(this);
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



}
