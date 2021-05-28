
package Controller;
import View.FereastraNormalizari;

public class ControllerUtilizator {
    
    private static ControllerUtilizator controllerUtilizator;
    private FereastraNormalizari fereastra;
    
    private ControllerUtilizator() {}
   
    public static ControllerUtilizator getInstance() {
        if (controllerUtilizator == null) {
            controllerUtilizator = new ControllerUtilizator();
        }
        return controllerUtilizator;
    }
    
    public void normalizareConstrangeriExistenta() {
        ControllerNormalizareConstrangeriExistenta.getInstance().normalizareConstrangeriExistenta();
    }
    
    public void normalizareCheiePrimara(){
        ControllerNormalizareCheiePrimara.getInstance().normalizareCheiePrimara();

    }
    
    public void normalizareCheieSemantica(){
        ControllerNormalizareCheieSemantica.getInstance().normalizareCheieSemantica();
    }
    
    public void cereNormalizareaBazeiDeDate() { 
        fereastra = new FereastraNormalizari(this);
        fereastra.afiseaza(); 
    }
}
