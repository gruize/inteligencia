package juegos.Connect4;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GabiPC
 */
class FuncionSucesora implements SuccessorFunction{

    public Estado crearSiguienteEstado(Estado padre, Momento posible){
        Estado siguiente = null;
        if(padre.permiteMovimiento(posible))
            siguiente = new Estado(padre,posible);
        return siguiente;
    }
    
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;
        //Ficha arriba

        //Ficha a la izquierda
        
        //Ficha a la derecha
                
        return siguientes;
    }

}
