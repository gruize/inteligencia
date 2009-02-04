package juegos.Puzzle;

/**
 * Diferentes momentos objetivos a los que se puede llegar desde el estado inicial
 * @author Grupo C15
 */
public class MomentosObjetivo {

    final int TAMANNO = 3;
    private int[][] objetivo;
    
    public MomentosObjetivo(){
        this.objetivo = new int[this.TAMANNO][this.TAMANNO];
        this.objetivo[0][0] = 0;
        this.objetivo[0][1] = 1;
        this.objetivo[0][2] = 2;
        this.objetivo[1][0] = 3;
        this.objetivo[1][1] = 4;
        this.objetivo[1][2] = 5;
        this.objetivo[2][0] = 6;
        this.objetivo[2][1] = 7;
        this.objetivo[2][2] = 8;
    }

    public int getObjetivo(int x, int y){
        return this.objetivo[x][y];
    }
    
    public int[][] getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int[][] objetivo) {
        this.objetivo = objetivo;
    }    
    
}
