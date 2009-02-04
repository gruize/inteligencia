package juegos.Robot;

import aima.basic.XYLocation;

/**
 *
 * @author Grupo C15
 */
class Momento {

    final int SIZE = 6;
    
    private Recorrido[][] recorrido;
    
    /**
     * Construtor parametrizado e inicial
     * @param origen Posicion inicial del robot
     */
    public Momento(XYLocation origen) {
        this.recorrido = new Recorrido[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                this.recorrido[i][j] = Recorrido.Camino;
            }
        }
        this.recorrido[origen.getXCoOrdinate()][origen.getYCoOrdinate()] = Recorrido.Robot;
        /**
         * Las posiciones de los muros son modificables.
         */
        this.recorrido[0][2] = Recorrido.Muro;
        this.recorrido[4][3] = Recorrido.Muro;
        this.recorrido[5][1] = Recorrido.Muro;
        this.recorrido[1][5] = Recorrido.Muro;
        this.recorrido[4][4] = Recorrido.Muro;
        this.recorrido[1][1] = Recorrido.Muro;
        this.recorrido[1][0] = Recorrido.Muro;
    }
    
    /**
     * Constructor parametrizado
     * @param padre Momento anterior
     * @param posCamino Posicion en la que estaba situado el robot y que vuelve a ser camino     * 
     * @param posRobot Posicion que ocupa actualmente el robot y que antes era camino
     */
    public Momento(Momento padre, XYLocation posCamino, XYLocation posRobot){
        this.recorrido = new Recorrido[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                this.recorrido[i][j] = padre.getRecorrido(i, j);
        this.recorrido[posCamino.getXCoOrdinate()][posCamino.getYCoOrdinate()] = Recorrido.Camino;
        this.recorrido[posRobot.getXCoOrdinate()][posRobot.getYCoOrdinate()] = Recorrido.Robot;
    }

    public Recorrido[][] getRecorrido() {
        return recorrido;
    }
    
    public Recorrido getRecorrido(int x, int y){
        return this.recorrido[x][y];
    }

    public Recorrido getRecorrido(XYLocation pos){
        return this.getRecorrido(pos.getXCoOrdinate(),pos.getYCoOrdinate());
    }
    
    public void setRecorrido(Recorrido[][] recorrido) {
        this.recorrido = recorrido;
    }
    
    /**
     * Devuelve la posicion en la que se encuentra el robor
     * @return XYLocation Posicion de robot
     */
    public XYLocation posRobot(){
	int x = 0, y = 0;
        boolean robot = false;
        XYLocation pos = null;
        while(!robot && (x < SIZE)){
            while(!robot && (y < SIZE)){
                if(this.getRecorrido(x, y) == Recorrido.Robot){
                    robot = true;
                    pos = new XYLocation(x,y);
                }
                y++;
            }
            x++;
            y = 0;            
        }
        return pos;              
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean iguales = true;
        Momento temp = (Momento)obj;
        int i = 0, j;
        while(iguales && (i < SIZE)){
            j = 0;
            while(iguales && (j < SIZE)){
                if(this.getRecorrido(i, j) != temp.getRecorrido(i, j))
                    iguales = false;
                j++;
            }
            i++;
        }
        return iguales;
    }

    @Override
    public String toString() {
        String tablero = "";
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(this.getRecorrido(i, j) == Recorrido.Camino)
                    tablero+="| C |";
                if(this.getRecorrido(i, j) == Recorrido.Muro)
                    tablero+="| M |";
                if(this.getRecorrido(i, j) == Recorrido.Robot)
                    tablero+="| R |";
            }
            tablero+="\n";
        }
        return tablero;
    }

    public int TamannoMaximo(){
        return SIZE;
    }
}
