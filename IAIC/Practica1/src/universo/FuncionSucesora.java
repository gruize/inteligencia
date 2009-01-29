package universo;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import juegos.Juego;
import universo.util.Enlace;
import universo.util.Nodo;

/**
 * 
 * @author GabiPC
 */      
public class FuncionSucesora implements SuccessorFunction{

    private Universo galaxia = null;

    public FuncionSucesora(Universo auxiliar) {
        this.galaxia = auxiliar;
    }

    public Universo getGalaxia() {
        return galaxia;
    }

    public void setGalaxia(Universo galaxia) {
        this.galaxia = galaxia;
    }
    
    /**
     * En este metodo, es donde se añade al recorrido el nodo, unicamente despues
     * de haber superado el juego.
     * @param padre Estado actual
     * @param posible Nodo del estado al que podria ir
     * @return Estado al que va a pasar o null, si no es posible o no supera el 
     *         juego.
     */
    public Estado crearSiguienteEstado(Estado padre, Nodo posible) throws IOException{
        Estado siguiente = null;
        if(padre.movimientoPosible(posible)){
            Juego game = padre.getActual().getEnlaces().get(posible.getId()).dameJuego();
            if(game != null){
                if(game.ejecutar())
                    siguiente = new Estado(posible,padre.getRecorridos());
            }else
                siguiente = new Estado(posible,padre.getRecorridos());
        }
        return siguiente;
    }
    
    //FIXME: PPuede generar errores por el acceso a la Hashtable o simplemente por la no generacion de nodos
    //Solo utilizo los nodos existentes en la tabla NodosH
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;        
        //Recorrido de los enlaces posible del nodo del estado actual 
        for(int j = 0; j < 10; j++){
            if(actual.getActual().getEnlaces().containsKey(j)){
                try {
                    posible = crearSiguienteEstado(actual, Universo.getPlanetas().getNodosH().get(j));
                    siguientes.add(new Successor("Enlazar el planeta " + actual.getActual().getNombre() + " con el planeta " + posible.getActual().getNombre() + " : ", posible));
                    posible = null;
                } catch (IOException ex) {
                    Logger.getLogger(FuncionSucesora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
        return siguientes;
    }
}
