package universo;

/**
 * Clase util para realizar pruebas
 * @author Grupo C15
 */
public class Main {
    public static void main(String[] args) {
       GestorConexion.getInstancia();
       Universo nuevo = new Universo();
       nuevo.ejecutar();
    }        
}
