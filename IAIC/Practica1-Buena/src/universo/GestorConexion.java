package universo;

import java.io.FileNotFoundException;
import java.io.IOException;

import universo.util.exceptions.BadFormattedFile;
import conector.Conector;
import conector.util.exceptions.BadUniFileException;

public class GestorConexion {
	
	private static Conector planetas = null;
	private static String file = ""; // Archivo de carga del universo
	
    /**
     * 
     * GetInstancia para los casos en los que no se desea cambiar
     * el universo
     * 
     * @return
     * @throws Exception
     */
    public static Conector getInstancia() {
        if (planetas == null) {
			try {
				planetas = new Conector(GestorConexion.file);
			} catch (FileNotFoundException e) {
				System.out.println("No se encuentra el archivo especificado.");
				planetas = null;
			} catch (IOException e) {
				System.out.println("No se encuentra el archivo especificado.");
				planetas = null;
			} catch (BadFormattedFile e) {
				System.out.println("El fichero de entrada es de un formato desconocido.");
				planetas = null;
			} catch (BadUniFileException e) {
				System.out.println("Error en el archivo de universo, no cumple las normas.");
				planetas = null;
			}   
        }
        return planetas;
    }
    
    /**
     * 
     * GetInstancia para la primera llamada al conector o
     * para cambiar el universo que este cargado en ese momento.
     * 
     * @param file
     * @return
     * @throws Exception
     */
    
    public static Conector getInstancia(String file) {
        try {
			planetas = new Conector(file);
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo especificado.");
			planetas = null;
		} catch (IOException e) {
			System.out.println("No se encuentra el archivo especificado.");
			planetas = null;
		} catch (BadFormattedFile e) {
			System.out.println("El fichero de entrada es de un formato desconocido.");
			planetas = null;
		} catch (BadUniFileException e) {
			System.out.println("Error en el archivo de universo, no cumple las normas.");
			planetas = null;
		}   
        return planetas;
    }

	public static String getFile() {
		return file;
	}
	public static void setFile(String file) {
		GestorConexion.file = file;
	}

}
