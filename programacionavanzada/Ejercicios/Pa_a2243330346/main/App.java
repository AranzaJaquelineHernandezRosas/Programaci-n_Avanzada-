package main;

import Controlador.AppService;
import Controlador.MainController;
import Vista.MenuPrincipalFrame;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppService service = new AppService();
            MenuPrincipalFrame frame = new MenuPrincipalFrame();
            new MainController(frame, service);
            frame.setVisible(true);
        });
    }
}