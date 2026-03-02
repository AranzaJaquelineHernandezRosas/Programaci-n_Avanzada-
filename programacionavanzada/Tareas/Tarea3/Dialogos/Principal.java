package dialogos;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	DialogosModelo modelo = new DialogosModelo();
            	DialogosVista vista = new DialogosVista();
                ControladorDialogos controlador = new ControladorDialogos(modelo, vista);

                controlador.iniciar();
            }
        });
    }
}
