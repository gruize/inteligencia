package juegos.Reinas;

import aima.basic.XYLocation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Grupo C15
 */
class Estado {

    private Reinas reino;
    private int tamanno;
    private int[][] tablero;

    public Estado(Reinas aThis, int i) {
        this.reino = aThis;
        this.tamanno = i;
        this.tablero = new int[i][i];
    }

    public Reinas getReino() {
        return reino;
    }

    public void setReino(Reinas reino) {
        this.reino = reino;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(List<XYLocation> posiciones) {
        this.quitarReinaTodas();
        for(int i = 0; i < posiciones.size(); i++){
            this.agregarReina(posiciones.get(i));
        }
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }

    public void agregarReina(XYLocation pos){
        if(!hayReina(pos))
            this.tablero[pos.getXCoOrdinate()][pos.getYCoOrdinate()] = 1;
    }

    public boolean hayReina(XYLocation pos) {
        return (this.tablero[pos.getXCoOrdinate()][pos.getYCoOrdinate()] == 1);
    }

    public void quitarReina(XYLocation pos){
        if(hayReina(pos))
            this.tablero[pos.getXCoOrdinate()][pos.getYCoOrdinate()] = 0;
    }

    public void modificaReina(XYLocation origen, XYLocation destino){
        if(hayReina(origen) && (!hayReina(destino))){
            this.quitarReina(origen);
            this.agregarReina(destino);
        }
    }

    public void quitarReinaTodas(){
        for(int i = 0; i < this.getTamanno(); i++)
            for(int j = 0; j < this.getTamanno(); j++){
                this.tablero[i][j] = 0;
            }
    }

    public int numeroReinasEnTablero(){
        int contador = 0;
        XYLocation pos;
        for(int i = 0; i < this.getTamanno(); i++)
        for(int j = 0; j < this.getTamanno(); j++){
            pos = new XYLocation(i,j);
            if(this.hayReina(pos))
                contador++;
        }        
        return contador;
    }

    public List<XYLocation> posicionReinas(){
        List<XYLocation> posiciones = new ArrayList<XYLocation>();
        for(int i = 0; i < this.getTamanno(); i++)
        for(int j = 0; j < this.getTamanno(); j++){
            XYLocation pos = new XYLocation(i,j);
            if(this.hayReina(pos))
                posiciones.add(pos);
            pos = null;
        }        
        return posiciones;
    }

    public boolean casillaAtacadaPorVertical(XYLocation pos){
        return (this.numeroAtaquesVertical(pos) > 0);
    }

    public boolean casillaAtacadaPorHorizontal(XYLocation pos){
        return (this.numeroAtaquesHorizontal(pos) > 0);
    }

    public boolean casillaAtacadaPorDiagonal(XYLocation pos){
        return (this.numeroAtaquesDiagonal(pos) > 0);
    }

    public boolean casillaAtacada(XYLocation pos){
        return ((this.casillaAtacadaPorVertical(pos))||(this.casillaAtacadaPorHorizontal(pos))||(this.casillaAtacadaPorDiagonal(pos)));
    }
    
    public int numeroAtaquesDiagonal(XYLocation pos) {
        int ataques = 0;
        //Subiendo hacia la izquierda (x-1)(y-1)
        int x = pos.getXCoOrdinate() - 1;
        int y = pos.getYCoOrdinate() - 1;
        while((x >= 0) && (y >= 0)){
            if(this.hayReina(new XYLocation(x,y)))
                ataques++;
            x--;
            y--;
        }
        //Bajando hacia la izquierda (x+1)(y-1)
        x = pos.getXCoOrdinate() + 1;
        y = pos.getYCoOrdinate() - 1;
        while((x < this.getTamanno() ) && (y >= 0)){
            if(this.hayReina(new XYLocation(x,y)))
                ataques++;            
            x++;
            y--;
        }
        //Subiendo hacia la izquierda (x-1)(y+1)
        x = pos.getXCoOrdinate() - 1;
        y = pos.getYCoOrdinate() + 1;
        while((x >= 0) && (y < this.getTamanno())){
            if(this.hayReina(new XYLocation(x,y)))
                ataques++;            
            x--;
            y++;
        }
        //Bajando hacia la derecha (x+1)(y+1)
        x = pos.getXCoOrdinate() + 1;
        y = pos.getYCoOrdinate() + 1;
        while((x < this.getTamanno()) && (y < this.getTamanno())){
            if(this.hayReina(new XYLocation(x,y)))
                ataques++;            
            x++;
            y++;
        }        
        return ataques;
    }
    
    public int numeroAtaquesHorizontal(XYLocation pos) {
        int ataques = 0;
        for(int i = 0; i < this.getTamanno(); i++){
            if((this.hayReina(new XYLocation(i,pos.getYCoOrdinate())))&&(i != pos.getXCoOrdinate()))
                    ataques++;
        }
        return ataques;
    }

    public int numeroAtaquesVertical(XYLocation pos) {
        int ataques = 0;
        for(int i = 0; i < this.getTamanno(); i++){
            if((this.hayReina(new XYLocation(pos.getXCoOrdinate(),i)))&&(i != pos.getYCoOrdinate()))
                    ataques++;
        }
        return ataques;
    }
    
    public int numeroAtaques(XYLocation pos){
        return (this.numeroAtaquesVertical(pos)+this.numeroAtaquesHorizontal(pos)+this.numeroAtaquesDiagonal(pos));
    }

    public void imprimir(){
        /**
        Puede generar problemas por el String, que podria ser StringBuffer        
        StringBuffer buffer = new StringBuffer();
        for (int row = 0; (row < size); row++) { // row
            for (int col = 0; (col < size); col++) { // col
                if (queenExistsAt(col, row)) {
                    buffer.append(" Q ");
                } else {
                    buffer.append(" - ");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();           
        No utilizamos el metodo ToString() ya que no usamos el StringBuffer
         */
	String tabla = "";
        for(int i = 0; i < this.getTamanno(); i++){
            for(int j = 0; j < this.getTamanno(); j++){
                if(this.hayReina(new XYLocation(i,j)))
                    tabla+="| Q |";
                else
                    tabla+="|   |";
            }
            tabla+="\n";
        }
        System.out.print(tabla);                             
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }
        if ((this == obj) ) {
            return true;
        }
        Estado other = (Estado) obj;
        boolean igual = true;
        List<XYLocation> posicion = this.posicionReinas();
        XYLocation pos;
        int i = 0;
        while(igual && (i < posicion.size())){
            pos = posicion.get(i);
            if(!(other.hayReina(pos)))
                igual = false;
            i++;
        }
        return igual;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        List<XYLocation> posicion = this.posicionReinas();
        for(XYLocation pos : posicion){
            hash = 37 * pos.hashCode();
        }
        return hash;
    }
    
}
