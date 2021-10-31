package Object.MovingObject.Item;

import Main.View;
import Object.Character.Chr;
import Object.GameObject;
import Object.MovingObject.MovingObject;
import Util.InGame;

import java.awt.*;

public class Item extends MovingObject
{
    protected int plusScore = 10;

    public Item(View view)
    {
        super(view);
        gap = 3;
        movingDelay = super.gameSpeed;
    }

    public void draw(Graphics g, View view)
    {
        super.draw(g, view);
        super.Move();
    }

    public void ItemEffect(Chr chr)
    {
        InGame.score += plusScore;
    }
}
