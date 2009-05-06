/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iaic2.conectores;

/**
 *
 * @author usuario_local
 */
public abstract class FactoriaConectoresAbs {
	
	private static FactoriaConectoresAbs _instancia;
	
	public static FactoriaConectoresAbs getInstancia()
	{
		if (_instancia==null)
		{
			_instancia= new FactoriaConectores();
		}
		return _instancia;
	}
	
	public abstract ConectorTecnico generaConectorTecnico();
        public abstract ConectorCoaching generaConectorCoaching();

}