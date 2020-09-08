package entrenaTuMemoria;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class VistaGUIEntrenaTuMemoria extends JFrame {

	//atributos
	private ArrayList<JLabel> cartasEnJuego;
	private JLabel mensaje; //Indica lo que hay que hacer
	private JPanel zonaCartas;
	private ImageIcon imagen;
	private Timer timer;
	private boolean cartasVisibles;
	private Escucha escucha;
	private Icon icon;
	private JFrame ventana;
	
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
		
		//referencia a JFrame
		ventana = this;
		//set up container - layout
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//escucha
		Escucha escucha = new Escucha();
		//Objetos de escucha y control
		controlEntrenaTuMemoria = new ControlEntrenaTuMemoria();
		controlEntrenaTuMemoria.organizarCartasEnJuego();
		
		cartasEnJuego = new ArrayList<JLabel>();
		
		//timer
		Timer timer = new Timer("Timer");
		TimerTask task = new TimerTask() {
			public void run()		 {
				controlEntrenaTuMemoria.setCartasVisibles(false);
				 //Se ejecuta después de los 30 segundos
				for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
					imagen = new ImageIcon("src/imagenes/" + (i+1) + ".png" ); //carga las nuevas imágenes
					cartasEnJuego.get(i).setIcon(imagen); //cambia las imágenes de los JLabel
						
				}
				mensaje.setText("si wenas, te toca adivinar"); //cambia el mensaje
				
		    }
		};
		timer.schedule(task, 3000);
		
		//Zona de cartas
		zonaCartas = new JPanel();
		zonaCartas.setLayout(new GridLayout(2,6));	
		
		//juego
		empezarJuego();
		
		//MENSAJE
		//restricciones
		constraints.gridx = 0;
		constraints.gridy = 0;
		//constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER; //centrado
		//adición
		add(mensaje, constraints);
		
			
		
		//Coloca todas las imágenes necesarias	
		for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
			imagen = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
			JLabel imagenCarta = new JLabel(imagen);
			imagenCarta.addMouseListener(escucha); //agrega la escucha
			cartasEnJuego.add(imagenCarta);	
			zonaCartas.add(cartasEnJuego.get(i));
		}
		//restricciones
		constraints.gridx = 0;
		constraints.gridy= 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		//zonaCartas.setPreferredSize(new Dimension(400,400));
		zonaCartas.setBorder(new TitledBorder("Zona de Juego"));
		//adición
		add(zonaCartas, constraints);
		 
		
	}
	
	private void empezarJuego() {
		if(controlEntrenaTuMemoria.getRonda() == 1) {
			icon = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartaEscogida().getNombre() + ".png");
			mensaje = new JLabel("Mira las imágenes por 30 segundos", icon, 0);
			
		}
		
		cartasEnJuego.clear();
		for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
			imagen = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
			JLabel imagenCarta = new JLabel(imagen);
			imagenCarta.addMouseListener(escucha); //agrega la escucha
			cartasEnJuego.add(imagenCarta);	
			zonaCartas.add(cartasEnJuego.get(i));
		}
	}
	
	
	private class Escucha implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//Si las cartas no están visibles
			if(!controlEntrenaTuMemoria.getCartasVisibles()) {
				for(int i = 0; i < cartasEnJuego.size(); i++) {
					//Revisa cuál JLabel fue clickeado
					if(eventMouse.getSource() == cartasEnJuego.get(i)) {
						//Control recibe la carta clickeada y luego determina el estado del juego
						controlEntrenaTuMemoria.setCartaClickeada(controlEntrenaTuMemoria.getCartasEnJuego().get(i));
						controlEntrenaTuMemoria.determinarEstadoJuego();
					}
				}
				//
				if(controlEntrenaTuMemoria.getEstado()) {		
					icon = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartaEscogida().getNombre() + ".png");
					mensaje.setText("Mira las imágenes por 30 segundos");
					mensaje.setIcon(icon);
					//
					
					ventana.pack();
					for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
						imagen = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
						JLabel imagenCarta = new JLabel(imagen);
						imagenCarta.addMouseListener(escucha); //agrega la escucha
						cartasEnJuego.add(imagenCarta);	
						zonaCartas.add(cartasEnJuego.get(i));
						
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Perdiste");
				}
			}
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
