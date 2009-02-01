package juegos.BlancasNegras;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GabiPC
 */
public class FuncionSucesora implements SuccessorFunction{

    public Estado crearSiguienteEstado(Estado padre, Momento hijo){
        Estado siguiente = null;
        if(padre.movimientoPosible(hijo))
            siguiente = new Estado(padre.getBn(),hijo,padre.getContenido());
        return siguiente;        
    }
    @Override
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;
        Momento momentoActual = actual.getInstante();
        Fichas[] nextPosible = momentoActual.getFichas().clone();
        int posHueco = momentoActual.getHueco();      
        /**
         * Movimiento hacia la izquierda
         */
        if(posHueco > 0){       
            Fichas temp = nextPosible[posHueco - 1];
            nextPosible[posHueco] = temp;
            nextPosible[posHueco - 1] = Fichas.Vacia;
            posHueco--;
            posible = crearSiguienteEstado(actual,new Momento(posHueco,nextPosible));                                       
            if(posible != null)
                siguientes.add(new Successor("Mover hacia la izquierda : [ "+posible.getInstante().getFichas(0)+" , "+posible.getInstante().getFichas(1)+" , "+posible.getInstante().getFichas(2)+" , "+posible.getInstante().getFichas(3)+" , "+posible.getInstante().getFichas(4)+" , "+posible.getInstante().getFichas(5)+" , "+posible.getInstante().getFichas(6)+" ]",posible));
            posHueco++;
            nextPosible[posHueco - 1] = temp;
            nextPosible[posHueco] = Fichas.Vacia;
        }
        posible = null;
        /**
         * Movimiento hacia la derecha
         */
        if(posHueco < 6){
            Fichas temp = nextPosible[posHueco + 1];
            nextPosible[posHueco] = temp;
            nextPosible[posHueco + 1] = Fichas.Vacia;
            posHueco++;
            posible = crearSiguienteEstado(actual,new Momento(posHueco,nextPosible));
            if(posible != null)
                siguientes.add(new Successor("Mover hacia la derecha : [ "+posible.getInstante().getFichas(0)+" , "+posible.getInstante().getFichas(1)+" , "+posible.getInstante().getFichas(2)+" , "+posible.getInstante().getFichas(3)+" , "+posible.getInstante().getFichas(4)+" , "+posible.getInstante().getFichas(5)+" , "+posible.getInstante().getFichas(6)+" ]",posible));
            posHueco--;
            nextPosible[posHueco + 1] = temp;
            nextPosible[posHueco] = Fichas.Vacia;
        }
        posible = null;
        /**
         * Saltar una ficha hacia la izquierda
         */
        if(posHueco > 1){
            Fichas temp = nextPosible[posHueco - 2];
            nextPosible[posHueco] = temp;
            nextPosible[posHueco - 2] = Fichas.Vacia;
            posHueco-=2;
            posible = crearSiguienteEstado(actual,new Momento(posHueco,nextPosible));
            if(posible != null)
                siguientes.add(new Successor("Saltar una ficha hacia la izquierda : [ "+posible.getInstante().getFichas(0)+" , "+posible.getInstante().getFichas(1)+" , "+posible.getInstante().getFichas(2)+" , "+posible.getInstante().getFichas(3)+" , "+posible.getInstante().getFichas(4)+" , "+posible.getInstante().getFichas(5)+" , "+posible.getInstante().getFichas(6)+" ]",posible));
            posHueco+=2;
            nextPosible[posHueco - 2] = temp;
            nextPosible[posHueco] = Fichas.Vacia;
        }
        posible = null;
        /**
         * Saltar una ficha hacia la derecha
         */
        if(posHueco < 5){
            Fichas temp = nextPosible[posHueco + 2];
            nextPosible[posHueco] = temp;
            nextPosible[posHueco + 2] = Fichas.Vacia;
            posHueco+=2;
            posible = crearSiguienteEstado(actual,new Momento(posHueco,nextPosible));            
            if(posible != null)
                siguientes.add(new Successor("Saltar una ficha hacia la derecha : [ "+posible.getInstante().getFichas(0)+" , "+posible.getInstante().getFichas(1)+" , "+posible.getInstante().getFichas(2)+" , "+posible.getInstante().getFichas(3)+" , "+posible.getInstante().getFichas(4)+" , "+posible.getInstante().getFichas(5)+" , "+posible.getInstante().getFichas(6)+" ]",posible));
            posHueco-=2;
            nextPosible[posHueco + 2] = temp;
            nextPosible[posHueco] = Fichas.Vacia;
        }
        posible = null;
        /**
         * Saltar 2 fichas hacia la izquierda
         */
        if(posHueco > 2){
            Fichas temp = nextPosible[posHueco - 3];
            nextPosible[posHueco] = temp;
            nextPosible[posHueco - 3] = Fichas.Vacia;
            posHueco-=3;
            posible = crearSiguienteEstado(actual,new Momento(posHueco,nextPosible));            
            if(posible != null)
                siguientes.add(new Successor("Saltar 2 fichas hacia la izquierda : [ "+posible.getInstante().getFichas(0)+" , "+posible.getInstante().getFichas(1)+" , "+posible.getInstante().getFichas(2)+" , "+posible.getInstante().getFichas(3)+" , "+posible.getInstante().getFichas(4)+" , "+posible.getInstante().getFichas(5)+" , "+posible.getInstante().getFichas(6)+" ]",posible));
            posHueco+=3;
            nextPosible[posHueco - 3] = temp;
            nextPosible[posHueco] = Fichas.Vacia;
        }
        posible = null;
        /**
         * Saltar 2 fichas hacia la derecha
         */
        if(posHueco < 4){
            Fichas temp = nextPosible[posHueco + 3];
            nextPosible[posHueco] = temp;
            nextPosible[posHueco + 3] = Fichas.Vacia;
            posHueco+=3;
            posible = crearSiguienteEstado(actual,new Momento(posHueco,nextPosible));            
            if(posible != null)
                siguientes.add(new Successor("Saltar 2 fichas hacia la derecha : [ "+posible.getInstante().getFichas(0)+" , "+posible.getInstante().getFichas(1)+" , "+posible.getInstante().getFichas(2)+" , "+posible.getInstante().getFichas(3)+" , "+posible.getInstante().getFichas(4)+" , "+posible.getInstante().getFichas(5)+" , "+posible.getInstante().getFichas(6)+" ]",posible));
            posHueco-=3;
            nextPosible[posHueco + 3] = temp;
            nextPosible[posHueco] = Fichas.Vacia;
        }
        posible = null;
        return siguientes;
    }

}
