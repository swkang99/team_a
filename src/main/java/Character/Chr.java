package Character;

import Main.View;
import Util.GameObject;
import Util.Time;

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

    private int jumpLimit = 200;
    protected int ground_y = 500;

    protected boolean invincible = false;
    protected double invincibleTime = 6;

    public Chr(View view)
    {
        super(view);
        gap = 20;
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
            if (pos_y < ground_y - jumpLimit)
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
            if (pos_y == ground_y)
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
