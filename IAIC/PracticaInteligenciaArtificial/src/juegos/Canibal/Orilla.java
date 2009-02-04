package juegos.Canibal;

/**
* Suponemos la orilla izquierda como TRUE (o 1) y la derecha como FALSE (o 0),
* por lo que al pasar de la orilla izquierda a la derecha (TRUE --> FALSE)
* se deben restar los elementos transportados a los datos existentes
* y si se transportan elementos de derecha a izquierda (FALSE --> TRUE)
* se deben sumar a los mismos (nmisioneros y ncanibales).
 * @author Grupo C15
 */
public class Orilla {

    final int NUM_MISIONEROS = 3;
    final int NUM_CANIBALES = 3;

    int nmisioneros;
    int ncanibales;
    boolean canoa;

    /**
     * Constructor por defecto
     */
    public Orilla(){
        this.nmisioneros = NUM_MISIONEROS;
        this.ncanibales = NUM_CANIBALES;
        this.canoa = true;
    }

    /**
     * Constructor parametrizado
     * @param nmisioneros
     * @param ncanibales
     * @param canoa
     */
    public Orilla(int nmisioneros, int ncanibales, boolean canoa) {
        this.nmisioneros = nmisioneros;
        this.ncanibales = ncanibales;
        this.canoa = canoa;
    }

    public boolean getCanoa() {
        return canoa;
    }

    public void setCanoa(boolean canoa) {
        this.canoa = canoa;
    }

    public int getNcanibales() {
        return ncanibales;
    }

    public void setNcanibales(int ncanibales) {
        this.ncanibales = ncanibales;
    }

    public int getNmisioneros() {
        return nmisioneros;
    }

    public void setNmisioneros(int nmisioneros) {
        this.nmisioneros = nmisioneros;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orilla other = (Orilla) obj;
        if (this.nmisioneros != other.nmisioneros) {
            return false;
        }
        if (this.ncanibales != other.ncanibales) {
            return false;
        }
        if (this.canoa != other.canoa) {
            return false;
        }
        return true;
    }
}
