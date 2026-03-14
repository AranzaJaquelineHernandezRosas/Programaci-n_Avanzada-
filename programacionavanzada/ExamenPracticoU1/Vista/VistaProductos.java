package VistaExamen;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class VistaProductos extends JInternalFrame {

    public JTextField txtId       = new JTextField(14);
    public JTextField txtCodigo   = new JTextField(14);
    public JTextField txtNombre   = new JTextField(14);
    public JTextField txtStock    = new JTextField(8);
    public JTextField txtPrecio   = new JTextField(8);

    public JComboBox<String> comboCategoria = new JComboBox<>(
            new String[]{"Abarratores", "Electronica", "Ropa",
                         "Frutas Y Verduras", "Farmacia"});

    public JRadioButton rbActivo   = new JRadioButton("Activo",   true);
    public JRadioButton rbInactivo = new JRadioButton("Inactivo", false);

    public JButton btnGuardar = new JButton("Guardar Cambios");
    public JButton btnLimpiar = new JButton("Limpiar Formulario");

    public JButton btnBuscar    = new JButton("Buscar");
    public JButton btnModificar = new JButton("Modificar");
    public JButton btnEliminar  = new JButton("Eliminar");

    public JTextField        txtBuscar    = new JTextField(12);
    public JComboBox<String> comboBuscarPor = new JComboBox<>(
            new String[]{"ID", "Nombre", "Código", "Categoría"});

    public JTable            tabla;
    public DefaultTableModel modeloTabla;

    public VistaProductos() {
        super("Productos", true, true, true, true);
        setSize(980, 600);
        setVisible(true);

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        
        root.add(crearPanelIzquierdo(), BorderLayout.WEST);
        root.add(crearPanelDerecho(),   BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel crearPanelIzquierdo() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(280, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Alta y Edición"));

        GridBagConstraints c = new GridBagConstraints();
        c.fill      = GridBagConstraints.HORIZONTAL;
        c.weightx   = 1.0;
        c.gridwidth = 2;
        c.insets    = new Insets(4, 10, 2, 10);

        txtId.setEnabled(false);

        int row = 0;
        fila(panel, c, row++, "ID [Auto]:",           txtId);
        fila(panel, c, row++, "Nombre del Producto:", txtNombre);
        fila(panel, c, row++, "Código:",              txtCodigo);
        fila(panel, c, row++, "Categoría:",           comboCategoria);

        // Precio e Inventario
        c.gridwidth = 1; c.weightx = 0.4;
        c.gridx = 0; c.gridy = row * 2;
        panel.add(new JLabel("Precio Compra:"), c);
        c.gridx = 1; c.weightx = 0.6;
        panel.add(txtPrecio, c);

        JTextField txtPV = new JTextField(8);
        c.gridx = 0; c.gridy = row * 2 + 1;
        panel.add(new JLabel("Precio Venta:"), c);
        c.gridx = 1;
        panel.add(txtPV, c);
        row++;

        c.gridx = 0; c.gridy = row * 2;
        panel.add(new JLabel("Stock Inicial:"), c);
        c.gridx = 1;
        panel.add(txtStock, c);

        JTextField txtSM = new JTextField(8);
        c.gridx = 0; c.gridy = row * 2 + 1;
        panel.add(new JLabel("Stock Mínimo:"), c);
        c.gridx = 1;
        panel.add(txtSM, c);
        row++;

  
        c.gridx = 0; c.gridy = row * 2; c.gridwidth = 2;
        c.insets = new Insets(8, 8, 4, 8);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbActivo); bg.add(rbInactivo);
        JPanel pe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pe.setBorder(BorderFactory.createTitledBorder("Estado"));
        pe.add(rbActivo); pe.add(rbInactivo);
        panel.add(pe, c);

      
        c.gridy = row * 2 + 1;
        JPanel pb = new JPanel(new GridLayout(1, 2, 8, 0));
        pb.add(btnGuardar); pb.add(btnLimpiar);
        panel.add(pb, c);

        c.gridy++; c.weighty = 1.0;
        panel.add(new JLabel(), c);

        return panel;
    }

    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Catálogo de Productos"));

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID","Código","Nombre",
                             "Categoría","Stock","Precio","Estado"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(crearPanelAcciones(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelAcciones() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Acciones y Filtros"));

        JPanel filaBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        filaBuscar.add(new JLabel("Buscar por:"));
        filaBuscar.add(comboBuscarPor);
        filaBuscar.add(txtBuscar);
        filaBuscar.add(btnBuscar);
        panel.add(filaBuscar, BorderLayout.NORTH);

        JPanel filaAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        filaAcciones.add(new JLabel("Seleccionar:"));
        filaAcciones.add(btnModificar);
        filaAcciones.add(btnEliminar);
        panel.add(filaAcciones, BorderLayout.SOUTH);

        return panel;
    }

    private void fila(JPanel p, GridBagConstraints c,
                      int fila, String txt, JComponent campo) {
        c.gridx = 0; c.gridy = fila * 2;
        c.insets = new Insets(5, 10, 0, 10);
        p.add(new JLabel(txt), c);
        c.gridy = fila * 2 + 1;
        c.insets = new Insets(1, 10, 2, 10);
        p.add(campo, c);
    }
}