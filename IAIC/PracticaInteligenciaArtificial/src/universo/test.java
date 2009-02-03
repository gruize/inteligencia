package universo;

import java.util.List;
import java.util.Vector;

import universo.util.UniversoMovie;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> acciones;
		acciones = new Vector<String>();
		acciones.add("Enlazar el planeta Nombre 0 con el planeta Nombre 2 cuya distancia es : 28");
		acciones.add("Enlazar el planeta Nombre 2 con el planeta Nombre 16 cuya distancia es : 85");
		acciones.add("Enlazar el planeta Nombre 16 con el planeta Nombre 30 cuya distancia es : 37");
		acciones.add("Enlazar el planeta Nombre 30 con el planeta Nombre 42 cuya distancia es : 13");
		acciones.add("Enlazar el planeta Nombre 42 con el planeta Nombre 55 cuya distancia es : 16");
		acciones.add("Enlazar el planeta Nombre 55 con el planeta Nombre 57 cuya distancia es : 24");
		acciones.add("Enlazar el planeta Nombre 57 con el planeta Nombre 68 cuya distancia es : 24");
		acciones.add("Enlazar el planeta Nombre 68 con el planeta Nombre 80 cuya distancia es : 28");
		acciones.add("Enlazar el planeta Nombre 80 con el planeta Nombre 88 cuya distancia es : 10");
		acciones.add("Enlazar el planeta Nombre 88 con el planeta Nombre 100 cuya distancia es : 32");
		acciones.add("Enlazar el planeta Nombre 100 con el planeta Nombre 110 cuya distancia es : 4");
		acciones.add("Enlazar el planeta Nombre 110 con el planeta Nombre 116 cuya distancia es : 78");
		acciones.add("Enlazar el planeta Nombre 116 con el planeta Nombre 128 cuya distancia es : 4");
		acciones.add("Enlazar el planeta Nombre 128 con el planeta Nombre 135 cuya distancia es : 95");
		acciones.add("Enlazar el planeta Nombre 135 con el planeta Nombre 136 cuya distancia es : 12");
		acciones.add("Enlazar el planeta Nombre 136 con el planeta Nombre 146 cuya distancia es : 1");
		acciones.add("Enlazar el planeta Nombre 146 con el planeta Nombre 157 cuya distancia es : 16");
		acciones.add("Enlazar el planeta Nombre 157 con el planeta Nombre 168 cuya distancia es : 70");
		acciones.add("Enlazar el planeta Nombre 168 con el planeta Nombre 181 cuya distancia es : 13");
		acciones.add("Enlazar el planeta Nombre 181 con el planeta Nombre 185 cuya distancia es : 6");
		acciones.add("Enlazar el planeta Nombre 185 con el planeta Nombre 197 cuya distancia es : 43");
		acciones.add("Enlazar el planeta Nombre 197 con el planeta Nombre 202 cuya distancia es : 5");
		acciones.add("Enlazar el planeta Nombre 202 con el planeta Nombre 209 cuya distancia es : 38");
		acciones.add("Enlazar el planeta Nombre 209 con el planeta Nombre 212 cuya distancia es : 6");
		acciones.add("Enlazar el planeta Nombre 212 con el planeta Nombre 224 cuya distancia es : 6");
		acciones.add("Enlazar el planeta Nombre 224 con el planeta Nombre 230 cuya distancia es : 48");
		acciones.add("Enlazar el planeta Nombre 230 con el planeta Nombre 241 cuya distancia es : 43");
		acciones.add("Enlazar el planeta Nombre 241 con el planeta Nombre 247 cuya distancia es : 25");
		
		GestorConexion.setFile("universos/Universo1.txt");
		
		new UniversoMovie().representar(acciones);

	}

}
