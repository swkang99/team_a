import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Dog implements KeyListener {
    private int x = 0;
    private int y = 0;
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
        int gap = 10;
        int pos[] = null;
        if(x < 0)
            x = 0;
        if(y < 0)
            y = 0;
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                y -= gap;
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
        x = pos[0];
        y = pos[1];
        System.out.println(x+", "+y);
    }
}
