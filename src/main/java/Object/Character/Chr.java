package Object.Character;

import Main.MainFrame;
import Main.View;
import Object.GameObject;
import Util.Audio;
import Util.Time;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Chr extends GameObject implements KeyListener
{
    public int maxLife;
    public int nowLife;

    private boolean jumping;
    private boolean landing;
    private double jumpingDelay;
    private double landingDelay;
    private Time movingTime;

    private double runAnimDelay;
    private double hitAnimDelay;
    private Time runAniTime;
    private Time hitAniTime;

    private boolean invincibleByObs;
    private boolean invincibleByItem;
    private boolean hitAnimSwitch;
    private double invincibleDelay;
    private Time invincibleTime;

    protected Image image_basic;
    protected Image image_basic_invincible;
    protected Image image_run;
    protected Image image_run_invincible;
    protected Image image_die;
    protected Image image_die_alphaSet;

    private Audio jumpSound;
    private Audio gameoverSound;

    public Chr(View view)
    {
        super(view);
        gap = 20;

        width = 65;
        height = 65;

        margin_x = width - 25;
        margin_y = height - 25;

        pos_x = 50;
        pos_y = MainFrame.ground_y;

        maxLife = 3;
        nowLife = 3;

        jumping = false;
        landing = false;
        jumpingDelay = 0.020;
        landingDelay = 0.020;
        movingTime = new Time();

        runAnimDelay = 0.07;
        hitAnimDelay = 0.1;
        runAniTime = new Time();
        hitAniTime = new Time();

        invincibleByObs = false;
        invincibleByItem = false;
        hitAnimSwitch = false;
        invincibleTime = new Time();

        jumpSound = new Audio("src/main/resources/sounds/jump.wav", false);
        gameoverSound = new Audio("src/main/resources/sounds/gameover.wav", false);
    }

    @Override
    public void draw (Graphics g, View view)
    {
        super.draw(g, view);

        Jump();
        ReleaseInvincible();

        RunningAnimation();
        HitAnimation();

        Die();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_SPACE:
                if (!jumping && !landing)
                {
                    jumping = true;
                    jumpSound.start();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

     protected void Jump()
     {
        if (jumping)
        {
            if (movingTime.timeCtrl(jumpingDelay))
                pos_y -= gap;
            if (pos_y < MainFrame.ground_y - MainFrame.jumpLimit)
            {
                jumping = false;
                landing = true;
            }
        }
        else if (landing)
        {
            if (movingTime.timeCtrl(landingDelay))
            {
                pos_y += gap;
            }
            if (pos_y == MainFrame.ground_y)
            {
                jumping = false;
                landing = false;
                jumpSound.stop();
            }
        }
    }

    public boolean isInvincible()
    {
        return invincibleByObs || invincibleByItem;
    }

    // invincibleSwitch: true -> by obs, false -> by item
    public void setInvincible(double invincibleTime, boolean invincibleSwitch)
    {
        if (invincibleSwitch)
        {
            invincibleByObs = true;
        }
        else
        {
            invincibleByItem = true;
            image = image_basic_invincible;
        }
        invincibleDelay = invincibleTime;
    }

    protected void ReleaseInvincible()
    {
        if (invincibleByItem || invincibleByObs)
        {
            if (invincibleTime.timeCtrl(invincibleDelay))
            {
                invincibleByObs = false;
                invincibleByItem = false;

                setHitAnimSwitch(false);
                image = image_basic;
            }
        }
    }

    private void RunningAnimation()
    {
        if (runAniTime.timeCtrl(runAnimDelay))
        {
            if (invincibleByItem)
            {
                if (image.equals(image_basic_invincible))
                {
                    image = image_run_invincible;
                }
                else if (image.equals(image_run_invincible))
                {
                    image = image_basic_invincible;
                }
            }
            else
            {
                if (image.equals(image_basic))
                {
                    image = image_run;
                }
                else if (image.equals(image_run))
                {
                    image = image_basic;
                }

            }
        }
    }

    private void HitAnimation()
    {
        if (hitAnimSwitch)
        {
            if (hitAniTime.timeCtrl(hitAnimDelay))
            {
                if (image.equals(image_die))
                {
                    image = image_die_alphaSet;
                }
                else if (image.equals(image_die_alphaSet))
                {
                    image = image_die;
                }
            }
        }
    }

    public void setHitAnimSwitch(boolean val)
    {
        hitAnimSwitch = val;
        if (hitAnimSwitch)
            image = image_die;
    }

    private void Die ()
    {
        if (nowLife == 0)
        {
            System.out.println("Game Over");
            image = image_die;
            gameoverSound.start();
            nowLife--;
        }
    }
}
