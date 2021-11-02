package Object.MovingObject.Obstacle.pix2;

import Main.View;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bamboo_Down extends ObstaclePix2
{
    public Bamboo_Down (View view)
    {
        super(view);

        try
        {
            image = ImageIO.read(new File("src/main/resources/obs/2 pix/bamboo_down.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
