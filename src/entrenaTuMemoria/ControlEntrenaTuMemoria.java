package entrenaTuMemoria;

import java.util.Random;

public class ControlEntrenaTuMemoria {
	//atributos
	private int ronda, numeroDeCartasEnRonda; 
	private Carta cartaEscogida, cartaClickeada;
	private Carta[] listaCartas;
	private Carta[] cartasEnJuego;
	private Carta python, java, javascript, php, cplusplus, rust, swift, ruby, go, racket, prolog, kotlin;
	private boolean modoVista; //true si las cartas están visibles	
	private boolean estado; //true:ganar. False: perder.
	
	//constructor
	public ControlEntrenaTuMemoria() {
		this.ronda = 1;
		python = new Carta("Python");
		java = new Carta("Java");
		javascript = new Carta("Javascript");
		php = new Carta("Php");
		cplusplus = new Carta("C++");
		rust = new Carta("Rust");
		swift = new Carta("swift");
		ruby = new Carta("Ruby");
		go = new Carta("Go");
	    racket = new Carta("Racket");
		prolog = new Carta("Prolog");
		kotlin = new Carta("Kotlin");
		listaCartas[0] = python;
		listaCartas[1] = java;
		listaCartas[2] = javascript;
		listaCartas[3] = php;
		listaCartas[4] = cplusplus;
		listaCartas[5] = rust;
		listaCartas[6] = swift;
		listaCartas[7] = ruby;
		listaCartas[8] = go;
		listaCartas[9] = racket;
		listaCartas[10] = prolog;
		listaCartas[11] = kotlin;
		
		//revolver todas las posiciones de las cartas
		revolverCartas(listaCartas);
		
		
	}
	//Revuelve las cartas aleatoriamente dentro de listaCartas
	private Carta[] revolverCartas(Carta[] lista) {
		Random rand = new Random();
		for(int i = 0; i < lista.length; i++) {
			int indiceRandom = rand.nextInt(lista.length);
			Carta auxiliar = lista[indiceRandom];
			lista[indiceRandom] = lista[i];
			lista[i] = auxiliar;
		}
		return lista;
	}
	
	//Organizar las cartas para empezar la partida. Coloca cartasEnJuego con todas las cartas para la ronda.
	public void organizarCartasEnJuego() {
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
			cartasEnJuego[i] = listaCartas[i];
		}	
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
		int numeroCartaEscogida = rand.nextInt(cartasEnJuego.length);
		this.cartaEscogida = cartasEnJuego[numeroCartaEscogida];
	}
	//Determina si se gana o pierde
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
	//Recibe la carta clickeada de la GUI
	public void setCartaClickeada(Carta cartaClickeada) {
		this.cartaClickeada = cartaClickeada;
	}
	
}
