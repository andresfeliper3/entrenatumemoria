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
	private boolean estado; //true:ganar. False: perder.
	private boolean cartasVisibles;
	
	//constructor
	public ControlEntrenaTuMemoria() {
		this.ronda = 1;
		python = new Carta("Python");
		java = new Carta("Java");
		javascript = new Carta("JavaScript");
		php = new Carta("PHP");
		cplusplus = new Carta("C++");
		rust = new Carta("Rust");
		swift = new Carta("Swift");
		ruby = new Carta("Ruby");
		go = new Carta("Go");
	    racket = new Carta("Racket");
		prolog = new Carta("Prolog");
		kotlin = new Carta("Kotlin");
		
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
		this.cartasVisibles = true;
		
		//Escoger carta para que el usuario adivine
		escogerCarta();
		
	}
	
	//Cambia de ronda si el usuario ha ganado
	private void pasarRonda() {
		//Cambia de ronda si el usuario a ganado
		if(ronda < 5 && estado) {
			ronda++;	
		}
		else if(ronda > 1 && !estado) {
			ronda--;
		}
		
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
			pasarRonda();
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

	//retorna true si las cartas están visibles y false en caso contrario
	public boolean getCartasVisibles() {
		return cartasVisibles;
	}

	
	public void setCartasVisibles(boolean cartasVisibles) {
		this.cartasVisibles = cartasVisibles;
	}
	
	public int getRonda() {
		return ronda;
	}
	
}
