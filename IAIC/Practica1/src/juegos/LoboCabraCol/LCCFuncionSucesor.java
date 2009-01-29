package juegos.LoboCabraCol;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class LCCFuncionSucesor implements SuccessorFunction{

	private EstadosProhibidos proh;

	public LCCFuncionSucesor(){
		proh = new EstadosProhibidos();
	}

        public String generarAccion(LCCEstado actual){
            String accion = "";
            if(actual.getEstado().isGranjero())
                accion+="Granjero Izquierda, ";
            else
                accion+="Granjero Derecha, ";
            if(actual.getEstado().isLobo())
                accion+="Lobo Izquierda, ";
            else
                accion+="Lobo Derecha, ";
            if(actual.getEstado().isCabra())
                accion+="Cabra Izquierda, ";
            else
                accion+="Cabra Derecha, ";
            if(actual.getEstado().isCol())
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
		if(board.getEstado().isGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,board.getEstado().isLobo(),board.getEstado().isCabra(),board.getEstado().isCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Solo desde la izquierda : ( "+this.generarAccion(child), child));
                    }                        
                }else{ 
                    child = siguienteMovimiento(board,(new Contenido(true,board.getEstado().isLobo(),board.getEstado().isCabra(),board.getEstado().isCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Solo desde la derecha : ( "+this.generarAccion(child), child));
                    }
                }
		child = null;
		//Lobo
		if(board.getEstado().isGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,false,board.getEstado().isCabra(),board.getEstado().isCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Lobo desde la izquierda : ( "+this.generarAccion(child), child));
                    }
                }else{
                    child = siguienteMovimiento(board,(new Contenido(true,true,board.getEstado().isCabra(),board.getEstado().isCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Lobo desde la derecha : ( "+this.generarAccion(child), child));
                    }                    
                }                
		child = null;
		//Cabra
		if(board.getEstado().isGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,board.getEstado().isLobo(),false,board.getEstado().isCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Cabra desde la izquierda : ( "+this.generarAccion(child), child));
                    }
                }else{
                    child = siguienteMovimiento(board,(new Contenido(true,board.getEstado().isLobo(),true,board.getEstado().isCol())));
                    if (child != null){
                        successors.add(new Successor("Cruzar Cabra desde la derecha : ( "+this.generarAccion(child), child));
                    }                         
                }
		child = null;
		//col
		if(board.getEstado().isGranjero()){
                    child = siguienteMovimiento(board,(new Contenido(false,board.getEstado().isLobo(),board.getEstado().isCabra(),false)));
                    if (child != null){
                        successors.add(new Successor("Cruzar Col desde la izquierda : ( "+this.generarAccion(child), child));
                    }                        
                }else{
                    child = siguienteMovimiento(board,(new Contenido(true,board.getEstado().isLobo(),board.getEstado().isCabra(),true)));
                    if (child != null){
                        successors.add(new Successor("Cruzar Col desde la derecha : ( "+this.generarAccion(child), child));
                    }                         
                }
		child = null;
		
		return successors;
	}
	
	private LCCEstado siguienteMovimiento(LCCEstado padre,Contenido estado){
		if(!permiteMovimiento(estado))
			return null;
		else{
			Vector<Contenido> historia = padre.getHistoria();
			LCCEstado hijo = new LCCEstado(estado,historia);
			return hijo;
		}
		
	}
	
	private Boolean permiteMovimiento(Contenido estado){
		Boolean puede = true;
		for( Contenido cont: proh.getEstadosPeligrosos() ){
			if ( (cont.isCol() != estado.isCol()) && (cont.isCabra() != estado.isCabra()) && (cont.isGranjero() != estado.isGranjero()) && (cont.isLobo() != estado.isLobo()) )
				puede = false;
		}
		return puede;
	}
	
}