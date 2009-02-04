/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegos.Canibal;

import java.util.Vector;

/**
 * Estados a los que nunca podra llegar.
 * @author Grupo C15
 */
public class EstadosProhibidos {

    private Vector<Orilla> estadosProhibidos;

    public Vector<Orilla> getEstadosProhibidos() {
        return estadosProhibidos;
    }

    public void setEstadosProhibidos(Vector<Orilla> estadosProhibidos) {
        this.estadosProhibidos = estadosProhibidos;
    }
    
    /**
     * Constructor por defecto
     */
    public EstadosProhibidos() {
        this.estadosProhibidos = new Vector<Orilla>();
        this.estadosProhibidos.add(new Orilla(1,0,true));
        this.estadosProhibidos.add(new Orilla(1,2,true));
        this.estadosProhibidos.add(new Orilla(1,3,true));
        this.estadosProhibidos.add(new Orilla(2,0,true));        
        this.estadosProhibidos.add(new Orilla(2,1,true));
        this.estadosProhibidos.add(new Orilla(2,3,true));
        this.estadosProhibidos.add(new Orilla(1,0,false));
        this.estadosProhibidos.add(new Orilla(1,2,false));
        this.estadosProhibidos.add(new Orilla(1,3,false));
        this.estadosProhibidos.add(new Orilla(2,0,false));
        this.estadosProhibidos.add(new Orilla(2,1,false));
        this.estadosProhibidos.add(new Orilla(2,3,false));        
        
    }

}
