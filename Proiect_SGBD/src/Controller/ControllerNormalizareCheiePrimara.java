
package Controller;

import Services.ManagerTabele;
import View.FereastraNormalizareCheiePrimara;
import javax.swing.JOptionPane;

public class ControllerNormalizareCheiePrimara {
    private static ControllerNormalizareCheiePrimara controller;
    private ManagerTabele managerTabele;
    private FereastraNormalizareCheiePrimara fereastra;
    private String tabel;
    
    private ControllerNormalizareCheiePrimara() {}
    
    public static ControllerNormalizareCheiePrimara getInstance() {
        if (controller == null) {
            controller = new ControllerNormalizareCheiePrimara(); 
        }
        return controller;
    }
    
    public void normalizareCheiePrimara() {
        fereastra = new FereastraNormalizareCheiePrimara(this);
        managerTabele = ManagerTabele.getInstance();
        fereastra.afiseaza();
    }
    
    public void cautaTabele() {
        String[] tabele = managerTabele.cautaTabeleFaraCheiePrimara();
        if (tabele == null) {
            JOptionPane.showMessageDialog(null, "Nu exista tabele in BD fara cheie primara.");
        } else {
            fereastra.afiseazaTabele(tabele);
        } 
    }
    
    public void selecteazaTabel(String tabel) {
        this.tabel = tabel;
        managerTabele.adaugaCheiePrimara(tabel);
        JOptionPane.showMessageDialog(null, "S-a adaugat cheie primara.");
        fereastra.inchide();
    }
}
