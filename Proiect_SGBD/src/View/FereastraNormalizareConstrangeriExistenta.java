
package View;

import Controller.ControllerNormalizareConstrangeriExistenta;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FereastraNormalizareConstrangeriExistenta extends javax.swing.JFrame {
    
    public FereastraNormalizareConstrangeriExistenta(ControllerNormalizareConstrangeriExistenta controller) {
        initComponents();
        this.controller = controller;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        bCauta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FereastraNormalizareConstrangeriExistenta");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        bCauta.setText("Cauta Tabele");
        bCauta.setMaximumSize(new java.awt.Dimension(93, 32));
        bCauta.setMinimumSize(new java.awt.Dimension(93, 32));
        bCauta.setPreferredSize(new java.awt.Dimension(93, 32));
        bCauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCautaActionPerformed(evt);
            }
        });

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bCauta, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bCauta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCautaActionPerformed
        // TODO add your handling code here:
        if (bCauta.getText().equals("Cauta Tabele")) {
            controller.cautaTabele();
            bCauta.setText("Selecteaza Tabel");
        } else if (bCauta.getText().equals("Selecteaza Tabel")) {
            if(jList1.getSelectedIndices().length == 0){
                JOptionPane.showMessageDialog(null, "Selectati un tabel!");
                return;
            }
            jList1.setEnabled(false);
            controller.selecteazaTabel(jList1.getSelectedValue());
            bCauta.setText("Selecteaza Coloana");
        } else {
            if(jList2.getSelectedIndices().length != 1){
                JOptionPane.showMessageDialog(null, "Selectati o singura coloana!");
                return;
            }
            jList2.setEnabled(false);
            bCauta.setEnabled(false);
            controller.selecteazaColoana(jList2.getSelectedValue());
        }
    }//GEN-LAST:event_bCautaActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCauta;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    
    private final ControllerNormalizareConstrangeriExistenta controller;
    
    
    public void afiseazaTabele(String[] tabele) {
        introduceInLista(jList1, tabele);
    }
    
    public void afiseazaColoane(String[] coloane) {
        introduceInLista(jList2, coloane);
    }
    
    public void introduceInLista(javax.swing.JList list, String[] iteme) {
        DefaultListModel newModel = new DefaultListModel();
        for (int i = 0; i < iteme.length; i++) {
            newModel.addElement(iteme[i]);
        }
        list.setModel(newModel);
    }
    
    public void inchide() {
        this.dispose();
    }
    
    public void afiseaza() {
        this.setVisible(true);
    }
}
