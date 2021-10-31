package Object.MovingObject.Item;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Water extends Item
{
    public Water (View view)
    {
        super(view);

        width = 50;
        height = 50;

        margin_x = width - 35;
        margin_y = height - 35;

        try
        {
            image = ImageIO.read(new File("src/main/resources/item/water.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
