package Object.MovingObject.Item;

import Main.MainFrame;
import Main.View;
import Object.Character.Chr;
import Object.GameObject;
import Object.MovingObject.MovingObject;
import Util.InGame;

import java.awt.*;

public class Item extends MovingObject
{
    protected int plusScore;

    public Item(View view)
    {
        super(view);
        gap = 3;
        movingDelay = InGame.gameSpeed;

        plusScore = 30;
    }

    public void draw(Graphics g, View view)
    {
        movingDelay = InGame.gameSpeed;
        super.draw(g, view);
        super.Move();
    }

    public void ItemEffect(Chr chr)
    {
        InGame.score += plusScore;
        DisableItem();
    }

    protected void DisableItem()
    {
        pos_x = -MainFrame.frameWidth;
        pos_y = -MainFrame.frameHeight;
        isEnable = false;
    }
}
