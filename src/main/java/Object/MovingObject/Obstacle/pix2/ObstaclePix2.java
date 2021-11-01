package Object.MovingObject.Obstacle.pix2;

import Main.MainFrame;
import Main.View;
import Object.MovingObject.MovingObject;
import Object.MovingObject.Obstacle.Obstacle;

import java.awt.*;
import java.util.Random;

public class ObstaclePix2 extends Obstacle
{
    public ObstaclePix2(View view)
    {
        super(view);

        gap = 5;

        width = 70;
        height = 70 * 2;

        margin_x = width - 40;
        margin_y = height - 90;
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
        pos_y = obj_pos_y[2] - 80;

        super.isEnable = true;
    }
}
