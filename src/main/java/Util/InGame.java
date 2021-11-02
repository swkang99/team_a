package Util;

import Main.MainFrame;
import Main.View;
import Object.Character.Chr;
import Object.Character.Heart;
import Object.Character.Pomeranian;
import Object.MovingObject.Item.*;
import Object.MovingObject.Obstacle.Obstacle;
import Object.MovingObject.Obstacle.pix2.*;
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
    private Obstacle[] obsPix1;
    private ObstaclePix2[] obsPix2Up;
    private ObstaclePix2[] obsPix2Down;
    private Item[] item;
    private Heart[] heart;

    private BGScroll bgScroll;

    private Time makingTime;
    private Time scoreUpTime;

    private Collision obsCollision;
    private Collision itemCollision;

    private double makingDelay;
    private double scoreUpDelay;
    private double invincibleTimeByObs;

    public static int score = 0;

    Random rand = new Random();

    private Audio itemSound;
    private Audio hitSound;
    private Audio ingameBGM;

    private Font font;

    public InGame(View view)
    {
        InitObjects(view);
        InitHeart();

        obsCollision = new Collision();
        itemCollision = new Collision();

        bgScroll = new BGScroll(view);

        makingTime = new Time();
        scoreUpTime = new Time();

        makingDelay = 1.7;
        scoreUpDelay = 0.13;
        invincibleTimeByObs = 7;

        itemSound = new Audio("src/main/resources/sounds/item.wav", false);
        hitSound = new Audio("src/main/resources/sounds/hit.wav", false);
        ingameBGM = new Audio("src/main/resources/sounds/ingamebgm.wav", true);
        ingameBGM.start();

        font = new Font("Serif", Font.PLAIN, 30);
    }

    private void InitObjects(View view)
    {
        // Character
        chr = new Pomeranian(view);
        view.addKeyListener(chr);

        int amountObsPix1 = 2;
        obsPix1 = new Obstacle[OBSTACLEPIX1.values().length * amountObsPix1];

        for (int i = 0; i < amountObsPix1; i++)
        {
            // Flying obs pix 1
            obsPix1[0 + i * OBSTACLEPIX1.values().length] = new Bird(view);

            // obs pix 1
            obsPix1[1 + i * OBSTACLEPIX1.values().length] = new Bollard(view);
            obsPix1[2 + i * OBSTACLEPIX1.values().length] = new DogHouse(view);
            obsPix1[3 + i * OBSTACLEPIX1.values().length] = new FirePlug(view);
            obsPix1[4 + i * OBSTACLEPIX1.values().length] = new TrashBag(view);
        }

        int amountObsPix2Up = 2;
        obsPix2Up = new ObstaclePix2[OBSTACLEPIX2UP.values().length * amountObsPix2Up];

        for (int i = 0; i < amountObsPix2Up; i++)
        {
            // obs pix 2 Up
            obsPix2Up[0 + i * OBSTACLEPIX2UP.values().length] = new Bamboo_Up(view);
            obsPix2Up[1 + i * OBSTACLEPIX2UP.values().length] = new BigDog_Up(view);
            obsPix2Up[2 + i * OBSTACLEPIX2UP.values().length] = new Sign_Up(view);
            obsPix2Up[3 + i * OBSTACLEPIX2UP.values().length] = new TrashCan_Up(view);
        }

        int amountObsPix2Down = 2;
        obsPix2Down = new ObstaclePix2[OBSTACLEPIX2DOWN.values().length * amountObsPix2Up];

        for (int i = 0; i < amountObsPix2Down; i++)
        {
            // obs pix 2 Down
            obsPix2Down[0 + i * OBSTACLEPIX2DOWN.values().length] = new Bamboo_Down(view);
            obsPix2Down[1 + i * OBSTACLEPIX2DOWN.values().length] = new BigDog_Down(view);
            obsPix2Down[2 + i * OBSTACLEPIX2DOWN.values().length] = new Sign_Down(view);
            obsPix2Down[3 + i * OBSTACLEPIX2DOWN.values().length] = new TrashCan_Down(view);
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

        for (int i = 0; i < obsPix1.length; i++)
        {
            if (obsPix1[i].isEnable())
            {
                obsPix1[i].draw(g, view);
                CheckObsCollision(obsPix1, i);
            }
        }

        for (int i = 0; i < obsPix2Down.length; i++)
        {
            if (obsPix2Down[i].isEnable())
            {
                obsPix2Down[i].draw(g, view);
                obsPix2Up[i].SetPosition(obsPix2Down[i].getPos_x(), obsPix2Down[i].getPos_y() - obsPix2Up[i].getHeight());
                obsPix2Up[i].draw(g, view);
                CheckObsCollision(obsPix2Down, i);
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

        if (makingTime.timeCtrl(makingDelay))
        {
            MakeMovingObject();
        }

        if (scoreUpTime.timeCtrl(scoreUpDelay))
        {
            score++;
        }

        drawHeart(g, view);
        g.setFont(font);
        g.drawString(Integer.toString(InGame.score), (MainFrame.frameWidth / 2) - 30, 72);
    }

    private void CheckObsCollision (Obstacle[] obs, int index)
    {
        boolean obsTrigger = obsCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                obs[index].getPos_x(), obs[index].getPos_y(), obs[index].getMargin_x(), obs[index].getMargin_y());

        // obstacle trigger
        if (obsTrigger && !chr.isInvincible())
        {
            chr.nowLife -= 1;
            chr.setInvincible(invincibleTimeByObs, true);

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
        switch (rand.nextInt(3))
        {
            case 0:
                MakeItem();
                break;

            case 1:
                MakeObsPix1();
                break;

            case 2:
                MakeObsPix2();
                break;
        }
    }

    private void MakeItem ()
    {
        int rand_item = rand.nextInt(item.length);
        if (!item[rand_item].isEnable())
            item[rand_item].Activate();
    }

    private void MakeObsPix1 ()
    {
        int rand_obs = rand.nextInt(obsPix1.length);
        if (!obsPix1[rand_obs].isEnable())
        {
            obsPix1[rand_obs].Activate();
        }
    }

    private void MakeObsPix2 ()
    {
        int rand_obs = rand.nextInt(obsPix2Down.length);
        if (!obsPix2Down[rand_obs].isEnable())
        {
            obsPix2Down[rand_obs].Activate();
            obsPix2Up[rand_obs].Activate();
        }
    }

    private void drawHeart(Graphics g, View view)
    {
        if (chr.nowLife >= 0)
        {
            for (int i = chr.maxLife - chr.nowLife; i > 0; i--)
            {
                heart[chr.nowLife].SetHeartBlank();
            }

            for (int i = 0; i < chr.nowLife; i++)
            {
                heart[i].SetHeartFill();
            }

            for (int i = 0; i < heart.length; i++)
            {
                heart[i].draw(g, view);
            }
        }
    }
}
