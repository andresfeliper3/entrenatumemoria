package entrenaTuMemoria;

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

		//métodos
	
	public VistaGUIEntrenaTuMemoria() //Constructor
	{		
		
		initGUI();
		
		//default config
		this.setTitle("Entrena tu memoria");
		//this.setSize(600,400);
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
			
			//Container container = this.getContentPane();
			//container.setLayout(new FlowLayout());
					
			//escucha, timer y control
			escucha = new Escucha();
			controlMemoria = new ControlEntrenaTuMemoria();
			
			controlMemoria.mostrarCartasEnJuego();
			controlMemoria.getCartaEnJuego();
			
			
			
			//Cartas en juego
			cartasEnJuego = new ArrayList<JLabel>();
			
			//set up GUICOmponents add to window
			
			//Mensaje
			Icon icono =  new ImageIcon("src/imagenes/"+controlMemoria.cualEsLaCarta().getNombre()+".png");
			mensaje = new JLabel("Por favor espere 30 segundos",icono,0);
			//restricciones
			constraints.gridx=0; 
			constraints.gridy=0;
			constraints.gridwidth=2;
			constraints.fill=GridBagConstraints.CENTER;
			add(mensaje,constraints);
			
			//Zona de cartas
			//La redimension debería de ir en el método que examine si pasa a la siguiente ronda y no en el constructor
			zonaCartas = new JPanel();
			zonaCartas.setPreferredSize(new Dimension(getDimensionX(controlMemoria),getDimensionY(controlMemoria))); //4 cartas->x=360,y=280; 6 cartas->x=480; 8 cartas->x=620;12 cartas y=420
			zonaCartas.setBorder(new TitledBorder("Cartas en juego"));
			
			for(int i = 0; i < controlMemoria.getCartasEnJuego().size(); i++) {
				imagen = new ImageIcon("src/imagenes/" + controlMemoria.getCartasEnJuego().get(i).getNombre() + ".png" );
				cartasEnJuego.add(new JLabel(imagen));
				cartasEnJuego.get(i).addMouseListener(escucha); //cada elemento le asiga una escucha de Mouse
				zonaCartas.add(cartasEnJuego.get(i));
			}
			
			constraints.gridx=0; 
			constraints.gridy=1;
			constraints.gridwidth=1; //Ocupa una columna
			constraints.gridheight=1; //Ocupa una fila
			constraints.fill=GridBagConstraints.LAST_LINE_END;
			add(zonaCartas,constraints);
		}
		
		//devuelve el ancho que debería tener la ventana dependiendo de las cartas que hayan en juego
		private int getDimensionX(ControlEntrenaTuMemoria control)
		{
			if(control.getRondaActual()==1)
			{
				return 360;
			}if(control.getRondaActual()==2) 
			{	
				return 480;
			}
			else //>3
			{
				return 620;
			}

		}
		
		//devuelve el alto que debería tener la ventana dependiendo de las cartas que hayan en juego
		private int getDimensionY(ControlEntrenaTuMemoria control) {
			
			if(control.getRondaActual()==1 || control.getRondaActual()==2 || control.getRondaActual()==3)
			{
				return 280;
				
			}else//>3
			{
				return 420;
			}

		}
		
		

class Escucha implements ActionListener,MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent eventMouse) { //Escoger carta click
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void actionPerformed(ActionEvent actionEvent) {//Timer
		// TODO Auto-generated method stub
		

	
	}
  }
}

