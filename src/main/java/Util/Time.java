package Util;

public class Time
{
    private long currentTime;

    public Time ()
    {
        currentTime = System.currentTimeMillis();
    }

    public boolean timeCtrl(double delay)
    {
        long systemTime =  System.currentTimeMillis();
        if (currentTime + delay * 1000 <= systemTime)
        {
            currentTime = System.currentTimeMillis();
            return true;
        }
        else
        {
            return false;
        }
    }
}
