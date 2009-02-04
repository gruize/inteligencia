package juegos.Canibal;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author GabiPC
 */
public class FuncionSucesora implements SuccessorFunction{

    private EstadosProhibidos estadosProhibidos;

    /**
     * Constructor por defecto
     */
    public FuncionSucesora() {
        this.estadosProhibidos = new EstadosProhibidos();
    }

	/**
	 * Genera el siguiente estado, comprobando antes que sea valido y posible
	 * @param padre Estado actual
	 * @param datos Posible siguiente orilla
	 * @return Nuevo estado
	 */
    public Estado crearSiguienteEstado(Estado actual, Orilla datos){
        Estado siguiente = null;
        if(estadoNoProhibido(datos)){
            if(actual.transporteValido(datos)){
                Vector<Orilla> camino = actual.getRecorrido();
                Misionero mision = actual.getMision();
                siguiente = new Estado(datos, camino, mision);
            }
        }
        return siguiente;
    }

    public EstadosProhibidos getEstadosProhibidos() {
        return estadosProhibidos;
    }

    public void setEstadosProhibidos(EstadosProhibidos estadosProhibidos) {
        this.estadosProhibidos = estadosProhibidos;
    }

    @Override
    public List getSuccessors(Object estado){
        List<Successor> siguientes = new ArrayList<Successor>();
        Estado actual = (Estado) estado;
        Estado posible = null;
        /**
         * Cruza un misionero
         */
        if(((actual.getDatos().getNmisioneros() > 0) && (actual.getDatos().getCanoa()))
         ||((actual.getDatos().getNmisioneros() < 3) && (!actual.getDatos().getCanoa()))){
            if(actual.getDatos().getCanoa()){
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros()-1,actual.getDatos().getNcanibales(),false));
                if(posible != null)
                    siguientes.add(new Successor("Cruza un misionero desde la orilla izquierda. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Derecha )",posible));                
            }else{
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros()+1,actual.getDatos().getNcanibales(),true));
                if(posible != null)
                    siguientes.add(new Successor("Cruza un misionero desde la orilla derecha. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Izquierda )",posible));
            }
        }
        posible = null;
        /**
         * Cruzan 2 misioneros
         */
        if(((actual.getDatos().getNmisioneros() > 1) && (actual.getDatos().getCanoa()))
         ||((actual.getDatos().getNmisioneros() < 2) && (!actual.getDatos().getCanoa()))){
            if(actual.getDatos().getCanoa()){
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros()-2,actual.getDatos().getNcanibales(),false));
                if(posible != null)
                    siguientes.add(new Successor("Cruzan 2 misioneros desde la orilla izquierda. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Derecha )",posible));                
            }else{
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros()+2,actual.getDatos().getNcanibales(),true));
                if(posible != null)
                    siguientes.add(new Successor("Cruza 2 misioneros desde la orilla derecha. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Izquierda )",posible));
            }
        }
        posible = null;
        /**
         * Cruza un canibal
         */
        if(((actual.getDatos().getNcanibales() > 0) && (actual.getDatos().getCanoa()))
         ||((actual.getDatos().getNcanibales() < 3) && (!actual.getDatos().getCanoa()))){
            if(actual.getDatos().getCanoa()){
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros(),actual.getDatos().getNcanibales()-1,false));
                if(posible != null)
                    siguientes.add(new Successor("Cruza un canibal desde la orilla izquierda. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Derecha )",posible));                
            }else{
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros(),actual.getDatos().getNcanibales()+1,true));
                if(posible != null)
                    siguientes.add(new Successor("Cruza un canibal desde la orilla derecha. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Izquierda )",posible));
            }
        }
        posible = null;
        /**
         * Cruzan 2 canibales
         */
        if(((actual.getDatos().getNcanibales() > 1) && (actual.getDatos().getCanoa()))
         ||((actual.getDatos().getNcanibales() < 2) && (!actual.getDatos().getCanoa()))){
            if(actual.getDatos().getCanoa()){
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros(),actual.getDatos().getNcanibales()-2,false));
                if(posible != null)
                    siguientes.add(new Successor("Cruza 2 canibales desde la orilla izquierda. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Derecha )",posible));                
            }else{
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros(),actual.getDatos().getNcanibales()+2,true));
                if(posible != null)
                    siguientes.add(new Successor("Cruza 2 canibales desde la orilla derecha. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Izquierda )",posible));
            }
        }
        /**
         * Cruzan 1 misionero y un canibal
         */
        if(((actual.getDatos().getNmisioneros() > 0) && (actual.getDatos().getNcanibales() > 0) && (actual.getDatos().getCanoa()))
         ||((actual.getDatos().getNmisioneros() < 3) && (actual.getDatos().getNcanibales() < 3) && (!actual.getDatos().getCanoa()))){
            if(actual.getDatos().getCanoa()){
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros()-1,actual.getDatos().getNcanibales()-1,false));
                if(posible != null)
                    siguientes.add(new Successor("Cruza un misionero y un canibal desde la orilla izquierda. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Derecha )",posible));                
            }else{
                posible = this.crearSiguienteEstado(actual, new Orilla(actual.getDatos().getNmisioneros()+1,actual.getDatos().getNcanibales()+1,true));
                if(posible != null)
                    siguientes.add(new Successor("Cruza un misionero y un canibal desde la orilla derecha. Estado: ( "+posible.getDatos().getNmisioneros()+" , "+posible.getDatos().getNcanibales()+" , Izquierda )",posible));
            }
        }
        posible = null;
        return siguientes;
    }

    private boolean estadoNoProhibido(Orilla datos) {
        boolean resultado = true;
        int i = 0;
        while(resultado && (i < this.getEstadosProhibidos().getEstadosProhibidos().size())){
            if ((this.getEstadosProhibidos().getEstadosProhibidos().get(i).getNmisioneros() == datos.getNmisioneros())
              &&(this.getEstadosProhibidos().getEstadosProhibidos().get(i).getNcanibales() == datos.getNcanibales())
              &&(this.getEstadosProhibidos().getEstadosProhibidos().get(i).getCanoa() == datos.getCanoa()))                
                resultado = false;
            i++;
        }
        return resultado;
    }
}