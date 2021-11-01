package Main;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import Util.InGame;

import javax.swing.*;

public class View extends Canvas
{
    private Graphics bufferGraphics;
    private Image offscreen;
    private Dimension dim;

    private Timer timer;
    private TimerTask timerTask;

    private InGame inGame;

    public View()
    {
        inGame = new InGame(this);

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
        inGame.Update(g, this);
    }
}
