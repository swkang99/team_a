public class TimeCtrl {
    long time;
    public TimeCtrl () {
        this.time = System.currentTimeMillis();
    }

    public boolean timeCtrl(long delay) {
        long systemTime =  System.currentTimeMillis();
        if (time + delay * 1000 <= systemTime) {
            time = System.currentTimeMillis();
            return true;
        }
        else {
            return false;
        }
    }
}
