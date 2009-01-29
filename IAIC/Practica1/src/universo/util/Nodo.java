package universo.util;

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
    public double obtenerValorHeuristico(Hashtable<Integer, Nodo> objetivos, Hashtable<Integer, Nodo> nodosH){
        Double valor = null, aux = 0.0;
        Enumeration<Enlace> caminos = this.getEnlaces().elements();
        Enumeration<Integer> destinos;
        Enlace ruta;
        while(caminos.hasMoreElements()){            
            ruta = caminos.nextElement();
            destinos = objetivos.keys();
            while(destinos.hasMoreElements()){
                if(destinos.nextElement() != caminos.nextElement().getDestino())
                    aux = aux + ruta.getDistancia() + nodosH.get(ruta.getDestino()).obtenerValorHeuristico(objetivos, nodosH);
            }
            if(valor == null)
                valor = aux;
            if(valor > aux)
                valor = aux;
        }
        return valor;        
    }            
}
