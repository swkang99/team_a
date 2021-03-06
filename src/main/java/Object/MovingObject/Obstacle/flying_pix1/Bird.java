package Object.MovingObject.Obstacle.flying_pix1;

import Main.View;
import Object.MovingObject.Obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bird extends ObstacleFlyingPix1
{
    public Bird(View view)
    {
        super(view);

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
