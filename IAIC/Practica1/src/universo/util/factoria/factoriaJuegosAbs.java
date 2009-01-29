package universo.util.factoria;

public class factoriaJuegosAbs {
	
	private static factoriaJuegos factoria = null;

	public static factoriaJuegos getInstancia(){
		if ( factoria == null ){
			factoria = new factoriaJuegos();
		}
		return factoria;
	}	
}
