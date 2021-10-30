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

        width = 40;
        height = 40;

        margin_x = width + 20;
        margin_y = height + 20;

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
