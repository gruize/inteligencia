package universo.util.factoria;

/**
 * 
 * Interfaz de la factoria de juegos.
 * 
 * Ademas sigue el patron singleton para garantizar que solo se cree una isntancia de esta clase
 * durante toda la ejecucion.
 * 
 * @author Kenzitron
 *
 */

public class FactoriaJuegosAbs {
	
	private static FactoriaJuegos factoria = null;

	public static FactoriaJuegos getInstancia(){
		if ( factoria == null ){
			factoria = new FactoriaJuegos();
		}
		return factoria;
	}	
}
