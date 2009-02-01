package universo.util.factoria;

public class FactoriaJuegosAbs {
	
	private static FactoriaJuegos factoria = null;

	public static FactoriaJuegos getInstancia(){
		if ( factoria == null ){
			factoria = new FactoriaJuegos();
		}
		return factoria;
	}	
}
