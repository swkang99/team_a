package Object.MovingObject.Item;

import Main.View;
import Object.Character.Chr;
import Util.Audio;
import Util.InGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DogBone extends Item
{
    private double invincibleTimebyDogBone;

    public DogBone (View view)
    {
        super(view);

        width = 50;
        height = 50;

        margin_x = width - 20;
        margin_y = height - 20;

        invincibleTimebyDogBone = 7;

        try
        {
            image = ImageIO.read(new File("src/main/resources/item/dogbone.png"));
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
        chr.setInvincible(invincibleTimebyDogBone, false);
        super.DisableItem();
    }
}
