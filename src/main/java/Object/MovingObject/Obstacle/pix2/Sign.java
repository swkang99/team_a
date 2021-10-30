package Object.MovingObject.Obstacle.pix2;

import Main.View;
import Object.MovingObject.Obstacle.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Sign extends Obstacle
{
    public Sign(View view)
    {
        super(view);

        gap = 5;

        width = 40;
        height = 40 * 2;

        margin_x = 0;
        margin_y = -25;

        try
        {
            image = ImageIO.read(new File("src/main/resources/obs/2 pix/sign.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
