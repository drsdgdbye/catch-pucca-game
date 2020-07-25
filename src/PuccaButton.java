import javax.swing.*;
import java.util.Random;

class PuccaButton extends JButton {
    private static final Random rnd = new Random();
    private final ImageIcon puccaIcon = new ImageIcon("imgs/pucca.png");
    private final ImageIcon puccaIconWin = new ImageIcon("imgs/pucca_win.png");
    private final PuccaSpeaker speaker;
    private int x;
    private int y;

    PuccaButton(PuccaSpeaker speaker) {
        this.speaker = speaker;
        initButton();
    }

    private void initButton() {
        setIcon(puccaIcon);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    void setButtonInRandomPlace(int width, int height) {
        x = rnd.nextInt(width - getIconWidth(puccaIcon));
        y = rnd.nextInt(height/2);
        speaker.updateLocation(x, y, getIconWidth(puccaIcon));
        setBounds(x, y, getIconWidth(puccaIcon), getIconHeight(puccaIcon));
    }

    void setWinButton() {
        setIcon(puccaIconWin);
        speaker.updateLocation(x, y, getIconWidth(puccaIconWin));
        setBounds(x, y, getIconWidth(puccaIconWin), getIconHeight(puccaIconWin));
    }

    private int getIconWidth(ImageIcon icon) {
        return icon.getIconWidth();
    }

    private int getIconHeight(ImageIcon icon) {
        return icon.getIconHeight();
    }
}
