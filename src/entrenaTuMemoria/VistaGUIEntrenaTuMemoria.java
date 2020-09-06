package entrenaTuMemoria;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VistaGUIEntrenaTuMemoria extends JFrame {

	//atributos
	private ArrayList<JLabel> cartasEnJuego;
	private JLabel cartaEscogida; //cartas que muestra el juego
	private JLabel carta1, carta2, carta3, carta4, carta5, carta6, carta7, carta8, carta9, carta10, carta11, carta12;
	private JLabel mensaje; //Indica lo que hay que hacer
	private ImageIcon imagen;
	
	private ControlEntrenaTuMemoria controlEntrenaTuMemoria;
	
	//métodos
	//constructor
	public VistaGUIEntrenaTuMemoria() {
		initGUI();
		
		//Configuración de la ventana
		this.setTitle("Entrena Tu Memoria");
		//this.setSize(900,900);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void initGUI() {
		//set up container - layout
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new FlowLayout());
				
		//Objetos de escucha y control
		controlEntrenaTuMemoria = new ControlEntrenaTuMemoria();
		controlEntrenaTuMemoria.organizarCartasEnJuego();
		
		cartasEnJuego = new ArrayList<JLabel>();
		//Componentes gráficos
		
		
		for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
			imagen = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
			cartasEnJuego.add(new JLabel(imagen));
			contenedor.add(cartasEnJuego.get(i));
		}
		
		
		
		
		
		
		
	}
}
