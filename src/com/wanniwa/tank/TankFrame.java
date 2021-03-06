package com.wanniwa.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 500, Dir.UP, Group.GOOD,this);
    //子弹
    List<Bullet> bullets = new ArrayList<>();
    //地方坦克
    List<Tank> tanks = new ArrayList<>();
    //爆炸
    List<Explode> explodes = new ArrayList<>();

    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyHandler());
    }



    class KeyHandler extends KeyAdapter {

        boolean bU = false;
        boolean bD = false;
        boolean bL = false;
        boolean bR = false;

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        public void setMainTankDir() {
            myTank.setMoving(bU || bD || bL || bR);
            if (bU) {
                myTank.setDir(Dir.UP);
            }
            if (bD) {
                myTank.setDir(Dir.DOWN);
            }
            if (bL) {
                myTank.setDir(Dir.LEFT);
            }
            if (bR) {
                myTank.setDir(Dir.RIGHT);
            }
        }

    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量"+bullets.size(),10,60);
        g.drawString("敌人的数量"+tanks.size(),10,80);
        g.drawString("爆炸的数量"+explodes.size(),10,100);
        g.setColor(color);
        myTank.paint(g);

        //坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        // 子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //碰撞
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        //爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

    }


}
