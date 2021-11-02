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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class InGame
{
    private Chr chr;
    private Obstacle[] obs;
    private Item[] item;
    private Heart[] heart;

    private BGScroll bgScroll;

    private Time makingTime;

    private Collision obsCollision;
    private Collision itemCollision;

    private double makingDelay;
    private double invincibleTimeByObs;

    public static int score = 0;

    Random rand = new Random();

    private Audio itemSound;
    private Audio hitSound;
    private Audio ingameBGM;

    public InGame(View view)
    {
        InitObjects(view);
        InitHeart();

        obsCollision = new Collision();
        itemCollision = new Collision();

        bgScroll = new BGScroll(view);

        makingTime = new Time();

        makingDelay = 1.7;
        invincibleTimeByObs = 7;

        itemSound = new Audio("src/main/resources/sounds/item.wav", false);
        hitSound = new Audio("src/main/resources/sounds/hit.wav", false);
        ingameBGM = new Audio("src/main/resources/sounds/ingamebgm.wav", true);
        ingameBGM.start();


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
            item[1 + i * ITEM.values().length] = new DogBone(view);
            item[2 + i * ITEM.values().length] = new DogBone(view);
            item[3 + i * ITEM.values().length] = new DogBone(view);
        }
    }

    private void InitHeart ()
    {
        heart = new Heart[chr.maxLife];

        for (int i = 0; i < heart.length; i++)
        {
            heart[i] = new Heart(40 + i * 40,40);
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

        for (int i = 0; i < heart.length; i++)
        {
            heart[i].draw(g, view);
        }

        if (makingTime.timeCtrl(makingDelay))
        {
            MakeMovingObject();
        }

        score++;

    }

    private void CheckObsCollision (int index)
    {
        boolean obsTrigger = obsCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                obs[index].getPos_x(), obs[index].getPos_y(), obs[index].getMargin_x(), obs[index].getMargin_y());

        // obstacle trigger
        if (obsTrigger && !chr.isInvincible())
        {
            chr.nowLife -= 1;
            chr.setInvincible(invincibleTimeByObs, true);

            heart[chr.nowLife].BreakHeart();

            chr.setHitAnimSwitch(true);
            hitSound.start();
        }
    }

    private void CheckItemCollision (int index)
    {
        boolean itemTrigger = itemCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                item[index].getPos_x(), item[index].getPos_y(), item[index].getMargin_x(), item[index].getMargin_y());

        // item trigger
        if (itemTrigger)
        {
            item[index].ItemEffect(chr);
            itemSound.start();
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
