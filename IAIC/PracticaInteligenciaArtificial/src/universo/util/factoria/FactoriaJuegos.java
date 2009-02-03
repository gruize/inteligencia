package universo.util.factoria;

import juegos.*;
import juegos.BlancasNegras.BlancasNegras;
import juegos.Bloques.Bloques;
import juegos.CajaColores.CajaDeColores;
import juegos.Canibal.Misionero;
import juegos.Garrafas.Garrafas;
import juegos.LoboCabraCol.LCC;
import juegos.Mono.Mono;
import juegos.Palillos.Palillos;
import juegos.Pollos.Pollos;
import juegos.Puzzle.Puzzle;
import juegos.Reinas.Reinas;
import juegos.Robot.Robot;

public class FactoriaJuegos {
	
	public FactoriaJuegos(){
		
	}
	
	/**
	 * 
	 * @param juego
	 * @return Juego
	 * 
	 * Se devuelve la interfaz del juego solicitado.
	 * 
	 * para a�adir mas juegos simplemente hay que a�adir
	 * en esta clase clausulas else if con el nombre del nuevo juego 
	 * del fichero de configuracion.
	 * 
	 */
	
	public Juego dameJuego(String juego){
		
		if (juego.equals("Blancas vs Negras")){
			return new BlancasNegras();
		}else if (juego.equals("Rojo vs Azul")){
			return new CajaDeColores();
		}else if (juego.equals("Misi vs Cani")){
			return new Misionero();
		}else if (juego.equals("Garrafas")){
			return new Garrafas();
		}else if (juego.equals("Lobo Cabra Col")){
			return new LCC();
		}else if (juego.equals("Pollos")) {
			return new Pollos();
		}else if (juego.equals("8-Puzzle")){
			return new Puzzle();
		}else if (juego.equals("N Reinas")){
			return new Reinas();
		}else if (juego.equals("Robot")){
			return new Robot();
		}else if (juego.equals("Mono")){
			return new Mono();
		}else if (juego.equals("Palillos")){
			return new Palillos();
		}else if (juego.equals("Bloques")){
			return new Bloques();
		}else {
			return null;
		}
		
	}
}
