package Object.Character;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Heart
{
    private int pos_x;
    private int pos_y;

    private int width;
    private int height;

    private Image image;
    private Image heart_fill;
    private Image heart_blank;

    public Heart (int pos_x, int pos_y)
    {
        this.pos_x = pos_x;
        this.pos_y = pos_y;

        width = 40;
        height = 40;

        try
        {
            image = ImageIO.read(new File("src/main/resources/chr/heart_fill.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            heart_fill = ImageIO.read(new File("src/main/resources/chr/heart_fill.png"));
            heart_fill = heart_fill.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            heart_blank = ImageIO.read(new File("src/main/resources/chr/heart_blank.png"));
            heart_blank = heart_blank.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, View view)
    {
        g.drawImage(image, pos_x, pos_y, (ImageObserver) view);
    }

    public void SetHeartFill ()
    {
        image = heart_fill;
    }

    public void SetHeartBlank ()
    {
        image = heart_blank;
    }
}
