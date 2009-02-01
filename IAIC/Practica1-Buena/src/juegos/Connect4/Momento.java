package juegos.Connect4;

/**
 *
 * @author GabiPC
 */
class Momento {

    final int DEFAULT = 4;
    
    private int tamanno;
    private int[][] tablero;
    
    public Momento(){
        this.tamanno = DEFAULT;
        this.tablero = new int[DEFAULT][DEFAULT];
        for(int i = 0; i < DEFAULT; i++)
            for(int j = 0; j < DEFAULT; j++)
                this.tablero[i][j] = 0;
        this.tablero[this.tamanno/3][this.tamanno/5] = 1;
        this.tablero[this.tamanno/5][this.tamanno/3] = 2;
    }
    
    public Momento(int size){
        this.tamanno = size;
        this.tablero = new int[size][size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                this.tablero[i][j] = 0;
        this.tablero[this.tamanno/3][this.tamanno/5] = 1;
        this.tablero[this.tamanno/5][this.tamanno/3] = 2;        
    }
    
    public Momento(Momento momento, int x, int y, int valor){
        this.tamanno = momento.getTamanno();
        this.tablero = new int[momento.getTamanno()][momento.getTamanno()];
        for(int i = 0; i < momento.getTamanno(); i++)
            for(int j = 0; j < momento.getTamanno(); j++)
                this.tablero[i][j] = momento.getTablero(i, j);
        this.setTablero(valor, x, y);
    }

    public int[][] getTablero() {
        return tablero;
    }
    
    public int getTablero(int x, int y){
        return this.tablero[x][y];
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    
    public void setTablero(int valor, int x, int y){
        this.tablero[x][y] = valor;
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }

    @Override
    public boolean equals(Object momento) {
        boolean iguales = true;
        int i = 0, j = 0;
        while(iguales && (i < this.getTamanno())){
            while(iguales && (j < this.getTamanno())){
                if(this.getTablero(i, j) != ((Momento)momento).getTablero(i, j))
                    iguales = false;
                j++;
            }
            i++;
            j = 0;
        }
        return iguales;
    }

    @Override
    public String toString() {
        String resultado = "";
        for(int i = 0; i < this.getTamanno(); i++){
            for(int j = 0; j < this.getTamanno(); j++){
                if(this.getTablero(i, j) == 0)
                    resultado+="|   |";
                if(this.getTablero(i, j) == 1)
                    resultado+="| B |";
                if(this.getTablero(i, j) == 2)
                    resultado+="| N |";
            }
            resultado+= "\n";
        }
        return resultado;
    }
   
}
