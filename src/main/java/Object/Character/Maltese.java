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
            image_basic = ImageIO.read(new File("src/main/resources/chr/Mal/basic.png"));
            image_basic = image_basic.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_basic_invincible = ImageIO.read(new File("src/main/resources/chr/Mal/basic_invincible.png"));
            image_basic_invincible = image_basic_invincible.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_run = ImageIO.read(new File("src/main/resources/chr/Mal/run.png"));
            image_run = image_run.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_run_invincible = ImageIO.read(new File("src/main/resources/chr/Mal/run_invincible.png"));
            image_run_invincible = image_run_invincible.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_die = ImageIO.read(new File("src/main/resources/chr/Mal/dead.png"));
            image_die = image_die.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_die_alphaSet = ImageIO.read(new File("src/main/resources/chr/Mal/dead_alphaset.png"));
            image_die_alphaSet = image_die_alphaSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image = image_basic;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
