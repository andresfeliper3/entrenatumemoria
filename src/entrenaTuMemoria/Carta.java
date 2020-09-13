package entrenaTuMemoria;

public class Carta {
	
	private String nombre;
	private boolean visible;
	
	//Constructor
	public Carta(String nombre) {
		this.nombre = nombre;
		this.visible = true;
	}
	
	//Hace que una carta deje de ser visible
	public void voltearCarta(boolean estadoCarta) {
		this.visible = estadoCarta;
	}
	
	//Retorna el nombre de la carta
	public String getNombre() {
		return this.nombre;
	}
	
}
