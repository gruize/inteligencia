package juegos.BlancasNegras;

import java.util.Vector;

/**
 * @author Grupo C15
 */
public class MomentosObjetivo {

    private Vector<Momento> momentosObjetivo;

    /**
     * Constructor por defecto
     */
    public MomentosObjetivo(){
        this.momentosObjetivo = new Vector<Momento>();
                /**
         * Posibles estados objetivo
         * Objetivo 1: (B,B,B,N,N,N, )
         * Objetivo 2: ( ,B,B,B,N,N,N)
         * Objetivo 3: (B,B,B, ,N,N,N)
         */
        Fichas[] fin1 = new Fichas[7];
        Fichas[] fin2 = new Fichas[7];
        Fichas[] fin3 = new Fichas[7];
        for(int i = 0; i < 3; i++){
            fin1[i] = Fichas.Blanca;
            fin2[i] = Fichas.Blanca;
            fin3[i] = Fichas.Blanca;
        }
        for(int i = 3; i < 7; i++){
            fin1[i] = Fichas.Negra;
            fin2[i] = Fichas.Negra;
            fin3[i] = Fichas.Negra;            
        }
        fin1[6] = Fichas.Vacia;
        fin2[0] = Fichas.Vacia;
        fin2[3] = Fichas.Blanca;
        fin2[6] = Fichas.Negra;
        fin3[3] = Fichas.Vacia;
        fin3[6] = Fichas.Negra;
        this.momentosObjetivo.add(new Momento(6,fin1));
        this.momentosObjetivo.add(new Momento(0,fin2));
        this.momentosObjetivo.add(new Momento(3,fin3));
    }    
    
    public Vector<Momento> getMomentosObjetivo() {
        return momentosObjetivo;
    }

    public void setMomentosObjetivo(Vector<Momento> momentosObjetivo) {
        this.momentosObjetivo = momentosObjetivo;
    }
    

    
}
