package CopiarTexto;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	CopiarTextoModelo modelo = new CopiarTextoModelo();
                CopiarTextoVista vista = new CopiarTextoVista();
                ControladorCopiarTexto controlador = new ControladorCopiarTexto(modelo, vista);

                controlador.iniciar();
            }
        });
    }
}
