package layouts;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	LayoutModelo modelo = new LayoutModelo();
	            	LayoutVista vista = new LayoutVista();
	                ControladorLayout controlador = new ControladorLayout(modelo, vista);

	                controlador.iniciar();
	            }
	        });
	    }
	
	}


