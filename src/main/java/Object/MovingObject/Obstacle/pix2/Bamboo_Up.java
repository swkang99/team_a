package Object.MovingObject.Obstacle.pix2;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bamboo_Up extends ObstaclePix2
{
    public Bamboo_Up (View view)
    {
        super(view);

        try
        {
            image = ImageIO.read(new File("src/main/resources/obs/2 pix/bamboo_up.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
