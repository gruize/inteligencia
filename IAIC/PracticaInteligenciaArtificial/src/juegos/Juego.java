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
	 * Devuelve el exito o fracaso del juego, y a su vez acciona los metodos de
	 * ejecucion del juego y la obtencion de sus resultados
	 * @return Solucion
	 */
	public boolean ejecutar();

	/**
	 * Obtiene el nombre del juego
	 * @return Nombre del juego
	 */
	public String getNombre();
	
	/**
	 * Genera un String con todas las acciones que se han realizado, siempre que el
	 * resultado del juego haya sido un exito.
	 * @param eventos Lista de acciones realizadas.
	 * @return String con las acciones realizadas.
	 */
    public String imprimir(List eventos);
    
    /**
     * Genera un String con las propiedades resultantes de la ejecucion del juego,
     * sin importar el resultado del mismo.
     * @param propiedades Propiedades resultantes de la ejecucion del juego.
     * @return String Propiedades.
     */
    public String imprimirPropiedades(Properties propiedades);
        
}
