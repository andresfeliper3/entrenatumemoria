/* Autores: Jose David Barona Hernández - 1727590
 * 				 Andrés Felipe Rincón	- 1922840
 * Correos: jose.david.barona@correounivalle.edu.co 
 * 			andres.rincon.lopez@correounivalle.edu.co
 * Mini proyecto 1: Juego Entrena Tu Memoria
 * Fecha: 13/09/2020
 * 
 * */
package entrenaTuMemoria;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaGUIEntrenaTuMemoria.
 */
public class VistaGUIEntrenaTuMemoria extends JFrame {

		private JLabel mensaje,carta,ronda;
		private ImageIcon imagen;
		private ArrayList<JLabel> cartasEnJuego;
		private Escucha escucha;
		private ControlEntrenaTuMemoria controlMemoria;
		private Timer timer;
		private JPanel zonaCartas,zonaTop,zonaMensaje,zonaRonda;
		private JFrame referenciaGUI;
		private int contador;
	/**
	 * Instantiates a new vista GUI entrena tu memoria. 
	 * Constructor de la clase que inicia los objetos auxiliares
	 */
	public VistaGUIEntrenaTuMemoria(){
			
		initGUI();
		
		//default config
		this.setTitle("Entrena tu memoria");
		this.setBackground(new Color(255,255,255));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
		/**
		 * Inits the GUI. 
		 */
		private void initGUI() {

			//set up container - layout
			this.getContentPane().setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			
					
			//escucha, referencia y control
			escucha = new Escucha();
			referenciaGUI = this;
			controlMemoria = new ControlEntrenaTuMemoria();
			
			//Cartas en juego
			cartasEnJuego = new ArrayList<JLabel>();
			
			//set up GUIComponents add to window
			//zona Ronda
			zonaRonda = new JPanel();
			zonaRonda.setBackground(Color.WHITE);
			
			//Ronda
			ronda = new JLabel("Ronda: " + controlMemoria.getRondaActual());
			ronda.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
			zonaRonda.add(ronda);
			
			//restricciones
			constraints.gridx=0; 
			constraints.gridy=0;
			constraints.gridwidth=2;
			constraints.fill=GridBagConstraints.HORIZONTAL;
			add(zonaRonda,constraints);
			
			//Zona TOP
			zonaTop = new JPanel();
			zonaTop.setBackground(Color.WHITE);
			
			//Carta
			Icon iconoTiempo =  new ImageIcon("src/imagenes/tiempo.gif");
			carta = new JLabel(iconoTiempo);
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			carta.setBorder(border);
			zonaTop.add(carta);
			
			//restricciones
			constraints.gridx=0; 
			constraints.gridy=1;
			constraints.gridwidth=2;
			constraints.fill=GridBagConstraints.HORIZONTAL;
			add(zonaTop,constraints);
			
			//Zona Mensaje
			zonaMensaje = new JPanel();
			zonaMensaje.setBackground(Color.WHITE);
			//mensaje
			mensaje = new JLabel("Espere 30 segundos");
			mensaje.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
			zonaMensaje.add(mensaje);
			constraints.gridx=0; 
			constraints.gridy=2;
			constraints.gridwidth=2;
			constraints.fill=GridBagConstraints.HORIZONTAL;
			add(zonaMensaje,constraints);
						
			//Zona de cartas
			zonaCartas = new JPanel();
			zonaCartas.setBorder(new TitledBorder("Cartas en juego"));
			zonaCartas.setBackground(Color.WHITE);

			//Restricciones
			constraints.gridx=0; 
			constraints.gridy=3;
			constraints.gridwidth=1; 
			constraints.gridheight=1;
			constraints.fill=GridBagConstraints.LAST_LINE_END;
			add(zonaCartas,constraints);
			
			//Empieza el Juego
			empezarJuego();
		}
		
		/**
		 * Empezar juego. 
		 * Inicia la ronda de juego
		 */
		private void empezarJuego(){
			
			//Ejecución del timer
			timer = new Timer("Timer");
			TimerTask task = new TimerTask(){
				public void run() {
					mensaje.setText("Por favor espere " + contador + " segundos");
					contador--;
						if(contador<0){
							ocultarCartas();
							cancel();
						}
				}
			};
			
			zonaCartas.removeAll();
			zonaCartas.repaint();

			cartasEnJuego.clear();
			controlMemoria.mostrarCartasEnJuego();	
			crearCartasEnJuego();
			redimensionarVentana();
			
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			ronda.setText("Ronda: " + controlMemoria.getRondaActual());
			mensaje.setText("Por favor espere 30 segundos");
			Icon iconoTiempo =  new ImageIcon("src/imagenes/tiempo.gif");
			carta.setIcon(iconoTiempo);
			carta.setBorder(border);

			if(controlMemoria.getModoVista())
			{
				contador=30;
				timer.scheduleAtFixedRate(task, 0, 1000);
			}
		}

		/**
		 * Gets the dimension X.
		 *	Método auxiliar que ayuda a redimensionar el eje X del JPanel dependiendo del número de la ronda
		 * @param control the control recibe un objeto de tipo controlEntrenaTuMemoria
		 * @return the dimension X retorna la dimensión en X(ancho) que debería de tener el JPanel
		 */
		private int getDimensionX(ControlEntrenaTuMemoria control){
			
			if(control.getRondaActual()==1){
				return 360;
			}
			if(control.getRondaActual()==2){
				return 480;
			}
			if(control.getRondaActual()==4){
				return 800;
			}
			else{
				return 660;
			}
		}
		
		/**
		 * Gets the dimension Y.
		 * Método auxiliar que ayuda a redimensionar el eje Y del JPanel dependiendo del número de la ronda
		 * @param control the control recibe un objeto de tipo controlEntrenaTuMemoria
		 * @return the dimension Y retorna la dimensión en Y(alto) que debería de tener el JPanel
		 */
		private int getDimensionY(ControlEntrenaTuMemoria control){
			
			if(control.getRondaActual()==1 || control.getRondaActual()==2 || control.getRondaActual()==3 || control.getRondaActual()==4){
				return 300;
			}
			else{
				return 440;
			}
		}
		
		/**
		 * Ocultar cartas.
		 * voltea las cartas boca abajo, y re-asigna nuevo icono y mensaje al JLabel mensaje
		 */
		private void ocultarCartas(){
			
			controlMemoria.voltearCartas(false); 
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			for(int i=0; i < controlMemoria.getCartasEnJuego().size();i++){
				
				imagen = new ImageIcon("src/imagenes/" + (i+1) + ".png" );
				cartasEnJuego.get(i).setIcon(imagen);
				cartasEnJuego.get(i).setBorder(border);
			}
			
			mensaje.setText("¿Dónde está " + controlMemoria.cualEsLaCarta().getNombre() +"?");
			Icon icono =  new ImageIcon("src/imagenes/"+controlMemoria.cualEsLaCarta().getNombre()+".png");
			carta.setIcon(icono);
		}
		
		/**
		 * Crear cartas en juego.
		 * Determina la cantidad de cartas tipo JLabel que estarán en la ronda de juego y asigna una escucha a cada uno de los elementos JLabel que deben haber en el ArrayList cartasEnJuego
		 */
		private void crearCartasEnJuego()
		{
			controlMemoria.voltearCartas(true);
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			
			for(int i = 0; i < controlMemoria.getCartasEnJuego().size(); i++) {
				imagen = new ImageIcon("src/imagenes/" + controlMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
				cartasEnJuego.add(new JLabel(imagen));
				cartasEnJuego.get(i).addMouseListener(escucha);
				cartasEnJuego.get(i).setBorder(border);
				zonaCartas.add(cartasEnJuego.get(i));
			}
		}
		
		/**
		 * Redimensionar ventana.
		 * Redimensiona la ventana de juego
		 */
		
		private void redimensionarVentana()
		{
			zonaCartas.setPreferredSize(new Dimension(getDimensionX(controlMemoria),getDimensionY(controlMemoria)));
			referenciaGUI.pack();
		}

/**
 * The Class Escucha.
 * Este método permite ejecutar una acción dependiendo del lugar donde el usuario haga click
 */
class Escucha implements MouseListener{

	/**
	 * Mouse clicked.
	 * Este método permite informar al usuario y hacer los respectivos cambios en el juego dependiendo de si el usuario gana o no la rond actual
	 * @param eventMouse the event mouse
	 */
	@Override
	public void mouseClicked(MouseEvent eventMouse){
		// TODO Auto-generated method stub
		
		if(controlMemoria.getModoVista()==false){
		
			for(int i=0;i<cartasEnJuego.size();i++){
				
				if(eventMouse.getSource() == cartasEnJuego.get(i)){
					controlMemoria.setCartaClickeada(i);
					controlMemoria.determinarEstadoJuego();
					
					Icon icono;
					icono  = new ImageIcon("src/imagenes/"+controlMemoria.getCartaEscogida().getNombre()+".png");
					
					//Mensaje de resultado cuando gana
					String ganaste = "\nEscogiste la carta: "+controlMemoria.getCartaEscogida().getNombre() 
								    +"\npasaste a la ronda #" +controlMemoria.getRondaActual() 
							        +" \n¡Buena suerte en la siguiente ronda!";
					//Mensaje de resultado cuando pierde
					String perdiste = "\nEscogiste la carta: "+controlMemoria.getCartaEscogida().getNombre() 
									 +"\ndevuelvete a la ronda #" +controlMemoria.getRondaActual()
									 +"\n¡Buena suerte en la próxima vez!";
					
					if(controlMemoria.getEstado()==true){
						JOptionPane.showMessageDialog(cartasEnJuego.get(i),"   ¡¡¡ C O R R E C T O !!!"+ganaste,"PASASTE",JOptionPane.DEFAULT_OPTION,icono);				
						empezarJuego();
						break;
					}
					else{
						JOptionPane.showMessageDialog(cartasEnJuego.get(i),"   ¡¡¡ I N C O R R E C T O !!!"+perdiste,"NO PASASTE",JOptionPane.DEFAULT_OPTION,icono);
						empezarJuego();
						break;
					}
				}	
			}
		}
	}

	/**
	 * Mouse entered.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Mouse exited.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Mouse pressed.
	 *
	 * @param eventMouse the event mouse
	 */
	@Override
	public void mousePressed(MouseEvent eventMouse) {
		// TODO Auto-generated method stub

	}

	/**
	 * Mouse released.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

  }
}

