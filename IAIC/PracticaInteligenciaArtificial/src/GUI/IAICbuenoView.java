/*
 * IAICbuenoView.java
 */

package GUI;

import java.io.IOException;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

import universo.GestorConexion;
import universo.Universo;
import universo.util.UniversoLogger;
import universo.util.UniversoMovie;
import aima.search.framework.GraphSearch;
import aima.search.framework.Search;
import aima.search.framework.TreeSearch;

import aima.search.informed.AStarSearch;
import aima.search.informed.GreedyBestFirstSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.DepthFirstSearch;
import aima.search.uninformed.DepthLimitedSearch;
import aima.search.uninformed.IterativeDeepeningSearch;
import aima.search.uninformed.UniformCostSearch;
import conector.GeneraUniverso;

/**
 * The application's main frame.
 */
public class IAICbuenoView extends FrameView {

    public IAICbuenoView(SingleFrameApplication app) {
        super(app);
        initComponents();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = IAICbuenoApp.getApplication().getMainFrame();
            aboutBox = new IAICbuenoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        IAICbuenoApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
        		new Object [][] {
                        {"", "", "", ""},
                        {"", "", "", ""},
                        {"", "", "", ""},
                        {"", "", "", ""}
                    },
                    new String [] {
                        "", "", "", ""
                    }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(GUI.IAICbuenoApp.class).getContext().getResourceMap(IAICbuenoView.class);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elige...", "A*","Voraz","Escalada simple","Enfriamiento simulado","Primero en anchura","Primero en profundidad","Profundidad limitada","Profundizacion iterativa","Coste uniforme" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(GUI.IAICbuenoApp.class).getContext().getActionMap(IAICbuenoView.class, this);
        jButton1.setAction(actionMap.get("SiguientePaso")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elige...", "A*","Voraz","Escalada simple","Enfriamiento simulado","Primero en anchura","Primero en profundidad","Profundidad limitada","Profundizacion iterativa","Coste uniforme" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elige...", "A*","Voraz","Escalada simple","Enfriamiento simulado","Primero en anchura","Primero en profundidad","Profundidad limitada","Profundizacion iterativa","Coste uniforme" }));
        jComboBox3.setName("jComboBox3"); // NOI18N

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jComboBox1, 0, 311, Short.MAX_VALUE))
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jComboBox2, 0, 311, Short.MAX_VALUE))
                            .add(mainPanelLayout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jComboBox3, 0, 311, Short.MAX_VALUE))))
                    .add(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .add(jButton1)
                .add(141, 141, 141))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 53, Short.MAX_VALUE)
                .add(jButton1)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Aqui realizaremos la creacion del universo
        String fich = null;
        do {
            fich = JOptionPane.showInputDialog("¿Nombre del fichero donde se guardará el universo?");
        }while ( fich.equals(null));

        System.out.print("Generacion de universos: \n");
        System.out.print("\t Se guarda con el nombre: " + fich + "\n\n");

        try {
            GeneraUniverso uni = new GeneraUniverso(fich);
            uni.generar();
        } catch (IOException ex) {
            System.out.print(ex.toString());
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        //Aqui se lanza la busqueda.

        Integer busqueda1 = Integer.valueOf(jComboBox1.getSelectedIndex());
        Integer busqueda2 = Integer.valueOf(jComboBox2.getSelectedIndex());
        Integer busqueda3 = Integer.valueOf(jComboBox3.getSelectedIndex());
        Search search1 = null;
        Search search2 = null;
        Search search3 = null;
        
        Properties prop1 = null;
        Properties prop2 = null;
        Properties prop3 = null;
        
        if ( GestorConexion.getInstancia(GestorConexion.getFile()) != null ){
        	
        	// Se ha cargado correctamente asi que procedemos a ejecutar la busqueda
        	// a traves de universo.
        	
        	Universo uni1 = new Universo();
        	Universo uni2 = new Universo();
        	Universo uni3 = new Universo();      	
        	
    		switch(busqueda1){
    		case 1:
    			search1 = new AStarSearch(new TreeSearch());
    			break;
    		case 2:
    			search1 = new GreedyBestFirstSearch(new TreeSearch());
    			break;
    		case 3:
    			search1 = new HillClimbingSearch();
    			break;
    		case 4:
    			search1 = new SimulatedAnnealingSearch();
    			break;
    		case 5:
    			search1 = new IterativeDeepeningSearch();
    			break;
    		case 6:
    			search1 = new BreadthFirstSearch(new GraphSearch());
    			break;
    		case 7:
    			search1 = new DepthFirstSearch(new TreeSearch());
    			break;
    		case 8:
    			search1 = new DepthLimitedSearch(50);
    			break;
    		case 9:
    			search1 = new UniformCostSearch(new TreeSearch());
    			break;
    		default:
    			search1 = new AStarSearch(new TreeSearch());      			
    		}
    		UniversoLogger logger1 = new UniversoLogger("Busqueda1 "+search1.getClass().toString()+".txt");
    		prop1 = uni1.ejecutar(search1, logger1);

    		new UniversoMovie().representar(uni1.getAgente().getActions());

    		switch(busqueda2){
    		case 1:
    			search2 = new AStarSearch(new TreeSearch());
    			break;
    		case 2:
    			search2 = new GreedyBestFirstSearch(new TreeSearch());
    			break;
    		case 3:
    			search2 = new HillClimbingSearch();
    			break;
    		case 4:
    			search2 = new SimulatedAnnealingSearch();
    			break;
    		case 5:
    			search2 = new IterativeDeepeningSearch();
    			break;
    		case 6:
    			search2 = new BreadthFirstSearch(new GraphSearch());
    			break;
    		case 7:
    			search2 = new DepthFirstSearch(new TreeSearch());
    			break;
    		case 8:
    			search2 = new DepthLimitedSearch(50);
    			break;
    		case 9:
    			search2 = new UniformCostSearch(new TreeSearch());
    			break;
    		default:
    			search2 = new AStarSearch(new TreeSearch());      			
    		}
    //		UniversoLogger logger2 = new UniversoLogger("Busqueda2 "+search2.getClass().toString()+".txt");
//    		prop2 = uni2.ejecutar(search2, logger2);

    	
    		switch(busqueda3){
    		case 1:
    			search3 = new AStarSearch(new TreeSearch());
    			break;
    		case 2:
    			search3 = new GreedyBestFirstSearch(new TreeSearch());
    			break;
    		case 3:
    			search3 = new HillClimbingSearch();
    			break;
    		case 4:
    			search3 = new SimulatedAnnealingSearch();
    			break;
    		case 5:
    			search3 = new IterativeDeepeningSearch();
    			break;
    		case 6:
    			search3 = new BreadthFirstSearch(new GraphSearch());
    			break;
    		case 7:
    			search3 = new DepthFirstSearch(new TreeSearch());
    			break;
    		case 8:
    			search3 = new DepthLimitedSearch(50);
    			break;
    		case 9:
    			search3 = new UniformCostSearch(new TreeSearch());
    			break;
    		default:
    			search3 = new AStarSearch(new TreeSearch());      			
    		}
    	//	UniversoLogger logger3 = new UniversoLogger("Busqueda3 "+search3.getClass().toString()+".txt");
       // 	prop3 = uni3.ejecutar(search3, logger3);
  

        	jTable1.setVisible(true);
        	
        	
        	//Aqui va la comparacion:
        	jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {"Coste", prop1.getProperty("pathCost"), prop2.getProperty("pathCost"), prop3.getProperty("pathCost")},
                        {"Tiempo", prop1.getProperty("tiempo"), prop2.getProperty("tiempo"), prop3.getProperty("tiempo")},
                        {"Nodos Expandidos", prop1.get("nodesExpanded"), prop2.getProperty("nodesExpanded"), prop3.getProperty("nodesExpanded")},
                        {"Solucion?", prop1.getProperty("resultado").toString(), prop2.getProperty("resultado").toString(), prop3.getProperty("resultado").toString()}
                    },
                    new String [] {
                        "Atributos", search1.getClass().toString(), search2.getClass().toString(), search3.getClass().toString()
                    }
                ){
                   
					Class[] types = new Class [] {
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
        	
        	
        }else{
        	JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "No se ha cargado ningun universo.");
        }
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser chooser = new JFileChooser();
        // Note: source for ExampleFileFilter can be found in FileChooserDemo,
        // under the demo/jfc directory in the Java 2 SDK, Standard Edition.
        int returnVal = chooser.showOpenDialog(jLabel1);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	System.out.println("Vas a cargar el fichero: " +
                chooser.getSelectedFile().getName());
           
        	GestorConexion.setFile(chooser.getSelectedFile().getPath());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;
}
