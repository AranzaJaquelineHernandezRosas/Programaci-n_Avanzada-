package Calculadora;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
        	@Override
            public void run() {
                CalculadoraGridModelo modelo = new CalculadoraGridModelo();
                CalculadoraGridVista vista = new CalculadoraGridVista();
                ControladorCalculadoraGrid controlador = new ControladorCalculadoraGrid(modelo, vista);

                controlador.iniciar();
            }
        });
    }
}
