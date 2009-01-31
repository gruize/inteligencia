package universo.util;

import universo.*;
import java.util.Enumeration;
import java.util.Hashtable;


public class Nodo {
    private Integer id;
    private String nombre;
    private Integer tipo;
    private Hashtable<Integer, Enlace> enlaces;

    public Nodo(){
        enlaces = new Hashtable<Integer, Enlace>();
    }

    public Nodo(Integer id, Integer tipo, Hashtable<Integer, Enlace> enlaces) {
        this.id = id;
        this.tipo = tipo;
        this.enlaces = enlaces;
    }

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
    public double obtenerValorHeuristico() throws Exception{
        /**
         * Ya que trabajamos con valores enteros, los decimales resultan utiles para
         * distinguir si es el valor de inicializacion o un valor obtenido mediante
         * el procedimiento.
         */
    	double valor = 0.1, aux = 0.0;
    	for(int j = 0; j < GestorConexion.getInstancia().getNodosH().size(); j++){
    		if(this.getEnlaces().containsKey(j)){
    			aux = aux + this.getEnlaces().get(j).getDistancia();     			
    			if(valor == 0.1)
    				valor = aux;
    			if(valor > aux)
    				valor = aux;
    		}
    	}
        return valor;        
    }            
}
