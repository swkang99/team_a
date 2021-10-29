package Character;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Beagle extends Chr
{
    public Beagle(View view)
    {
        super(view);

        width = 80;
        height = 80;

        margin_x = width;
        margin_y = height;

        try
        {
            image = ImageIO.read(new File("src/main/resources/chr/Bi/basic.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        pos_x = 50;
        pos_y = super.ground_y;
    }
}
