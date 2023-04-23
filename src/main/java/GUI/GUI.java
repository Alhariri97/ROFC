package GUI;

import java.awt.Color;

/**
 * @author hariri
 */
public class GUI {

    public static final Color CORRECT_COLOR = new Color(36, 179, 59);
    public static final Color WRONG_COLOR = new Color(250, 65, 32);
    public static final Color INFO_COLOR = new Color(32, 137, 250);

    public static void main(String[] args) {

        MyFrame fram = new MyFrame();
        fram.setVisible(true);
    }

}
