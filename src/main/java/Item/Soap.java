package Item;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Soap extends Item
{
    public Soap (View view)
    {
        super(view);

        pos_x = 750;
        pos_y = 300;

        width = 40;
        height = 40;

        margin_x = width + 20;
        margin_y = height + 20;

        try
        {
            image = ImageIO.read(new File("src/main/resources/item/soap.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
