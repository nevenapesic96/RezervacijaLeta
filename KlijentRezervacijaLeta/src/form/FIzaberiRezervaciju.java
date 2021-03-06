/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import controller.Controller;
import domen.LetEntity;
import domen.PutnikEntity;
import domen.RezervacijaLetaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tableModel.PrikazRezervacijeTableModel;
import tableModel.RezervacijaTableModel;

/**
 *
 * @author NEVEN
 */
public class FIzaberiRezervaciju extends javax.swing.JFrame {

    /**
     * Creates new form FRezervacije
     */
    public FIzaberiRezervaciju() {
        initComponents();
        
        setLocationRelativeTo(null);
        popuniTabelu();
        prepareTableForSort();
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
        jtblRezervacije = new javax.swing.JTable();
        jbtnPrikazi = new javax.swing.JButton();
        jbtnObrisi = new javax.swing.JButton();
        jtxtPretraga = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sve rezervacije");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Izaberi rezervaciju"));

        jtblRezervacije.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblRezervacije);

        jbtnPrikazi.setText("Prikazi");
        jbtnPrikazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPrikaziActionPerformed(evt);
            }
        });

        jbtnObrisi.setText("Obrisi");
        jbtnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnObrisiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnObrisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnPrikazi)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPrikazi)
                    .addComponent(jbtnObrisi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Filter:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jtxtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPrikaziActionPerformed
        int selektovaRed=jtblRezervacije.getSelectedRow();
        if (selektovaRed >= 0) {
            RezervacijaLetaEntity rezervacija=new RezervacijaLetaEntity();
            rezervacija.setSifraRezervacije((Long) jtblRezervacije.getValueAt(selektovaRed, 0));

            JFrame novaRez=new FNovaRezervacija(rezervacija,this);
            novaRez.setVisible(true);
            //JDialog forma=new FIzmenaRezervacije(this,true,rezervacija,this);
            //forma.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Morate selektovati rezervaciju");
        }
    }//GEN-LAST:event_jbtnPrikaziActionPerformed

    private void jbtnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnObrisiActionPerformed
        int selektovaRed=jtblRezervacije.getSelectedRow();
        if (selektovaRed >= 0) {
            RezervacijaLetaEntity rezervacija=new RezervacijaLetaEntity();
            rezervacija.setSifraRezervacije((Long) jtblRezervacije.getValueAt(selektovaRed, 0));
            
            int odgovor=JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da izbrisete ovu rezervaciju?", "Da li ste sigurni", JOptionPane.YES_NO_OPTION); 
            if(odgovor==0){
                try {
                    if(Controller.obrisiRezervaciju(rezervacija)){
                        JOptionPane.showMessageDialog(rootPane, "Rezervacija je obrisan");
                        
                        try {
                                List<RezervacijaLetaEntity> rezervacije=Controller.vratiSveRezervacije();
                                PrikazRezervacijeTableModel tm=(PrikazRezervacijeTableModel) jtblRezervacije.getModel();
                                tm.osveziTabelu(rezervacije);
                                prepareTableForSort();
                                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Dogodila se greska prilikom brisanja rezervacije");
        }
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Rezervacija nije obrisana");
                    }       } catch (Exception ex) {
                    Logger.getLogger(FIzaberiLet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Morate selektovati rezervaciju");
        }
    }//GEN-LAST:event_jbtnObrisiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnObrisi;
    private javax.swing.JButton jbtnPrikazi;
    private javax.swing.JTable jtblRezervacije;
    private javax.swing.JTextField jtxtPretraga;
    // End of variables declaration//GEN-END:variables

    public void popuniTabelu() {
        try {
            List<RezervacijaLetaEntity> rezervacije=Controller.vratiSveRezervacije();
            TableModel tm=new PrikazRezervacijeTableModel(rezervacije);
            jtblRezervacije.setModel(tm);
        } catch (Exception ex) {
            Logger.getLogger(FIzaberiLet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void prepareTableForSort() {
        
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jtblRezervacije.getModel());
        jtblRezervacije.setRowSorter(rowSorter);

        jtxtPretraga.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                String filterCity = jtxtPretraga.getText();

                if (filterCity.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterCity));
                }
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                String text = jtxtPretraga.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
