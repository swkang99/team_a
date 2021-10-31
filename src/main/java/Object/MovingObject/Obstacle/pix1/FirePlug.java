package Object.MovingObject.Obstacle.pix1;

import Main.View;
import Object.MovingObject.Obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FirePlug extends ObstaclePix1
{
    public FirePlug(View view)
    {
        super(view);

        try
        {
            image = ImageIO.read(new File("src/main/resources/obs/1 pix/sh.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
