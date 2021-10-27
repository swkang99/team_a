package Util;

import Main.View;

import java.awt.*;
import java.awt.image.ImageObserver;

public class GameObject
{
    protected int pos_x;
    protected int pos_y;

    protected int gap;              // moving distance

    protected int width;            // width of image
    protected int height;           // height of image

    protected int margin_x;         // x value of margin for collision check
    protected int margin_y;         // y value of margin for collision check

    public Image image;
    public View view;
    public Time time;

    public GameObject (View view)
    {
        this.view = view;
        time = new Time();
    }

    public void draw(Graphics g, View view)
    {
        g.drawImage(image, pos_x, pos_y, (ImageObserver) view);
    }

    public int getPos_x ()
    {
        return pos_x;
    }

    public int getPos_y ()
    {
        return pos_y;
    }

    public int getWidth ()
    {
        return width;
    }

    public int getHeight ()
    {
        return height;
    }

    public int getMargin_x ()
    {
        return margin_x;
    }

    public int getMargin_y ()
    {
        return margin_y;
    }
}
