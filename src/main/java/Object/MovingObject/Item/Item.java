package Object.MovingObject.Item;

import Main.View;
import Object.GameObject;
import Object.MovingObject.MovingObject;

import java.awt.*;

public class Item extends MovingObject
{
    private boolean enable = false;

    public Item(View view)
    {
        super(view);
        gap = 2;
        movingDelay = 0.02;
    }

    public void draw(Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }
}
