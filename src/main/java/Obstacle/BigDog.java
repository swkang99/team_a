package Obstacle;

import Main.View;
import Manager.GameMng;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BigDog {
    private int x;
    private int y;

    private Image image;
    private View view;

    public BigDog (View view) {
        this.view = view;
        try {
            image = ImageIO.read(new File("src/main/resources/bigdog.png"));
            image = image.getScaledInstance(40, 80, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = 700;
        y = GameMng.getInstance().ground_y;
    }

    public void draw(Graphics g, View view) {
        g.drawImage(image, x, y, (ImageObserver) view);
    }
}
