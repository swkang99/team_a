package Util;

public class Collision {
    public Collision () {

    }

    public void Check (int source_x, int source_y, int source_margin_x, int source_margin_y,
                       int target_x, int target_y, int target_margin_x, int target_margin_y) {

        if ((source_x + source_margin_x == target_x + target_margin_x)
                && (source_y + source_margin_y == target_y + target_margin_y)) {
            System.out.println("Triggered " + target_x + ", " + target_y);
            /*try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
    }
}
