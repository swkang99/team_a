package Object.Character;

import Main.MainFrame;
import Main.View;
import Object.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Chr extends GameObject implements KeyListener
{
    public int life = 3;

    private double jumpingDelay = 0.020;
    private double landingDelay = 0.020;

    private boolean jumping = false;
    private boolean landing = false;

    protected boolean invincible = false;
    protected double invincibleTime = 7;

    public Chr(View view)
    {
        super(view);
        gap = 20;

        width = 40;
        height = 40;

        margin_x = width;
        margin_y = height;

        pos_x = 50;
        pos_y = MainFrame.ground_y;
    }

    @Override
    public void draw (Graphics g, View view)
    {
        super.draw(g, view);
        Jump();
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

    public void setInvincible(boolean val)
    {
        invincible = val;
    }

    public double getInvincibleTime()
    {
        return invincibleTime;
    }
}
