package Character;

import Main.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Dog extends Chr {

    public Dog (View view) {
        super(view);
        width = 40;
        height = 40;
        try {
            image = ImageIO.read(new File("src/main/resources/chr/dog.png"));
            image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pos_x = 50;
        pos_y = super.ground_y;
    }
}
