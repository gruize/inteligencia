package juegos.Reinas;

import aima.basic.XYLocation;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario_local
 */
class FuncionSucesor implements SuccessorFunction{

    public Estado crearSiguienteEstado(Estado padre, XYLocation pos){
        Estado siguiente = new Estado(padre.getReino(),padre.getTamanno());        
        List<XYLocation> posiciones = padre.posicionReinas();
        posiciones.add(pos);
        siguiente.setTablero(posiciones);
        return siguiente;
    }
    
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;
        int numReinas = actual.numeroReinasEnTablero();
        for(int i = 0; i < actual.getTamanno(); i++){
            XYLocation pos = new XYLocation(numReinas,i);
            if(!actual.casillaAtacada(pos)){
                posible = crearSiguienteEstado(actual,pos);
                if(posible != null)
                    siguientes.add(new Successor("Colocar una reina en la posicion [ "+pos.getXCoOrdinate()+" , "+pos.getYCoOrdinate()+" ] ",posible));                
            }
            posible = null;
        }
        return siguientes;
    }

}
