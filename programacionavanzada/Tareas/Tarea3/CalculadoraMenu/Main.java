package CalculadoraMenu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	CalculadoraModelo modelo = new CalculadoraModelo();
		                CalculadoraVista vista = new CalculadoraVista();
		                ControladorCalculadora controlador = new ControladorCalculadora(modelo, vista);

		                controlador.iniciar();
		            }
		        });
		    }
		

	}


