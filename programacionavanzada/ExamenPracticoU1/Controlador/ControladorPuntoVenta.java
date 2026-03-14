package ControladorExamen;
import Modelo.*;
import ModeloExamen.GestorProductos;
import ModeloExamen.GestorVentas;
import ModeloExamen.Producto;
import ModeloExamen.Venta;
import Vista.*;
import VistaExamen.VistaPuntoVenta;

import javax.swing.*;
import java.text.DecimalFormat;

public class ControladorPuntoVenta {

    private VistaPuntoVenta vista;
    private GestorProductos gestorProductos;
    private GestorVentas gestorVentas;
    private DecimalFormat df = new DecimalFormat("#.00");

    public ControladorPuntoVenta(VistaPuntoVenta v, GestorProductos gp, GestorVentas gv) {
        this.vista = v;
        this.gestorProductos = gp;
        this.gestorVentas = gv;

        cargarProductosTabla();
        actualizarResumen();

        vista.btnAgregar.addActionListener(e -> agregarProducto());
        vista.btnEliminar.addActionListener(e -> eliminarProductoTicket());
        vista.btnPagar.addActionListener(e -> pagar());
    }

    private void cargarProductosTabla() {
        vista.modeloProductos.setRowCount(0);
        for (Producto p : gestorProductos.getLista()) {
            vista.modeloProductos.addRow(new Object[]{
                    p.getId(), p.getCodigo(), p.getNombre(), p.getStock(), p.getPrecio()
            });
        }
    }

    private void agregarProducto() {
        int fila = vista.tablaProductos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona un producto para agregar.", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String codigo = String.valueOf(vista.modeloProductos.getValueAt(fila, 1));
        Producto p = gestorProductos.buscarPorCodigo(codigo);
        if (p == null) return;
        if (p.getStock() <= 0) {
            JOptionPane.showMessageDialog(vista, "Producto sin stock.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Reducir stock en gestor
        p.setStock(p.getStock() - 1);
        vista.modeloProductos.setValueAt(p.getStock(), fila, 3);

        // Verificar si ya existe en ticket
        int filasTicket = vista.modeloTicket.getRowCount();
        for (int i = 0; i < filasTicket; i++) {
            String codTick = String.valueOf(vista.modeloTicket.getValueAt(i, 0));
            if (codTick.equals(codigo)) {
                int qty = Integer.parseInt(String.valueOf(vista.modeloTicket.getValueAt(i, 3)));
                qty++;
                double price = Double.parseDouble(String.valueOf(vista.modeloTicket.getValueAt(i, 2)));
                vista.modeloTicket.setValueAt(qty, i, 3);
                vista.modeloTicket.setValueAt(df.format(price * qty), i, 4);
                actualizarResumen();
                return;
            }
        }

        // No existe, agregar nueva fila
        double precio = p.getPrecio();
        vista.modeloTicket.addRow(new Object[]{codigo, p.getNombre(), precio, 1, df.format(precio * 1)});
        actualizarResumen();
    }

    private void eliminarProductoTicket() {
        int fila = vista.tablaTicket.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona un item del ticket para eliminar.", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String codigo = String.valueOf(vista.modeloTicket.getValueAt(fila, 0));
        int qty = Integer.parseInt(String.valueOf(vista.modeloTicket.getValueAt(fila, 3)));
        double price = Double.parseDouble(String.valueOf(vista.modeloTicket.getValueAt(fila, 2)));

        // Restaurar stock en gestor (devolver 1 unidad)
        Producto p = gestorProductos.buscarPorCodigo(codigo);
        if (p != null) {
            p.setStock(p.getStock() + 1);
            // actualizar tabla productos
            for (int i = 0; i < vista.modeloProductos.getRowCount(); i++) {
                if (String.valueOf(vista.modeloProductos.getValueAt(i,1)).equals(codigo)) {
                    vista.modeloProductos.setValueAt(p.getStock(), i, 3);
                    break;
                }
            }
        }

        if (qty > 1) {
            qty--;
            vista.modeloTicket.setValueAt(qty, fila, 3);
            vista.modeloTicket.setValueAt(df.format(price * qty), fila, 4);
        } else {
            vista.modeloTicket.removeRow(fila);
        }
        actualizarResumen();
    }

    private void pagar() {
        int filas = vista.modeloTicket.getRowCount();
        if (filas == 0) {
            JOptionPane.showMessageDialog(vista, "No hay productos en el ticket.", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String id = gestorVentas.generarSiguienteId();
        for (int i = 0; i < filas; i++) {
            String codigo = String.valueOf(vista.modeloTicket.getValueAt(i, 0));
            String nombre = String.valueOf(vista.modeloTicket.getValueAt(i, 1));
            double precio = Double.parseDouble(String.valueOf(vista.modeloTicket.getValueAt(i, 2)));
            int cantidad = Integer.parseInt(String.valueOf(vista.modeloTicket.getValueAt(i, 3)));
            Venta v = new Venta(id, codigo, nombre, precio, cantidad);
            gestorVentas.agregarVenta(v);
        }

        String msg = "Ticket " + id + " pagado.\n" +
                "Subtotal: " + vista.lblSubtotal.getText() + "\n" +
                "IVA: " + vista.lblIva.getText() + "\n" +
                "Total: " + vista.lblTotal.getText();
        JOptionPane.showMessageDialog(vista, msg, "Pago realizado", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar ticket
        vista.modeloTicket.setRowCount(0);
        actualizarResumen();
    }

    private void actualizarResumen() {
        double subtotal = 0.0;
        for (int i = 0; i < vista.modeloTicket.getRowCount(); i++) {
            String totalStr = String.valueOf(vista.modeloTicket.getValueAt(i, 4));
            try {
                subtotal += Double.parseDouble(totalStr);
            } catch (NumberFormatException ex) {
                try { subtotal += Double.parseDouble(totalStr.replace("$","")); } catch (Exception e){}
            }
        }
        double iva = subtotal * 0.16;
        double total = subtotal + iva;
        vista.lblSubtotal.setText("$" + df.format(subtotal));
        vista.lblIva.setText("$" + df.format(iva));
        vista.lblTotal.setText("$" + df.format(total));
    }
}
