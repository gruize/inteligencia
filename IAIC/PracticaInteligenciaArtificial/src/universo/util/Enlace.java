package universo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import juegos.Juego;
import universo.util.factoria.FactoriaJuegosAbs;

/**
 * 
 * Esta clase modela el enlace que existe entre un nodo y otro.
 * 
 * 	Atributos:
 * 		- Id del destino.
 * 		- Juego que hay que superar para llegar al destino, en caso de existir. 
 * 		- Distancia que separa a los dos planetas.
 * 
 * @author Kenzitron
 *
 */

public class Enlace {
    private Integer destino;
    private Integer juego;
    private Integer distancia;

    public Integer getDestino() {
            return destino;
    }
    public void setDestino(Integer destino) {
            this.destino = destino;
    }
    public Integer getJuego() {
            return juego;
    }
    public void setJuego(Integer juego) {
            this.juego = juego;
    }
    public void setDistancia(Integer distancia) {
            this.distancia = distancia;
    }
    public Integer getDistancia() {
            return distancia;
    }
    
    /**
     * 
     * Mira el fichero de configuracion de juegos y basandose en el id del juego que tiene el enlace
     * devuelve un String que lo caracterice mejor y se llama a la factoria de juegos para que
     * devuelva una instancia de ese juego a traves de la interfaz Juego.java
     * 
     * @return Juego
     * @throws IOException
     */
    
    public Juego dameJuego() throws IOException{
        FileInputStream prop = new FileInputStream("conf/juegos.conf");
        Properties propiedades = new Properties();
        propiedades.load(prop);
        prop.close();     
        String juego = String.valueOf( propiedades.getProperty(String.valueOf(this.juego)));
        return FactoriaJuegosAbs.getInstancia().dameJuego(juego);        
    }
}

