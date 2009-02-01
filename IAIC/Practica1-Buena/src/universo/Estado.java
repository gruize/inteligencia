package universo;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import universo.util.Nodo;

/**
 * Contiene las datos del estado actual y el recorrido que se ha realizado.
 * @author Grupo C15
 */
public class Estado {

    private Nodo actual;
    private Vector<Nodo> recorridos;
    
    /**
     * Constructor parametrizado
     * 
     * @param aThis Universo, que contiene los datos obtenidos desde el fichero
     */
    public Estado() {
        try {
            this.actual = GestorConexion.getInstancia().getOrigen();
            this.recorridos = new Vector<Nodo>();
        } catch (Exception ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Constructor parametrizado
     * El nodo actual se añade al recorrido despues de haber realizado con exito
     * el juego de ese nodo si existiese. Si dicho nodo no supone como requisito,
     * para pasar por él, realizar ningun juego, suponemos la inexistencia de ese
     * requisito como un exito.
     * @param galaxia Universo, que contiene los datos obtenidos desde el fichero
     * @param actual Nodo a partir del cual generamos un nuevo estado
     * @param recorridos Listado de los nodos en los que hemos estado y que han 
     * realizado con exito el juego que impone su enlace.
     */
    public Estado(Nodo actual, Vector<Nodo> recorridos) {
        this.actual = actual;
        this.recorridos = recorridos;
        this.recorridos.add(actual);
    }

    public Nodo getActual() {
        return actual;
    }

    public void setActual(Nodo actual) {
        this.actual = actual;
    }

    public Vector<Nodo> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(Vector<Nodo> recorridos) {
        this.recorridos = recorridos;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getActual().equals(((Estado)obj).getActual());
    }

    /** 
     * Elimina la posibilidad de pasar dos veces por el mismo nodo si ha sido
     * un movimiento efectivo, es decir, es posible y el juego del enlace,es decir
     * el camino desde el nodo actual al nodo posible, tiene resultado posiitivo
     * (solucion correcta).
     */
    public boolean movimientoPosible(Nodo posible){
        boolean valido = true;
        int i = 0;
        while(valido && (i < this.getRecorridos().size())){
            if(this.getRecorridos().elementAt(i).equals(posible))
                valido = false;
            i++;
        }
        return valido;
    }

    /**
     * Genera el valor heuristico del estado actual.
     * @return Valor heuristico del estado actual.
     * @throws Exception
     */
    public double obtenerValorHeuristico() throws Exception{
        return this.getActual().obtenerValorHeuristico();
    }
 
    /**
     * Genera el coste de pasar del estado actual al estado destino.
     * @param destino Siguiente estado
     * @return Real con el coste que supone pasar por un enlace entre el estado 
     * actual y el estado destino
     */
    public double generarCoste(Estado destino){
        return this.getActual().getEnlaces().get(destino.getActual().getId()).getDistancia().doubleValue();
    }
    
}
