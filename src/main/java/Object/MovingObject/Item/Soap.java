package Object.MovingObject.Item;

import Main.View;
import Object.Character.Chr;
import Util.InGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Soap extends Item
{
    public Soap (View view)
    {
        super(view);

        width = 40;
        height = 40;

        margin_x = width - 20;
        margin_y = height - 35;

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

    @Override
    public void ItemEffect (Chr chr)
    {
        InGame.score += plusScore;

        if (chr.nowLife < chr.maxLife)
            chr.nowLife += 1;

        super.DisableItem();
    }
}
