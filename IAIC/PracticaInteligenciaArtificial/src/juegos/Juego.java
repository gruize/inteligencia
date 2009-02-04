package juegos;

import java.util.List;
import aima.search.framework.Search;
import java.util.Properties;

public interface Juego {
    
	/**
	 * Modifica la variable boolean Solucion, que determina el exito o fracaso 
	 * del juego
	 * @param b Boolean Nuevo valor de la variable Solucion
	 */
	public void setSolucion(boolean b);

	/**
	 * Accedente de la variable boolean Solucion, que determina el exito o fracaso
	 * del juego
	 * @return Boolean Solucion
	 */
	public boolean getSolucion();

	/**
	 * Modifica el valor de la variable Search Busqueda.
	 * @param s Search Nuevo valor de la variable Busqueda
	 */
	public void setBusqueda(Search s);

    /**
     * Establece el metodo de busqueda y hace las llamadas necesarias a las diferentes 
     * clases, para obtener una lista de acciones que deben realizarse segun el algoritmo
     * establecido para poder conseguir el mejor recorrido llegando a sus objetivos     
	 * @return Solucion
	 */
	public boolean ejecutar();

	/**
	 * Obtiene el nombre del juego
	 * @return Nombre del juego
	 */
	public String getNombre();
	
    /**
     * Genera un String con todos las acciones que deben realizarse
     * @param eventos Listado de acciones que deben realizarse
     * @return String con el listado de acciones concatenados
     */
    public String imprimir(List eventos);
    
    /**
    * Genera un String con las propiedades resultantes del recorrido
    * @param propiedades Propiedades obtenidas
    * @return String con las propiedades concatenadas
    */
    public String imprimirPropiedades(Properties propiedades);
        
}
