package GUI.dibujos;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

/**  Dibujo     por Michael Gonzalez. Version 2.2.3 octubre 2008 <p>

Es una clase sencilla que ofrece facilidades para hacer dibujos: <p>
- puntos <p>
- rectas <p>
- poligonos abiertos y cerrados <p>
- circulos y elipses <p>
- rectangulos <p>
- arcos circulares <p>
- texto <p>
- imagenes <p>

Interfaz:  <p>
********   <p>

basica
------

new  Dibujo(titulo)             Constructor con tama\no 640*480<p>
new  Dibujo(titulo,alto,ancho)  Constructor con tama\no definible <p>
espera()                        Hace el dibujo y espera a que se 
pulse aceptar <p>

atributos del dibujo <p>
-------------------- <p>
<p>
ponColorLapiz(color)    pone el color del lapiz con el que se pinta <p>
ponGrosorLapiz(ancho)   pone el grosor del lapiz con el que se pinta <p>
ponRelleno(color)       pone el color del relleno de las figuras cerradas <p>
ponLetra(tamano))       pone el tamano de la letra del texto dibujado <p>
<p>
funciones de dibujar <p>
-------------------- <p>
Observar que las coordenadas X aumentan hacia la derecha <p>
y que las coordenadas Y aumentan hacia abajo  en el dibujo <p>
<p>
borra() <p>
borra(color) <p>
dibujaTexto(texto,xOrigen,yOrigen) <p>
dibujaPunto(x,y) <p>
dibujaLinea(xOrigen,yOrigen,xDestino,yDestino) <p>
dibujaLineas (x[],y[]); <p>
dibujaRectangulo(x1,y1,x2,y2)   //(x1,y1): sup. izqda. (x2,y2):inf.dcha. <p>
dibujaElipse(x1,y1,x2,y2)       //(x1,y1): sup. izqda. (x2,y2):inf.dcha. <p>
dibujaArco(x1,y1,x2,y2,angulo1,angulo2); <p>
dibujaPoligono(x[],y[]) <p>
dibujaImagen(xOrigen,yOrigen,nombreFichero) <p>
<p>
*/

public class Dibujo extends JFrame implements ActionListener {

    private static final long serialVersionUID=3918001L;

    private int windowWidth, windowHeight;
    private final int xborder=8, yBorder=20;
    private Vector<Figura> figs;
    private Button okButton, closeButton;
    private Watcher okWatcher = new Watcher();

    private class Atributos {
        ColorFig col=ColorFig.negro;
        ColorFig colRelleno=ColorFig.blanco;
        int grosorLapiz=1;
        int fontSize=12;

        Atributos copia() {
            Atributos atr=new Atributos();
            atr.col=col;
            atr.colRelleno=colRelleno;
            atr.grosorLapiz=grosorLapiz;
            atr.fontSize=fontSize;
            return atr;
        }
    }

    private Atributos atributosActuales=new Atributos();

    private abstract class Figura {
        Atributos atr;

        static final int xBorder=8;
        static final int yBorder=30;

        Figura () {
            this.atr=atributosActuales.copia();
        }

        abstract void dibuja(Graphics g);

    }

    private class Linea extends Figura {

        int x1,y1,x2,y2;

        Linea (int x1,int y1,int x2,int y2) {
            super();
            this.x1=x1+xBorder;
            this.y1=y1+yBorder;
            this.x2=x2+xBorder;
            this.y2=y2+yBorder;
        }

        void dibuja(Graphics g) {
            int rangoSup=(atr.grosorLapiz-1)/2;
            int rangoInf=-(atr.grosorLapiz-2)/2;
            g.setColor(atr.col.colorOf());
            if (atr.grosorLapiz==1) {
                g.drawLine(x1,y1,x2,y2);
            } else {
                for (int i=rangoInf; i<=rangoSup; i++) {
                    if (Math.abs(x2-x1)>Math.abs(y2-y1)){
                        // linea mas horizontal
                        g.drawLine(x1,y1+i,x2,y2+i);
                    } else {
                        // linea mas vertical
                        g.drawLine(x1+i,y1,x2+i,y2);
                    }
                }
            } 
        }
    }

    private class Rectangulo extends Figura {

        int x1,y1,x2,y2;

        Rectangulo (int x1,int y1,int x2,int y2) {
            super();
            this.x1=x1+xBorder;
            this.y1=y1+yBorder;
            this.x2=x2+xBorder;
            this.y2=y2+yBorder;
        }

        void dibuja(Graphics g) {
            g.setColor(atr.col.colorOf());
            g.fillRect(x1,y1,x2-x1,y2-y1);

            g.setColor(atr.colRelleno.colorOf());
            g.fillRect(x1+atr.grosorLapiz,y1+atr.grosorLapiz,
                       x2-x1-2*atr.grosorLapiz,y2-y1-2*atr.grosorLapiz);
        }
    }

    private class Elipse extends Figura {

        int x1,y1,x2,y2;

        Elipse (int x1,int y1,int x2,int y2) {
            super();
            this.x1=x1+xBorder;
            this.y1=y1+yBorder;
            this.x2=x2+xBorder;
            this.y2=y2+yBorder;
        }

        void dibuja(Graphics g) {
            g.setColor(atr.col.colorOf());
            g.fillOval(x1,y1,x2-x1,y2-y1);

            g.setColor(atr.colRelleno.colorOf());
            g.fillOval(x1+atr.grosorLapiz,y1+atr.grosorLapiz,
                       x2-x1-2*atr.grosorLapiz,y2-y1-2*atr.grosorLapiz);
        }
    }

    private class Arco extends Elipse {

        int angulo1,angulo2; // en grados

        Arco (int x1,int y1,int x2,int y2,int angulo1, int angulo2) {
            super(x1,y1,x2,y2);
            this.angulo1=angulo1;
            this.angulo2=angulo2;
        }

        void dibuja(Graphics g) {
            g.setColor(atr.col.colorOf());
            g.fillArc(x1,y1,x2-x1,y2-y1,angulo1,angulo2-angulo1);

            g.setColor(atr.colRelleno.colorOf());
            g.fillArc(x1+atr.grosorLapiz,y1+atr.grosorLapiz,
                      x2-x1-2*atr.grosorLapiz,y2-y1-2*atr.grosorLapiz,
                      angulo1,angulo2-angulo1);
        }
    }

    private class Texto extends Figura {

        int x,y;
        String s;

        Texto (String s,int x, int y) {
            super();
            this.x=x+xBorder;
            this.y=y+yBorder;
            this.s=s;
        }

        void dibuja(Graphics g) {
            g.setFont(g.getFont().deriveFont(Font.PLAIN,atr.fontSize));
            g.setColor(atr.col.colorOf());
            g.drawString(s,x,y);
        }
    }

    private class Lineas extends Figura {

        int x[],y[];

        Lineas (int x[], int y[]) {
            super();
            this.x=new int[x.length];
            this.y=new int[x.length];
            for (int i=0; i<x.length; i++) {
                this.x[i]=x[i]+xBorder;
                this.y[i]=y[i]+yBorder;
            }
        }

        void dibuja(Graphics g) {
            g.setColor(atr.col.colorOf());
            int rangoSup=(atr.grosorLapiz-1)/2;
            int rangoInf=-(atr.grosorLapiz-2)/2;
            for (int n=0; n<x.length-1; n++) {
                if (atr.grosorLapiz==1) {
                    g.drawLine(x[n],y[n],x[n+1],y[n+1]);
                } else {
                    for (int i=rangoInf; i<=rangoSup; i++) {
                        if (Math.abs(x[n+1]-x[n])>Math.abs(y[n+1]-y[n])){
                            // linea mas horizontal
                            g.drawLine(x[n],y[n]+i,x[n+1],y[n+1]+i);
                        } else {
                            // linea mas vertical
                            g.drawLine(x[n]+i,y[n],x[n+1]+i,y[n+1]);
                        }
                    }
                } 
            }
        }
    }

    private static double modulo(double x1,double y1,double x2,double y2) {
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }

    private class Poligono extends Lineas {
      
        Poligono (int x[], int y[]) {
            super(x,y);
        }
      
        private void drawSegment(int x1, int y1, int x2, int y2, Graphics g) 
        {       
            if (x2!=x1 || y2!=y1) {

                int[] cX=new int[4];
                int[] cY=new int[4];

                // vector of unit length in the direction of the line
                double vX=x2-x1;
                double vY=y2-y1;
                double module=Math.sqrt(vX*vX+vY*vY);
                vX=vX*atr.grosorLapiz*1.1/module/2;
                vY=vY*atr.grosorLapiz*1.1/module/2;
               
                // vector of unit length orthogonal to the line
                double pX=-vY;
                double pY=vX;
               
                // calculate polygon enclosing the line
                cX[0]=(int) Math.rint(x1-vX-pX);
                cY[0]=(int) Math.rint(y1-vY-pY);
                cX[1]=(int) Math.rint(x1-vX+pX);
                cY[1]=(int) Math.rint(y1-vY+pY);
                cX[2]=(int) Math.rint(x2+vX+pX);
                cY[2]=(int) Math.rint(y2+vY+pY);
                cX[3]=(int) Math.rint(x2+vX-pX);
                cY[3]=(int) Math.rint(y2+vY-pY);
        
                // Draw the polygon       
                g.fillPolygon(cX,cY,4);
            }
        }
           
        void dibuja(Graphics g) {
            // draw the backround
            g.setColor(atr.colRelleno.colorOf());
            g.fillPolygon(x,y,x.length);
           
            // draw the foreground
            g.setColor(atr.col.colorOf());
            for (int i=0; i<x.length-1; i++) {
                drawSegment(x[i],y[i],x[i+1],y[i+1],g);
            }
            drawSegment(x[x.length-1],y[x.length-1],x[0],y[0],g);
        }
    }

    private class Punto extends Figura {

        int x,y;

        Punto (int x, int y) {
            super();
            this.x=x+xBorder;
            this.y=y+yBorder;
        }

        void dibuja(Graphics g) {
            g.setColor(atr.col.colorOf());
            if (atr.grosorLapiz==1) {
                g.drawLine(x,y,x,y);
            } else {
                g.fillOval(x-atr.grosorLapiz/2,y-atr.grosorLapiz/2,
                           atr.grosorLapiz,atr.grosorLapiz);
            }
        }
    }

    private class Imagen extends Figura {

        int x,y;
        String filename;

        Imagen (int x, int y, String filename) {
            super();
            this.x=x+xBorder;
            this.y=y+yBorder;
            this.filename=filename;
            File f =new File(filename);
            if (!f.exists()) {
                Mensaje error=new Mensaje("Error");
                error.escribe("Fichero de imagen "+filename+" no existe");
            }
        }

        void dibuja(Graphics g) {
            ImageIcon im=new ImageIcon(filename);
            g.drawImage(im.getImage(),x,y,null);
        }
    }

    /**
     *  Pone el grosor del lapiz con el que se pinta      
     */
    public void ponGrosorLapiz (int grosor) {
        atributosActuales.grosorLapiz=grosor;
    }

    /**
     *  Pone el color del lapiz con el que se pinta
     */
    public void ponColorLapiz (ColorFig color) {
        atributosActuales.col=color;
    }

    /**
     *  Pone el tamano de la letra del texto dibujado
     */
    public void ponLetra (int tamano) {
        atributosActuales.fontSize=tamano;
    }

    /**
     *  Pone el color del relleno de las figuras cerradas
     */
    public void ponRelleno (ColorFig color) {
        atributosActuales.colRelleno=color;
    }

    /**
     *  Borra la imagen poniendola del color de fondo indicado
     */
    public void borra (ColorFig color) {
        figs.clear();
        Atributos atr=atributosActuales.copia();
        atributosActuales.colRelleno=color;
        atributosActuales.col=color;
        figs.add(new Rectangulo(0,0,windowWidth,windowHeight));
        atributosActuales.col=atr.col;
        atributosActuales.colRelleno=atr.colRelleno;
    }

    /**
     *  Borra la imagen poniendola del color de fondo gris claro
     */
    public void borra () {
        borra(ColorFig.gris.masClaro());
    }

    /**
     *  Dibuja una linea desde el punto (x1,y1) hasta (x2,y2)
     */
    public void dibujaLinea(int x1,int y1,int x2,int y2) {
        figs.add(new Linea(x1,y1,x2,y2));
    }

    /**
     *  Dibuja un rectangulo cuya esquina superior izquierda es el
     *  punto (x1,y1) y la esquina inferior derecha es (x2,y2)
     */
    public void dibujaRectangulo(int x1,int y1,int x2,int y2) {
        figs.add(new Rectangulo(x1,y1,x2,y2));
    }

    /**
     *  Dibuja un ovalo contenido en el rectangulo
     *  cuya esquina superior izquierda es el
     *  punto (x1,y1) y la esquina inferior derecha es (x2,y2)
     */
    public void dibujaElipse(int x1,int y1,int x2,int y2) {
        figs.add(new Elipse(x1,y1,x2,y2));
    }

    /**
     *  Dibuja una parte (un arco) de un ovalo. 
     *  El ovalo completo es el contenido en el rectangulo
     *  cuya esquina superior izquierda es el
     *  punto (x1,y1) y la esquina inferior derecha es (x2,y2)
     *  La parte que se dibuja es la comprendida entre angulo1 y angulo2
     *  Los angulos van en grados. El angulo cero es la horizontal hacia
     *  la derecha.
     */
    public void dibujaArco(int x1,int y1,int x2,int y2,
                           int angulo1, int angulo2) 
    {
        figs.add(new Arco(x1,y1,x2,y2,angulo1,angulo2));
    }

    /**
     *  Dibuja el texto indicado a partir del punto (x,y)
     */
    public void dibujaTexto(String s, int x,int y) {
        figs.add(new Texto(s,x,y));
    }

    /**
     *  Dibuja un  poligono abierto cuyas coordenadas x e y estan
     *  en los respectivos arrays.
     */
    public void dibujaLineas(int x[],int y[]) {
        figs.add(new Lineas(x,y));
    }

    /**
     *  Dibuja un  poligono cerrado cuyas coordenadas x e y estan
     *  en los respectivos arrays.
     */
    public void dibujaPoligono(int x[],int y[]) {
        figs.add(new Poligono(x,y));
    }

    /**
     *   Dibuja un  punto en la posicion (x,y)
     */
    public void dibujaPunto(int x,int y) {
        figs.add(new Punto(x,y));
    }

    /**
     *  Dibuja la imagen contenida en el fichero cuyo nombre se indica,
     *  dibujandola desde la posicion (x,y)
     */
    public void dibujaImagen(int x,int y, String filename) {
        figs.add(new Imagen(x,y,filename));
    }

    /**
     * Constructor simple, que hace la ventana de tamano 640*480
     */
    public Dibujo (String titulo) {
        initializeGraph(titulo,640,480);
        figs=new Vector<Figura>(50,50);
    }
   
    /**
     *  Constructor completo, que pone el titulo y tamano de la ventana
     */
    public Dibujo (String titulo, int ancho, int alto) {
        //--------------
        /* the alternative constructor -
           has window and axes titles */
        initializeGraph(titulo,ancho,alto);
        figs=new Vector<Figura>(50,50);
    }
   
    private void initializeGraph (String titulo, int width, int height) {
        windowWidth=width;
        windowHeight=height;
        setSize(width+25,height+25);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setTitle (titulo);
    }
   
    /**
     *  Pinta el dibujo y espera a que se pulse el boton aceptar
     */
    public void espera (int tiempo) {
        //-----------------
        repaint();
        okWatcher.watch(tiempo);
    }

    /**
     *  No usar directamente esta operacion interna de la clase
     */
    public void paint (Graphics g) {
        //-----------------
        for (int i=0; i<figs.size();i++) {
            figs.elementAt(i).dibuja(g);
        }
    }

    /**
     *  No usar directamente esta operacion interna de la clase
     */
    public void actionPerformed (ActionEvent e) {
    }

    class Watcher {

        private boolean ok;

        Watcher () {
            ok = false;
        }

        synchronized void watch (int nombre) {
            try {wait(nombre); }
            catch(InterruptedException e) {}
            notifyAll();
        }

        synchronized void ready () {
            ok = true;
            notify();
        }
    }

}
