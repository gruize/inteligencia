package juegos.Bloques;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class FuncionSucesora implements SuccessorFunction {


    public List getSuccessors(Object state) {
            List<Successor> siguientes = new ArrayList<Successor>();
            Estado actual = (Estado) state;
            Estado posible = null;
            boolean aplicado=false;
            // Mover el bloque A sobre B 
            aplicado = libre(Posiciones.A,actual) && libre(Posiciones.B,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(Posiciones.B,actual.getBloques().getBloques()[1],actual.getBloques().getBloques()[2]));
            if (posible != null)
                siguientes.add(new Successor("Mover el Bloque A sobre B : ",posible));    
            }
            aplicado=false;
            posible=null;
            // Mover el bloque de A sobre C
            aplicado = libre(Posiciones.A,actual) && libre(Posiciones.C,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(Posiciones.C,actual.getBloques().getBloques()[1],actual.getBloques().getBloques()[2]));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque A sobre C : ",posible));
            }
            aplicado=false;
            posible=null;
            //Mover el bloque de B sobre A
            aplicado = libre(Posiciones.B,actual) && libre(Posiciones.A,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(actual.getBloques().getBloques()[0],Posiciones.A,actual.getBloques().getBloques()[2]));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque B sobre A : ",posible));
            }
            aplicado=false;
            posible=null;
            //Mover el bloque de B sobre C
            aplicado = libre(Posiciones.B,actual) && libre(Posiciones.C,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(actual.getBloques().getBloques()[0],Posiciones.C,actual.getBloques().getBloques()[2]));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque B sobre C : ",posible));
            }
            aplicado=false;
            posible=null;
            //Mover el bloque de C sobre A
            aplicado = libre(Posiciones.C,actual) && libre(Posiciones.A,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(actual.getBloques().getBloques()[0],actual.getBloques().getBloques()[1],Posiciones.A));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque C sobre A : ",posible));
            }
            aplicado=false;
            posible=null;
            //Mover el bloque de C sobre B
            aplicado = libre(Posiciones.C,actual) && libre(Posiciones.B,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(actual.getBloques().getBloques()[0],actual.getBloques().getBloques()[1],Posiciones.B));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque C sobre B : ",posible));
            }
            aplicado=false;
            posible=null;
            //Mover el bloque A sobre la mesa
            aplicado = libre(Posiciones.A,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(Posiciones.mesa,actual.getBloques().getBloques()[1],actual.getBloques().getBloques()[2]));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque A sobre la mesa : ",posible));
            }
            aplicado=false;
            posible=null;
            //Mover el bloque de B sobre la mesa
            aplicado = libre(Posiciones.B,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(actual.getBloques().getBloques()[0],Posiciones.mesa,actual.getBloques().getBloques()[2]));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque B sobre la mesa : ",posible));
            }
            aplicado=false;
            posible=null;
            //Mover el bloque de C sobre la mesa
            aplicado = libre(Posiciones.C,actual);
            if (aplicado){
            	posible = siguienteMovimiento(actual,new Momento(actual.getBloques().getBloques()[0],actual.getBloques().getBloques()[1],Posiciones.mesa));
            if (posible != null)
                    siguientes.add(new Successor("Mover el Bloque C sobre la mesa : ",posible));
            }            
            return siguientes;
    }
    
    private Estado siguienteMovimiento(Estado padre, Momento posible){
        Estado siguiente = null;
    	if(padre.permiteMovimiento(posible))
    		siguiente = new Estado(posible,padre.getRecorrido());      
    	return siguiente;
    }

    private boolean libre(Posiciones pos,Estado b) {
        int i = 0;
        boolean free = true;
        while (i < 3 && free) {
            free=!b.getBloques().getBloques()[i].equals(pos);
            i++;
        }
        return free;
    }

}
