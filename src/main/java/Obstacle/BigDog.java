package Obstacle;

import Main.View;
import Util.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BigDog extends Obstacle
{
    public BigDog (View view)
    {
        super(view);

        pos_x = 700;
        pos_y = 460;
        gap = 5;

        width = 40;
        height = 80;

        margin_x = 0;
        margin_y = -25;

        try
        {
            image = ImageIO.read(new File("src/main/resources/obs/bigdog.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
