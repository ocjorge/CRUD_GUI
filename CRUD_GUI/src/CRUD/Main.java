package CRUD;

import GUI.GUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jorge
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    
    }
}
