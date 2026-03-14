package VistaExamen;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public JDesktopPane desktop;
    public JMenuItem menuProductos     = new JMenuItem("  Productos");
    public JMenuItem menuPuntoVenta    = new JMenuItem("  Punto de Venta");
    public JMenuItem menuSalir         = new JMenuItem("  Salir");
    public JButton   btnAbrirProductos = new JButton("  Gestión de Productos");
    public JButton   btnAbrirPuntoVenta= new JButton("  Punto de Venta");

 

    public VentanaPrincipal() {
        setTitle("Sistema de Inventario");
        setSize(1100, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
     
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        desktop.add(crearPanelBienvenida());
        add(desktop);

        
        JMenuBar barra = new JMenuBar();
     

        JMenu menu = new JMenu("  Sistema  ");
       

        menu.add(menuProductos);
        menu.add(menuPuntoVenta); 
        menu.addSeparator();
        menu.add(menuSalir);
        barra.add(menu);

      
        JLabel hint = new JLabel("  Haz clic en \"Sistema\" para abrir");
        barra.add(hint);

        barra.add(Box.createHorizontalGlue());
        JLabel ver = new JLabel("v1.0   ");
        barra.add(ver);

        setJMenuBar(barra);
    }

    private JInternalFrame crearPanelBienvenida() {
        JInternalFrame frame = new JInternalFrame(
                "", false, false, false, false);
        frame.setSize(380, 230);
        frame.setLocation(360, 220);
        frame.setVisible(true);

        JPanel p = new JPanel(new GridBagLayout());
      
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.insets = new Insets(5, 0, 5, 0);

      
        c.gridx = 0; c.gridy = 0;
        JLabel titulo = new JLabel(
                "Sistema de Inventario", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        p.add(titulo, c);

        
        c.gridy = 1;
        JLabel sub = new JLabel(
                "Selecciona",
                SwingConstants.CENTER);
        p.add(sub, c);

       
        c.gridy = 2;
        p.add(new JSeparator(), c);

       
        c.gridy = 3; c.insets = new Insets(14, 0, 4, 0);
       
        btnAbrirProductos.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAbrirProductos.setPreferredSize(new Dimension(0, 38));
        p.add(btnAbrirProductos, c);

      
        c.gridy = 4; c.insets = new Insets(6, 0, 0, 0);
       
        btnAbrirPuntoVenta.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAbrirPuntoVenta.setPreferredSize(new Dimension(0, 34));
        p.add(btnAbrirPuntoVenta, c);

        frame.setContentPane(p);
        return frame;
    }
}
