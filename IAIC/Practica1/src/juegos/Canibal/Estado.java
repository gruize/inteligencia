package juegos.Canibal;

import java.util.Vector;

/**
 *
 * @author GabiPC
 */
public class Estado {
    
    private Orilla datos;
    private Vector<Orilla> recorrido;
    private Misionero mision;

    public Estado(Misionero mision) {
        this.datos = new Orilla();
        this.recorrido = new Vector<Orilla>();
        this.recorrido.add(this.datos);
        this.mision = mision;
    }

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

    public boolean transporteValido(Orilla datos){
        boolean valido = true;
        if( this.mision.permiteControlCiclos())
            for(int i = 0; i < this.recorrido.size(); i++)
                if(this.recorrido.get(i).equals(datos))
                    valido = false;
        return valido;
    }
}