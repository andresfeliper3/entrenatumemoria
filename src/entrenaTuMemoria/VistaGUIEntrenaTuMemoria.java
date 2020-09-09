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
	private TimerTask task;
	private ControlEntrenaTuMemoria controlEntrenaTuMemoria;
	
	//métodos
	//constructor
	public VistaGUIEntrenaTuMemoria() {
		initGUI();
		
		//Configuración de la ventana
		this.setTitle("Entrena Tu Memoria");
		this.setSize(900,900);
		//this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void initGUI() {
		
		//referencia a JFrame
		ventana = this;
		//set up container - layout
		//Container contenedor = this.getContentPane();
		//contenedor.setLayout(new GridBagLayout());
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//escucha
		Escucha escucha = new Escucha();
		//Objetos de escucha y control
		controlEntrenaTuMemoria = new ControlEntrenaTuMemoria();
		controlEntrenaTuMemoria.organizarCartasEnJuego();
		
		cartasEnJuego = new ArrayList<JLabel>();
		
		
		
		//Zona de cartas
		zonaCartas = new JPanel();
		zonaCartas.setLayout(new GridLayout(2,6));	
		//restricciones
		constraints.gridx = 0;
		constraints.gridy= 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.LAST_LINE_END;
		//zonaCartas.setPreferredSize(new Dimension(400,400));
		zonaCartas.setBorder(new TitledBorder("Zona de Juego"));
		//adición
		add(zonaCartas, constraints);
		
		timer = new Timer("Timer");
		task = new TimerTask() {
			//PROBAR QUITAR ESCUCHAS DEL TIMER, COLOCARLAS EN MÉTODO Y COLOCAR TIMER EN EL MÉTODO
			public void run()		 {
				controlEntrenaTuMemoria.setCartasVisibles(false);
				 //Se ejecuta después de los 30 segundos
				for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
					imagen = new ImageIcon("src/imagenes/" + (i+1) + ".png" ); //carga las nuevas imágenes
					cartasEnJuego.get(i).setIcon(imagen); //cambia las imágenes de los JLabel
					cartasEnJuego.get(i).addMouseListener(escucha);		
				}
				mensaje.setText("si wenas, te toca adivinar"); //cambia el mensaje
						
			}
		};
	
		//Mensaje
		icon = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartaEscogida().getNombre() + ".png");
		mensaje = new JLabel("Mira las imágenes por 30 segundos", icon, 0);
		//restricciones
		constraints.gridx = 0;
		constraints.gridy = 0;
		//constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER; //centrado
		//adición
		add(mensaje, constraints);
		
		//juego
		empezarJuego(); //primera ronda
		
		
	}
	
	//Muestra las cartas en pantalla
	private void mostrarCartas() {
		for(int i = 0; i < controlEntrenaTuMemoria.getCartasEnJuego().size(); i++) {
			imagen = new ImageIcon("src/imagenes/" + controlEntrenaTuMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
			cartasEnJuego.add(new JLabel(imagen));	
			zonaCartas.add(cartasEnJuego.get(i));
			System.out.println(cartasEnJuego.get(i));
			System.out.println(controlEntrenaTuMemoria.getCartasEnJuego().get(i).getNombre());
		}
		
		
	}
	
	private void empezarJuego() {
		
		
		controlEntrenaTuMemoria.setCartasVisibles(true);
		
		if(controlEntrenaTuMemoria.getRonda() == 1) {
			mostrarCartas();
			timer.schedule(task, 3000);
		} 
		else if(controlEntrenaTuMemoria.getRonda() >= 2 ) {
			zonaCartas.removeAll();
			zonaCartas.repaint();
			cartasEnJuego.clear();
			mostrarCartas();	
			//POR QUÉ NO SIRVE SI NO SE IMPRIME'?????
			mensaje.setText("Por favor espere 30 segundos \n ronda actual: " + controlEntrenaTuMemoria.getRonda());
			Icon iconoTiempo =  new ImageIcon("src/imagenes/1.png");
			mensaje.setIcon(iconoTiempo);	
			System.out.println("tamaño del array " + cartasEnJuego.size());
			timer.schedule(task,3000);
			
		}
		
		
	}
	
	
	private class Escucha implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//Si las cartas no están visibles
			System.out.println("Click");
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
						
					JOptionPane.showMessageDialog(null, "Ganaste");
					empezarJuego();
					
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
