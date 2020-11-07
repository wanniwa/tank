package com.wanniwa.tank.abstractfactory;

import com.wanniwa.tank.Dir;
import com.wanniwa.tank.Group;
import com.wanniwa.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);


}
