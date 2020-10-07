
package gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import logica.*;
public class TestGUI extends JFrame {

	private JPanel panelTablero;
	private JPanel panelReloj;
	private JPanel panelBoton;
	private Juego juego;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI frame = new TestGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestGUI() {
		this.setTitle("Sudoku");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 456, 469);
		//
		panelTablero = new JPanel();
		panelTablero.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		panelTablero.setPreferredSize(new Dimension(656,669));
		juego = new Juego();
		if (!juego.getSolucionValida()) {
			JOptionPane cartelError=new JOptionPane();
			cartelError.showMessageDialog(panelTablero, "El Sudoku no ha podido iniciar correctamente.", "Sudoku", JOptionPane.ERROR_MESSAGE);
			System.exit(0);//finaliza la ejecucion
		}
		panelTablero.setLayout(new GridLayout(juego.getCantFilas(), 0, 0, 0));
		
		
		panelReloj=new JPanel();
		JLabel dosPuntosII = new JLabel();
		JLabel dosPuntosI = new JLabel();
		JLabel horasII = new JLabel();
		JLabel horasI = new JLabel();
		JLabel minutosII = new JLabel();
		JLabel minutosI = new JLabel();
		JLabel segundosI=new JLabel();
		JLabel segundosII=new JLabel();
		panelReloj.setPreferredSize(new Dimension(210,110));
		panelReloj.setLayout(new GridLayout(0, 8, 0, 0));
		panelReloj.add(horasII);
		panelReloj.add(horasI);
		panelReloj.add(dosPuntosII);
		panelReloj.add(minutosII);
		panelReloj.add(minutosI);
		panelReloj.add(dosPuntosI);
		panelReloj.add(segundosII);
		panelReloj.add(segundosI);
		
		EntidadGraficaReloj e=new EntidadGraficaReloj();
		dosPuntosII.setIcon(e.getDosPuntos());
		dosPuntosI.setIcon(e.getDosPuntos());
		horasII.setIcon(e.getImagen(0));
		horasI.setIcon(e.getImagen(0));
		minutosII.setIcon(e.getImagen(0));
		minutosI.setIcon(e.getImagen(0));
		segundosII.setIcon(e.getImagen(0));
		segundosI.setIcon(e.getImagen(0));
		
		
		
		
		panelBoton = new JPanel();
		JButton btn = new JButton( "Iniciar" );
		panelBoton.add(btn);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				EntidadGraficaReloj e=new EntidadGraficaReloj();
				Cronometro cr=new Cronometro();
				ActionListener accion = new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						horasII.setIcon(e.getImagen(cr.getHoras()/10));
						horasI.setIcon(e.getImagen(cr.getHoras()%10));
						minutosII.setIcon(e.getImagen(cr.getMinutos()/10));
						minutosI.setIcon(e.getImagen(cr.getMinutos()%10));
						segundosII.setIcon(e.getImagen(cr.getSegundos()/10));
						segundosI.setIcon(e.getImagen(cr.getSegundos()%10)); 
					}
				};
				Timer timer = new Timer(1000, accion);
				timer.start();
				btn.setEnabled(false);//desactivo el boton
			}
		});
		
		getContentPane().add(panelReloj,BorderLayout.NORTH);
		getContentPane().add(panelTablero,BorderLayout.CENTER);
		getContentPane().add(panelBoton,BorderLayout.SOUTH);
		/*Cronometro crono=new Cronometro();
		panelBoton=new JPanel();
		panelBoton.setLayout(new GridLayout(juego.getCantFilas(), 0, 0, 0));
		JLabel hI=new JLabel();
		hI.setIcon(crono.getHorasI());
		JLabel hII=new JLabel();
		hII.setIcon(crono.getHorasII());
		JLabel mI=new JLabel();
		mI.setIcon(crono.getMinutosI());
		JLabel mII=new JLabel();
		mII.setIcon(crono.getMinutosII());
		JLabel sI=new JLabel();
		sI.setIcon(crono.getSegundosI());
		JLabel sII=new JLabel();
		sII.setIcon(crono.getSegundosII());
		contentPane.add(hI);
		contentPane.add(hII);
		contentPane.add(mI);
		contentPane.add(mII);
		panelBoton.add(sI);
		panelBoton.add(sII);
		contentPane.add(panelBoton);
		JButton btn = new JButton( "Iniciar" );
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn.setBackground(Color.blue);
			}
		});
       	panelBoton.add( btn);*/
		for (int i = 0; i <juego.getCantFilas(); i++) {
			for(int j =0; j<juego.getCantFilas(); j++) {
				Celda c = juego.getCelda(i,j);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				c.getEntidadGrafica().setJLabel(label);//guarda el label en la entidad gráfica de la celda
				label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                if ((i== 3 && j == 3) ||  (i== 6 && j == 6) || (i == 3 && j == 6) || (i == 6 && j == 3)) {//establece los bordes a los JLabel
                    label.setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.BLACK));
                }
                else {
                    if (j == 3 || j == 6 ) {
                        label.setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.BLACK));
                    }
                    else {
                        if(i == 3 || i == 6 ) {
                            label.setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.BLACK));
                        }
                    }
                }
                label.setOpaque(true);
                if (c.getCeldaFija()) {
                	label.setBackground(new Color(216,216,216));//pinta las celdas fijas de color gris
                }
				panelTablero.add(label);
				
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});
				
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (c.getCeldaFija()==false) {//si no es una celda inicial
							juego.accionar(c);
							comprobarSolucion();
							System.out.println("celdas cumplen: "+juego.cantCeldasCumplenRegla());
							reDimensionar(label,grafico);
							if (juego.gano()) {
								JOptionPane cartelError=new JOptionPane();
								cartelError.showMessageDialog(panelTablero, "Felicidades! Ha ganado la partida.", "Sudoku", JOptionPane.INFORMATION_MESSAGE);
								System.exit(0);//finaliza la ejecucion
							}
						}
					}
				});
			}
		}
		
	}
	
	private void comprobarSolucion() {
		for (int f=0;f<juego.getCantFilas();f++) {//chequea cada celda para ver si cumple con las reglas
			for (int c=0;c<juego.getCantFilas();c++) {
				if (juego.getCelda(f, c).getValor()!=null) {
					if  (juego.cumpleReglaFila(f, c, juego.getCelda(f, c).getValor()) && juego.cumpleReglaColumna(f, c, juego.getCelda(f, c).getValor()) && juego.cumpleReglaPanel(f, c, juego.getCelda(f, c).getValor())) {
						if(juego.getCelda(f, c).getCeldaFija()) {
							juego.getCelda(f, c).getEntidadGrafica().getJLabel().setBackground(new Color(216,216,216));//si es celda fija se pinta de gris
						}
						else {
							juego.getCelda(f, c).getEntidadGrafica().getJLabel().setBackground(null);//sin fondo
						}
					}
					else {
						System.out.println("fila: "+f+" columna: "+c+" no cumple");
						juego.getCelda(f, c).getEntidadGrafica().getJLabel().setBackground(Color.red);
					}
				}
			}
		}
	}
	
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}

}