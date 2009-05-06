/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iaic2.conectores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenzitron
 */
public class ConectorCoaching {

    private Hashtable<String, String> consejos = null;

    public Hashtable<String, String> getConsejos() {
        return consejos;
    }

    public ConectorCoaching(){
        this.consejos = new Hashtable<String, String>();
    }
    public void loadConfigFile(){
        FileInputStream prop = null;
        try {
            prop = new FileInputStream("config/Coaching.conf");
            Properties propiedades = new Properties();
            propiedades.load(prop);
            Set<Object> set = propiedades.keySet();
            Iterator<Object> itr = set.iterator();
            String clave;
            while (itr.hasNext()) {
                clave = itr.next().toString();
                this.consejos.put(clave, String.valueOf(propiedades.getProperty(clave)));
            }
        } catch (IOException ex) {
                System.out.println(ex);
        } finally {
            try {
                prop.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
