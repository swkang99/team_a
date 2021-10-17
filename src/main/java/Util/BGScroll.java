package Util;

import Main.MainFrame;
import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BGScroll extends GameObject{

    public BGScroll (View view) {
        super(view);
        try {
            image = ImageIO.read(new File("src/main/resources/bg/bg1.png"));
            image = image.getScaledInstance(MainFrame.frameWidth, MainFrame.frameWidth, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pos_x = 0;
        pos_y = 0;
    }

    public void draw (Graphics g, View view) {
        super.draw(g, view);
    }
}
