package Obstacle;

import Main.View;
import Util.GameObject;

import java.awt.*;

public class Obstacle extends GameObject {

    private double movingDelay = 0.05;

    public Obstacle (View view) {
        super(view);
    }

    public void draw (Graphics g, View view) {
        super.draw(g, view);
        Move();
    }

    private void Move () {
        if (pos_x >= 0) {
            if (time.timeCtrl(movingDelay))
                pos_x -= gap;
        }
        else {
            pos_x = 800;
        }
    }
}
