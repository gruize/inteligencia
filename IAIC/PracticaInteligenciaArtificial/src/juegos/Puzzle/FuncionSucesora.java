package juegos.Puzzle;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo C15
 */
public class FuncionSucesora implements SuccessorFunction{

    final int TAMANNO = 3;
    
	/**
	 * Genera el siguiente estado, comprobando antes que sea posible
	 * @param actual Estado actual
	 * @param posible Posible siguiente momento
	 * @return Nuevo estado
	 */
    public Estado crearSiguienteEstado(Estado actual, Momento posible){
        Estado siguiente = null;
        if(actual.permiteMovimiento(posible))
            siguiente = new Estado(actual, posible);
        return siguiente;
    }
    
    @Override
    public List getSuccessors(Object estado) {
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado)estado;
        Estado posible = null;
        int posX = actual.getInstante().getXHueco();
        int posY = actual.getInstante().getYHueco();
        //Mover el hueco hacia arriba
        if(posX > 0){
            posible = this.crearSiguienteEstado(actual, new Momento(actual.getInstante(),posX - 1,posY));
            if(posible != null)
                siguientes.add(new Successor("Mover el hueco hacia arriba. Posición vacia: [ "+(posX - 1)+" , "+posY+" ]",posible));
        }
        posible = null;
        //Mover el hueco hacia abajo
        if(posX < this.TAMANNO - 1){
            posible = this.crearSiguienteEstado(actual, new Momento(actual.getInstante(),posX + 1,posY));
            if(posible != null)
                siguientes.add(new Successor("Mover el hueco hacia abajo. Posición vacia: [ "+(posX + 1)+" , "+posY+" ]",posible));
        }
        posible = null;
        //Mover el hueco hacia la izquierda
        if(posY > 0){
            posible = this.crearSiguienteEstado(actual, new Momento(actual.getInstante(),posX,posY - 1));
            if(posible != null)
                siguientes.add(new Successor("Mover el hueco hacia arriba. Posición vacia: [ "+posX+" , "+(posY - 1)+" ]",posible));
        }
        posible = null;
        //Mover el hueco hacia la derecha
        if(posY < this.TAMANNO - 1){
            posible = this.crearSiguienteEstado(actual, new Momento(actual.getInstante(),posX,posY + 1));
            if(posible != null)
                siguientes.add(new Successor("Mover el hueco hacia arriba. Posición vacia: [ "+posX+" , "+(posY + 1)+" ]",posible));
        }
        posible = null;
        System.out.println("Estados creados : "+siguientes.size());
        return siguientes;
    }

}
