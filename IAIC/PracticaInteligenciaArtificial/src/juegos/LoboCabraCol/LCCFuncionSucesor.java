package juegos.LoboCabraCol;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class LCCFuncionSucesor implements SuccessorFunction{

	private EstadosProhibidos proh;

	/**
	 * Constructor por defecto
	 */
	public LCCFuncionSucesor(){
		proh = new EstadosProhibidos();
	}

	/**
	 * Devuelve un string con la accion que se realiza
	 * @param actual
	 * @return
	 */
    public String generarAccion(LCCEstado actual){
        String accion = "";
        if(actual.getEstado().getGranjero())
            accion+="Granjero Izquierda, ";
        else
            accion+="Granjero Derecha, ";
        if(actual.getEstado().getLobo())
            accion+="Lobo Izquierda, ";
        else
            accion+="Lobo Derecha, ";
        if(actual.getEstado().getCabra())
            accion+="Cabra Izquierda, ";
        else
            accion+="Cabra Derecha, ";
        if(actual.getEstado().getCol())
            accion+="Col Izquierda )";
        else
            accion+="Col Derecha )";
        return accion;
    }
    
    @Override
	public List<Successor> getSuccessors(Object state) {
		List<Successor> successors = new ArrayList<Successor>();
		LCCEstado board = (LCCEstado) state;
		LCCEstado child = null;
		//solo
		if(board.getEstado().getGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,board.getEstado().getLobo(),board.getEstado().getCabra(),board.getEstado().getCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Solo desde la izquierda : ( "+this.generarAccion(child), child));
                    }                        
                }else{ 
                    child = siguienteMovimiento(board,(new Contenido(true,board.getEstado().getLobo(),board.getEstado().getCabra(),board.getEstado().getCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Solo desde la derecha : ( "+this.generarAccion(child), child));
                    }
                }
		child = null;
		//Lobo
		if(board.getEstado().getGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,false,board.getEstado().getCabra(),board.getEstado().getCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Lobo desde la izquierda : ( "+this.generarAccion(child), child));
                    }
                }else{
                    child = siguienteMovimiento(board,(new Contenido(true,true,board.getEstado().getCabra(),board.getEstado().getCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Lobo desde la derecha : ( "+this.generarAccion(child), child));
                    }                    
                }                
		child = null;
		//Cabra
		if(board.getEstado().getGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,board.getEstado().getLobo(),false,board.getEstado().getCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Cabra desde la izquierda : ( "+this.generarAccion(child), child));
                    }
                }else{
                    child = siguienteMovimiento(board,(new Contenido(true,board.getEstado().getLobo(),true,board.getEstado().getCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Cabra desde la derecha : ( "+this.generarAccion(child), child));
                    }                         
                }
		child = null;
		//col
		if(board.getEstado().getGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,board.getEstado().getLobo(),board.getEstado().getCabra(),false)));
                    if (child != null){
                        successors.add(new Successor("Cruzar Col desde la izquierda : ( "+this.generarAccion(child), child));
                    }                        
                }else{
                    child = siguienteMovimiento(board,(new Contenido(true,board.getEstado().getLobo(),board.getEstado().getCabra(),true)));
                    if (child != null){
                        successors.add(new Successor("Cruzar Col desde la derecha : ( "+this.generarAccion(child), child));
                    }                         
                }
		child = null;
		
		return successors;
	}
	
	/**
	 * Genera el siguiente estado, comprobando antes que sea valido y posible
	 * @param padre Estado actual
	 * @param estado Posible siguiente estado
	 * @return Nuevo estado
	 */
    public LCCEstado siguienteMovimiento(LCCEstado padre,Contenido estado){
		if(!permiteMovimiento(estado))
			return null;
		else{
			Vector<Contenido> historia = padre.getHistoria();
			LCCEstado hijo = new LCCEstado(estado,historia);
			return hijo;
		}
		
	}
	
    /**
     * Indica si un estado puede crearse si no es igual a ningun estado peligroso
     * @param estado Contenido
     * @return	True Si es posible
     * 			False En cualquier otro caso
     */
	public Boolean permiteMovimiento(Contenido estado){
		Boolean puede = true;
		for( Contenido cont: proh.getEstadosPeligrosos() ){
			if ( (cont.getCol() != estado.getCol()) && (cont.getCabra() != estado.getCabra()) && (cont.getGranjero() != estado.getGranjero()) && (cont.getLobo() != estado.getLobo()) )
				puede = false;
		}
		return puede;
	}
	
}