package universo;

import conector.Conector;

public class GestorConexion {
	
	private static Conector planetas = null;
	private static String file = "universos/GabiFinal.txt"; // Archivo de carga del universo
	
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
			planetas = new Conector(GestorConexion.file);
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
        if (planetas == null)
            planetas = new Conector(file);
        return planetas;
    }

	public static String getFile() {
		return file;
	}

	public static void setFile(String file) {
		GestorConexion.file = file;
	}

}
