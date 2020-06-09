package com.wanniwa.tank;

import com.wanniwa.TankFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
