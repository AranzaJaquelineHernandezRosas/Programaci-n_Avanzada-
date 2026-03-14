package App;

import Vista.*;
import VistaExamen.VentanaPrincipal;
import VistaExamen.VistaProductos;
import VistaExamen.VistaPuntoVenta;
import Modelo.*;
import ModeloExamen.Archivos;
import ModeloExamen.GestorProductos;
import ModeloExamen.GestorVentas;
import ModeloExamen.Producto;
import Controlador.*;
import ControladorExamen.ControladorProductos;
import ControladorExamen.ControladorPuntoVenta;

import javax.swing.*;
import java.awt.Font;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont",
                    new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("OptionPane.buttonFont",
                    new Font("Segoe UI", Font.BOLD, 12));
            UIManager.put("Menu.font",
                    new Font("Segoe UI", Font.PLAIN, 13));
            UIManager.put("MenuItem.font",
                    new Font("Segoe UI", Font.PLAIN, 13));
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {

            GestorProductos gestor   = new GestorProductos();
            GestorVentas gestorVentas = new GestorVentas(); // added
            VentanaPrincipal ventana = new VentanaPrincipal();

            // Cargar productos desde CSV usando Modelo.Archivos
            java.util.ArrayList<Producto> cargados = Archivos.importarCSV();
            for (Producto p : cargados) gestor.insertar(p);

            Runnable abrirProductos = () -> {
                VistaProductos vp = new VistaProductos();
                ventana.desktop.add(vp);
                new ControladorProductos(vp, gestor);
                try { vp.setSelected(true); }
                catch (Exception ignored) {}
            };

            Runnable abrirPuntoVenta = () -> {
                VistaPuntoVenta vpv = new VistaPuntoVenta();
                ventana.desktop.add(vpv);
                new ControladorPuntoVenta(vpv, gestor, gestorVentas);
                try { vpv.setSelected(true); }
                catch (Exception ignored) {}
            };

            ventana.menuProductos.addActionListener(
                    e -> abrirProductos.run());

            ventana.btnAbrirProductos.addActionListener(
                    e -> abrirProductos.run());

            // wire punto de venta actions
            ventana.menuPuntoVenta.addActionListener(e -> abrirPuntoVenta.run());
            ventana.btnAbrirPuntoVenta.addActionListener(e -> abrirPuntoVenta.run());

            ventana.menuSalir.addActionListener(e -> {
                int op = JOptionPane.showConfirmDialog(ventana,
                        "¿Deseas salir del sistema?", "Salir",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) System.exit(0);
            });

            ventana.setVisible(true);
        });
    }
}