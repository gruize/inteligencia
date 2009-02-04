package juegos.Pollos;

/**
 *
 * @author GabiPC
 */
public class Momento {

    final int coordenadaX = 2;
    final int coordenadaY = 5;
    
    private Valores [][] valor;
    
    /**
     * Constructor por defecto
     */
    public Momento(){
        this.valor = new Valores[coordenadaX][coordenadaY];
        for(int i = 0; i < coordenadaX; i++)
            for(int j = 0; j < coordenadaY; j++)
                this.valor[i][j] = Valores.Blanco;
    }
    
    /**
     * Constructor parametrizado
     * @param padre Momento anterior
     * @param x Nuevo valor de la coordenada X
     * @param y Nuevo valor de la coordenada Y
     */
    public Momento(Momento padre, int x, int y){
        this.valor = new Valores[coordenadaX][coordenadaY];
        for(int i = 0; i < coordenadaX; i++)
            for(int j = 0; j < coordenadaY; j++)
                this.valor[i][j] = padre.getValor(i, j);
        this.valor[x][y] = this.obtenerSucesor(padre.getValor(x,y));
        if(x - 1 >= 0)
            this.valor[x-1][y] = this.obtenerSucesor(padre.getValor(x - 1, y));
        if(x + 1 < coordenadaX)
            this.valor[x+1][y] = this.obtenerSucesor(padre.getValor(x + 1, y));
        if(y - 1 >= 0)
            this.valor[x][y-1] = this.obtenerSucesor(padre.getValor(x, y - 1));
        if(y + 1 < coordenadaY)
            this.valor[x][y+1] = this.obtenerSucesor(padre.getValor(x, y + 1));
    }

    public Valores[][] getValor() {
        return valor;
    }

    public void setValor(Valores[][] valor) {
        this.valor = valor;
    }
    
    public Valores getValor(int x, int y){
        return this.valor[x][y];
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean iguales = true;
        int i = 0, j = 0;
        while(iguales && (i < coordenadaX)){
            while(iguales && (j < coordenadaY)){
                if(this.getValor(i, j) != ((Momento)obj).getValor(i, j))
                    iguales = false;
                j++;
            }
            i++;
            j = 0;
        }
        return iguales;
    }  
    
    /**
     * Genera el coste de ir desde el momento actual a otro pasado por parametro.
     * Se utiliza la distancia manhattan
     * @param instante Momento destino
     * @return
     */
    public double generarCoste(Momento instante) {
        double coste = 0.0;
        for(int i = 0; i < coordenadaX; i++)
            for(int j = 0; j < coordenadaY; j++)
                if(this.getValor(i, j) != instante.getValor(i, j))
                    coste++;
        return coste;						
    }

    /**
     * Devuelve el valor heuristico, segun la evolucion del huevo la heuristica aumenta
     * o disminuye. 
     * El orden de mayor a menor es: Blanco --> Azul --> Rojo --> Pollito (0)
     * @return
     */
    public double generarHeuristica() {
        double heuristica = 0.0;
        for(int i = 0; i < coordenadaX; i++)
            for(int j = 0; j < coordenadaY; j++){
                if(this.getValor(i, j) == Valores.Rojo)
                    heuristica+=1;
                if(this.getValor(i, j) == Valores.Azul)
                    heuristica+=2;
                if(this.getValor(i, j) == Valores.Blanco)
                    heuristica+=3;
            }
        return heuristica;
    }

    public Valores obtenerSucesor(Valores valor) {
        Valores siguiente = null;
        if(valor == Valores.Azul)
            siguiente = Valores.Rojo;
        else{
            if(valor == Valores.Blanco)
                siguiente = Valores.Azul;
            else{
                if(valor == Valores.Pollito)
                    siguiente = Valores.Blanco;
                else
                    siguiente = Valores.Pollito;                
            }

        }
        return siguiente;
    }

    @Override
    public String toString() {
        String granja = "";
        for(int i = 0; i < coordenadaX; i++){
            for(int j = 0; j < coordenadaY; j++){
                switch(this.getValor(i, j)){
                    case Azul: granja+= "| A |"; break;
                    case Blanco: granja+= "| B |"; break;
                    case Rojo: granja+= "| R |"; break;
                    case Pollito: granja+= "| P |"; break;
                }
            }
            granja+="\n";
        }            
        return granja;
    }

    
}
