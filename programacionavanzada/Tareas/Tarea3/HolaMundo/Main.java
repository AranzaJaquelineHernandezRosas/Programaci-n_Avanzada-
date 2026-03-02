package HolaMundo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                HolaMundoModelo modelo = new HolaMundoModelo();
                HolaMundoVista vista = new HolaMundoVista();
                ControladorHolaMundo controlador = new ControladorHolaMundo(modelo, vista);

                controlador.iniciar();
            }
        });
    }
}
