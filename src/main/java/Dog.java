import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class Dog implements KeyListener {

    public int x;
    public int y;

    public int jumpLimit = 100;
    public int ground_y = 500;
    public int gap = 20;
    private long jumpingDelay = 1;

    public boolean jumping = false;

    private Image image;
    private View view;

    private TimeCtrl time = new TimeCtrl();

    public Dog (View view) {
        this.view = view;
        try {
            image = ImageIO.read(new File("src/main/resources/dog.png"));
            image = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.x = 50;
        this.y = ground_y;
    }

    public void draw(Graphics g, View view) {
        g.drawImage(image, x, y, (ImageObserver) view);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if (!jumping)
                    Jump();
                break;
            case KeyEvent.VK_DOWN:
                y += gap;
                break;
            case KeyEvent.VK_LEFT:
                x -= gap;
                break;
            case KeyEvent.VK_RIGHT:
                x += gap;
                break;
        }
        System.out.println(x+", "+y);
    }

    private void Jump() {
        jumping = true;
        if(jumping) {
                // 점프
                while (true) {
                    if (time.timeCtrl(jumpingDelay)) {
                        y -= gap;
                        System.out.println("jumping, x: " + x + ", y: " + y);
                    }
                    if (y <= ground_y - jumpLimit)
                        break;
                }
                // 착지
                while (true) {
                    if (time.timeCtrl(jumpingDelay)) {
                        y += gap;
                        System.out.println("landing, x: " + x + ", y: " + y);
                    }
                    if (y == ground_y) {
                        jumping = false;
                        break;
                    }
                }
        }
    }

}
