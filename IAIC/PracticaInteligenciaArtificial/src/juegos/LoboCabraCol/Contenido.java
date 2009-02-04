package juegos.LoboCabraCol;

public class Contenido {
	
	private boolean granjero;
	private boolean lobo;
	private boolean cabra;
	private boolean col;
	
	/**
	 * Constructor por defecto
	 */
	public Contenido() {
		granjero=true;
		lobo=true;
		cabra=true;
		col=true;
	}
	
	/**
	 * Constructor parametrizado
	 * @param gran
	 * @param lob
	 * @param cab
	 * @param cl
	 */
	public Contenido(boolean gran,boolean lob,boolean cab, boolean cl){
		granjero=gran;
		lobo=lob;
		cabra=cab;
		col=cl;
	}

	public boolean getCabra() {
		return cabra;
	}

	public void setCabra(boolean cabra) {
		this.cabra = cabra;
	}

	public boolean getCol() {
		return col;
	}

	public void setCol(boolean col) {
		this.col = col;
	}

	public boolean getGranjero() {
		return granjero;
	}

	public void setGranjero(boolean granjero) {
		this.granjero = granjero;
	}

	public boolean getLobo() {
		return lobo;
	}

	public void setLobo(boolean lobo) {
		this.lobo = lobo;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Contenido other = (Contenido) obj;
		if (cabra != other.cabra)
			return false;
		if (col != other.col)
			return false;
		if (granjero != other.granjero)
			return false;
		if (lobo != other.lobo)
			return false;
		return true;
	}

	
}
