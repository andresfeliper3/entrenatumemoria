package entrenaTuMemoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ControlEntrenaTuMemoria {
	
	private int ronda; //n�mero de la ronda actual
	private int numeroDeCartasEnRonda; //Para hacer la array din�mica, se utiliza una variable auxiliar
	private ArrayList<Carta> cartas; //array de cartas actuales
	private ArrayList<Carta> cartasEnJuego;
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
		ronda = 1;
		estado= true;
		
		cartas = new ArrayList<>(); 
		cartasEnJuego = new ArrayList<>();
		
		//Agregar cartas a la lista de cartas
		cartas.add(cplusplus);
		cartas.add(java);
		cartas.add(javaScript);
		cartas.add(python);
		cartas.add(ruby);
		cartas.add(php);
		cartas.add(rust);
		cartas.add(go);
		cartas.add(swift);
		cartas.add(prolog);
		cartas.add(racket);
		cartas.add(kotlin);
		
		
		cartas = ordenarCartas(cartas); //Desordena todas las cartas al iniciar el juego
		
	}

	//ordena aleatoriamente una lista de cartas
	private ArrayList<Carta> ordenarCartas(ArrayList<Carta> lista)
	{
		Collections.shuffle(lista);
		return lista;
	}
	 
	//Determina las cartas que se usar�n en el juego
	public void mostrarCartasEnJuego()
	{
		cartasEnJuego.clear();
		
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
			numeroDeCartasEnRonda = 12;
		}
		
		for(int i=0; i<numeroDeCartasEnRonda;i++)
		{
			cartasEnJuego.add(cartas.get(i)); 
		}
		
		cartasEnJuego = ordenarCartas(cartasEnJuego);
		
	}
	//Pasar a la siguiente ronda
	private void pasarRonda()
	{
			this.ronda++;
		
		getCartaEnJuego();
	}
	
	//examina el estado del juego, si avanzas a la siguiente ronda o perdiste.
	public void determinarEstadoJuego()
	{
		if(cartaEnJuego == cartaEscogida)
		{
			estado= true;
			pasarRonda(); //avanza a la siguiente ronda
			
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
	
	//Dice que carta escogi� el usuario de la lista de cartas (GUI manda la posici�n de la carta escogida)
	public void setCartaClickeada(int posicionCarta)
	{
		this.cartaEscogida = cartasEnJuego.get(posicionCarta);
	}
	
	//dice si la carta est� boca abajo (false) o boca arriba (true)
	public boolean getModoVista()
	{
		return modoVista;
	}
	//Escoge la carta a buscar en la ronda del juego de la lista de Cartas en Juego
	public void getCartaEnJuego()
	{
		Random random = new Random();
		int posicionCarta = random.nextInt(cartasEnJuego.size());
		cartaEnJuego = cartasEnJuego.get(posicionCarta);
	}
	
	//retorna el n�mero de la ronda que se est� jugando
	public int getRondaActual()
	{
		return ronda;
	}
	
	//retorna el estado del juego
	public boolean getEstado()
	{
		return estado;
	}
	
	//retorna el array de cartas en la ronda actual
	public ArrayList<Carta> getCartasEnJuego()
	{
		return cartasEnJuego;
	}
	//Retorna la carta que se debe buscar
	public Carta cualEsLaCarta()
	{
		return cartaEnJuego;
	}
		
	//
	public Carta getCartaEscogida()
	{
		return cartaEscogida;
	}
}
