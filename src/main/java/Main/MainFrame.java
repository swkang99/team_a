package Main;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private View view = new View();

    public static int frameWidth = 800;
    public static int frameHeight = 800;

    public MainFrame () {
        setTitle("My Dog Run");
        setSize(frameWidth, frameHeight);
        setResizable(false);

        add(view);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.initBuffered();
    }
}
