package Util;

import Item.DogFood;
import Item.Item;
import Main.View;
import Obstacle.Obstacle;
import Obstacle.pix2.BigDog;
import Character.*;
import Item.DogFood;
import Item.Item;
import Obstacle.pix2.BigDog;
import Obstacle.Obstacle;
import Util.BGScroll;
import Util.Collision;
import Util.Time;
import java.awt.*;

public class InGame
{
    private Chr chr;
    private Obstacle obs;
    private Item item;

    private BGScroll bgScroll;

    private Time time;

    private Collision obsCollision;
    private Collision itemCollision;

    public InGame(View view)
    {
        chr = new Pomeranian(view);
        view.addKeyListener(chr);

        obs = new BigDog(view);

        item = new DogFood(view);

        obsCollision = new Collision();
        itemCollision = new Collision();

        bgScroll = new BGScroll(view);

        time = new Time();
    }

    public void Update(Graphics g, View view)
    {
        bgScroll.draw(g, view);

        chr.draw(g, view);

        obs.draw(g, view);

        item.draw(g, view);

        ObsCollisionCheck();
        ItemCollisionCheck();
        System.out.println("Ingame test");
    }

    public void ObsCollisionCheck ()
    {
        boolean obsTrigger = obsCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                obs.getPos_x(), obs.getPos_y(), obs.getMargin_x(), obs.getMargin_y());

        // obstacle trigger
        if (obsTrigger && !chr.isInvincible())
        {
            chr.life -= 1;
            System.out.println("life: " + chr.life);
            chr.setInvincible(true);
            System.out.println(chr.isInvincible());

            if (chr.life == 0)
            {
                System.out.println("Game Over");
            }
        }

        // invincible check
        if (chr.isInvincible())
        {
            if (time.timeCtrl(chr.getInvincibleTime()))
            {
                chr.setInvincible(false);
                System.out.println(chr.isInvincible());
            }
        }
    }

    public void ItemCollisionCheck ()
    {
        boolean itemTrigger = itemCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                item.getPos_x(), item.getPos_y(), item.getWidth(), item.getHeight());
        //System.out.println("chr checking: " + chr.getPos_x() + ", " + chr.getPos_y());
        //System.out.println("item checking: " + item.getPos_x() + ", " + item.getPos_y());
        // item trigger
        if (itemTrigger)
        {

            System.out.println("Item get: " + item.toString());

        }
    }
}
