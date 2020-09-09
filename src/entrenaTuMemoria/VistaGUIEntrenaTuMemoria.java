package entrenaTuMemoria;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.border.TitledBorder;

public class VistaGUIEntrenaTuMemoria extends JFrame {

	//atributos
	
		private JLabel mensaje;
		private ImageIcon imagen;
		private ArrayList<JLabel> cartasEnJuego;
		private Escucha escucha;
		private ControlEntrenaTuMemoria controlMemoria;
		private Timer timer;
		private JPanel zonaCartas;
		private JFrame referenciaGUI;

	//métodos
	
	public VistaGUIEntrenaTuMemoria() //Constructor
	{		
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
			
			//Mensaje
			Icon iconoTiempo =  new ImageIcon("src/imagenes/tiempo.gif");
			mensaje = new JLabel("Por favor espere 30 segundos \n ronda actual: " + controlMemoria.getRondaActual(),iconoTiempo,0);
			//Restricciones
			constraints.gridx=0; 
			constraints.gridy=0;
			constraints.gridwidth=2;
			constraints.fill=GridBagConstraints.CENTER;
			add(mensaje,constraints);
			
			//Zona de cartas
			zonaCartas = new JPanel();
			zonaCartas.setBorder(new TitledBorder("Cartas en juego"));
			zonaCartas.setBackground(Color.WHITE);

			//Restricciones
			constraints.gridx=0; 
			constraints.gridy=1;
			constraints.gridwidth=1; //Ocupa una columna
			constraints.gridheight=1; //Ocupa una fila
			constraints.fill=GridBagConstraints.LAST_LINE_END;
			add(zonaCartas,constraints);
			
			//Empieza el Juego
			empezarJuego();
		}
		//Método que se encarga de repintar el juego dependiendo de la ronda en que se encuentre
		private void empezarJuego()
		{
			if(controlMemoria.getRondaActual()>=2) {
				
				zonaCartas.removeAll();
				zonaCartas.repaint();

			cartasEnJuego.clear();
			controlMemoria.mostrarCartasEnJuego();
			crearCartasEnJuego();
			nuevaRonda();
			
			mensaje.setText("Por favor espere 30 segundos \n ronda actual: " + controlMemoria.getRondaActual());
			Icon iconoTiempo =  new ImageIcon("src/imagenes/tiempo.gif");
			mensaje.setIcon(iconoTiempo);
			
			timer = new Timer("Timer");
			TimerTask task = new TimerTask() {
				public void run() {
					ocultarCartas(); //oculta las cartas
				}
			};
			
			timer.schedule(task,3000);
			}
			else //exclusivo de la primera ronda
			{
				controlMemoria.mostrarCartasEnJuego();
				controlMemoria.getCartaEnJuego();	
				crearCartasEnJuego();
				nuevaRonda();
				
				timer = new Timer("Timer");
				TimerTask task = new TimerTask() {
					public void run() {
						ocultarCartas();//oculta las cartas	
					}
				};
				timer.schedule(task,3000);
			}
		}

		//devuelve el ancho que debería tener el JPanel "zonaCartas" dependiendo de las cartas que hayan en juego
		private int getDimensionX(ControlEntrenaTuMemoria control)
		{
			if(control.getRondaActual()==1)
			{
				return 360;
			}if(control.getRondaActual()==2) 
			{	
				return 480;
			}
			else //ronda >= 3
			{
				return 620;
			}

		}
		
		//devuelve el alto que debería tener el JPanel "zonaCartas" dependiendo de las cartas que hayan en juego
		private int getDimensionY(ControlEntrenaTuMemoria control) {
			
			if(control.getRondaActual()==1 || control.getRondaActual()==2 || control.getRondaActual()==3)
			{
				return 280;
			}
			else//ronda > 3
			{
				return 420;
			}
		}
		
		//voltea las cartas boca abajo
		private void ocultarCartas()
		{
			controlMemoria.voltearCarta(false); //voltea las cartas boca abajo, y re-asigna nuevo icono y mensaje al JLabel mensaje
			for(int i=0; i < controlMemoria.getCartasEnJuego().size();i++)
			{
				imagen = new ImageIcon("src/imagenes/" + (i+1) + ".png" );
				cartasEnJuego.get(i).setIcon(imagen);
			}
			
			mensaje.setText("¿Dónde está " + controlMemoria.cualEsLaCarta().getNombre() +"?");
			Icon icono =  new ImageIcon("src/imagenes/"+controlMemoria.cualEsLaCarta().getNombre()+".png");
			mensaje.setIcon(icono);
		}
		
		//determina la cantidad de cartas tipo JLabel y asigna una escucha a cada uno de los elementos JLabel que deben haber en el ArrayList cartasEnJuego
		private void crearCartasEnJuego()
		{
			controlMemoria.voltearCarta(true); //voltea las cartas boca arriba
			
			for(int i = 0; i < controlMemoria.getCartasEnJuego().size(); i++) {
				imagen = new ImageIcon("src/imagenes/" + controlMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
				cartasEnJuego.add(new JLabel(imagen));
				cartasEnJuego.get(i).addMouseListener(escucha); //cada elemento le asiga una escucha de Mouse
				zonaCartas.add(cartasEnJuego.get(i));
			}
		}
		
		//redimensiona la ventana dependiendo de la ronda
		private void nuevaRonda()
		{
			zonaCartas.setPreferredSize(new Dimension(getDimensionX(controlMemoria),getDimensionY(controlMemoria)));
			referenciaGUI.pack();
		}

class Escucha implements ActionListener,MouseListener{

	@Override
	public void mouseClicked(MouseEvent eventMouse) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<cartasEnJuego.size();i++)
		{
			if(eventMouse.getSource() == cartasEnJuego.get(i))//busca cuál carta le dio click
			{
				controlMemoria.setCartaClickeada(i);
				controlMemoria.determinarEstadoJuego();
				
				if(controlMemoria.getEstado()==true)
				{
					JOptionPane.showMessageDialog(cartasEnJuego.get(i),"GANASTE " + controlMemoria.getRondaActual() + " " + controlMemoria.getCartaEscogida().getNombre());				
					empezarJuego();
					break;
				}
				else
				{
					JOptionPane.showMessageDialog(cartasEnJuego.get(i),"PERDISTE BRO " + controlMemoria.cualEsLaCarta().getNombre() + " " + controlMemoria.getCartaEscogida().getNombre());
					break;
				}
			}	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent eventMouse) { //Escoger carta click
		// TODO Auto-generated method stub

	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void actionPerformed(ActionEvent actionEvent) {//Timer
		// TODO Auto-generated method stub

	}
  }
}

