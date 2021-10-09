package Character;

import Main.View;
import Main.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Dog implements KeyListener {

    private int x;
    private int y;

    private int jumpLimit = 150;
    private int ground_y = 500;

    private int gap = 20;
    private double jumpingDelay = 0.020;
    private double landingDelay = 0.020;

    private boolean jumping = false;
    private boolean landing = false;

    private Image image;
    private View view;

    private Time time;

    public Dog (View view) {
        this.view = view;
        try {
            image = ImageIO.read(new File("src/main/resources/chr/dog.png"));
            image = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = 50;
        this.y = ground_y;

        time = new Time();
    }

    public void draw(Graphics g, View view) {
        g.drawImage(image, x, y, (ImageObserver) view);
        Jump();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
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
    public void keyReleased(KeyEvent e) {

    }

    private void Jump() {
        if (jumping) {
            if (time.timeCtrl(jumpingDelay)) // 점프
                y -= gap;
            if (y < ground_y - jumpLimit) {
                jumping = false;
                landing = true;
            }
        }
        else if (landing) {
            if (time.timeCtrl(landingDelay)) {      // 착지
                y += gap;
            }
            if (y == ground_y) {
                jumping = false;
                landing = false;
            }
        }
    }
}
