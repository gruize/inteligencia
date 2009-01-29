package conector;

//TODO: Permitir que se generen ciclos. Para poner a prueba las busquedas.

import universo.util.Enlace;
import universo.util.Nodo;
import universo.util.Tipo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class generaUniverso {
	
	private Hashtable<Integer, Nodo> universo = null;
	private boolean primero = false;
	private final Integer planetas = 216;
	private final Integer destinos = 4;
	private final Integer rangoDest = 13;
	private final Integer probJuegos = 33;
	
	private Integer dameJuego(){
		Integer random;
		switch( (int)(Math.random()*probJuegos)){
			case 1:
				random = 1;
				break;
			case 2:	
				random = 2;
				break;
			case 3:	
				random = 3;
				break;
			case 4:	
				random = 4;
				break;
			case 5:	
				random = 5;
				break;
			case 6:	
				random = 6;
				break;
			case 7:	
				random = 7;
				break;
			case 8:	
				random = 8;
				break;
			case 9:	
				random = 9;
				break;
			case 10:	
				random = 10;
				break;
			case 11:
				random = 11;
				break;
			case 12:
				random = 12;
				break;
			default:
				random = null;
		}
		return random;
	}
	
	public generaUniverso(){
		universo = new Hashtable<Integer, Nodo>();
	}
	public void generar() throws IOException{
		
		for(int i = 0; i<planetas;i++){
			//TODO: Hacer un generador de nombres.
			Nodo tmp = new Nodo();
			tmp.setId(i);
			if ( i == 0 )
				tmp.setTipo(Tipo.ORIGEN);
			else if ( i >= planetas - destinos)
				tmp.setTipo(Tipo.DESTINO);
			else
				tmp.setTipo(Tipo.NORMAL);
			
			universo.put(i,tmp);
		}
	
		for(int i = 0; i<planetas-destinos;i++){
			//Math.random()*100
			/**
			 * Parametros:
			 * 		Numero de planetas: random
			 * 		Probabilidad de tener enlace: 60%
			 * 		Distancia de 0 a 100;
			 * 		Probabilidad de tener juego: 20%
			 * 
			 */
			String enlacesHechos = "";
			//60% de tener enlaces.
			
			Nodo nodoTemp = new Nodo();
			
			nodoTemp.setId(universo.get(i).getId());
			nodoTemp.setNombre(universo.get(i).getNombre());
			nodoTemp.setTipo(universo.get(i).getTipo());
			
			while( ( (int)(Math.random()*100) < 75 ) || ( !primero ) ) {
				Enlace newEnlace = new Enlace();
				
				Integer destino = Integer.valueOf( ((int)(Math.random()*(planetas)) % (rangoDest)) + i);
				Integer distancia = Integer.valueOf((int)(Math.random()*100));
				Integer juego = dameJuego(); 
				
				newEnlace.setDestino(destino);
				newEnlace.setDistancia(distancia);
				newEnlace.setJuego(juego);
				
				// Solo añadimos el enlace si no lo hemos añadido ya.
				// Nos aseguramos que no se meta el mismo nodo, aunque probablemente no pasa nada.
				if ( ( enlacesHechos.indexOf(String.valueOf(destino)) <= 0) && (i != destino) && (destino < planetas) ){
					enlacesHechos += String.valueOf(destino)+" ";
					nodoTemp.addEnlaces(newEnlace);	
				}
				
				if ( (nodoTemp.getEnlaces().size() > 4 ) && (!primero) ){
					//Ya tenemos enlaces para el primer nodo.
					primero = true;
				}
			}
			universo.put(i, nodoTemp);
		}
		// Ver si los destinos estan enlazados.
		for(int i = planetas-rangoDest; i<planetas-destinos;i++){
			Nodo nodoAux = universo.get(i);
			
			Set<Integer> set = nodoAux.getEnlaces().keySet();
		    Iterator<Integer> itr = set.iterator();
		    Integer inte;
		    while (itr.hasNext()) {
		    	inte = itr.next();
			    if ( inte >= planetas-destinos){
			    	System.out.println("Hay enlace con el planeta: "+ i + "y el planeta destino: " +nodoAux.getEnlaces().get(inte).getDestino() );
			    }
		    }
		}
		// Generamos el archivo.
		
		String sFichero = "planetas.txt";

		File fichero = new File(sFichero);
		if (fichero.exists()) {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
			
			for(int i = 0; i<planetas;i++){
				Nodo nodoPrint = this.universo.get(i);
				//Escribimos el tipo, nombre e id.	    	
		    	if (nodoPrint.getTipo() == Tipo.ORIGEN){
		    		bw.write("[O]|");
		    	}else if (nodoPrint.getTipo() == Tipo.DESTINO){
		    		bw.write("[D]|");
		    	}
		    	bw.write("Nombre|");
		    	bw.write(nodoPrint.getId() + "|");
		    	
		    	// Escribimos los enlaces.
				Set<Integer> set = nodoPrint.getEnlaces().keySet();
			    Iterator<Integer> itr = set.iterator();
			    Integer inte;
			    while (itr.hasNext()) {
			    	inte = itr.next();
			    	bw.write("[");
			    	if (nodoPrint.getEnlaces().get(inte).getDestino() != null ){
			    		bw.write(nodoPrint.getEnlaces().get(inte).getDestino().toString());
			    	}
			    	bw.write(",");
			    	if (nodoPrint.getEnlaces().get(inte).getJuego() != null ){
			    		bw.write(nodoPrint.getEnlaces().get(inte).getJuego().toString());
			    	}
			    	bw.write(",");
			    	if (nodoPrint.getEnlaces().get(inte).getDistancia() != null ){
			    		bw.write(nodoPrint.getEnlaces().get(inte).getDistancia().toString());
			    	}
			    	bw.write("]");
			    }
			    bw.write("\n");
		    }
			bw.close();
		}

	}
	
}
