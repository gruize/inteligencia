package practica3b.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;


/**
 *
 * @author e0s87
 */
public class Codificador {
    
    public static String getEncoded(String texto) {
        String output="";
        try {

            byte[] textBytes = texto.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(textBytes);
            byte[] codigo = md.digest();
            output = new String(codigo);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Codificador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;

    }
}
