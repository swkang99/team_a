import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Dog implements KeyListener {

    private int x;
    private int y;

    private int jumpLimit = 100;
    private int ground_y = 500;
    private int gap = 10;

    private Image image;
    private View view;

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
                Jump();
                break;
        }
        System.out.println(x+", "+y);
    }

    private void Jump() {
        // 점프
        while (true) {
            y -= gap;
            System.out.println("jumping");
            if (y <= ground_y - jumpLimit)
                break;
        }
        // 착지
        while (true) {
            y += gap;
            System.out.println("landing");
            if (y == ground_y)
                break;
        }
    }
}
