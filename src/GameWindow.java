import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.Instant;

class GameWindow extends JFrame {
    private final ImageIcon background = new ImageIcon("imgs/background.jpg");
    private Instant startPatience;
    private int width;
    private int height;

    GameWindow() {
        begin();
    }

    private void begin() {
        initGameWindow();
        startPatience = Instant.now();
    }

    private PuccaSpeaker puccaSpeaker;
    private PuccaButton puccaBtn;
    private void initGameWindow() {
        width = background.getIconWidth();
        height = background.getIconHeight();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Catch Pucca Game");
        setSize(width, height);
        setResizable(false);
        setLayout(null);
        setIconImage(background.getImage());
        setContentPane(new JLabel(background));
        puccaSpeaker = new PuccaSpeaker();
        puccaBtn = new PuccaButton(puccaSpeaker);
        puccaBtn.setButtonInRandomPlace(width, height);
        add(puccaSpeaker);
        add(puccaBtn);
        puccaSpeaker.sayHello();
        addMouseListener();
        setVisible(true);
    }

    private void addMouseListener() {
        puccaBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (Duration.between(startPatience, Instant.now()).toSeconds() <= 5) {
                    puccaBtn.setButtonInRandomPlace(width, height);
                    puccaSpeaker.setLabelNearButton();
                    startPatience = Instant.now();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                puccaBtn.setWinButton();
                puccaSpeaker.sayWin();
            }
        });
    }
}
