package Object.Character;

import Main.MainFrame;
import Main.View;
import Object.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Chr extends GameObject implements KeyListener
{
    public int maxLife;
    public int nowLife;

    private double jumpingDelay;
    private double landingDelay;

    private boolean jumping;
    private boolean landing;

    protected boolean invincible;
    protected double invincibleTime;

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

        jumpingDelay = 0.020;
        landingDelay = 0.020;

        jumping = false;
        landing = false;

        invincible = false;
        invincibleTime = 7;
    }

    @Override
    public void draw (Graphics g, View view)
    {
        super.draw(g, view);
        Jump();
        RealeaseInvincible();
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
        //System.out.println(x+", "+y);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

     protected void Jump()
     {
        if (jumping)
        {
            if (time.timeCtrl(jumpingDelay))
                pos_y -= gap;
            if (pos_y < MainFrame.ground_y - MainFrame.jumpLimit)
            {
                jumping = false;
                landing = true;
            }
        }
        else if (landing)
        {
            if (time.timeCtrl(landingDelay))
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
        this.invincible = true;
        this.invincibleTime = invincibleTime;
    }

    //public double getInvincibleTime()
//    {
//        return invincibleTime;
//    }

    protected void RealeaseInvincible()
    {
        if (invincible)
        {
            if (time.timeCtrl(invincibleTime))
            {
                this.invincible = false;
                System.out.println("invincible release-chr state: " + this.invincible);
            }
        }
    }
}
