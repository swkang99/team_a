package Object.MovingObject.Item;

import Main.View;
import Object.GameObject;
import Object.MovingObject.MovingObject;

import java.awt.*;

public class Item extends MovingObject
{
    public Item(View view)
    {
        super(view);
        gap = 3;
        movingDelay = super.gameSpeed;
    }

    public void draw(Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }
}
