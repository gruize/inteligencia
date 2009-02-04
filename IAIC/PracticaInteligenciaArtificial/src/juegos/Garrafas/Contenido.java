/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegos.Garrafas;

/**
 *
 * @author Grupo C15
 */
public class Contenido {

    private int contG3;
    private int contG4;

    /**
     * Constructor parametrizado
     * @param contG3
     * @param contG4
     */
    public Contenido(int contG3, int contG4) {
        this.contG3 = contG3;
        this.contG4 = contG4;
    }

    public int getContG3() {
        return contG3;
    }

    public void setContG3(int contG3) {
        this.contG3 = contG3;
    }

    public int getContG4() {
        return contG4;
    }

    public void setContG4(int contG4) {
        this.contG4 = contG4;
    }  
    
}
