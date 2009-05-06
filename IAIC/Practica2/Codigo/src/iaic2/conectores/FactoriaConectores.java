/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iaic2.conectores;

/**
 *
 * @author usuario_local
 */
public class FactoriaConectores extends FactoriaConectoresAbs {

    protected FactoriaConectores(){}

    public ConectorTecnico generaConectorTecnico() {
        return new ConectorTecnico();
    }
    public ConectorCoaching generaConectorCoaching() {
        return new ConectorCoaching();
    }
}
