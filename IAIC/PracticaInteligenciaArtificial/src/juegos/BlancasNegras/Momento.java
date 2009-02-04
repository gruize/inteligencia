package juegos.BlancasNegras;

/**
 * @author Grupo C15
 */
public class Momento {
    
	/**
     * Indica en que posicion del array está la ficha vacía
     */    
    private int hueco;
    private Fichas[] fichas;
    
    /**
     * Constructor por defecto
     */
    public Momento() {
        /**
         * Genera el Momento inicial (N,N,N, ,B,B,B)
         */
        this.fichas = new Fichas[7];
        for(int i = 0; i < 3; i++)
            this.fichas[i] = Fichas.Negra;
        this.fichas[3] = Fichas.Vacia;
        for(int i = 4; i < this.fichas.length; i++)
            this.fichas[i] = Fichas.Blanca;
        this.hueco = 3;
    }

    /**
     * Constructor parametrizado
     * @param vacio Posicion del huevo o casilla vacia
     * @param fichas
     */
    public Momento(int vacio, Fichas[] fichas) {
        this.hueco = vacio;
        this.fichas = new Fichas[7];
        this.fichas = fichas.clone();
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual = true;
        Momento comparado = (Momento)obj;
        int i = 0;
        while((igual) && (i < 7)){
            if(comparado.getFichas(i) != this.getFichas(i))
                igual = false;
            i++;
        }
        return igual;
    }

    public Fichas[] getFichas() {
        return fichas;
    }

    public Fichas getFichas(int pos) {
        return this.fichas[pos];
    }    
    
    public void setFichas(Fichas[] fichas) {
        for(int i = 0; i < this.fichas.length; i++)
            this.fichas[i] = fichas[i];
    }

    public int getHueco() {
        return hueco;
    }

    public void setHueco(int hueco) {
        this.hueco = hueco;
    }

    /**
     * Genera el coste que supone el pasar del momento actual al siguiente pasado
     * por paramentro
     * @param instante Posible siguiente momento
     * @return Coste
     */
    public Double generarCoste(Momento instante) {
        double coste = 0.0;
        int posIni = 0, posFin = 0;
        boolean finded = false;
        int i = 0;
        while((!finded) && (i < 9)){
            if(this.getFichas(i) == Fichas.Vacia){
                posIni = i;
                finded = true;
            }
            i++;
        }
        i = 0;
        finded = false;
        while((!finded) && (i < 9)){
            if(instante.getFichas(i) == Fichas.Vacia){
                posFin = i;
                finded = true;
            }
            i++;
        }       
        coste = Math.abs(posIni - posFin)-1;
        if(coste == 0)
            coste = 1;
        return coste;
    }
    
}
