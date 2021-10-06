package Main;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private View view = new View();

    public MainFrame () {
        setTitle("Dog Run");
        setSize(800, 800);
        setResizable(false);

        add(view);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.initBuffered();
    }
}
