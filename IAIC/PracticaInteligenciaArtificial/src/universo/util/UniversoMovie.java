package universo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import universo.GestorConexion;

import GUI.dibujos.ColorFig;
import GUI.dibujos.Dibujo;

public class UniversoMovie  {

	private Dibujo dib= null;
	private Properties propiedades = null;
	
	public UniversoMovie(){
		this.dib = new Dibujo("Universo", 800,500);
		this.dib.setVisible(true);
		//Cargamos las propiedades para saber el nombre de los juegos.
		FileInputStream prop;
		try {
			prop = new FileInputStream("conf/juegos.conf");
	        this.propiedades = new Properties();
	        this.propiedades.load(prop);
	        prop.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void representar(List acciones){

		String[] enlaceFin = acciones.get(acciones.size()-1).toString().split(" ");
		for (int i = 0; i < acciones.size()-1; i++) {     	
        	// Consiguiendo informacion del enlace.
        	String[] enlaces = acciones.get(i).toString().split(" ");
        	Nodo nodoTmp = GestorConexion.getInstancia().getNodosH().get(Integer.valueOf(enlaces[4]));
        	
        	//Pasamos el nodo final y el siguiente para colorearlos de 
        	//distinto color.
    		String[] enlaceTmp = acciones.get(i+1).toString().split(" ");
        	dibuja(nodoTmp, Integer.valueOf(enlaceTmp[4]), Integer.valueOf(enlaceFin[4]));
	
        	this.dib.espera(1500);
        }
		this.dib.espera(1500);
		this.dib.setVisible(false);
	}

	public void dibuja(Nodo nodoTmp, Integer sig, Integer fin) {
		
		//Borramos lo que hubiera antes.
		this.dib.borra(ColorFig.blanco);
		
		//Dibujamos el nodo padre
		
		this.dib.ponColorLapiz(ColorFig.rojo);
		this.dib.ponGrosorLapiz(2);
		
		this.dib.dibujaElipse(280,20,320,60);
		String id = String.valueOf(nodoTmp.getId());
		this.dib.dibujaTexto(id, 301-id.length()*3, 45);
		
		this.dib.ponColorLapiz(ColorFig.negro);
		
		//Recorremos todos los enlaces...
		//Empezamos siempre en el mismo punto 30,60
		
		Integer x = 30;
		Integer y = 180;
		Integer i = 1;
        Iterator keys = nodoTmp.getEnlaces().keySet().iterator();
        
        while (keys.hasNext()) {
        	Integer key = (Integer) keys.next();
            Enlace enlace = nodoTmp.getEnlaces().get(key);
            
            if (key == sig){
            	//Ponemos otro color al lapiz
            	this.dib.ponColorLapiz(ColorFig.verde);
            }
            if ( key.equals(fin) ){
            	this.dib.ponGrosorLapiz(3);
            	this.dib.ponColorLapiz(ColorFig.naranja);
            }
            
            this.dib.dibujaElipse(x,y,x+40,y+40);
            String destino = String.valueOf(enlace.getDestino());
    		this.dib.dibujaTexto(destino, x+20-destino.length()*3, y+25);
            
    		//Pintamos la linea que las une.
    		dib.dibujaLinea(300, 60, x+20, y);
    		//Pintamo el juego y la distancia
    		String distancia = String.valueOf(enlace.getDistancia());
    		this.dib.dibujaTexto(distancia, x+18-distancia.length()*3, y+60);
            if ( enlace.getJuego() != null ){
            	String juego = String.valueOf( this.propiedades.getProperty(String.valueOf(enlace.getJuego())));
            	if ( i % 2 == 1){
            		//Es un numero impar
            		this.dib.dibujaTexto(juego, x+20-(juego.length()*3), y+75);
            	}else{
            		this.dib.dibujaTexto(juego, x+20-(juego.length()*3), y+90);
            	}
            	i++;
            }
            
    		x=x+70;
            
    		this.dib.espera(1000);
    		
            if (key.equals(sig)){
            	//Reestablecemos el color negro
            	this.dib.ponColorLapiz(ColorFig.negro);
            }
            if (key.equals(fin)){
            	this.dib.ponColorLapiz(ColorFig.negro);
            	this.dib.ponGrosorLapiz(2);
            }  		
        }
	}
}
