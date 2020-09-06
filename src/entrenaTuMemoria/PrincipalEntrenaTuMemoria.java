package entrenaTuMemoria;

import java.awt.EventQueue;

public class PrincipalEntrenaTuMemoria {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				VistaGUIEntrenaTuMemoria myWindow = new VistaGUIEntrenaTuMemoria();
				//ControlEntrenaTuMemoria control = new ControlEntrenaTuMemoria();
				//System.out.println(control.getCartasEnJuego()[0].getNombre());
			}
		});
	}

}
