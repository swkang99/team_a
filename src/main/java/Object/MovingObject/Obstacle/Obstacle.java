package Object.MovingObject.Obstacle;

import Main.View;
import Object.GameObject;
import Object.MovingObject.MovingObject;
import Util.InGame;

import java.awt.*;

public class Obstacle extends MovingObject
{
    public Obstacle (View view)
    {
        super(view);
    }

    public void draw (Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }
}
