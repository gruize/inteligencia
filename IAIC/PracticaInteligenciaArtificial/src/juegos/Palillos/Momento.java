package juegos.Palillos;

public class Momento {

	private int palillos;
	private int turno;

	/**
	 * Constructor por defecto
	 */
	public Momento() {
		this.palillos = 6;
		this.turno = 0;
	}	

	/**
	 * Constructor parametrizado
	 * @param palillos Numero de palillos que quedan por quitar
	 * @param turno Jugador
	 */
	public Momento(int palillos, int turno) {
		this.palillos = palillos;
		this.turno = turno%2;
	}	

	public int getPalillos() {
		return palillos;
	}

	public void setPalillos(int palillos) {
		this.palillos = palillos;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	@Override
	public boolean equals(Object obj) {
		return ((this.getPalillos() == ((Momento)obj).getPalillos()) &&
				(this.getTurno() == ((Momento)obj).getTurno()));
	}

	/**
	 * Devuelve el valor heuristico
	 * @return Valor heuristico
	 */
	public double generarHeuristica() {
		return (this.getPalillos() + (this.getTurno()%2));
	}
	
}
