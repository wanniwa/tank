package com.wanniwa;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x,y =10;
    public TankFrame(){
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x,y,50,50);
        x+=50;
        y+=50;
        g.setColor(Color.lightGray);
    }
}
