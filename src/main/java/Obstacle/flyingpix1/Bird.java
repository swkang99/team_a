package Obstacle.flyingpix1;

import Main.View;
import Obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bird extends Obstacle
{
    public Bird(View view)
    {
        super(view);

        pos_x = 700;
        pos_y = 460;
        gap = 5;

        width = 40;
        height = width;

        margin_x = 0;
        margin_y = -25;

        try
        {
            image = ImageIO.read(new File("src/main/resources/obs/flying 1 pix/bird.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
