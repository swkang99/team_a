package Object.MovingObject;

import Main.MainFrame;
import Main.View;
import Object.GameObject;
import Object.Character.Chr;
import java.util.Random;

public class MovingObject extends GameObject
{
    protected double movingDelay;
    private boolean isEnable = false;
    private final int[] obj_pos_y = new int[3];    // moving object will appear 3 value of y

    public MovingObject (View view)
    {
        super(view);

        // y value of moving objects
        obj_pos_y[0] = MainFrame.jumpLimit;
        obj_pos_y[1] = (MainFrame.ground_y - MainFrame.jumpLimit) / 2;
        obj_pos_y[2] = MainFrame.ground_y;
    }

    protected void Move ()
    {
        if(isEnable)
        {
            if (pos_x >= 0)
            {
                if (time.timeCtrl(movingDelay))
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
