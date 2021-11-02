package Object.Character;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pomeranian extends Chr
{
    public Pomeranian(View view)
    {
        super(view);

        try
        {
            image_basic = ImageIO.read(new File("src/main/resources/chr/Po/basic.png"));
            image_basic = image_basic.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_basic_invincible = ImageIO.read(new File("src/main/resources/chr/Po/basic_invincible.png"));
            image_basic_invincible = image_basic_invincible.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_run = ImageIO.read(new File("src/main/resources/chr/Po/run.png"));
            image_run = image_run.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_run_invincible = ImageIO.read(new File("src/main/resources/chr/Po/run_invincible.png"));
            image_run_invincible = image_run_invincible.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_die = ImageIO.read(new File("src/main/resources/chr/Po/dead.png"));
            image_die = image_die.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image_die_alphaSet = ImageIO.read(new File("src/main/resources/chr/Po/dead_alphaset.png"));
            image_die_alphaSet = image_die_alphaSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            image = image_basic;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
