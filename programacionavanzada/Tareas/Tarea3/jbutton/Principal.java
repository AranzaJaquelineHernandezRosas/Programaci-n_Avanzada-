package jbutton;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	BotonModelo modelo = new BotonModelo();
                BotonVista vista = new BotonVista();
                ControladorBoton controlador = new ControladorBoton(modelo, vista);

                controlador.iniciar();
            }
        });
    }
}
