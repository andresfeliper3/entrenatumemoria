package entrenaTuMemoria;

import java.util.Random;

public class ControlEntrenaTuMemoria {
	
	private int ronda; //número de la ronda actual
	private int numeroDeCartasEnRonda; //Para hacer la array dinámica, se utiliza una variable auxiliar
	private Carta[] cartas; //array de cartas actuales
	private Carta[] cartasEnJuego;
	private Carta cplusplus, java, javaScript, python, ruby, php, rust, kotlin, go, swift, prolog, racket; //Cartas que pueden usarse
	private Carta cartaEnJuego;
	private Carta cartaEscogida;
	private boolean estado;
	private boolean modoVista;

	
	public ControlEntrenaTuMemoria() //Constructor
	{
		//Cartas
		cplusplus = new Carta("Cplusplus");
		java = new Carta("Java");
		javaScript = new Carta("JavaScript");
		python = new Carta("Python");
		ruby = new Carta("Ruby");
		php = new Carta("Php");
		rust = new Carta("Rust");
		kotlin = new Carta("Kotlin");
		go = new Carta("Go");
		swift = new Carta("Swift");
		prolog = new Carta("Prolog");
		racket = new Carta("Racket");

		
		ronda = 1; //Inicia en la ronda 1
		cartas = new Carta[12]; //12 cartas totales
		numeroDeCartasEnRonda = 0;
		cartasEnJuego = new Carta[numeroDeCartasEnRonda]; // 4 cartas primera ronda
		
		//Agregar cartas a la lista de cartas
		cartasEnJuego[0] = cplusplus;
		cartasEnJuego[1] = java;
		cartasEnJuego[2] = javaScript;
		cartasEnJuego[3] = python;
		cartasEnJuego[4] = ruby;
		cartasEnJuego[5] = php;
		cartasEnJuego[6] = rust;
		cartasEnJuego[7] = go;
		cartasEnJuego[8] = swift;
		cartasEnJuego[9] = prolog;
		cartasEnJuego[10] = racket;
		cartasEnJuego[11] = kotlin;
		
		cartas = ordenarCartas(cartas); //Desordena todas las cartas al iniciar el juego
		
	}

	//ordena aleatoriamente una lista de cartas
	public Carta[] ordenarCartas(Carta[] lista)
	{
		Random random = new Random();
		for(int i=0;i< lista.length;i++)
		{
			int indiceRandom = random.nextInt(lista.length);
			Carta cartaActual = lista[indiceRandom];
			lista[indiceRandom] = lista[i];
			lista[i] = cartaActual;
		}
		return lista;
	}
	 
	//Determina las cartas que se usarán en el juego
	public void mostrarCartasEnJuego()
	{
		if(ronda == 1)
		{
			numeroDeCartasEnRonda = 4;
		}
		if(ronda == 2)
		{
			numeroDeCartasEnRonda = 6;
		}
		if(ronda == 3)
		{
			numeroDeCartasEnRonda = 8;
		}
		if(ronda == 4)
		{
			numeroDeCartasEnRonda = 10;
		}
		if(ronda >= 5)
		{
			numeroDeCartasEnRonda = 12;
		}
		
		for(int i=0; i<numeroDeCartasEnRonda;i++)
		{
			cartasEnJuego[i] = cartas[i]; 
		}
		cartasEnJuego = ordenarCartas(cartasEnJuego);
	}
	
	//examina el estado del juego, si avanzas a la siguiente ronda o perdiste.
	public void determinarEstadoJuego()
	{
		if(cartaEnJuego == cartaEscogida)
		{
			ronda = ronda+1; //avanza a la siguiente ronda
		}
		else
		{
			estado = false; //Perdio 
		}
	}
	
	//voltea las cartas
	public boolean voltearCarta() 
	{
		if(modoVista == false)
		{
		return modoVista = true;//boca arriba
		}
		else 
		{
		return modoVista = false;//boca abajo
		}
	}
	
	//Dice que carta escogió el usuario de la lista de cartas
	public void CartaClickeada(int posicionCarta)
	{
		cartaEscogida = cartasEnJuego[posicionCarta-1];
	}
	
	//dice si la carta está boca abajo (false) o boca arriba (true)
	public boolean getEstadoCarta()
	{
		return modoVista;
	}
	//Escoge la carta a buscar en la ronda del juego de la lista de Cartas en Juego
	public Carta getCartaEnJuego(Carta[] lista)
	{
		Random random = new Random();
		int posicionCarta = random.nextInt(lista.length);
		cartaEnJuego = cartasEnJuego[posicionCarta];
		return cartaEnJuego;
	}
	
	//retorna el número de la ronda que se está jugando
	public int rondaActual()
	{
		return ronda;
	}
	
}
