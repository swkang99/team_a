package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import Character.*;
import Obstacle.BigDog;
import Obstacle.Obstacle;
import Util.BGScroll;
import Util.Collision;

public class View extends Canvas {
    private Graphics bufferGraphics;
    private Image offscreen;
    private Dimension dim;

    private Timer timer;
    private TimerTask timerTask;

    private Chr chr;
    private Obstacle obs;

    private Collision collision;

    private BGScroll bgScroll;

    public View() {
        chr = new Dog(this);
        addKeyListener(chr);

        obs = new BigDog(this);

        collision = new Collision();

        bgScroll = new BGScroll(this);

        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        timer.schedule(timerTask, 0, 1);
    }

    public void initBuffered() {
        dim = getSize();
        setBackground(Color.white);
        offscreen = createImage(dim.width, dim.height);
        bufferGraphics = offscreen.getGraphics();
    }

    public void paint(Graphics g) {
        //super.paint(g);
        bufferGraphics.clearRect(0, 0, dim.width, dim.height);
        render(bufferGraphics);
        g.drawImage(offscreen, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        // TODO Auto-generated method stub
        //super.update(g);
        paint(g);
    }

    public void render (Graphics g) {
        bgScroll.draw(g, this);
        chr.draw(g, this);
        obs.draw(g, this);

        CollisionCheck();
    }

    public void CollisionCheck () {
        boolean trigger = collision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getWidth(), chr.getHeight(),
                obs.getPos_x(), obs.getPos_y(), obs.getWidth(), obs.getHeight());

        if (trigger) {
            chr.life -= 1;
            System.out.println("life: " + chr.life);
            if (chr.life == 0)
            {
                System.out.println("Game Over");
            }
        }
    }
}
