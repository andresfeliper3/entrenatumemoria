package entrenaTuMemoria;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VistaGUIEntrenaTuMemoria extends JFrame {

	//atributos
	private ArrayList<JLabel> cartasEnJuego;
	private JLabel mensaje; //Indica lo que hay que hacer
	private ImageIcon imagen;
	private Timer timer;
	
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
		contenedor.setLayout(new GridLayout(3,3));
				
		//Objetos de escucha y control
		controlEntrenaTuMemoria = new ControlEntrenaTuMemoria();
		controlEntrenaTuMemoria.organizarCartasEnJuego();
		
		cartasEnJuego = new ArrayList<JLabel>();
		//timer
		Timer timer = new Timer();
		
		//Componentes gráficos
		//Título
		mensaje = new JLabel("Mira las imágenes por 30 segundos");
		contenedor.add(mensaje);
		
		for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
			imagen = new ImageIcon("src/imagenes/" + (i+1) + ".png" );
			cartasEnJuego.add(new JLabel(imagen));
			contenedor.add(cartasEnJuego.get(i));
		}
		
		
				
		
		
		
	}
	
	
	private class Escucha implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

	
	
}
