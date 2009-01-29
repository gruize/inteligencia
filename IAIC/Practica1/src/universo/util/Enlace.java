package universo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import universo.util.factoria.factoriaJuegosAbs;

import juegos.Juego;

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
    public Juego dameJuego() throws IOException{
        FileInputStream prop = new FileInputStream("juegos.conf");
        Properties propiedades = new Properties();
        propiedades.load(prop);
        prop.close();     
        String juego = String.valueOf( propiedades.getProperty(String.valueOf(this.juego)));
        return factoriaJuegosAbs.getInstancia().dameJuego(juego);        
    }
}

