package tablemodel;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	TableModelModelo modelo = new TableModelModelo();
            	TableModelVista vista = new TableModelVista();
                ControladorTableModel controlador = new ControladorTableModel(modelo, vista);

                controlador.iniciar();
            }
        });
    }
}
