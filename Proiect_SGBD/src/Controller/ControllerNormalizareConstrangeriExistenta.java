
package Controller;

import Services.ManagerTabele;
import View.FereastraNormalizareConstrangeriExistenta;
import javax.swing.JOptionPane;

public class ControllerNormalizareConstrangeriExistenta {
    private static ControllerNormalizareConstrangeriExistenta controller;
    private ManagerTabele managerTabele;
    private FereastraNormalizareConstrangeriExistenta fereastra;
    private String tabel;
    
    private ControllerNormalizareConstrangeriExistenta() {}
    
    public static ControllerNormalizareConstrangeriExistenta getInstance() {
        if (controller == null) {
            controller = new ControllerNormalizareConstrangeriExistenta(); 
        }
        return controller;
    }
    
    public void normalizareConstrangeriExistenta() {
        fereastra = new FereastraNormalizareConstrangeriExistenta(this);
        managerTabele = ManagerTabele.getInstance();
        fereastra.afiseaza();
    }
    
    public void cautaTabele() {
        String[] tabele = managerTabele.cautaTabeleFaraConstrangeriExistenta();
        if (tabele == null) {
            JOptionPane.showMessageDialog(null, "Nu exista tabele in BD sau toate tabelele din BD au constrangeri de existenta");
        } else {
            fereastra.afiseazaTabele(tabele);
        } 
    }
    
    public void selecteazaTabel(String tabel) {
        this.tabel = tabel;
        String[] coloane = managerTabele.cautaColoaneCareNuContinNull(tabel);
        if (coloane == null) {
            JOptionPane.showMessageDialog(null, "Toate coloanele din tabelul " + tabel + " au valori null in coloane. Trebuie introduse date in toate campurile de pe cel putin o coloana");
            fereastra.inchide();
        } else {
            fereastra.afiseazaColoane(coloane);
        }   
    }
    
    public void selecteazaColoana(String coloana) { 
        if (!managerTabele.adaugaConstrangere(tabel, coloana)) {
            JOptionPane.showMessageDialog(null, "Nu s-a adaugat constrangerea NOT NULL pe coloana " + coloana);
        } else {
            JOptionPane.showMessageDialog(null, "S-a adaugat constrangerea NOT NULL pe coloana " + coloana);
        }
        fereastra.inchide();
    }
}
