package GUI.dibujos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/** CajaTexto      por Michael Gonzalez. Version 2.2.3 enero 2008 <p>

Es una clase sencilla para leer datos introducidos por teclado,
usando una ventana de texto multilinea<p>

Los valores leidos son del tipo String<p>

Interfaz simple <p>
==============  <p>
new CajaTexto (String titulo,int filas,int columnas)   <p>
- crea una ventana con su titulo y el numero de
filas y columnas indicado
(medidos en caracteres)<p>
void espera()         - espera a que el usuario teclee datos y pulse
aceptar<p>
boolean hayMas()      - retorna un booleano indicando si hay mas 
lineas por leer o n <p>
String leeString ()   - retorna un String que corresponde a la linea
actual <p>
void avanzaLinea()    - avanza a la siguiente linea del texto <p>

Interfaz avanzada <p>
=================  <p>
void esperaYCierra()  - espera a que el usuario teclee datos y pulse
aceptar, cerrando ademas la ventana<p>
int leeInt()          - retorna un entero que corresponde a la linea
actual <p>
int leeInt(int pos)   - retorna un entero que es el que ocupa la posicion
pos en la linea actual, que contiene varios 
enteros separados por espacios en blanco; 
el primer entero corresponde a pos=0<p>
double leeDouble()    - retorna un real que corresponde a la linea
actual <p>
double leeDouble(int pos)  
- retorna un numero real que es el que ocupa la 
posicion pos en la linea actual, que contiene
varios numeros reales separados por espacios
en blanco; el primer numero corresponde a pos=0<p>
void borra()          - borra el texto de la caja de texto <p>
void println(String s)- anade al final del texto una linea <p>
*/

    public class CajaTexto extends Frame implements ActionListener {


        private static final long serialVersionUID=3918001L;

        private String title;
        private int filas, columnas;
        private String textoLeido="";
        private int indiceActual;

        /**
           crea una ventana con su titulo
        */
        public CajaTexto (String titulo, int filas, int columnas) {
            title = titulo;
            this.filas=filas;
            this.columnas=columnas;
            inicializa();
        }

        private int xwidth, yheight;
        private Button okButton, closeButton;
        private TextArea texto;
        private Watcher okWatcher = new Watcher();

        private void inicializa () {
            //xwidth = 520;
            //yheight = 420;
            //setSize(xwidth,yheight);
            setTitle(title);
            setLayout(new BorderLayout());

            Panel op = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
            //outPane.setSize(xwidth - 40, 80);
            texto = new TextArea("",filas, Math.max(title.length()+10,columnas),
                                 TextArea.SCROLLBARS_BOTH);
            texto.setEditable(true);
            op.add(texto);
            add(op,"North");

            Panel p = new Panel(new BorderLayout());
            okButton = new Button("Aceptar");
            okButton.addActionListener(this);
            okButton.setEnabled(false);
            p.add("Center",okButton);
            closeButton = new Button("Cerrar");
            closeButton.addActionListener(this);
            closeButton.setEnabled(true);
            p.add("East",closeButton);
            add("South",p);
            addWindowListener (new WindowAdapter () {
                    public void windowClosing(WindowEvent e) {
                        System.exit (0);
                    }
                });
            setVisible(true);
        }

        /**
           No usar directamente esta operacion interna de la clase
        */
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == okButton) {
                okWatcher.ready();
            } else
            if (e.getSource() == closeButton) {
                System.exit(0);
            }
        }

        /**
         * retorna el numero de lineas que el usuario tecleo
         * es private para ocultarlo en ejercicios sobre secuencias
         */
        private int numLineas() {
            int contador=-1; // porque cuenta uno de mas
            int indice=-1; //valor anterior al primer caracter
            do {
                // buscar proximo salto de linea
                indice=textoLeido.indexOf('\n',indice+1);
                contador++;
            } while (indice!=-1);
            if (indiceActual<textoLeido.length()-1) {
                // todavia queda texto
                return contador+1;
            } else {
                return contador;
            }
        }

        /**
         *  Retorna un booleano indicando si hay mas lineas por leer o no
         */
        public boolean hayMas() {
            int finLinea=textoLeido.indexOf('\n',indiceActual+1);
            return !((finLinea==-1) && indiceActual>=textoLeido.length()-1);
        }

        /**
         * Retorna el entero que corresponde a la linea actual
         */
        public int leeInt () {
            String linea="";
            try {
                linea=leeString();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entero con formato incorrecto en la linea de texto: "+
                              linea);
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("No hay mas lineas");
                throw e;
            }
        }

        /**
         * Retorna un entero que es el que ocupa la posicion pos en la linea
         *   actual, que contiene varios enteros separados por espacios en
         *   blanco; el primer entero corresponde a pos=0
         */

        public int leeInt (int pos) {
            String linea="";
            String palabra[];
            try {
                linea=leeString();
                palabra=linea.split("\\s+",0);
                return Integer.parseInt(palabra[pos]);
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entero con formato incorrecto en la linea de texto: "+
                              linea);
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("No hay mas lineas");
                throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("No hay "+(pos+1)+" numeros en la linea");
                throw e;
            }
        }

        /**
         * Retorna el numero real que corresponde a la linea actual
         */
        public double leeDouble () {
            String linea="";
            try {
                linea=leeString();
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Numero real con formato incorrecto en la linea de texto: "
                              +linea);
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("No hay mas lineas");
                throw e;
            }
        }

        /**
         * Retorna un numero real que es el que ocupa la posicion pos en la linea
         *   actual, que contiene varios numeros reales separados por espacios en
         *   blanco; el primer numero corresponde a pos=0
         */

        public double leeDouble (int pos) {
            String linea="";
            String palabra[];
            try {
                linea=leeString();
                palabra=linea.split("\\s+",0);
                return Double.parseDouble(palabra[pos]);
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe
                ("Numero real con formato incorrecto en la linea de texto: "+
                 linea);
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("No hay mas lineas");
                throw e;
            } catch (ArrayIndexOutOfBoundsException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("No hay "+(pos+1)+" numeros en la linea");
                throw e;
            }
        }

        /**
           Avanza a la siguiente linea del texto
        */
        public void avanzaLinea () {
            int finLinea=textoLeido.indexOf('\n',indiceActual+1);
            if (finLinea==-1) {
                // ultima linea
                if (indiceActual<textoLeido.length()-1) {
                    // todavia queda texto
                    indiceActual=textoLeido.length();
                } else {
                    // no quedan mas lineas
                    Mensaje error =new Mensaje("Error");
                    error.escribe("No hay mas lineas");
                    throw new NullPointerException();
                }
            }else {
                // encontrado salto de linea
                indiceActual=finLinea;
            }
        }

        /**
         * Retorna el String que corresponde a la linea actual
         */ 
        public String leeString () {
            String linea;
            int finLinea=textoLeido.indexOf('\n',indiceActual+1);
            if (finLinea==-1) {
                // ultima linea
                if (indiceActual<textoLeido.length()-1) {
                    // todavia queda texto
                    linea=textoLeido.substring(indiceActual+1,textoLeido.length());
                } else {
                    // no quedan mas lineas
                    linea=null; 
                }
            }else {
                // encontrado salto de linea
                if (finLinea>0 && textoLeido.charAt(finLinea-1)=='\r') {
                    //quitar el "retorno de carro (\r)"
                    linea=textoLeido.substring(indiceActual+1,finLinea-1);
                } else {
                    //La linea no tiene retorno de carro
                    linea=textoLeido.substring(indiceActual+1,finLinea);
                }
            }
            return linea;
        }

        /** 
         * Borra el texto de la caja de texto
         */
        public void borra() {
            textoLeido="";
            indiceActual=-1;
            texto.setText(null);
        }

        /**
         * Anade al final del texto una linea
         */
        public void println(String s) {
            texto.append(s+"\n");
        }

        /**
           Espera a que el usuario
           teclee datos y pulse aceptar; luego cierra la ventana<p>
        */
        public void esperaYCierra () {
            espera();
            setVisible(false);
        }

        /**
           Espera a que el usuario
           teclee datos y pulse aceptar; luego cierra la ventana<p>
        */
        public void espera () {
            okButton.setEnabled(true);
            this.pack();
            setVisible(true);
            okWatcher.watch();
            // Lee el texto tecleado
            textoLeido=texto.getText();
            indiceActual=-1; // antes del primer caracter
        }

        class Watcher {

            private boolean ok;

            Watcher () {
                ok = false;
            }

            synchronized void watch () {
                while (!ok) {
                    try {wait(500); }
                    catch(InterruptedException e) {}
                }
                ok = false;
            }

            synchronized void ready () {
                ok = true;
                notify();
            }
        }

    }
