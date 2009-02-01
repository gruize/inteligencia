package juegos.Palillos;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class FuncionSucesora implements SuccessorFunction {

	public Estado crearSiguienteEstado(Estado padre, Momento posible){
		Estado siguiente = null;
		if(padre.permiteMovimiento(posible))
			siguiente = new Estado(padre,posible);
		return siguiente;
	}
	
	@Override
	public List getSuccessors(Object state) {
		List<Successor> siguientes = new ArrayList<Successor>();
		Estado actual = (Estado)state;
		Estado posible = null;
		for(int i = 0; i < 3; i++){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getPalillos() - i,actual.getInstante().getTurno() + 1));
			if(posible != null)
				siguientes.add(new Successor("El turno "+actual.getInstante().getTurno()+" quita "+i+" palillos.",posible));
			posible = null;
		}		
		return siguientes;
	}

}
