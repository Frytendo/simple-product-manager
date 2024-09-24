import org.example.frame.MainFrame;

import javax.swing.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        MainFrame app = new MainFrame();
        app.setVisible(true);
    });
}
