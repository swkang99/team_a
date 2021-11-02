package Object.MovingObject.Obstacle.flying_pix1;

import Main.MainFrame;
import Main.View;
import Object.MovingObject.Obstacle.Obstacle;

import java.awt.*;
import java.util.Random;

public class ObstacleFlyingPix1 extends Obstacle
{
    public ObstacleFlyingPix1(View view)
    {
        super(view);

        gap = 5;

        width = 40;
        height = 40;

        margin_x = width - 20;
        margin_y = height - 20;
    }

    public void draw (Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }
}
