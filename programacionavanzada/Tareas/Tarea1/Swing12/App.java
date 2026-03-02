package Swing12;
import javax.swing.SwingUtilities;
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new MainFrame();
	            }
	        });

	}

}
