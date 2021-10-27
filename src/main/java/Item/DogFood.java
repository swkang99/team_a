package Item;

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

        pos_x = 750;
        pos_y = 370;

        width = 40;
        height = 40;

        margin_x = width;
        margin_y = height;

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
