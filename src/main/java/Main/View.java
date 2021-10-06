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

public class View extends Canvas {
    private Graphics bufferGraphics;
    private Image offscreen;
    private Dimension dim;

    private Timer timer;
    private TimerTask timerTask;

    private Dog dog;
    private BigDog bidDog;

    public View() {
        dog = new Dog(this);
        addKeyListener(dog);

        bidDog = new BigDog(this);

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
        dog.draw(g, this);
        bidDog.draw(g, View.this);
    }
}