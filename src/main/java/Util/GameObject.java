package Util;

import Main.View;

import java.awt.*;
import java.awt.image.ImageObserver;

public class GameObject {
    protected int pos_x;
    protected int pos_y;

    protected int gap;

    protected int width;
    protected int height;

    public Image image;
    public View view;
    public Time time;

    public GameObject (View view) {
        this.view = view;
        time = new Time();
    }

    public void draw(Graphics g, View view) {
        g.drawImage(image, pos_x, pos_y, (ImageObserver) view);
    }

    public int getPos_x () {
        return pos_x;
    }

    public int getPos_y () {
        return pos_y;
    }

    public int getWidth () {
        return width;
    }

    public int getHeight () {
        return height;
    }
}
