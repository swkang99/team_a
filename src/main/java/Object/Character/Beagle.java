package Object.Character;

import Main.MainFrame;
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

        try
        {
            image = ImageIO.read(new File("src/main/resources/chr/Bi/basic.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
