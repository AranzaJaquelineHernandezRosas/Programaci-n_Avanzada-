package Conversormonedas;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	ConversorMonedasModelo modelo = new ConversorMonedasModelo();
                ConversorMonedasVista vista = new ConversorMonedasVista();
                ControladorConversorMonedas controlador = new ControladorConversorMonedas(modelo, vista);

                controlador.iniciar();
            }
        });
    }
}
