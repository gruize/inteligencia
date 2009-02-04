package juegos.Puzzle;

/**
 *
 * @author Grupo C15
 */
public class Momento {

    final int TAMANNO = 3;
    private int[][] tablero;
    int xHueco, yHueco;
    
    /**
     * Genera el estado inicial el cual es modificable
     */
    public Momento(){
        this.tablero = new int[this.TAMANNO][this.TAMANNO];
        this.tablero[0][0] = 7;
        this.tablero[0][1] = 2;
        this.tablero[0][2] = 4;
        this.tablero[1][0] = 5;
        this.tablero[1][1] = 0;
        this.tablero[1][2] = 6;
        this.tablero[2][0] = 8;
        this.tablero[2][1] = 3;
        this.tablero[2][2] = 1;
        this.xHueco = 1;
        this.yHueco = 1;
    }
    
    public Momento(Momento momento, int xFin, int yFin){
        this.tablero = new int[this.TAMANNO][this.TAMANNO];
        for(int i = 0; i < this.TAMANNO; i++)
            for(int j = 0; j < this.TAMANNO; j++){
                if((i == momento.getXHueco()) && (j == momento.getYHueco()))
                    this.tablero[i][j] = momento.getTablero(xFin, yFin);
                else{
                    if((i == xFin) && (j == yFin))
                        this.tablero[i][j] = momento.getTablero(momento.getXHueco(), momento.getYHueco());
                    else
                        this.tablero[i][j] = momento.getTablero(i, j);
                }
            }
        this.xHueco = xFin;
        this.yHueco = yFin;
    }

    public int getXHueco() {
        return xHueco;
    }

    public void setXHueco(int xHueco) {
        this.xHueco = xHueco;
    }

    public int getYHueco() {
        return yHueco;
    }

    public void setYHueco(int yHueco) {
        this.yHueco = yHueco;
    }
    
    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    
    public int getTablero(int x, int y){
        return this.tablero[x][y];
    }

    @Override
    public boolean equals(Object momento) {
        boolean iguales = true;
        int i = 0, j = 0;
        while(iguales && (i < this.TAMANNO)){
            while(iguales && (j < this.TAMANNO)){
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
        String tablero = "";
        for(int i = this.TAMANNO - 1; i >= 0; i--){
            for(int j = 0; j < this.TAMANNO; j++)
                tablero+=("| "+this.getTablero(i, j)+" |");            
            tablero+="\n";
        }
        return tablero;
    }

    public double obtenerValorHeuristico(){
        double heuristica = 0.0;
        MomentosObjetivo objetivo = new MomentosObjetivo();
        for(int i = 0; i < this.TAMANNO; i++)
            for(int j = 0; j < this.TAMANNO; j++){
                if(this.getTablero(i, j) != objetivo.getObjetivo(i, j))
                    heuristica++;
            }        
        return heuristica;
    }
}
