package juegos.Mono;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class FuncionSucesora implements SuccessorFunction {

	public Estado crearSiguienteEstado(Estado actual, Momento posible){
		Estado siguiente = null;
		if(actual.permiteMovimiento(posible))
			siguiente = new Estado(actual,posible);
		return siguiente;
	}
	
	@Override
	public List getSuccessors(Object state) {
		List<Successor> siguientes = new ArrayList<Successor>();
		Estado actual = (Estado)state;
		Estado posible = null;
		/**
		 * El mono va hacia la ventana
		 */
		if((actual.getInstante().getMono() != 2) && (!actual.getInstante().getSobreCaja())){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getMono() + 1,actual.getInstante().getCaja(),actual.getInstante().getSobreCaja(),actual.getInstante().getPlatano()));
			if(posible != null)
				siguientes.add(new Successor("El mono se mueve en direccion a la ventana",posible));
			posible = null;
		}
		/**
		 * El mono va hacia la puerta
		 */
		if((actual.getInstante().getMono() != 0) && (!actual.getInstante().getSobreCaja())){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getMono() - 1,actual.getInstante().getCaja(),actual.getInstante().getSobreCaja(),actual.getInstante().getPlatano()));
			if(posible != null)
				siguientes.add(new Successor("El mono se mueve en direccion a la puerta",posible));
			posible = null;
		}
		/**
		 * El mono empuja la caja hacia la ventana
		 */
		if((actual.getInstante().getMono() == actual.getInstante().getCaja()) && (actual.getInstante().getMono() != 2) && (!actual.getInstante().getSobreCaja())){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getMono() + 1,actual.getInstante().getCaja() + 1,actual.getInstante().getSobreCaja(),actual.getInstante().getPlatano()));
			if(posible != null)
				siguientes.add(new Successor("El mono empuja la caja en direccion a la ventana",posible));
			posible = null;
		}	
		/**
		 * El mono empuja la caja hacia la puerta
		 */
		if((actual.getInstante().getMono() == actual.getInstante().getCaja()) && (actual.getInstante().getMono() != 0) && (!actual.getInstante().getSobreCaja())){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getMono() - 1,actual.getInstante().getCaja() - 1,actual.getInstante().getSobreCaja(),actual.getInstante().getPlatano()));
			if(posible != null)
				siguientes.add(new Successor("El mono empuja la caja en direccion a la puerta",posible));
			posible = null;
		}		
		/**
		 * El mono se sube sobre la caja
		 */
		if((actual.getInstante().getMono() == actual.getInstante().getCaja()) && (!actual.getInstante().getSobreCaja())){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getMono(),actual.getInstante().getCaja(),true,actual.getInstante().getPlatano()));
			if(posible != null)
				siguientes.add(new Successor("El mono sube sobre la caja",posible));
			posible = null;
		}		
		/**
		 * El mono se baja de la caja
		 */
		if((actual.getInstante().getMono() == actual.getInstante().getCaja()) && (actual.getInstante().getSobreCaja())){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getMono(),actual.getInstante().getCaja(),false,actual.getInstante().getPlatano()));
			if(posible != null)
				siguientes.add(new Successor("El mono sube sobre la caja",posible));
			posible = null;
		}		
		/**
		 * El mono coje el platano
		 */
		if((actual.getInstante().getSobreCaja()) && (actual.getInstante().getCaja() == 1) && (actual.getInstante().getMono() == 1) && (!actual.getInstante().getPlatano())){
			posible = crearSiguienteEstado(actual,new Momento(actual.getInstante().getMono(),actual.getInstante().getCaja(),actual.getInstante().getSobreCaja(),true));
			if(posible != null)
				siguientes.add(new Successor("El mono coje el platano",posible));
			posible = null;			
		}		
		return siguientes;
	}

}
