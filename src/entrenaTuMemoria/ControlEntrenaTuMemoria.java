/* Autores: Jose David Barona Hernández - 1727590
 * 				 Andrés Felipe Rincón	- 1922840
 * Correos: jose.david.barona@correounivalle.edu.co 
 * 			andres.rincon.lopez@correounivalle.edu.co
 * Mini proyecto 1: Juego Entrena Tu Memoria
 * Fecha: 13/09/2020
 * 
 * */
package entrenaTuMemoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlEntrenaTuMemoria.
 * Esta clase maneja la lógica del juego. Distribuya las cartas aleatoriamente según la ronda y decide si el usuario pierde o gana al hacer click en una carta.
 */
public class ControlEntrenaTuMemoria {
	
	/** The ronda.
	 * Número de la ronda actual. Va de 1 a 5. */
	private int ronda; 
	
	/** The numero de cartas en ronda. 
	 * Para hacer el arrayList de cartas.*/
	private int numeroDeCartasEnRonda; 
	
	/** The cartas.
	 * ArrayList de todas las cartas existentes */
	private ArrayList<Carta> cartas; 
	
	/** The cartas en juego.
	 * ArrayList de cartas seleccionadas para la ronda */
	private ArrayList<Carta> cartasEnJuego;
	
	private Carta cplusplus, java, javaScript, python, ruby, php, rust, kotlin, go, swift, prolog, racket;
	
	/** The carta en juego. 
	 * Carta seleccionada aleatoriamente para mostrar al usuario.
	 * */
	private Carta cartaEnJuego;
	
	/** The carta escogida.
	 * Carta escogida por el usuario al hacer click.
	 *  */
	private Carta cartaEscogida;
	
	/** The estado.
	 * Estado del juego: true si gana la ronda, false si pierde la ronda.
	 *  */
	private boolean estado;
	
	/** The modo vista. 
	 * True si las imágenes de las cartas están visibles al usuario, false en caso contrario.
	 * */
	private boolean modoVista; 

	
	/**
	 * Constructor:
	 * Instantiates a new control entrena tu memoria.
	 * Crea las cartas y los dos arrayLists para el juego. 
	 * Añade todas las cartas al primer ArrayList y las mezcla, para que cada juego sea diferente.
	 * Las cartas del ArrayList "cartas" siempre son las mismas y mantienen su posición en un juego.
	 * Las cartas del ArrayList "cartasEnJuego" cambian su posición y su cantidad dependiendo de la ronda.
	 * Inicializa la ronda en 1 y el estado en true.
	 */
	public ControlEntrenaTuMemoria(){ 
	
		//Cartas
		cplusplus = new Carta("C++");
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
		
		cartas = new ArrayList<Carta>(); 
		cartasEnJuego = new ArrayList<Carta>();
		
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
		
		//Desordena todas las cartas al iniciar el juego
		cartas = ordenarCartas(cartas); 
	}
	/**
	 * Ordenar cartas.
	 * 
	 * Ordena aleatoriamente una lista de cartas 
	 * 
	 * @param lista the lista recibe una lista de tipo Carta
	 * @return the array list retorna una lista de tipo Carta
	 * */
	private ArrayList<Carta> ordenarCartas(ArrayList<Carta> lista){
		Collections.shuffle(lista);
		return lista;
	}
	/**
	 * Mostrar cartas en juego.
	 * 
	 * Determina las cartas que se usarán en el juego según la ronda, las añade al arrayList con las cartas en juego y las mezcla para que cambien de posiciones en cada ronda.
	 */	
	public void mostrarCartasEnJuego(){
		
		cartasEnJuego.clear();
		
		if(ronda==1) {
			numeroDeCartasEnRonda = 4;
		}
		if(ronda==2) {
			numeroDeCartasEnRonda = 6;
		}
		if(ronda==3) {
			numeroDeCartasEnRonda = 8;
		}
		if(ronda==4) {
			numeroDeCartasEnRonda = 10;
		}
		if(ronda>=5) {
			numeroDeCartasEnRonda = 12;
		}
		
		for(int i=0; i<numeroDeCartasEnRonda;i++){
			cartasEnJuego.add(cartas.get(i)); 
		}
		
		cartasEnJuego = ordenarCartas(cartasEnJuego);
		setCartaEnJuego();
	}
	
	/**
	 * Pasar ronda.
	 * Pasa a la siguiente ronda.
	 */
	private void pasarRonda(){	

		this.ronda++;
		setCartaEnJuego();
	}
	/**
	 * Devolver ronda.
	 * Devuelve a la ronda anterior.
	 */	
	private void devolverRonda(){
		if(ronda>1){		
			this.ronda--;
			setCartaEnJuego();
		}else {
			setCartaEnJuego();
		}
	}
	
	/**
	 * Determinar estado juego. 
	 * Examina el estado del juego, si el usuario avanza a la siguiente ronda o pierde.
	 */
	public void determinarEstadoJuego(){
		
		if(cartaEnJuego == cartaEscogida){
			
			estado= true;
			pasarRonda();
		}
		else{
			estado = false;
			devolverRonda();
		}
	}
	/**
	 * Voltear cartas.
	 * 
	 * Voltea todas las cartas que hayan en la ronda de juego.	
	 * @param bolean the bolean
	 */
	public void voltearCartas(boolean bolean) {

		for(int i=0;i<cartasEnJuego.size();i++){
			
			cartasEnJuego.get(i).voltearCarta(bolean);
		}
		this.modoVista = bolean;
	}
	/**
	 * Sets the carta clickeada.
	 * Recibe la posición de la carta clickeada por el usuario.
	 * @param posicionCarta the new carta clickeada
	 */
	
	public void setCartaClickeada(int posicionCarta){
		this.cartaEscogida = cartasEnJuego.get(posicionCarta);
	}
	/**
	 * Gets the modo vista.
	 * Dice si las cartas están boca abajo (false) o boca arriba (true)
	 * @return the modo vista
	 */
	public boolean getModoVista(){
		return modoVista;
	}
	/**
	 *	Determina la carta para mostrar al usuario escogida aleatoriamente en la ronda de juego de la lista de cartas. 
	 *
	 * @return the carta en juego
	 */
	private void setCartaEnJuego(){
		Random random = new Random();
		int posicionCarta = random.nextInt(cartasEnJuego.size());
		cartaEnJuego = cartasEnJuego.get(posicionCarta);
	}
	/**
	 * Gets the ronda actual.
	 * retorna el número de la ronda que se está jugando
	 * @return the ronda actual
	 */
	public int getRondaActual(){
		return ronda;
	}
	
	/**
	 * Gets the estado.
	 * retorna el estado del juego
	 * @return the estado
	 */
	public boolean getEstado(){
		return estado;
	}
	/**
	 * Gets the cartas en juego.
	 * retorna el array de cartas en la ronda actual
	 * @return the cartas en juego
	 */
	public ArrayList<Carta> getCartasEnJuego(){
		return cartasEnJuego;
	}
	/**
	 * Cual es la carta.
	 * Retorna la carta que el usuario debe buscar.
	 * @return the carta
	 */
	public Carta cualEsLaCarta(){
		return cartaEnJuego;
	}
	/**
	 * Gets the carta escogida.
	 * Retorna la carta escogida por el usuario al hacer click.
	 *
	 * @return the carta escogida
	 */
	public Carta getCartaEscogida(){
		return cartaEscogida;
	}
}
