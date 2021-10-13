package Obstacle;

import Main.View;
import Util.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BigDog {
    public int pos_x;
    public int pos_y;

    public int width;
    public int height;

    private int gap;

    private Image image;
    private View view;

    private Time time;

    private double movingDelay = 0.05;

    public BigDog (View view) {
        this.view = view;
        width = 40;
        height = 80;
        try {
            image = ImageIO.read(new File("src/main/resources/obs/bigdog.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pos_x = 700;
        pos_y = 460;
        gap = 10;

        time = new Time();
    }

    public void draw(Graphics g, View view) {
        g.drawImage(image, pos_x, pos_y, (ImageObserver) view);
        Move();
    }

    private void Move () {
        if (pos_x >= 0) {
            if (time.timeCtrl(movingDelay))
                pos_x -= gap;
        }
        else {
            pos_x = 800;
        }
    }
}
