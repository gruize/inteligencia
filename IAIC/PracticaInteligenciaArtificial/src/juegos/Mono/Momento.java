package juegos.Mono;

public class Momento {
	/**
	 * 0 si el mono esta en la puerta de la habitacion
	 * 1 si el mono esta en el centro de la habitacion
	 * 2 si el mono esta en la ventana de la habitacion
	 */
	private int mono;
	/**
	 * 0 si el ventana esta en la puerta de la habitacion
	 * 1 si el ventana esta en el centro de la habitacion
	 * 2 si el ventana esta en la ventana de la habitacion
	 */
	private int caja;
	/**
	 * True si el mono esta sobre la caja
	 * False en cualquier otro caso.
	 */
	private boolean sobreCaja;
	/**
	 * True si el mono tiene el platano
	 * False en cualquier otro caso
	 */
	private boolean platano;
	
	public Momento(){
		this.mono = 0;
		this.caja = 2;
		this.sobreCaja = false; 
		this.platano = false;
	}
	
	public Momento(int mono, int caja, boolean sobreCaja, boolean platano) {
		super();
		this.mono = mono;
		this.caja = caja;
		this.sobreCaja = sobreCaja;
		this.platano = platano;
	}

	public int getMono() {
		return mono;
	}

	public void setMono(int mono) {
		this.mono = mono;
	}

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}

	public boolean getSobreCaja() {
		return sobreCaja;
	}

	public void setSobreCaja(boolean sobreCaja) {
		this.sobreCaja = sobreCaja;
	}

	public boolean getPlatano() {
		return platano;
	}

	public void setPlatano(boolean platano) {
		this.platano = platano;
	}	

	public double generarHeuristica(){
		double heuristica = 0.0;
		if(this.getMono() == 0){
			heuristica = 5.0;	
		}else{
			if(this.getMono() == 1){
				if(this.getSobreCaja() && !this.getPlatano()){
					heuristica = 1.0;
				}else{
					if(this.getSobreCaja() && this.getPlatano()){
						heuristica = 0.0;
					}else{
						if(this.getCaja() != 1){
							heuristica = 4.0;
						}else{
							heuristica = 6.0;
						}
					}
				}
			}else{
				if(this.getMono() == 2){
					if((this.getCaja() == 2) && !this.getSobreCaja()){
						heuristica = 3.0;
					}else{
						if((this.getCaja() == 2) && this.getSobreCaja()){
							heuristica = 2.0;
						}else{
							if(this.getCaja() != 2){
								heuristica = 5.0;
							}else{
								heuristica = 6.0;
							}
						}
					}
				}
			}
		}        
		return heuristica; 
	}

	@Override
	public boolean equals(Object obj) {
		Momento actual = (Momento)obj;
		return ((this.getCaja() == actual.getCaja())
		 &&(this.getPlatano() == actual.getPlatano())
		 &&(this.getMono() == actual.getMono())
		 &&(this.getSobreCaja() == actual.getSobreCaja()));
	}
		
}
