/* Autores: Jose David Barona Hernández - 1727590
 * 				 Andrés Felipe Rincón	- 1922840
 * Correos: jose.david.barona@correounivalle.edu.co 
 * 			andres.rincon.lopez@correounivalle.edu.co
 * Mini proyecto 1: Juego Entrena Tu Memoria
 * Fecha: 13/09/2020
 * 
 * */
package entrenaTuMemoria;

// TODO: Auto-generated Javadoc
/**
 * The Class Carta. 
 * Esta clase simula una carta y permite ver si está boca arriba o boca abajo.
 */
public class Carta {

	private String nombre;
	
	/** 
	 * The visible. 
	 * Permite saber si la carta está boca arriba(true) o boca abajo (false)
	 * */
	private boolean visible;

	/** 
	 * Instantiates a new Carta. 
	 * Constructor que crea una nueva carta y recibe el nombre de la carta como parámetro
	 * @param nombre the nombre es el nombre de la carta
	 */
	public Carta(String nombre) {
		this.nombre = nombre;
		this.visible = true;
	}
	/** 
	 * Voltear carta.
	 * Este método simula la acción de voltear una carta boca arriba o boca abajo
	 * @param estadoCarta the estado carta es un boleano que determina si la carta debe voltearse boca arriba(true) o boca abajo(false)
	 */
	public void voltearCarta(boolean estadoCarta) {
		this.visible = estadoCarta;
	}
	/**
	 * Gets the nombre.
	 * Este método dice el nombre de la carta
	 * @return the nombre retorna el nombre de la carta
	 */
	public String getNombre() {
		return this.nombre;
	}
	
}
