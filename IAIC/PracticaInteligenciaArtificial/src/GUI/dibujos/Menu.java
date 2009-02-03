package GUI.dibujos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/** Menu      por Michael Gonzalez. Version 2.2.3. Octubre 2008

(modificacion de la clase Display por J M Bishop, July 1999) <p>

Es una clase sencilla que ofrece facilidades para crear un menu
de opciones, y poder elegir una de ellas.<p>

Interfaz  <p>
========  <p>
new Menu (title)      - crea el objeto Menu con su titulo<p>
println (string)      - muestra un string en la cabecera<p>
insertaOpcion (nombre, codigo)
- crea un boton con el nombre y codigo indicado <p>
int leeOpcion ()
- espera a que se pulse un boton, 
y retorna el codigo del boton pulsado.<p>
int leeOpcion (mensaje)
- muestra un mensaje, espera a que se pulse un
boton, y retorna el codigo del boton pulsado.<p>
void cierra()
- cierra la ventana del menu.<p>
*/

    public class Menu extends Frame implements ActionListener {


        private static final long serialVersionUID=3918001L;

        private String title;

        /**
         * Crea el objeto Menu con su titulo
         */
        public Menu (String t) {
            /* the alternative constructor -
               has a title */
            title = t;
            initializeDisplay();
        }

        private class Data {
            String name;
            int code;
            Button button;
        }

        private int code;
        private Vector<Data> listOfNames = new Vector<Data>();
        private int xwidth, yheight;
        private Button closeButton;
        private TextArea outDisplay;
        private Panel inDisplay;
        private ScrollPane inPane, outPane;
        private Watcher okWatcher = new Watcher();

        private void initializeDisplay () {
            code=-1;
            xwidth = 400;
            yheight = 400;
            setSize(xwidth,yheight);
            setTitle(title);
            setLayout(new BorderLayout());

            Panel op = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
            outPane = new ScrollPane();
            outPane.setSize(xwidth - 40, 60);
            outDisplay = new TextArea("",2, 24, TextArea.SCROLLBARS_VERTICAL_ONLY);
            outDisplay.setEditable(false);
            outPane.add(outDisplay);
            op.add(outPane);
            add(op,"North");

            Panel p = new Panel (new FlowLayout(FlowLayout.CENTER,15,0));
            inPane = new ScrollPane();
            inPane.setSize(xwidth - 40, yheight - 100);
            Panel otro = new Panel();
            otro.setSize(xwidth-50, yheight -110);
            inDisplay = new Panel(new GridLayout (0,1,10,10));
            otro.add(inDisplay);
            inPane.add(otro);
            p.add(inPane);
            add(p,"Center");


            p = new Panel(new BorderLayout());
            closeButton = new Button("Cerrar");
            closeButton.addActionListener(this);
            closeButton.setEnabled(true);
            p.add("Center",closeButton);
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
            Data d;
            for (int i=0; i<listOfNames.size();i++) {
                d=listOfNames.elementAt(i);
                if (e.getSource() == d.button) {
                    code=d.code;
                    okWatcher.ready();
                    return;
                }
            }
            if (e.getSource() == closeButton) {
                System.exit(0);
            }
        }

        /**
         *  Crea un boton con el nombre y codigo indicado
         */
        public void insertaOpcion (String name, int code) {
            Data d = new Data();
            Button b = new Button(name);
            d.name=name;
            d.code=code;
            d.button=b;
            b.addActionListener(this);
            b.setEnabled(true);
            listOfNames.addElement(d);
            inDisplay.add(b);
        }

        /**
         *  Muestra un mensaje, espera a que se pulse un
         *  boton, y retorna el codigo del boton pulsado
         */
        public int leeOpcion (String s) {
            outDisplay.append("\n"+s);
            if (listOfNames.size()==0) {
                Mensaje error=new Mensaje("Errorr");
                error.escribe("No hay ninguna opcion insertada");
                throw new NullPointerException();
            } else {
                setVisible(true);
                okWatcher.watch();
                return code;
            }
        }

        /**
         *  Espera a que se pulse un boton,
         *  y retorna el codigo del boton pulsado
         */
        public int leeOpcion () {
            return leeOpcion("");
        }

        /**
         *  Muestra un string en la cabecera
         */
        public void println (String s) {
            outDisplay.append(s+"\n");
        }

        /**
         *  Cierra la ventana
         */
        public void cierra () {
            setVisible(false);
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
