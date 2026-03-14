package ControladorExamen;


	import Modelo.*;
import ModeloExamen.Archivos;
import ModeloExamen.GestorProductos;
import ModeloExamen.Producto;
import  Vista.*;
import VistaExamen.VistaProductos;

import javax.swing.*;
	import java.awt.event.*;
	import java.util.ArrayList;

	public class ControladorProductos implements ActionListener {

	    private VistaProductos  vista;
	    private GestorProductos gestor;

	    public ControladorProductos(VistaProductos v, GestorProductos g) {
	        vista  = v;
	        gestor = g;

	        // Cargar CSV al iniciar
	        ArrayList<Producto> cargados = Archivos.importarCSV();
	        for (Producto p : cargados) gestor.insertar(p);

	        vista.btnGuardar.addActionListener(this);
	        vista.btnLimpiar.addActionListener(this);
	        vista.btnBuscar.addActionListener(this);
	        vista.btnModificar.addActionListener(this);
	        vista.btnEliminar.addActionListener(this);

	        // Clic en fila → carga formulario
	        vista.tabla.getSelectionModel().addListSelectionListener(evt -> {
	            if (!evt.getValueIsAdjusting()) cargarFila();
	        });

	        actualizarTabla();
	    }

	    private void actualizarTabla() {
	        vista.modeloTabla.setRowCount(0);
	        for (Producto p : gestor.getLista()) {
	            vista.modeloTabla.addRow(new Object[]{
	                p.getId(),
	                p.getCodigo(),
	                p.getNombre(),
	                p.getCategoria(),
	                p.getStock(),
	                String.format("$%.2f", p.getPrecio()),
	                p.getEstado()
	            });
	        }
	    }

	    private void limpiar() {
	        vista.txtId.setText("");
	        vista.txtCodigo.setText("");
	        vista.txtNombre.setText("");
	        vista.txtStock.setText("");
	        vista.txtPrecio.setText("");
	        vista.txtBuscar.setText("");
	        vista.comboCategoria.setSelectedIndex(0);
	        vista.rbActivo.setSelected(true);
	        vista.tabla.clearSelection();
	    }

	    private void cargarFila() {
	        int fila = vista.tabla.getSelectedRow();
	        if (fila < 0) return;
	        vista.txtId.setText(
	            vista.modeloTabla.getValueAt(fila, 0).toString());
	        vista.txtCodigo.setText(
	            vista.modeloTabla.getValueAt(fila, 1).toString());
	        vista.txtNombre.setText(
	            vista.modeloTabla.getValueAt(fila, 2).toString());
	        vista.comboCategoria.setSelectedItem(
	            vista.modeloTabla.getValueAt(fila, 3).toString());
	        vista.txtStock.setText(
	            vista.modeloTabla.getValueAt(fila, 4).toString());
	        vista.txtPrecio.setText(
	            vista.modeloTabla.getValueAt(fila, 5)
	                .toString().replace("$", ""));
	        String est = vista.modeloTabla.getValueAt(fila, 6).toString();
	        vista.rbActivo.setSelected("Activo".equals(est));
	        vista.rbInactivo.setSelected(!"Activo".equals(est));
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {

	        // ── LIMPIAR 
	        if (e.getSource() == vista.btnLimpiar) {
	            limpiar(); return;
	        }

	        try {
	            // ── BUSCAR
	            if (e.getSource() == vista.btnBuscar) {
	                String texto = vista.txtBuscar.getText().trim();
	                if (texto.isEmpty()) {
	                    error("Escribe algo en el campo de búsqueda."); return;
	                }
	                String campo = vista.comboBuscarPor
	                        .getSelectedItem().toString();
	                vista.modeloTabla.setRowCount(0);
	                for (Producto p : gestor.getLista()) {
	                    boolean ok = false;
	                    if ("Nombre".equals(campo))
	                        ok = p.getNombre().toLowerCase()
	                               .contains(texto.toLowerCase());
	                    else if ("Código".equals(campo))
	                        ok = p.getCodigo().toLowerCase()
	                               .contains(texto.toLowerCase());
	                    else if ("Categoría".equals(campo))
	                        ok = p.getCategoria().toLowerCase()
	                               .contains(texto.toLowerCase());
	                    if (ok) vista.modeloTabla.addRow(new Object[]{
	                        p.getId(), p.getCodigo(), p.getNombre(),
	                        p.getCategoria(), p.getStock(),
	                        String.format("$%.2f", p.getPrecio()),
	                        p.getEstado()
	                    });
	                }
	                return;
	            }

	            // ── GUARDAR (ID automático) ──────────────────────────────
	            if (e.getSource() == vista.btnGuardar) {
	                String nombre    = vista.txtNombre.getText().trim();
	                String codigo    = vista.txtCodigo.getText().trim();
	                String stockStr  = vista.txtStock.getText().trim();
	                String precioStr = vista.txtPrecio.getText().trim();

	                if (nombre.isEmpty() || codigo.isEmpty()
	                        || stockStr.isEmpty() || precioStr.isEmpty()) {
	                    error("Nombre, Código, Stock y Precio son obligatorios.");
	                    return;
	                }

	                int    stock  = Integer.parseInt(stockStr);
	                double precio = Double.parseDouble(precioStr);
	                String cat    = vista.comboCategoria
	                                     .getSelectedItem().toString();
	                String estado = vista.rbActivo.isSelected()
	                                ? "Activo" : "Inactivo";

	                int nuevoId = 1;
	                while (gestor.existe(nuevoId)) nuevoId++;

	                gestor.insertar(new Producto(
	                        nuevoId, codigo, nombre, cat,
	                        stock, precio, estado));
	                Archivos.exportarCSV(gestor.getLista());
	                actualizarTabla();
	                limpiar();
	                ok("Producto guardado con ID: " + nuevoId);
	                return;
	            }

	            // Modificar y Eliminar requieren fila seleccionada
	            String idStr = vista.txtId.getText().trim();
	            if (idStr.isEmpty()) {
	                error("Primero haz clic en una fila de la tabla.");
	                return;
	            }
	            int id = Integer.parseInt(idStr);

	            // ── MODIFICAR ────────────────────────────────────────────
	            if (e.getSource() == vista.btnModificar) {
	                String nombre    = vista.txtNombre.getText().trim();
	                String codigo    = vista.txtCodigo.getText().trim();
	                String stockStr  = vista.txtStock.getText().trim();
	                String precioStr = vista.txtPrecio.getText().trim();

	                if (nombre.isEmpty() || codigo.isEmpty()
	                        || stockStr.isEmpty() || precioStr.isEmpty()) {
	                    error("Completa todos los campos antes de modificar.");
	                    return;
	                }

	                int    stock  = Integer.parseInt(stockStr);
	                double precio = Double.parseDouble(precioStr);
	                String cat    = vista.comboCategoria
	                                     .getSelectedItem().toString();
	                String estado = vista.rbActivo.isSelected()
	                                ? "Activo" : "Inactivo";

	                gestor.actualizar(new Producto(
	                        id, codigo, nombre, cat,
	                        stock, precio, estado));
	                Archivos.exportarCSV(gestor.getLista());
	                actualizarTabla();
	                limpiar();
	                ok("Producto actualizado correctamente.");

	            // ── ELIMINAR 
	            } else if (e.getSource() == vista.btnEliminar) {
	                int op = JOptionPane.showConfirmDialog(
	                        vista,
	                        "¿Deseas eliminar el producto con ID " + id + "?",
	                        "Confirmar eliminación",
	                        JOptionPane.YES_NO_OPTION,
	                        JOptionPane.WARNING_MESSAGE);
	                if (op == JOptionPane.YES_OPTION) {
	                    gestor.eliminar(id);
	                    Archivos.exportarCSV(gestor.getLista());
	                    actualizarTabla();
	                    limpiar();
	                    ok("Producto eliminado.");
	                }
	            }

	        } catch (NumberFormatException ex) {
	            error("Stock y Precio deben ser valores numéricos.");
	        } catch (Exception ex) {
	            error("Error inesperado: " + ex.getMessage());
	        }
	    }

	    private void error(String msg) {
	        JOptionPane.showMessageDialog(vista, msg,
	                "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    private void ok(String msg) {
	        JOptionPane.showMessageDialog(vista, msg,
	                "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
