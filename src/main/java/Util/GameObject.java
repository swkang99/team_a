package Util;

import Main.View;

import java.awt.*;
import java.awt.image.ImageObserver;

public class GameObject {
    public int pos_x;
    public int pos_y;

    public int gap;

    public int width;
    public int height;

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
}
