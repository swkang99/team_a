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
    private Obstacle[] obs;
    private Item[] item;

    private BGScroll bgScroll;

    private Time time;

    private Collision obsCollision;
    private Collision itemCollision;

    private double makingDelay;
    private double invincibleTimeByObs;

    public static int score = 0;

    Random rand = new Random();

    public InGame(View view)
    {
        InitObjects(view);

        obsCollision = new Collision();
        itemCollision = new Collision();

        bgScroll = new BGScroll(view);

        time = new Time();

        makingDelay = 1.7;
        invincibleTimeByObs = 7;
    }

    private void InitObjects(View view)
    {
        // Character
        chr = new Pomeranian(view);
        view.addKeyListener(chr);

        int amountObs = 2;
        obs = new Obstacle[OBSTACLE.values().length * amountObs];

        for (int i = 0; i < amountObs; i++)
        {
            // Flying obs pix 1
            obs[0 + i * OBSTACLE.values().length] = new Bird(view);

            // obs pix 1
            obs[1 + i * OBSTACLE.values().length] = new Bollard(view);
            obs[2 + i * OBSTACLE.values().length] = new DogHouse(view);
            obs[3 + i * OBSTACLE.values().length] = new FirePlug(view);
            obs[4 + i * OBSTACLE.values().length] = new TrashBag(view);

            // obs pix 2
            obs[5 + i * OBSTACLE.values().length] = new Bamboo(view);
            obs[6 + i * OBSTACLE.values().length] = new BigDog(view);
            obs[7 + i * OBSTACLE.values().length] = new Sign(view);
            obs[8 + i * OBSTACLE.values().length] = new TrashCan(view);
        }

        int amountItem = 2;
        item = new Item[ITEM.values().length * amountItem];

        // item
        for (int i = 0; i < amountItem; i++)
        {
            item[0 + i * ITEM.values().length] = new DogBone(view);
            item[1 + i * ITEM.values().length] = new DogFood(view);
            item[2 + i * ITEM.values().length] = new Soap(view);
            item[3 + i * ITEM.values().length] = new Water(view);
        }
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
            chr.nowLife -= 1;
            System.out.println("life: " + chr.nowLife);
            chr.setInvincible(invincibleTimeByObs);
            System.out.println("invincible state: " + chr.isInvincible());

            chr.setHitAnimSwitch(true);
        }
    }

    private void CheckItemCollision (int index)
    {
        boolean itemTrigger = itemCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                item[index].getPos_x(), item[index].getPos_y(), item[index].getMargin_x(), item[index].getMargin_y());

        // item trigger
        if (itemTrigger)
        {
            System.out.println("Item get: " + item[index].toString());
            item[index].ItemEffect(chr);
        }
    }

    private void MakeMovingObject ()
    {
        switch (rand.nextInt(2))
        {
            case 0:
                MakeItem();

            case 1:
                MakeObs();
        }
    }

    private void MakeItem ()
    {
        int rand_item = rand.nextInt(item.length);
        if (!item[rand_item].isEnable())
            item[rand_item].Activate();
    }

    private void MakeObs ()
    {
        int rand_obs = rand.nextInt(obs.length);
        if (!obs[rand_obs].isEnable())
            obs[rand_obs].Activate();
    }
}
