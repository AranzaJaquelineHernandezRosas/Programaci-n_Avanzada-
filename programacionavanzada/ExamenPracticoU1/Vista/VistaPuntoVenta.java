package VistaExamen;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaPuntoVenta extends JInternalFrame {

    public JTable tablaProductos;
    public DefaultTableModel modeloProductos;

    public JTable tablaTicket;
    public DefaultTableModel modeloTicket;

    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnPagar = new JButton("Pagar");

    public JLabel lblSubtotal = new JLabel("$0.00");
    public JLabel lblIva = new JLabel("$0.00");
    public JLabel lblTotal = new JLabel("$0.00");

    public VistaPuntoVenta() {
        super("Punto de Venta", true, true, true, true);
        setSize(900, 520);
        setVisible(true);

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        modeloProductos = new DefaultTableModel(
                new Object[]{"ID", "Código", "Nombre", "Stock", "Precio"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaProductos = new JTable(modeloProductos);
        tablaProductos.setRowHeight(24);
        JScrollPane spProductos = new JScrollPane(tablaProductos);
        spProductos.setPreferredSize(new Dimension(480, 0));

        // --- Ticket panel (derecha) ---
        modeloTicket = new DefaultTableModel(
                new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Total"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaTicket = new JTable(modeloTicket);
        tablaTicket.setRowHeight(24);
        JScrollPane spTicket = new JScrollPane(tablaTicket);
        spTicket.setPreferredSize(new Dimension(380, 0));

     
        JPanel pButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
     
        pButtons.add(btnAgregar);
        pButtons.add(btnEliminar);
        pButtons.add(btnPagar);

        // --- Panel de Resumen ---
        JPanel pResumen = new JPanel(new GridLayout(3, 2, 8, 6));
        
        pResumen.setBorder(BorderFactory.createTitledBorder("Resumen"));
        
        pResumen.add(new JLabel("Subtotal:")); 
        pResumen.add(lblSubtotal);
        pResumen.add(new JLabel("IVA (16%):")); 
        pResumen.add(lblIva);
        pResumen.add(new JLabel("Total:")); 
        pResumen.add(lblTotal);

    
        JPanel right = new JPanel(new BorderLayout(8,8));
        right.add(spTicket, BorderLayout.CENTER);
        right.add(pResumen, BorderLayout.SOUTH);

    
        root.add(spProductos, BorderLayout.WEST);
        root.add(right, BorderLayout.CENTER);
        root.add(pButtons, BorderLayout.SOUTH);

        setContentPane(root);
    }
}