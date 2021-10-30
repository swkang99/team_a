package Object.MovingObject.Obstacle;

import Main.View;
import Object.GameObject;
import Object.MovingObject.MovingObject;

import java.awt.*;

public class Obstacle extends MovingObject
{
    private boolean enable = false;

    public Obstacle (View view)
    {
        super(view);
        movingDelay = 0.017;
    }

    public void draw (Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }
}
