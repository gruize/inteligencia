package universo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import universo.GestorConexion;

/**
 * 
 * Clase encargada de almacenar en un archivo el resultado de la busqueda.
 * 
 * El fichero se guarda en la carpeta logs con el siguiente formato:
 * 		- TipoBusqueda dia mes año horas-minutos.log
 * 
 * De este modo se puede seguir mas comodamentela traza del algoritmo asi como informacion
 * asociada a la ejecucion del mismo.
 * 
 * @author Kenzitron
 *
 */

public class UniversoLogger {
	
	private BufferedWriter output;
	private String fich = null;
	
	/**
	 * Constructor de la clase, recibe el tipo de busqueda que debe almacenar.
	 * @param fichero
	 */
	
	public UniversoLogger(String fichero){  
		String time = new Date().toGMTString();
		time = time.substring(0, time.length()-7);
		this.fich = "logs/"+fichero+" "+time.replace(":", "-")+".log";
		try {
			output = new BufferedWriter(new FileWriter(new File(this.fich)));
		} catch (IOException e) {
			System.out.print("asdad");
		}	
	}
	
	
	/**
	 * 
	 * Funcion para guardar en el fichero las acciones.
	 * 
	 *   Se guarda en una linea el camino y en la siguiente linea con un tabulador mas
	 *   el juego que ha tenido que superar para llegar ahi, en caso de haber tenido algun juego que superar.
	 * 
	 * @param acciones
	 * @throws IOException
	 */
	public void printAcciones(List acciones) throws IOException {
		
		// Cargamos el fichero de configuracion de los juegos
    	FileInputStream prop = new FileInputStream("conf/juegos.conf");
        Properties propiedades = new Properties();
        propiedades.load(prop);
        prop.close();
		
		output.write("A continuación se detalla el camino:\n\n");
        for (int i = 0; i < acciones.size(); i++) {
        	output.write("\t"+acciones.get(i)+"\n");
        	
        	// Consiguiendo informacion del enlace.
        	String[] enlaces = acciones.get(i).toString().split(" ");
        	Nodo nodoTmp = GestorConexion.getInstancia().getNodosH().get(Integer.valueOf(enlaces[4]));
        	
            Integer numJuego = nodoTmp.getEnlaces().get(Integer.valueOf(enlaces[9])).getJuego();
            if ( (numJuego != null ) && ( numJuego >= 1 ) && ( numJuego <= 12) ){
            	String juego = String.valueOf( propiedades.getProperty(String.valueOf(numJuego)));
            	output.write("\t\t Consiguiendo superar el juego: " + juego + "\n");
            }   
         }
		output.write("\n\n");
	}
	
	/**
	 * 
	 * Imprime los valores de la ejecucion del algoritmo.
	 * 
	 * @param prop
	 * @throws IOException
	 */
	
	public void printProperties(Properties prop) throws IOException{
		
		output.write("Informacion de costes:\n");
        Iterator keys = prop.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = prop.getProperty(key);
            output.write("\t"+ key + ": " + property+"\n");
        }
	}
	public void closeLogger() throws IOException{
		output.close();
	}
}
