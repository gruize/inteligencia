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

/**
 * 
 * Clase encargada de la representacion del universo a traves de dibujos.
 * 
 * @author Kenzitron
 *
 */

public class UniversoMovie {

	private Dibujo dib= null;
	private Properties juegos = null;
	private Properties propiedades = null;
	
	/**
	 * Constructor de la clase
	 * 
	 * Inicializa los archivos de propiedades que necesita y crea el formulario donde
	 * se van a mostrar los planetas.
	 * 
	 * @param nombre -> Titulo del frame
	 */
	
	public UniversoMovie(String nombre){
		this.dib = new Dibujo(nombre, 800,500);
		//Cargamos las propiedades para saber el nombre de los juegos.
		FileInputStream prop;
		try {
			//Propiedades de los juegos
			prop = new FileInputStream("conf/juegos.conf");
	        this.juegos = new Properties();
	        this.juegos.load(prop);
	        prop.close();
	        
	        //Propiedades de configuracion
	        prop = new FileInputStream("conf/propiedades.conf");
	        this.propiedades = new Properties();
	        this.propiedades.load(prop);
	        prop.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Encargado de recorrer las acciones y pasar ciertos parametros de configuracion al metodo
	 * dibuja que es el que realmente pinta sobre el formulario.
	 * 
	 * En este metodo se hace visible o invisible el formulario.
	 * 
	 * @param acciones -> Acciones a representar.
	 */
	public void representar(List acciones){
		
		this.dib.setVisible(true);
		
		String[] enlaceFin = acciones.get(acciones.size()-1).toString().split(" ");
		for (int i = 0; i < acciones.size(); i++) {     
        	// Consiguiendo informacion del enlace.
        	String[] enlaces = acciones.get(i).toString().split(" ");
        	Nodo nodoTmp = GestorConexion.getInstancia().getNodosH().get(Integer.valueOf(enlaces[4]));
        	
        	//Pasamos el nodo final y el siguiente para colorearlos de 
        	//distinto color.
        	dibuja(nodoTmp, Integer.valueOf(enlaces[9]), Integer.valueOf(enlaceFin[9]));
	
        	this.dib.espera(Integer.valueOf(propiedades.getProperty("delayNivel")));
        }
		this.dib.espera(Integer.valueOf(propiedades.getProperty("delayNivel"))*2);
		this.dib.setVisible(false);

	}

	/**
	 * 
	 * Escribe y colorea segun sea nodo, enlace siguiente, enlace normal o nodo destino.
	 * 
	 * Leyenda de colores:
	 * 		- Rojo: Color para el nodo que estamos representando.
	 * 		- Negro: Color para nodos normales.
	 * 		- Verde: Color para el nodo siguiente.
	 * 		- Naranja: Color para el nodo destino final del recorrido. 
	 * 
	 * @param nodoTmp Nodo que tiene que dibuja
	 * @param sig Siguiente nodo
	 * @param fin Nodo final
	 * 
	 */
	
	public void dibuja(Nodo nodoTmp, Integer sig, Integer fin) {
		
		//Borramos lo que hubiera antes.
		this.dib.borra(ColorFig.negro);
		
		//Dibujamos el nodo padre
		
		this.dib.ponColorLapiz(ColorFig.rojo);
		this.dib.ponGrosorLapiz(2);
		
		this.dib.dibujaElipse(280,20,320,60);
		String id = String.valueOf(nodoTmp.getId());
		this.dib.dibujaTexto(id, 300-id.length()*3, 45);
		
		this.dib.ponColorLapiz(ColorFig.blanco);
		
		//Recorremos todos los enlaces...
		//Empezamos siempre en el mismo punto 30,60
		
		Integer x = 30;
		Integer y = 180;
		Integer i = 1;
        Iterator keys = nodoTmp.getEnlaces().keySet().iterator();
        
        while (keys.hasNext()) {
        	Integer key = (Integer) keys.next();
            Enlace enlace = nodoTmp.getEnlaces().get(key);
            
            
            
            if ( key.equals(fin) ){
            	this.dib.ponGrosorLapiz(3);
            	this.dib.ponColorLapiz(ColorFig.naranja);
            	this.dib.dibujaElipse(x,y,x+40,y+40);
                String destino = String.valueOf(enlace.getDestino());
                this.dib.ponColorLapiz(ColorFig.negro);
        		this.dib.dibujaTexto(destino, x+19-destino.length()*3, y+25);
        		this.dib.ponColorLapiz(ColorFig.blanco);
        		this.dib.ponColorLapiz(ColorFig.naranja);
            }else if ( key.equals(sig) ){
            	this.dib.ponColorLapiz(ColorFig.verde);
            	this.dib.dibujaElipse(x,y,x+40,y+40);
                String destino = String.valueOf(enlace.getDestino());
                this.dib.ponColorLapiz(ColorFig.negro);
        		this.dib.dibujaTexto(destino, x+19-destino.length()*3, y+25);
        		this.dib.ponColorLapiz(ColorFig.blanco);
        		this.dib.ponColorLapiz(ColorFig.verde);
            }else{
            	this.dib.dibujaElipse(x,y,x+40,y+40);
                String destino = String.valueOf(enlace.getDestino());
                this.dib.ponColorLapiz(ColorFig.negro);
        		this.dib.dibujaTexto(destino, x+19-destino.length()*3, y+25);
        		this.dib.ponColorLapiz(ColorFig.blanco);
            }
	
    		//Pintamos la linea que las une.
    		dib.dibujaLinea(300, 60, x+20, y-75);
    		dib.dibujaLinea(x+20, y-75, x+20, y);
    		//Pintamo el juego y la distancia
    		String distancia = String.valueOf(enlace.getDistancia());
    		this.dib.dibujaTexto(distancia, x+19-distancia.length()*3, y+60);
            if ( enlace.getJuego() != null ){
            	String juego = String.valueOf( this.juegos.getProperty(String.valueOf(enlace.getJuego())));
            	if ( i % 2 == 1){
            		//Es un numero impar
            		this.dib.dibujaTexto(juego, x+22-(juego.length()*3), y+75);
            	}else{
            		this.dib.dibujaTexto(juego, x+22-(juego.length()*3), y+90);
            	}
            	i++;
            }
            
            this.dib.espera(100);
            
    		x=x+70;
    		
            if (key.equals(sig)){
            	//Reestablecemos el color negro
            	this.dib.ponColorLapiz(ColorFig.blanco);
            }
            if (key.equals(fin)){
            	this.dib.ponColorLapiz(ColorFig.blanco);
            	this.dib.ponGrosorLapiz(2);
            }  		
        }
	}
}
