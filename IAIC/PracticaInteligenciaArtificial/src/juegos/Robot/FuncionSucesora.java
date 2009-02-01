package juegos.Robot;

import aima.basic.XYLocation;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GabiPC
 */
class FuncionSucesora implements SuccessorFunction{

    public Estado crearSiguienteEstado(Estado actual, Momento posible){
        Estado siguiente = null;
        if(actual.permiteMovimiento(posible))
            siguiente = new Estado(actual,posible);
        return siguiente;
    }

    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;
        XYLocation posPos = null;
        XYLocation posicion = actual.getInstante().posRobot();
        //Robot hacia arriba [x-1][y]
        if((posicion.getXCoOrdinate() - 1 >= 0) 
        && (actual.getInstante().getRecorrido(posicion.getXCoOrdinate() - 1, posicion.getYCoOrdinate()) != Recorrido.Muro)){
            posPos = new XYLocation(posicion.getXCoOrdinate() - 1, posicion.getYCoOrdinate());
            posible = crearSiguienteEstado(actual,new Momento(actual.getInstante(),posicion,posPos));
            if(posible != null)
                siguientes.add(new Successor("Robot hacia arriba. Posicion actual del Robot: [ "+posPos.getXCoOrdinate()+" , "+posPos.getYCoOrdinate()+" ] ",posible));
        }                      
        posible = null;
        posPos = null;
        //Robot hacia abajo [x+1][y]
        if((posicion.getXCoOrdinate() + 1 < actual.getInstante().TamannoMaximo())
         &&(actual.getInstante().getRecorrido(posicion.getXCoOrdinate() + 1, posicion.getYCoOrdinate()) != Recorrido.Muro)){
            posPos = new XYLocation(posicion.getXCoOrdinate() + 1, posicion.getYCoOrdinate());
            posible = crearSiguienteEstado(actual,new Momento(actual.getInstante(),posicion,posPos));
            if(posible != null)
                siguientes.add(new Successor("Robot hacia abajo. Posicion actual del Robot: [ "+posPos.getXCoOrdinate()+" , "+posPos.getYCoOrdinate()+" ] ",posible));
        }
        posible = null;
        posPos = null;
        //Mover hacia la izquierda [x][y-1]
        if((posicion.getYCoOrdinate() - 1 >= 0)
         &&(actual.getInstante().getRecorrido(posicion.getXCoOrdinate(), posicion.getYCoOrdinate() - 1) != Recorrido.Muro)){
            posPos = new XYLocation(posicion.getXCoOrdinate(), posicion.getYCoOrdinate() - 1);
            posible = crearSiguienteEstado(actual,new Momento(actual.getInstante(),posicion,posPos));
            if(posible != null)
                siguientes.add(new Successor("Robot hacia la izquierda. Posicion actual del Robot: [ "+posPos.getXCoOrdinate()+" , "+posPos.getYCoOrdinate()+" ] ",posible));
        }
        posible = null;
        posPos = null;
        //Mover hacia la derecha [x][y+1]
        if((posicion.getYCoOrdinate() + 1 < actual.getInstante().TamannoMaximo())
         &&(actual.getInstante().getRecorrido(posicion.getXCoOrdinate(), posicion.getYCoOrdinate() + 1) != Recorrido.Muro)){
            posPos = new XYLocation(posicion.getXCoOrdinate(), posicion.getYCoOrdinate() + 1);
            posible = crearSiguienteEstado(actual,new Momento(actual.getInstante(),posicion,posPos));
            if(posible != null)
                siguientes.add(new Successor("Robot hacia la derecha. Posicion actual del Robot: [ "+posPos.getXCoOrdinate()+" , "+posPos.getYCoOrdinate()+" ] ",posible));
        }
        posible = null;
        posPos = null;
        return siguientes;
    }

}
