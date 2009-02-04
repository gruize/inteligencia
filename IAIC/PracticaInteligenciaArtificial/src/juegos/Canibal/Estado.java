package juegos.Canibal;

import java.util.Vector;

/**
 *
 * @author Grupo C15
 */
public class Estado {
    
    private Orilla datos;
    private Vector<Orilla> recorrido;
    private Misionero mision;

    /**
     * Constructor parametrizado
     * @param mision
     */
    public Estado(Misionero mision) {
        this.datos = new Orilla();
        this.recorrido = new Vector<Orilla>();
        this.recorrido.add(this.datos);
        this.mision = mision;
    }

    /**
     * Constructor parametrizado
     * @param datos
     * @param recorrido
     * @param mision
     */
    public Estado(Orilla datos, Vector<Orilla> recorrido, Misionero mision) {
        this.datos = datos;
        this.recorrido = recorrido;
        this.mision = mision;
        this.recorrido.add(datos);
    }

    public Orilla getDatos() {
        return datos;
    }

    public void setDatos(Orilla datos) {
        this.datos = datos;
    }

    public Misionero getMision() {
        return mision;
    }

    public void setMision(Misionero mision) {
        this.mision = mision;
    }

    public Vector<Orilla> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Vector<Orilla> recorrido) {
        this.recorrido = recorrido;
    }

    /**
     * Indica si un estado ya ha sido creado en algun otro momento del recorrido.
     * @param datos Siguiente posible orilla
     * @return	True Si es posible
     * 			False En cualquier otro caso
     */
    public boolean transporteValido(Orilla datos){
        boolean valido = true;
        if( this.mision.permiteControlCiclos())
            for(int i = 0; i < this.recorrido.size(); i++)
                if(this.recorrido.get(i).equals(datos))
                    valido = false;
        return valido;
    }
}