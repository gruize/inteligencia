package juegos;

import java.util.List;
import aima.search.framework.Search;
import java.util.Properties;

public interface Juego {
    
	public void setSolucion(boolean b);

	public boolean getSolucion();

	public void setBusqueda(Search s);

	public boolean ejecutar();

	public String getNombre();
	
        public String imprimir(List eventos);
        
        public String imprimirPropiedades(Properties propiedades);
        
}
