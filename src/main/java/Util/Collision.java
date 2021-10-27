package Util;

public class Collision
{
    public boolean OnTrigger;

    public Collision ()
    {
        OnTrigger = false;
    }

    public boolean TriggerEnter (int source_x, int source_y, int source_margin_x, int source_margin_y,
                                 int target_x, int target_y, int target_margin_x, int target_margin_y)
    {
        boolean trigger_x = Math.abs(source_x - target_x) < (source_margin_x + target_margin_x);
        boolean trigger_y = Math.abs(source_y - target_y) < (source_margin_y + target_margin_y);
        boolean triggered = trigger_x && trigger_y;

        if (OnTrigger == false) // Enter
        {
            if (triggered) {
                System.out.println("Triggered " + target_x + ", " + target_y);
            /*try
            {
                Thread.sleep(10000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }*/
                OnTrigger = true;
                return true;
            }
        }
        else // Stay
        {
            if(triggered)
            {
                return false;
            }
        }
        OnTrigger = false;  // Exit
        return false;
    }
}
