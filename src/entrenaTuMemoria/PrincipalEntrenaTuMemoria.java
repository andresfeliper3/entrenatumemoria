/* Autores: Jose David Barona Hernández - 1727590
 * 				 Andrés Felipe Rincón	- 1922840
 * Correos: jose.david.barona@correounivalle.edu.co 
 * 			andres.rincon.lopez@correounivalle.edu.co
 * Mini proyecto 1: Juego Entrena Tu Memoria
 * Fecha: 13/09/2020
 * 
 * */
package entrenaTuMemoria;

import java.awt.EventQueue;


// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalEntrenaTuMemoria.
 * Clase que contiene el método main y ejecuta el programa
 */
public class PrincipalEntrenaTuMemoria {

	/**
	 * The main method.
	 * Método principal en Java
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() 
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				VistaGUIEntrenaTuMemoria myVista = new VistaGUIEntrenaTuMemoria();
			}	
		});
	}

}
