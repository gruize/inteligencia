/*
 * IAICbuenoApp.java
 */

package GUI;

import java.io.File;
import java.io.PrintStream;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class IAICbuenoApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new IAICbuenoView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of IAICbuenoApp
     */
    public static IAICbuenoApp getApplication() {
        return Application.getInstance(IAICbuenoApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
    	//System.setOut(new PrintStream(new File("logs/logGlobal.og")));
        launch(IAICbuenoApp.class, args);
    }
}
