package juegos.CajaColores;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GabiPC
 */
public class FuncionSucesora implements SuccessorFunction {

    public Estado crearSiguienteEstado(Estado padre, Momento hijo){
        Estado siguiente = null;
        if(padre.permiteMovimiento(hijo)){
            siguiente = new Estado(padre.getTamanno(),hijo,padre);
        }
        return siguiente;
    }
    
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;
        Momento padre = actual.getInstante();
        for(int i = 0; i < actual.getTamanno(); i++)
            for(int j = 0; j < actual.getTamanno(); j++){
                Momento hijo = new Momento(actual.getTamanno(),padre,i,j);
                posible = crearSiguienteEstado(actual,hijo);
                if(posible != null)
                    siguientes.add(new Successor("Modificar la posicion [ " + i + " ][ " + j+ " ]: " + posible.getInstante().aString(),posible));        
                posible = null;
            }
        return siguientes;
    }

}