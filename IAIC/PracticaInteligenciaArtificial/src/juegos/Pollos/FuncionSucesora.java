package juegos.Pollos;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GabiPC
 */
public class FuncionSucesora implements SuccessorFunction{
    
    final int coordenadaX = 2;
    final int coordenadaY = 5;
    
    public Estado crearSiguienteEstado(Estado actual,Momento posible){
        Estado siguiente = null;
        if(actual.movimientoPosible(posible))
            siguiente = new Estado(actual,posible);        
        return siguiente;
    }
    
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;
        for(int i = 0; i < coordenadaX; i++)
            for(int j = 0; j < coordenadaY; j++){
                posible = crearSiguienteEstado(actual,new Momento(actual.getInstante(),i,j));
                if(posible != null)
                    siguientes.add(new Successor("Cambio en [ "+i+" , "+j+" ] : "+posible.getInstante().getValor(i, j).toString(),posible));
                posible = null;
            }        
        return siguientes;
    }


}
