package Object.Character;

import Main.MainFrame;
import Main.View;
import Object.GameObject;
import Util.Time;

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

    private boolean invincible;
    private boolean hitAnimSwitch;
    private double invincibleDelay;
    private Time invincibleTime;

    protected Image image_basic;
    protected Image image_run;
    protected Image image_die;

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
        hitAnimDelay = 0.07;
        runAniTime = new Time();
        hitAniTime = new Time();

        invincible = false;
        hitAnimSwitch = false;
        invincibleTime = new Time();
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
            case KeyEvent.VK_UP:
                if (!jumping && !landing)
                    jumping = true;
            case KeyEvent.VK_SPACE:
                if (!jumping && !landing)
                    jumping = true;
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
            image = image_basic;
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
            }
        }
    }

    public boolean isInvincible()
    {
        return invincible;
    }

    public void setInvincible(double invincibleTime)
    {
        invincible = true;
        invincibleDelay = invincibleTime;
    }

    protected void ReleaseInvincible()
    {
        if (invincible)
        {
            if (invincibleTime.timeCtrl(invincibleDelay))
            {
                invincible = false;
                System.out.println("invincible release-chr state: " + invincible);

                setHitAnimSwitch(false);
                image = image_basic;
            }
        }
    }

    private void RunningAnimation()
    {
        if (runAniTime.timeCtrl(runAnimDelay))
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

    private void HitAnimation()
    {
        if (hitAnimSwitch)
        {
            if (hitAniTime.timeCtrl(hitAnimDelay))
            {
                
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
        }
    }
}
