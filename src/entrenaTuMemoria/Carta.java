package entrenaTuMemoria;

public class Carta {
	//atributos
	private String nombre;
	private int posicion;
	private boolean visible;
	
	//Constructor
	public Carta(String nombre) {
		this.nombre = nombre;
		posicion = 0; //posición inicial, antes de comenzar el juego
		visible = true;
	}
	
	//Hace que una carta deje de ser visible
	public void voltearCarta() {
		this.visible = false;
	}
	
	//Retorna el nombre de la carta
	public String getNombre() {
		return this.nombre;
	}
	
}
