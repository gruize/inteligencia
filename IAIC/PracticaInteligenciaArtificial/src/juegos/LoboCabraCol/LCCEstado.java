package juegos.LoboCabraCol;

import java.util.Vector;

public class LCCEstado {
	
	private Vector<Contenido> historia;
	private Contenido estado;

	/**
	 * Constructor por defecto
	 */
	public LCCEstado(){
		estado=new Contenido(true,true,true,true);
		 historia=new Vector<Contenido> ();
		 historia.add(estado);
	 }

	/**
	 * Constructor parametrizado
	 * @param est Estado
	 * @param his Historia
	 */
	public LCCEstado (Contenido est,Vector<Contenido> his){
		estado=est;
		historia=his;
		historia.add(estado);
	}
		
	public boolean isFinal(){
		return estado.equals(new Contenido(false,false,false,false));
	}
	
	public Contenido getEstado() {
		return estado;
	}

	public void setEstado(Contenido estado) {
		this.estado = estado;
	}

	public Vector<Contenido> getHistoria() {
		return historia;
	}

	public void setHistoria(Vector<Contenido> historia) {
		this.historia = historia;
	}	
}