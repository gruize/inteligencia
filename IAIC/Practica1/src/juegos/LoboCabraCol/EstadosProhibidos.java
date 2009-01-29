package juegos.LoboCabraCol;

import java.util.Vector;

public class EstadosProhibidos {
	
	private Vector<Contenido>estadosPeligrosos = new Vector<Contenido> ();

	public EstadosProhibidos(){
		
		// Estados peligrosos
		
		estadosPeligrosos.add(new Contenido(false,true,true,true));
		estadosPeligrosos.add(new Contenido(false,false,true,true));
		estadosPeligrosos.add(new Contenido(false,true,true,false));
		estadosPeligrosos.add(new Contenido(true,true,false,false));
		estadosPeligrosos.add(new Contenido(true,false,false,true));
		estadosPeligrosos.add(new Contenido(true,false,false,false));		
	}
	
	public Vector<Contenido> getEstadosPeligrosos() {
		return estadosPeligrosos;
	}

	public void setEstadosPeligrosos(Vector<Contenido> estadosPeligrosos) {
		this.estadosPeligrosos = estadosPeligrosos;
	}
	
}
