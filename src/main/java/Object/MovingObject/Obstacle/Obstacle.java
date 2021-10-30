package Object.MovingObject.Obstacle;

import Main.View;
import Object.GameObject;
import Object.MovingObject.MovingObject;

import java.awt.*;

public class Obstacle extends MovingObject
{
    public Obstacle (View view)
    {
        super(view);
        movingDelay = 0.01;
    }

    public void draw (Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }
}
