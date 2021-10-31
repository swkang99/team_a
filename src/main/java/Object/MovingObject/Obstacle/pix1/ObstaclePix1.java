package Object.MovingObject.Obstacle.pix1;

import Main.MainFrame;
import Main.View;
import Object.MovingObject.Obstacle.Obstacle;

import java.awt.*;
import java.util.Random;

public class ObstaclePix1 extends Obstacle
{
    public ObstaclePix1(View view)
    {
        super(view);

        gap = 5;

        width = 70;
        height = 70;

        margin_x = width - 50;
        margin_y = height - 50;
    }

    public void draw (Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }

    @Override
    public void Activate ()
    {
        pos_x = MainFrame.frameWidth;
        pos_y = obj_pos_y[2];

        super.isEnable = true;
    }
}
