package Object.Character;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Maltese extends Chr
{
    public Maltese(View view)
    {
        super(view);

        try
        {
            image = ImageIO.read(new File("src/main/resources/chr/Mal/basic.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
