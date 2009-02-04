package conector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import universo.util.Enlace;
import universo.util.Nodo;
import universo.util.Tipo;
import universo.util.exceptions.BadFormattedFile;
import conector.util.exceptions.BadUniFileException;

/**
 * 
 * Esta clase es la encargada de leer el fichero de configuracion de universo
 * para que la aplicacion pueda funcionar.
 * 
 * @author Kenzitron
 *
 */

public class Conector {

	private Hashtable<Integer, Nodo> nodosH;
	private String archivo;
	private Nodo origen;
	private Hashtable<Integer, Nodo> destinos;
	private Vector<String> errores;
/**
 * Constructor de la clase, recibe el fichero que debe cargar como parametro de entrada.
 * Y lanza las siguientes excepciones
 * 	
 * @param archivo
 * @throws NumberFormatException
 * @throws IOException
 * @throws BadUniFileException  No es un archivo de Universo
 * @throws BadFormattedFile  El formato del archivo no es correcto
 */
	public Conector(String archivo) throws NumberFormatException, IOException, BadUniFileException, BadFormattedFile {
            this.setArchivo(archivo);
            origen = null;
            destinos = new Hashtable<Integer, Nodo>();
            nodosH = new Hashtable<Integer, Nodo>();
            File f = new File(this.archivo);
            FileReader file;
        
			
			file = new FileReader(f);
			BufferedReader entrada = new BufferedReader( file );
		    String linea;
		    Enlace enlaceTmp;
		    Properties propiedades;	 
		    
		    if (entrada.readLine().equals("[Fichero de universo]")){
		    
			    while (( linea = entrada.readLine() ) != null){
					//Recogemos toda la mierda del archivo de texto.			
					String [] campos = linea.split("\\|");
					Nodo nodotmp = new Nodo();			
					Boolean existeEnlaces = false;
					if (linea.lastIndexOf(",") > 0 ) {
						// Si hay , es que hay enlaces... asi que hay que 
						// coger su informacion
						existeEnlaces = true;
					}			
					//Eso es que es un campo tipo planeta 
					String tipo = campos[0].substring(1, campos[0].length()-1);
					if ( (tipo.length() < 2 ) && ( campos[0].indexOf("]") > 0 ) ) {				
						String nombre = campos[1];
						nodotmp.setNombre(nombre);				
						Integer idtmp = Integer.valueOf(campos[2]);
						nodotmp.setId(idtmp);
						if ( existeEnlaces ){
							String[] enlaces = campos[3].split("\\]");
							for (String tmp : enlaces) {
								tmp = tmp.substring(1, tmp.length());
								String[] infoEnlace = tmp.split(",");
								enlaceTmp= new Enlace();
								if ( !infoEnlace[0].isEmpty() ){
									enlaceTmp.setDestino(Integer.valueOf(infoEnlace[0]));
								}else{
									enlaceTmp.setDestino(null);
								}
								if ( !infoEnlace[1].isEmpty() ){
									enlaceTmp.setJuego(Integer.valueOf(infoEnlace[1]));
								}else{
									enlaceTmp.setJuego(null);
								}
								if ( !infoEnlace[2].isEmpty() ){
									enlaceTmp.setDistancia(Integer.valueOf(infoEnlace[2]));
								}else{
									enlaceTmp.setDistancia(null);
								}
								nodotmp.addEnlaces(enlaceTmp);
							}
						}
						if ( tipo.equals("O")){
							nodotmp.setTipo(Tipo.ORIGEN);
							if ( origen == null ){
								origen = nodotmp;
							}else{
								errores.add("Ya hay un planeta origen.");
							}
						} else if (tipo.equals("D")){
							nodotmp.setTipo(Tipo.DESTINO);
							destinos.put(nodotmp.getId(), nodotmp);
						} else {
							nodotmp.setTipo(Tipo.NORMAL);
						}				
					}else{
						//No hay distincion, asi que es normal
						nodotmp.setTipo(Tipo.NORMAL);
						nodotmp.setNombre(campos[0]);
						
						Integer idtmp = Integer.valueOf(campos[1]);
						nodotmp.setId(idtmp);				
						if ( existeEnlaces ){
							// Si hay enlaces cogemos la info
							String[] enlaces = campos[2].split("\\]");
							for (String tmp : enlaces) {
								tmp = tmp.substring(1, tmp.length());
								String[] infoEnlace = tmp.split(",");
								enlaceTmp= new Enlace();
								if ( !infoEnlace[0].isEmpty() ){
									enlaceTmp.setDestino(Integer.valueOf(infoEnlace[0]));
								}else{
									enlaceTmp.setDestino(null);
								}
								if ( !infoEnlace[1].isEmpty() ){
									enlaceTmp.setJuego(Integer.valueOf(infoEnlace[1]));
								}else{
									enlaceTmp.setJuego(null);
								}
								if ( !infoEnlace[2].isEmpty() ){
									enlaceTmp.setDistancia(Integer.valueOf(infoEnlace[2]));
								}else{
									enlaceTmp.setDistancia(null);
								}
								nodotmp.addEnlaces(enlaceTmp);
							}
						}
					}
					this.nodosH.put(nodotmp.getId(), nodotmp);			
			    }
			    //Cargamos el fichero de configuracion

		    	FileInputStream prop = new FileInputStream("conf/propiedades.conf");
		  
		        propiedades = new Properties();
		        propiedades.load(prop);
		        prop.close();	     
		        Integer numplanetas = Integer.valueOf( propiedades.getProperty("planetas"));
		        Integer numdestinos = Integer.valueOf( propiedades.getProperty("destinos"));
			    if ( origen == null ){
			    	throw new BadUniFileException();
			    }
			    if ( destinos.size() < numdestinos ){
			    	throw new BadUniFileException();
			    }
			    if ( this.nodosH.size() < numplanetas ) {
			    	throw new BadUniFileException();
			    }
		    }else{
		    	throw new BadFormattedFile();
		    }        
	}
	/**
	 * Accedente
	 * @return archivo
	 */
	public String getArchivo() {
		return archivo;
	}
	/**
	 * Mutador
	 * @param archivo
	 */
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	/**
	 * Accedente
	 * @return nodosh
	 */
	public Hashtable<Integer, Nodo> getNodosH() {
		return nodosH;
	}
	/**
	 * Mutador
	 * @param nodosH
	 */
	public void setNodosH(Hashtable<Integer, Nodo> nodosH) {
		this.nodosH = nodosH;
	}
	/**
	 * Accedente
	 * @return Nodo Origen
	 */
	public Nodo getOrigen() {
		return origen;
	}
	/**
	 * Mutador
	 * @param origen
	 */
	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}
	/**
	 * Accedente
	 * @return destinos
	 */
	public Hashtable<Integer, Nodo> getDestinos() {
		return destinos;
	}
	/**
	 * Mutador 
	 * @param destinos
	 */
	public void setDestinos(Hashtable<Integer, Nodo> destinos) {
		this.destinos = destinos;
	}	
}