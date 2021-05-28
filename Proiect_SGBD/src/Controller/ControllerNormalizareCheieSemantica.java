
package Controller;

import Services.ManagerTabele;
import View.FereastraNormalizareCheieSemantica;
import javax.swing.JOptionPane;

public class ControllerNormalizareCheieSemantica {
    private static ControllerNormalizareCheieSemantica controller;
    private ManagerTabele managerTabele;
    private FereastraNormalizareCheieSemantica fereastra;
    private String tabel;
    
    private ControllerNormalizareCheieSemantica() {}
    
    public static ControllerNormalizareCheieSemantica getInstance() {
        if (controller == null) {
            controller = new ControllerNormalizareCheieSemantica(); 
        }
        return controller;
    }
    
    public void normalizareCheieSemantica() {
        fereastra = new FereastraNormalizareCheieSemantica(this);
        managerTabele = ManagerTabele.getInstance();
        fereastra.afiseaza();
    }
    
    public void cautaTabele() {
        String[] tabele = managerTabele.cautaTabeleFaraCheieSemantica();
        if (tabele == null) {
            JOptionPane.showMessageDialog(null, "Nu exista tabele in BD care se pot normaliza.");
        } else {
            fereastra.afiseazaTabele(tabele);
        } 
    }
    
    public void selecteazaTabel(String tabel) {
        this.tabel = tabel;
        String[] coloane = managerTabele.cautaColoaneFaraDuplicate(tabel);
        if (coloane == null) {
            JOptionPane.showMessageDialog(null, "Toate coloanele din tabelul " + tabel + " au valori duplicate in coloane. Tabelul nu a fost proiectat corect.");
            fereastra.inchide();
        } else {
            fereastra.afiseazaColoane(coloane);
        }   
    }
    
    public void selecteazaColoana(String coloana) { 
        if (!managerTabele.adaugaCheieSemantica(tabel, coloana)) {
            JOptionPane.showMessageDialog(null, "Nu s-a adaugat cheie semantica pe coloana " + coloana);
        } else {
            JOptionPane.showMessageDialog(null, "S-a adaugat cheie semantica pe coloana " + coloana);
        }
        fereastra.inchide();
    }
    
    public void selecteazaColoane(String coloana1, String coloana2) { 
        if (!managerTabele.adaugaCheieSemanticaCompusa(tabel, coloana1, coloana2)) {
            JOptionPane.showMessageDialog(null, "Nu s-a adaugat cheie semantica pe coloanele " + coloana1 + " si " + coloana2);
        } else {
            JOptionPane.showMessageDialog(null, "S-a adaugat cheie semantica pe coloanele " + coloana1 + " si " + coloana2);
        }
        fereastra.inchide();
    }
}
