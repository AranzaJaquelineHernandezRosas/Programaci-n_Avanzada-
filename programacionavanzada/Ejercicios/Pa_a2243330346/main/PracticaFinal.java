package main;

import Controlador.AppService;
import Controlador.MainController;
import Modelo.StorageType;
import Vista.MenuPrincipalFrame;

import javax.swing.SwingUtilities;

public class PracticaFinal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppService service = new AppService();
            service.setStorageType(StorageType.TXT);
            MenuPrincipalFrame frame = new MenuPrincipalFrame();
            new MainController(frame, service);
            frame.setVisible(true);
        });
    }
}