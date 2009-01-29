package juegos.CajaColores;

/**
 *
 * @author usuario_local
 */
public class Momento {

    private int tamanno;
    private Colores[][] color;
    
    Momento(int tamanno) {
        this.tamanno = tamanno;
        this.color = new Colores[this.getTamanno()][this.getTamanno()];
        int aux = this.getTamanno()/2;
        for(int i = 0; i < aux + 1; i++)
            for(int j = 0; j < this.getTamanno(); j++)
                color[i][j] = Colores.Rojo;
        for(int i = aux; i < this.getTamanno(); i++)
            for(int j = 0; j < aux ; j++)
                color[i][j] = Colores.Azul;
        for(int i = aux + 1; i < this.getTamanno(); i++)
            for(int j = 0; j < this.getTamanno(); j++)
                color[i][j] = Colores.Azul;
    }

    public Colores getColor(int x, int y) {
        return color[x][y];
    }

    public void setColor(Colores[][] color) {
        this.color = color;
    }

    public int getTamanno() {
        return tamanno;
    }

    public void setTamanno(int tamanno) {
        this.tamanno = tamanno;
    }

    public Momento(int tamanno, Momento padre, int a, int b){
        this.tamanno = tamanno;
        this.color = new Colores[tamanno][tamanno];
        for (int i = 0; i < tamanno; i++)
                for (int j = 0; j < tamanno; j++)
                        this.color[i][j] = padre.color[i][j];
        if(this.color[a][b] == Colores.Azul)
            this.color[a][b] = Colores.Rojo;
        else
            this.color[a][b] = Colores.Azul;            
    }
    
    String aString(){
        String salida = "";
        for (int i = 0; i < this.getTamanno(); i++) {
                salida = salida + "\n";
                for (int j = 0; j < this.getTamanno(); j++)
                        if (this.getColor(i,j) == Colores.Rojo)
                                salida = salida + "R ";
                        else
                                salida = salida + "A ";

        }
        return salida;        
    }
    boolean equals(Momento nuevo){
        boolean igual = true;
        int a = 0, b = 0;
        while(igual && (a < this.getTamanno())){
            for(b = 0; b < this.getTamanno(); b++){
                if(this.getColor(a, b) != nuevo.color[a][b])
                    igual = false;
            }
            b = 0;
            a++;
        }        
        return igual;
    }
    
    double obtenerValorHeuristico(){
        double heuristica = 0.0;
        for(int i = 0; i < this.getTamanno(); i++)
            for(int j = 0; j < this.getTamanno(); j++){
                if((i - 1 >= 0) && (this.getColor(i, j) == this.getColor(i - 1, j)))
                    heuristica+= 1;
                if((i + 1 < this.getTamanno()) && (this.getColor(i, j) == this.getColor(i + 1, j)))
                    heuristica+= 1;
                if((j - 1 >= 0) && (this.getColor(i, j) == this.getColor(i, j - 1)))
                    heuristica+= 1;
                if((j + 1 < this.getTamanno()) && (this.getColor(i, j) == this.getColor(i, j + 1)))
                    heuristica+= 1;
            }        
        return heuristica;
    }
}
