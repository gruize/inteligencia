/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iaic2.conectores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

public class ConectorTecnico {
    private Hashtable<String, Vector<String>> propiedades = null;
    private Hashtable<String,Vector<String>> webs = null;

    public ConectorTecnico(){
        this.propiedades = new Hashtable<String, Vector<String>>();
        this.webs = new Hashtable<String,Vector<String>>();
    }
    
    public Hashtable<String, Vector<String>> getPropiedades() {
        return propiedades;
    }

    public Hashtable<String, Vector<String>> getCiertasWebs(Vector<String> dondeBuscar) {
        Hashtable<String, Vector<String>> tmp = null;
        if ( dondeBuscar != null ){
            tmp = new Hashtable<String, Vector<String>>();
            for (String web: dondeBuscar){
                tmp.put(web, this.webs.get(web));
            }
        }
        return tmp;
    }

    public void loadConfigFile(){

        try {
            BufferedReader entrada;
            String linea;
            String tipoCV="";
            Boolean leemos = false;
            Boolean leemosWebs = false;
            Vector<String> descripciones = new Vector<String>();

            entrada = new BufferedReader(new FileReader(new File("config/AsesorTecnico.conf")));
            while ((linea = entrada.readLine()) != null) {
                //Cargamos las URL para cada tipo de busqueda de trabajo.
                if (linea.contains("[end]") && leemosWebs ){
                    leemosWebs = false;
                    leemos = true;
                    //Consumimos una linea para ajustar el parseo
                    linea = entrada.readLine();
                }
                if (leemosWebs){
                    // Procesamos las lineas que vengan
                    String aux[] = linea.split("=");
                    String aux2[] = aux[1].split(",");
                    Vector<String> tmp = new Vector<String>();
                    for(String web: aux2){
                        tmp.add(web);
                    }
                    this.webs.put(aux[0],tmp);
                }
                if (linea.contains("[Webs]")){
                    leemosWebs = true;
                }
                if (leemos){
                    //Procesamos todas las lineas
                    if (!linea.contains("[end]")){
                        //No estamos en un fin.
                        if (linea.contains("TipoCV")){
                            //Leemos la cabecera del tipoCV
                            String tmp[]= linea.split("=");
                            tipoCV=tmp[1].substring(0, tmp[1].length()-1);

                            //Creamos el nuevo vector que vamos a meter dentro
                            descripciones = new Vector<String>();
                        }else{
                            //No es inicio de Consejos asi que cogemos las frases
                            descripciones.add(linea);
                        }
                    }else{
                        // Estamos en [end] hay que meter
                        // todo lo extraido en su sitio.
                        this.propiedades.put(tipoCV,descripciones);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            //Utilizar gestion de Excepciones
            System.out.println("Capturamos las excepciones");
        } catch (IOException ex) {
            //Utilizar gestion de Excepciones
            System.out.println("Capturamos las excepciones");
        }
    }
}
