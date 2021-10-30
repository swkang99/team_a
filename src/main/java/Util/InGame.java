package Util;

import Main.View;
import Object.Character.Chr;
import Object.Character.Pomeranian;
import Object.MovingObject.Item.*;
import Object.MovingObject.Obstacle.Obstacle;
import Object.MovingObject.Obstacle.pix2.Bamboo;
import Object.MovingObject.Obstacle.pix2.BigDog;
import Object.MovingObject.Obstacle.pix2.Sign;
import Object.MovingObject.Obstacle.pix2.TrashCan;
import Object.MovingObject.Obstacle.flying_pix1.Bird;
import Object.MovingObject.Obstacle.pix1.Bollard;
import Object.MovingObject.Obstacle.pix1.DogHouse;
import Object.MovingObject.Obstacle.pix1.FirePlug;
import Object.MovingObject.Obstacle.pix1.TrashBag;

import java.awt.*;
import java.util.Random;

public class InGame
{
    private Chr chr;
    private Obstacle[] obs = new Obstacle[OBSTACLE.values().length];
    private Item[] item = new Item[ITEM.values().length];

    private BGScroll bgScroll;

    private Time time;

    private Collision obsCollision;
    private Collision itemCollision;

    private double makingDelay = 3;

    public InGame(View view)
    {
        InitObjects(view);

        obsCollision = new Collision();
        itemCollision = new Collision();

        bgScroll = new BGScroll(view);

        time = new Time();
    }

    private void InitObjects(View view)
    {
        // Character
        chr = new Pomeranian(view);
        view.addKeyListener(chr);

        // Flying obs pix 1
        obs[0] = new Bird(view);

        // obs pix 1
        obs[1] = new Bollard(view);
        obs[2] = new DogHouse(view);
        obs[3] = new FirePlug(view);
        obs[4] = new TrashBag(view);

        // obs pix 2
        obs[5] = new Bamboo(view);
        obs[6] = new BigDog(view);
        obs[7] = new Sign(view);
        obs[8] = new TrashCan(view);

        item[0] = new DogBone(view);
        item[1] = new DogFood(view);
        item[2] = new Soap(view);
        item[3] = new Water(view);

    }

    public void Update(Graphics g, View view)
    {
        bgScroll.draw(g, view);

        chr.draw(g, view);

        for (int i = 0; i < obs.length; i++)
        {
            if (obs[i].isEnable())
            {
                obs[i].draw(g, view);
                CheckObsCollision(i);
            }
        }

        for (int i = 0; i < item.length; i++)
        {
            if (item[i].isEnable())
            {
                item[i].draw(g, view);
                CheckItemCollision(i);
            }
        }

        if (time.timeCtrl(makingDelay))
        {
            System.out.println("making");
            MakeMovingObject();
        }
    }

    private void CheckObsCollision (int index)
    {
        boolean obsTrigger = obsCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                obs[index].getPos_x(), obs[index].getPos_y(), obs[index].getMargin_x(), obs[index].getMargin_y());

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

        // checking invincible
        if (chr.isInvincible())
        {
            if (time.timeCtrl(chr.getInvincibleTime()))
            {
                chr.setInvincible(false);
                System.out.println(chr.isInvincible());
            }
        }
    }

    private void CheckItemCollision (int index)
    {
        boolean itemTrigger = itemCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                item[index].getPos_x(), item[index].getPos_y(), item[index].getWidth(), item[index].getHeight());
        //System.out.println("chr checking: " + chr.getPos_x() + ", " + chr.getPos_y());
        //System.out.println("item checking: " + item.getPos_x() + ", " + item.getPos_y());
        // item trigger
        if (itemTrigger)
        {

            System.out.println("Item get: " + item.toString());

        }
    }

    private void MakeMovingObject ()
    {
        Random rand = new Random();

        switch (rand.nextInt(2))
        {
            case 0:
            // Making Item
            int rand_item = rand.nextInt(item.length);
            item[rand_item].Activate();

            case 1:
            // Making Obs
            int rand_obs = rand.nextInt(obs.length);
            obs[rand_obs].Activate();
        }
    }
}
