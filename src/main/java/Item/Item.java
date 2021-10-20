package Item;

import Main.View;
import Util.GameObject;

import java.awt.*;

public class Item extends GameObject {

    private double movingDelay = 0.01;

    public Item (View view) {
        super(view);
        gap = 2;
    }

    public void draw (Graphics g, View view) {
        super.draw(g, view);
        Move();
    }

    public void Move () {
        if (pos_x >= 0) {
            if (time.timeCtrl(movingDelay))
                pos_x -= gap;
        }
        else {
            pos_x = 800;
        }
    }
}
