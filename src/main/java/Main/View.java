package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import Character.*;
import Item.DogFood;
import Item.Item;
import Obstacle.pix2.BigDog;
import Obstacle.Obstacle;
import Util.BGScroll;
import Util.Collision;
import Util.Time;

public class View extends Canvas
{
    private Graphics bufferGraphics;
    private Image offscreen;
    private Dimension dim;

    private Timer timer;
    private TimerTask timerTask;

    private Chr chr;
    private Obstacle obs;
    private Item item;

    private Collision obsCollision;
    private Collision itemCollision;

    private BGScroll bgScroll;

    private Time time;

    public View()
    {
        chr = new Pomeranian(this);
        addKeyListener(chr);

        obs = new BigDog(this);

        item = new DogFood(this);

        obsCollision = new Collision();
        itemCollision = new Collision();

        bgScroll = new BGScroll(this);

        time = new Time();

        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        timer.schedule(timerTask, 0, 1);
    }

    public void initBuffered()
    {
        dim = getSize();
        setBackground(Color.white);
        offscreen = createImage(dim.width, dim.height);
        bufferGraphics = offscreen.getGraphics();
    }

    public void paint(Graphics g)
    {
        //super.paint(g);
        bufferGraphics.clearRect(0, 0, dim.width, dim.height);
        render(bufferGraphics);
        g.drawImage(offscreen, 0, 0, this);
    }

    @Override
    public void update(Graphics g)
    {
        // TODO Auto-generated method stub
        //super.update(g);
        paint(g);
    }

    public void render (Graphics g)
    {
        bgScroll.draw(g, this);

        chr.draw(g, this);

        obs.draw(g, this);

        item.draw(g, this);

        ObsCollisionCheck();
        ItemCollisionCheck();
    }

    public void ObsCollisionCheck ()
    {
        boolean obsTrigger = obsCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                                                       obs.getPos_x(), obs.getPos_y(), obs.getMargin_x(), obs.getMargin_y());

        // obstacle trigger
        if (obsTrigger && !chr.isInvincible())
        {
            chr.life -= 1;
            System.out.println("life: " + chr.life);
            chr.setInvincible(true);
            System.out.println(chr.isInvincible());

            if (chr.life == 0)
            {
                System.out.println("Game Over");
            }
        }

        // invincible check
        if (chr.isInvincible())
        {
            if (time.timeCtrl(chr.getInvincibleTime()))
            {
                chr.setInvincible(false);
                System.out.println(chr.isInvincible());
            }
        }
    }

    public void ItemCollisionCheck ()
    {
        boolean itemTrigger = itemCollision.TriggerEnter(chr.getPos_x(), chr.getPos_y(), chr.getMargin_x(), chr.getMargin_y(),
                                                     item.getPos_x(), item.getPos_y(), item.getWidth(), item.getHeight());
        //System.out.println("chr checking: " + chr.getPos_x() + ", " + chr.getPos_y());
        //System.out.println("item checking: " + item.getPos_x() + ", " + item.getPos_y());
        // item trigger
        if (itemTrigger)
        {

            System.out.println("Item get: " + item.toString());

        }
    }
}
