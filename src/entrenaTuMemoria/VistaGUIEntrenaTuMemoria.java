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



public class VistaGUIEntrenaTuMemoria extends JFrame {

	//atributos
	
		private JLabel mensaje,carta,ronda;
		private ImageIcon imagen;
		private ArrayList<JLabel> cartasEnJuego;
		private Escucha escucha;
		private ControlEntrenaTuMemoria controlMemoria;
		private Timer timer,timer2;
		private JPanel zonaCartas,zonaTop,zonaMensaje,zonaRonda;
		private JFrame referenciaGUI;
		private int contador;

	//métodos
	
	public VistaGUIEntrenaTuMemoria(){ //Constructor
			
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
			constraints.gridwidth=1; //Ocupa una columna
			constraints.gridheight=1; //Ocupa una fila
			constraints.fill=GridBagConstraints.LAST_LINE_END;
			add(zonaCartas,constraints);
			
			//Empieza el Juego
			empezarJuego();
		}
		
		//Método que se encarga de repintar el juego dependiendo de la ronda en que se encuentre
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

		//devuelve el ancho que debería tener el JPanel "zonaCartas" dependiendo de las cartas que hayan en juego
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
			else{ //ronda >= 3
			
				return 660;
			}

		}
		
		//devuelve el alto que debería tener el JPanel "zonaCartas" dependiendo de las cartas que hayan en juego
		private int getDimensionY(ControlEntrenaTuMemoria control){
			
			if(control.getRondaActual()==1 || control.getRondaActual()==2 || control.getRondaActual()==3 || control.getRondaActual()==4){
				return 300;
			}
			else{//ronda > 3
			
				return 440;
			}
		}
		
		//voltea las cartas boca abajo
		private void ocultarCartas(){
			
			controlMemoria.voltearCartas(false); //voltea las cartas boca abajo, y re-asigna nuevo icono y mensaje al JLabel mensaje
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
		
		//determina la cantidad de cartas tipo JLabel y asigna una escucha a cada uno de los elementos JLabel que deben haber en el ArrayList cartasEnJuego
		private void crearCartasEnJuego()
		{
			controlMemoria.voltearCartas(true); //voltea las cartas boca arriba
			Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
			
			for(int i = 0; i < controlMemoria.getCartasEnJuego().size(); i++) {
				imagen = new ImageIcon("src/imagenes/" + controlMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
				cartasEnJuego.add(new JLabel(imagen));
				cartasEnJuego.get(i).addMouseListener(escucha); //cada elemento le asiga una escucha de Mouse
				cartasEnJuego.get(i).setBorder(border);
				zonaCartas.add(cartasEnJuego.get(i));
			}
		}
		
		//redimensiona la ventana dependiendo de la ronda
		private void redimensionarVentana()
		{
			zonaCartas.setPreferredSize(new Dimension(getDimensionX(controlMemoria),getDimensionY(controlMemoria)));
			referenciaGUI.pack();

		}

class Escucha implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent eventMouse){
		// TODO Auto-generated method stub
		
		if(controlMemoria.getModoVista()==false){//Mira si las cartas están boca abajo (modoVista=false)
		
			for(int i=0;i<cartasEnJuego.size();i++){
				
				if(eventMouse.getSource() == cartasEnJuego.get(i)){//busca cuál carta le dio click
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

  }
}

