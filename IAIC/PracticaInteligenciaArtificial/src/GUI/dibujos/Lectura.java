package GUI.dibujos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/** Lectura      por Michael Gonzalez. Version 2.2.3. Enero 2008

(modificacion de la clase Display, por J M Bishop, July 1999)<p>

Es una clase sencilla para leer datos introducidos por teclado,
usando una ventana <p>

Los valores se introducen en ventanas creadas al efecto<p>

Los valores leidos pueden ser del tipo String, double u int<p>

Interfaz  <p>
========  <p>
new Lectura (titulo)  - crea una ventana con su titulo<p>
println (string)      - muestra un string en la cabecera <p>
creaEntrada (etiqueta, valor) <p>
- crea una caja para leer un valor; el valor
puede ser double, int o String<P>
espera (mensaje)      - muestra un mensaje, y espera a que el
usuario teclee datos y pulse aceptar<p>
esperaYCierra (mensaje)      
- igual que espera, pero ademas cierra la <p>
ventana al aceptar <p>
espera ()             - espera a que el usuario teclee datos y 
pulse aceptar<p>
esperaYCierra ()      - igual que espera, pero ademas cierra la <p>
ventana al aceptar <p>
leeDouble (label)     - lee un double de la caja con esa etiqueta<p>
leeInt (label)        - lee un int de la caja con esa etiqueta<p>
leeString (label)     - lee un String de la caja con esa etiqueta<p>
*/

    public class Lectura extends Frame implements ActionListener {

        private static final long serialVersionUID=3918001L;

        private String title;

        /**
         * Crea una ventana con su titulo
         */
        public Lectura (String t) {
            title = t;
            initializeDisplay();
        }

        private class Data {
            TextField field;
            String value;
        }

        private Hashtable<String,Data> table = new Hashtable<String,Data> (10);
        private int xwidth, yheight;
        private Button okButton, closeButton;
        private TextArea outDisplay;
        private Panel inDisplay;
        private ScrollPane inPane, outPane;
        private Watcher okWatcher = new Watcher();

        private void initializeDisplay () {
            xwidth = 520;
            yheight = 420;
            setSize(xwidth,yheight);
            setTitle(title);
            setLayout(new BorderLayout());

            Panel op = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
            outPane = new ScrollPane();
            outPane.setSize(xwidth - 40, 80);
            outDisplay = new TextArea("",4, 24,TextArea.SCROLLBARS_VERTICAL_ONLY);
            outDisplay.setEditable(false);
            outPane.add(outDisplay);
            op.add(outPane);
            add(op,"North");

            Panel p = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
            inPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
            inPane.setSize(xwidth - 40, yheight - 120);
            inDisplay = new Panel(new GridLayout (0,2,10,10));
            inPane.add(inDisplay);
            p.add(inPane);
            add(p,"Center");


            p = new Panel(new BorderLayout());
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
         *  No usar directamente esta operacion interna de la clase
         */
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == okButton) {
                okWatcher.ready();
            } else
                if (e.getSource() == closeButton) {
                    System.exit(0);
                }
        }

        private Data getEntry (String s) {
            if (table.containsKey(s))
                return table.get(s);
            else {
                outDisplay.append("\nERROR: No such input label: "+s+"\n");
                return null;
            }
        }

        /**
         *  Lee un int de la caja con esa etiqueta
         */
        public int leeInt (String s) {
            try {
                Data d = getEntry(s);
                return Integer.valueOf(d.value).intValue();
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entero en la entrada "+s+" con formato incorrecto");
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entrada "+s+" no encontrada");
                throw e;
            }
        }

        /**
         *  Lee un double de la caja con esa etiqueta
         */
        public double leeDouble (String s) {
            try {
                Data d = getEntry(s);
                return Double.valueOf(d.value).doubleValue();
            } catch (NumberFormatException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entero en la entrada "+s+" con formato incorrecto");
                throw e;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entrada "+s+" no encontrada");
                throw e;
            }
        }

        /**
         * Lee un String de la caja con esa etiqueta
         */
        public String leeString (String s) {
            try {
                Data d = getEntry(s);
                return d.value;
            } catch (NullPointerException e) {
                Mensaje error =new Mensaje("Error");
                error.escribe("Entrada "+s+" no encontrada");
                throw e;
            }
        }

        private void insertPrompt(Data d, String s, TextField t) {
            Panel p;
            p = new Panel(new FlowLayout(FlowLayout.RIGHT));
            p.add(new Label(s));
            inDisplay.add(p);
            t.addActionListener(this);
            t.setEditable(true);
            p = new Panel(new FlowLayout(FlowLayout.LEFT));
            p.add(t);
            inDisplay.add(p);
            d.field = t;
        }

        /**
         * Crea una caja para leer un valor int
         */
        public void creaEntrada (String s, int n) {
            Data d = new Data();
            TextField t = new TextField(10);
            insertPrompt(d, s, t);
            d.value = Integer.toString(n);
            t.setText(d.value);
            if (table.containsKey(s)) {
                Mensaje error=new Mensaje("Error");
                error.escribe("Entrada "+s+" esta repetida");
            }
            table.put(s, d);
        }

        /**
         *  Crea una caja para leer un valor double
         */
        public void creaEntrada (String s, double n) {
            Data d = new Data();
            TextField t = new TextField(15);
            insertPrompt(d, s, t);
            d.value = Double.toString(n);
            t.setText(d.value);
            if (table.containsKey(s)) {
                Mensaje error=new Mensaje("Error");
                error.escribe("Entrada "+s+" esta repetida");
            }
            table.put(s, d);
        }

        /**
         * Crea una caja para leer un valor String
         */
        public void creaEntrada (String s, String n) {
            Data d = new Data();
            int maxLength=n.length()+2;
            if (maxLength<20) {
                maxLength=20;
            }
            TextField t = new TextField(maxLength);
            insertPrompt(d, s, t);
            d.value = n;
            t.setText(d.value);
            if (table.containsKey(s)) {
                Mensaje error=new Mensaje("Error");
                error.escribe("Entrada "+s+" esta repetida");
            }
            table.put(s, d);
        }

        /**
         *  Muestra un mensaje, y espera a que el
         *  usuario teclee datos y pulse aceptar; luego cierra la ventana<p>
         */
        public void esperaYCierra (String s) {
            espera(s);
            setVisible(false);
        }
        /**
         *  Espera a que el usuario teclee datos y pulse aceptar; 
         *  luego cierra la ventana<p>
         */
        public void esperaYCierra () {
            espera("");
            setVisible(false);
        }

        /** 
         * Espera a que el usuario teclee datos y pulse aceptar <p> 
         */
        public void espera () {
            espera("");
        }

        /**
         *  Muestra un mensaje, y espera a que el
         *  usuario teclee datos y pulse aceptar<p>
         */
        public void espera (String s) {
            outDisplay.append("\n"+s);
            okButton.setEnabled(true);
            this.pack();
            setVisible(true);
            okWatcher.watch();
            outDisplay.append("\n\n");
            // copy all the values from the boxes to the table
            for (Enumeration e = table.keys(); e.hasMoreElements();) {
                String name = (String) e.nextElement();
                Data d = table.get(name);
                d.value = d.field.getText();
                table.put(name, d);
            }
        }

        /**
         *  Muestra un string en la cabecera
         */
        public void println (String s) {
            outDisplay.append(s+"\n");
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
