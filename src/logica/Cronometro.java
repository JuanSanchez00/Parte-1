package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Cronometro {
	boolean cronometroActivo;
	ImageIcon segundosI,segundosII,minutosI,minutosII,horasI,horasII;
	Integer seg,min,hor;
	private String[] imagenes;
	
	public Cronometro() {
		cronometroActivo=true;
		segundosI=new ImageIcon();
		segundosII=new ImageIcon();
		minutosI=new ImageIcon();
		minutosII=new ImageIcon();
		horasI=new ImageIcon();
		horasII=new ImageIcon();

		seg=min=hor=0;
		imagenes=new String[]{"0_reloj.png", "1_reloj.png", "2_reloj.png", "3_reloj.png", "4_reloj.png", "5_reloj.png", "6_reloj.png", "7_reloj.png", "8_reloj.png", "9_reloj.png", };
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/0_reloj.png"));
		segundosI.setImage(imageIcon.getImage());
		segundosII.setImage(imageIcon.getImage());
		minutosI.setImage(imageIcon.getImage());
		minutosII.setImage(imageIcon.getImage());
		horasI.setImage(imageIcon.getImage());
		horasII.setImage(imageIcon.getImage());
		seg=min=hor=0;
		run();
	}
	public void run(){
        //min es minutos, seg es segundos y mil es milesimas de segundo
        try {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
        	ActionListener accion = new ActionListener() {
    			public void actionPerformed(ActionEvent ae) {
    				seg += 1;
    				 if( seg == 60 ) {
    	                    seg = 0;
    	                    min += 1;
    	                    if( min == 60 ) {
    	                        min = 0;
    	                        seg = 0;
    	                        hor++;
    	                    }
    				 }

    			}
    		};
    		Timer timer = new Timer(1000, accion);
    		timer.start();
          
        }catch(Exception e){
        	
        }
	}

    public int getSegundos() {
        return seg;
     }
    
    public int getMinutos() {
        return min;
     }
    
    public int getHoras() {
        return hor;
     }
    
    public ImageIcon getSegundosI() {
    	return segundosI;
    }
    
    public ImageIcon getSegundosII() {
    	return segundosII;
    }
    
    public ImageIcon getMinutosI() {
    	return minutosI;
    }
    
    public ImageIcon getMinutosII() {
    	return minutosII;
    }
    
    public ImageIcon getHorasI() {
    	return horasI;
    }
    
    public ImageIcon getHorasII() {
    	return horasII;
    }
 
}

