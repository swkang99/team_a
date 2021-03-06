package Object.MovingObject;

import Main.Main;
import Main.MainFrame;
import Main.View;
import Object.GameObject;
import Object.Character.Chr;
import Util.InGame;
import Util.Time;

import java.util.Random;

public class MovingObject extends GameObject
{
    protected double movingDelay;
    protected boolean isEnable;
    protected final int[] obj_pos_y = new int[3];    // moving object will appear 3 value of y
    private Time movingTime;

    public MovingObject (View view)
    {
        super(view);

        isEnable = false;

        // y value of moving objects
        obj_pos_y[0] = MainFrame.ground_y - MainFrame.jumpLimit;
        obj_pos_y[1] = MainFrame.ground_y - (MainFrame.jumpLimit / 2);  // between obj_pos[0] and obj_pos[2]
        obj_pos_y[2] = MainFrame.ground_y;

        movingTime = new Time();
    }

    protected void Move ()
    {
        movingDelay = InGame.gameSpeed + 0.003;
        if(isEnable)
        {
            if (pos_x >= 0)
            {
                if (movingTime.timeCtrl(movingDelay))
                    pos_x -= gap;
            }
            else
            {
                pos_x = -MainFrame.frameWidth;
                isEnable = false;
            }
        }
    }

    public void Activate ()
    {
        Random rand = new Random();
        pos_x = MainFrame.frameWidth;
        pos_y = obj_pos_y[rand.nextInt(obj_pos_y.length)];

        isEnable = true;
    }

    public boolean isEnable ()
    {
        return isEnable;
    }
}
