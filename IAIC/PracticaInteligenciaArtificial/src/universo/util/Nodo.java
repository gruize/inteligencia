package universo.util;

import java.util.Hashtable;

import universo.GestorConexion;

/**
 * 
 * Representacion de un planeta.
 * 		- Identificador unico.
 * 		- Un nombre asociado (Es una pijada ponerle nombre).
 * 		- Tipo para reconocer si hemos llegado al final.
 * 		- Enlaces de los planetas con los que está unido.
 * 
 * @author Kenzitron
 *
 */

public class Nodo {
	
    private Integer id;
    private String nombre;
    private Integer tipo;
    private Hashtable<Integer, Enlace> enlaces;

    /**
     * Inicializacion de la clase, se crea la HashTable de enlaces.
     */
    
    public Nodo(){
        enlaces = new Hashtable<Integer, Enlace>();
    }
    
    /**
     * Otro constructor de la clase, este recibe todos los parametros del nodo.
     * @param id -> Identificador del planeta
     * @param nombre -> Nombre asociado al planeta
     * @param tipo -> Tipo de planeta
     * @param enlaces -> Enlaces que tiene el planeta
     */

    public Nodo(Integer id, String nombre, Integer tipo, Hashtable<Integer, Enlace> enlaces) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.enlaces = enlaces;
    }
    
    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }
    public String getNombre() {
            return nombre;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
    public Hashtable<Integer, Enlace> getEnlaces() {
            return enlaces;
    }
    public void setEnlaces(Hashtable<Integer, Enlace> enlaces) {
            this.enlaces = enlaces;
    }
    public void addEnlaces(Enlace enlacetmp){
            this.enlaces.put(enlacetmp.getDestino(),enlacetmp);
    }
    public Integer getTipo() {
            return tipo;
    }
    public void setTipo(Integer tipo) {
            this.tipo = tipo;
    }
    @Override
    public boolean equals(Object obj) {
        Nodo temp = (Nodo)obj;
        return ((this.getId() == temp.getId()) && (this.getNombre() == temp.getNombre()));
    }          
    
    /**
     * 
     * Funcion Heuristica del nodo.
     * 
     * @return
     * @throws Exception
     */
    
    public double obtenerValorHeuristico() throws Exception {
    	
        /**
         * Ya que trabajamos con valores enteros, los decimales resultan utiles para
         * distinguir si es el valor de inicializacion o un valor obtenido mediante
         * el procedimiento.
         */
    	
    	double valor = GestorConexion.getInstancia().getNodosH().size() - this.getId(); 
        return valor;        
    }            
}
