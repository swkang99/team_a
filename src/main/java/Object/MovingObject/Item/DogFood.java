package Object.MovingObject.Item;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DogFood extends Item
{
    public DogFood (View view)
    {
        super(view);

        width = 60;
        height = 60;

        margin_x = width - 40;
        margin_y = height - 50;

        try
        {
            image = ImageIO.read(new File("src/main/resources/item/dogfood.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
