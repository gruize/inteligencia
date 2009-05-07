/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica3b.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica3b.utils.Codificador;

/**
 *
 * @author usuario_local
 */
public class crearUsers {

    public String getChar(Integer num){
        String letra = null;
        switch(num){
            case 1:
                letra = "a";
                break;
            case 2:
                letra = "b";
                break;
            case 3:
                letra = "c";
                break;
            case 4:
                letra = "d";
                break;
            case 5:
                letra = "e";
                break;
            case 6:
                letra = "f";
                break;
            case 7:
                letra = "g";
                break;
            case 8:
                letra = "h";
                break;
            case 9:
                letra = "i";
                break;
            case 10:
                letra = "j";
                break;
            case 11:
                letra = "k";
                break;
            case 12:
                letra = "l";
                break;
            case 13:
                letra = "m";
                break;
            case 14:
                letra = "n";
                break;
            case 15:
                letra = "ñ";
                break;
            case 16:
                letra = "o";
                break;
            case 17:
                letra = "p";
                break;
            case 18:
                letra = "q";
                break;
            case 19:
                letra = "r";
                break;
            case 20:
                letra = "s";
                break;
            case 21:
                letra = "t";
                break;
            case 22:
                letra = "u";
                break;
            case 23:
                letra = "v";
                break;
            case 24:
                letra = "w";
                break;
            case 25:
                letra = "x";
                break;
            case 26:
                letra = "y";
                break;
            case 27:
                letra = "z";
                break;

        }
        return letra;
    }
    public crearUsers(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.dat"));

            String nombre = "";
            Random r = new Random(154563288);
            for(int i=1;i<=3000;i++){
                //Generamos un nombre aleatorio
                
                Integer numLetras = r.nextInt(10)+5;

                nombre = "";
                for(int j = 0; j < numLetras; j++){
                    Integer letra =  r.nextInt(27) + 1;
                    nombre = nombre + getChar(letra);
                }

                //Encriptamos la contraseña
                String pass = String.valueOf(i)+String.valueOf(i)+String.valueOf(i)+String.valueOf(i);
                bw.write(i+"::"+nombre+"::"+String.valueOf(pass)+"\n");
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(crearUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void main(String[] args) {
        crearUsers cUs = new crearUsers();
    }
}
