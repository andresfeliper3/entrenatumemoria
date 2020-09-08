package entrenaTuMemoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ControlEntrenaTuMemoria {
	//atributos
	private int ronda, numeroDeCartasEnRonda; 
	private Carta cartaEscogida, cartaClickeada;
	private ArrayList<Carta> cartasEnJuego, listaCartas;
	private Carta python, java, javascript, php, cplusplus, rust, swift, ruby, go, racket, prolog, kotlin;
	private boolean modoVista; //true si las cartas están visibles	
	private boolean estado; //true:ganar. False: perder.
	
	//constructor
	public ControlEntrenaTuMemoria() {
		this.ronda = 5;
		python = new Carta("python");
		java = new Carta("java");
		javascript = new Carta("javaScript");
		php = new Carta("php");
		cplusplus = new Carta("cplusplus");
		rust = new Carta("rust");
		swift = new Carta("swift");
		ruby = new Carta("ruby");
		go = new Carta("go");
	    racket = new Carta("racket");
		prolog = new Carta("prolog");
		kotlin = new Carta("kotlin");
		
		listaCartas = new ArrayList<Carta>();
		cartasEnJuego = new ArrayList<Carta>();
		
		
		listaCartas.add(python);
		listaCartas.add(java);
		listaCartas.add(javascript);
		listaCartas.add(php);
		listaCartas.add(cplusplus);
		listaCartas.add(rust);
		listaCartas.add(swift);
		listaCartas.add(ruby);
		listaCartas.add(go);
		listaCartas.add(racket);
		listaCartas.add(prolog);
		listaCartas.add(kotlin);
		
		
		//revolver el orden en el que aparecen las cartas en las rondas
		Collections.shuffle(listaCartas);
		
		
	}
	
	
	//Organizar las cartas para empezar la partida. Coloca cartasEnJuego con todas las cartas para la ronda.
	public void organizarCartasEnJuego() {
		cartasEnJuego.clear();
		//Según la ronda escoge 
		switch (ronda) {
		case 1:
			numeroDeCartasEnRonda = 4;
			break;
		case 2:
			numeroDeCartasEnRonda = 6;
			break;
		case 3:
			numeroDeCartasEnRonda = 8;
			break; 
		case 4:
			numeroDeCartasEnRonda = 10;
			break;
		case 5:
			numeroDeCartasEnRonda = 12;
			break;
		}		
		//
		for(int i = 0; i < numeroDeCartasEnRonda; i++) {
			cartasEnJuego.add(listaCartas.get(i));
		}
		//revolver las posiciones de las cartas en cada partida
		Collections.shuffle(cartasEnJuego);
	}
	
	//Cambia de ronda si el usuario ha ganado
	private void pasarRonda() {
		//Cambia de ronda si el usuario a ganado
		if(ronda < 5) {
			ronda++;	
		}
		escogerCarta();
	}
	//Escoge la carta en juego, que el usuario debe adivinar
	private void escogerCarta() {
		Random rand = new Random();
		int numeroCartaEscogida = rand.nextInt(cartasEnJuego.size());
		this.cartaEscogida = cartasEnJuego.get(numeroCartaEscogida);
	}
	//Determina si se gana (y se pasa de ronda) o se pierde
	public void determinarEstadoJuego() {
		if(cartaClickeada == cartaEscogida) {
			estado = true; //Victoria
			pasarRonda();
		} 
		else {
			estado = false; //Derrota
		}
	}
	
	//Retorna el estado del juego
	public boolean getEstado() {
		return estado;
	}
	
	public Carta getCartaEscogida() {
		return cartaEscogida;
	}
	//Retorna las cartas en juego en la ronda
	public ArrayList<Carta> getCartasEnJuego() {
		return cartasEnJuego;
	}
	//Recibe la carta clickeada de la GUI
	public void setCartaClickeada(Carta cartaClickeada) {
		this.cartaClickeada = cartaClickeada;
	}
	
	
}
