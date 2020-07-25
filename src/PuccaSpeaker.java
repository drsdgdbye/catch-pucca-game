import javax.swing.*;
import java.awt.*;
import java.util.Random;

class PuccaSpeaker extends JLabel {
    private final String[] phrases = {"miss, sucker!", "catch-catch-catch", "lolololo", "catch me, nerd!", "jerky jerk!", "miss again"};
    private static final Random rnd = new Random();
    private int shiftX;
    private int shiftY;

    PuccaSpeaker() {
        initLabel();
    }

    private void initLabel() {
        setFont(new Font("Verdana", Font.ITALIC, 11));
        setHorizontalTextPosition(JLabel.CENTER);
        setOpaque(true);
    }

    void updateLocation(int x, int y, int iconWidth) {
        shiftX = x + iconWidth + 10;//magic numbers
        shiftY = y - 10;
    }

    void setLabelNearButton() {
        setText(phrases[rnd.nextInt(phrases.length)]);
        setBounds(shiftX, shiftY, 100, 30);
    }

    void sayHello() {
        setText("hello");
        setBounds(shiftX, shiftY, 50, 30);
    }

    void sayWin() {
        setText("you're my hero!");
        setBounds(shiftX, shiftY, 100, 30);
    }
}
