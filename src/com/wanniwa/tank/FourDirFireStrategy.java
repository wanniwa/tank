package com.wanniwa.tank;

public class FourDirFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank tank) {

        int width = Tank.WIDTH;
        int height = Tank.HEIGHT;
        Group group = tank.getGroup();


        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            int bx = tank.getX() + width / 2;
            int by = tank.getY() + height / 2;
            switch (dir) {
                case LEFT:
                    bx -= width / 2;
                    break;
                case UP:
                    by -= height / 2;
                    break;
                case DOWN:
                    by += height / 2;
                    break;
                case RIGHT:
                    bx += width / 2;
                    break;
            }
            new Bullet(bx, by, dir, group, tank.getTf());
        }
        if (group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
