package Object.MovingObject.Obstacle.pix2;

import Main.View;
import Object.MovingObject.Obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TrashCan_Down extends ObstaclePix2
{
    public TrashCan_Down(View view)
    {
        super(view);

        try
        {
            image = ImageIO.read(new File("src/main/resources/obs/2 pix/trash_down.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
