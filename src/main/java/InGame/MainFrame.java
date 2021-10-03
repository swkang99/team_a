package InGame;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private View view = new View();

    public MainFrame () {
        setTitle("Dog Run");
        setSize(300, 300);
        setResizable(false);

        add(view);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.initBufferd();
    }
}
