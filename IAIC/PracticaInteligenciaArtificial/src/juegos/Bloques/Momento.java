package juegos.Bloques;

import java.util.Arrays;

public class Momento {
    
	private Posiciones [] bloques = new Posiciones[3];

	/**
	 * Constructor por defecto
	 */
    public Momento(){
	    bloques[0]= Posiciones.C;
	    bloques[1]= Posiciones.A;
	    bloques[2]= Posiciones.mesa;    
    }
    
    /**
     * Constructor parametrizado
     * @param a 
     * @param b
     * @param c
     */
    public Momento(Posiciones a, Posiciones b, Posiciones c){       
	    bloques[0]= a;
	    bloques[1]= b;
	    bloques[2]= c;           
    }
         
    public Posiciones[] getBloques() {
        return this.bloques;
    }

    public void setBloques(Posiciones[] bloques) {
        this.bloques = bloques;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bloques);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Momento other = (Momento) obj;
		if (!Arrays.equals(bloques, other.bloques))
			return false;
		return true;
	}

}
