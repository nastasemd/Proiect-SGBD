
package Controller;

import Services.Conexiune;
import View.FereastraLogare;
import javax.swing.JOptionPane;

public class ControllerLogare {
    private static ControllerLogare controller;
    private FereastraLogare fereastra;
    private Conexiune conexiune;
    private ControllerUtilizator controllerUtilizator;
    
    private ControllerLogare() {}
    
    public static ControllerLogare getInstance() {
        if (controller == null) {
            controller = new ControllerLogare();
        }
        return controller;
    }
    
    public void cereLogare() {
        fereastra = new FereastraLogare(this);
        fereastra.afiseaza();
    }
    
    public void trimiteDateLogare(String username, String password) {
        conexiune = Conexiune.getInstance();
        
        if(conexiune.logare(username, password)) {
            controllerUtilizator = ControllerUtilizator.getInstance();
            controllerUtilizator.cereNormalizareaBazeiDeDate();
            fereastra.inchide();
        } else {
            JOptionPane.showMessageDialog(null, "Username sau parola gresita");
            fereastra.golireCampuri();
        }
    }
}
