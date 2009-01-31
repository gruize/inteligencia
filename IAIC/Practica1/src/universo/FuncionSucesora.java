package universo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import juegos.Juego;
import universo.util.Nodo;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

/**
 * 
 * @author Grupo C15
 */      
public class FuncionSucesora implements SuccessorFunction{

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
            	
            	System.out.println(game.getNombre());
            	
                if(game.ejecutar())
                    siguiente = new Estado(posible,padre.getRecorridos());
            }else
                siguiente = new Estado(posible,padre.getRecorridos());
        }
        return siguiente;
    }
    
    /**
     * Funcion sucesora
     */
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        
        System.out.println();
        System.out.println("Estado actual: " + actual.getActual().getId());
        
        Estado posible = null;        
        //Recorrido de los enlaces posible del nodo del estado actual 
        for(int j = 0; j < 10; j++){
            if(actual.getActual().getEnlaces().containsKey(j)){
                try {
                    
                    System.out.println(j);
                	
                	posible = crearSiguienteEstado(actual, GestorConexion.getInstancia().getNodosH().get(j));
                    if(posible != null){
                    	siguientes.add(new Successor("Enlazar el planeta " + actual.getActual().getNombre() + " " + actual.getActual().getId() + " con el planeta " + posible.getActual().getNombre() + " " + posible.getActual().getId() + " cuya distancia es : " + actual.getActual().getEnlaces().get(posible.getActual().getId()).getDistancia(), posible));
                    	System.out.println("Estado: Planeta " + actual.getActual().getId() + " -> Planeta " + posible.getActual().getId() + " con coste " + actual.getActual().getEnlaces().get(posible.getActual().getId()).getDistancia() + "con el juego " + actual.getActual().getEnlaces().get(posible.getActual().getId()).getJuego());
                    }
                    posible = null;
                } catch (IOException ex) {
                    Logger.getLogger(FuncionSucesora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }  
        return siguientes;
    }
}
