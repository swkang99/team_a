package Util;

import Main.MainFrame;
import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BGScroll extends GameObject
{

    private double drawDelay = 0.001;
    private int pos_x2;

    public BGScroll (View view)
    {
        super(view);
        try
        {
            image = ImageIO.read(new File("src/main/resources/bg/bg1.png"));
            image = image.getScaledInstance(MainFrame.frameWidth * 2, MainFrame.frameHeight, Image.SCALE_SMOOTH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        gap = 5;
        pos_x = 0;
        pos_y = 0;
        pos_x2 = image.getWidth(null);
    }

    public void draw (Graphics g, View view)
    {
        Scroll();
        super.draw(g, view);
        g.drawImage(image, pos_x2, pos_y, view);
    }

    public void Scroll ()
    {
        if (time.timeCtrl(drawDelay))
        {
            pos_x -= gap;
            pos_x2 -= gap;

            if (pos_x <= -(image.getWidth(null)))
                pos_x = image.getWidth(null);
            if (pos_x2 <= -(image.getWidth(null)))
                pos_x2 = image.getWidth(null);
        }
    }
}
