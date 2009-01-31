package conector;

import universo.util.Enlace;
import universo.util.Nodo;
import universo.util.Tipo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class GeneraUniverso {
	
	private Hashtable<Integer, Nodo> universo = null;
	private boolean primero = false;
	private Integer planetas = null;    // Numero de planetas en el Universo
	private Integer destinos = null;     // Numero de planetas destino
	private Integer rangoDest = null;    // Rango para asignar enalces
	private Integer probJuegos = null;  // Probailidad de que haya juego en un enlace
	private Integer topeInicio = null;   // Numero de enlaces para el primer nodo.
	private Integer topeDelante = null;  // Numero de enlaces hacia delante como minimo 
	private Integer topeDetras = null;   // Numero de enlaces hacia detras como minimo
    private String fich = null;
	
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
	
	public GeneraUniverso(String fichero){

		try {
			this.universo = new Hashtable<Integer, Nodo>();
	        this.fich = fichero;
	        Properties propiedades = new Properties();
	        FileInputStream prop;
			prop = new FileInputStream("conf/propiedades.conf");
			propiedades.load(prop);
	        prop.close();	     
	        planetas = Integer.valueOf( propiedades.getProperty("planetas"));
	    	destinos = Integer.valueOf( propiedades.getProperty("destinos"));
	    	rangoDest = Integer.valueOf( propiedades.getProperty("rangoDest"));
	    	probJuegos = Integer.valueOf( propiedades.getProperty("probJuegos"));
	    	topeInicio = Integer.valueOf( propiedades.getProperty("topeInicio"));
	    	topeDelante = Integer.valueOf( propiedades.getProperty("topeDelante"));
	    	topeDetras = Integer.valueOf( propiedades.getProperty("topeDetras"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
			//79& de tener enlaces.
			
			Nodo nodoTemp = new Nodo();
			
			nodoTemp.setId(universo.get(i).getId());
			nodoTemp.setNombre(universo.get(i).getNombre());
			nodoTemp.setTipo(universo.get(i).getTipo());
			
			Integer numEnlaceDelante = 0;
			Integer numEnlaceAtras = 0;
			
			while( ( (int)(Math.random()*100) < 60 ) || ( !primero ) || (( numEnlaceDelante<=topeDelante ) && ( (numEnlaceAtras<=topeDetras) && (i>rangoDest-topeInicio) ) )) {
				Enlace newEnlace = new Enlace();
				// Refinar los rangos para que encuentre casi siempre un destino
				// y no salgan destinos en planetas que no existen.
				// El normal, solo hacia delante Integer.valueOf( (int)(Math.random()*(planetas)) % (rangoDest) + i); 
				Integer destino;
				if ( i < rangoDest ){
					// Solo damos valores hacia delante.
					destino = Integer.valueOf( (int)(Math.random()*(planetas)) % (rangoDest + i ));
				}else if ( i+rangoDest > planetas  ){
					// Nos podemos pasar por arriba
					destino = Integer.valueOf( (int)(Math.random()*(planetas)) % (rangoDest + (planetas-i)) + i -(rangoDest));
				}else{
					//es una situacion normal
					destino = Integer.valueOf( (int)(Math.random()*(planetas)) % (rangoDest*2) + i-(rangoDest));
				}
				Integer distancia = Integer.valueOf((int)(Math.random()*100));
				Integer juego = dameJuego(); 
				
				newEnlace.setDestino(destino);
				newEnlace.setDistancia(distancia);
				newEnlace.setJuego(juego);
				
				// Solo a�adimos el enlace si no lo hemos a�adido ya.
				// Nos aseguramos que no se meta el mismo nodo, aunque probablemente no pasa nada.
				if ( ( enlacesHechos.indexOf(String.valueOf(destino)) < 0) && (i != destino) && (destino < planetas) && (destino > 0) ){
					enlacesHechos += String.valueOf(destino)+" ";
					nodoTemp.addEnlaces(newEnlace);
					if ( i < destino ){
						numEnlaceDelante++;
					}else if ( i > destino ){
						numEnlaceAtras++;
					}
				}
				
				if ( (nodoTemp.getEnlaces().size() >= topeInicio ) && (!primero) ){
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
			    	System.out.println("Hay enlace con el planeta: "+ i + " y el planeta destino: " +nodoAux.getEnlaces().get(inte).getDestino() );
			    }
		    }
		}
		// Generamos el archivo.
		File fichero = new File("universos/"+this.fich);
		if (!fichero.exists()) {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("universos/"+this.fich));
			
			// Introducimos la cabecera del fichero.
			
			bw.write("[Fichero de universo]\n");
			
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
		}else{
			System.out.print("\tYa existe un archivo con ese nombre.");
		}

	}
	public void setFichero(String fich){
            this.fich = fich;
        }
}
