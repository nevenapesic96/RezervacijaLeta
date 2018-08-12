/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import domen.PutnikEntity;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NEVEN
 */
public class PutnikTableModel extends AbstractTableModel{

    List<PutnikEntity> putnici;
    String kolone[]={"Sifra putnika","Ime","Prezime","jmbg","telefon"};

    public PutnikTableModel(List<PutnikEntity> putnici) {
        this.putnici = putnici;
    }

    @Override
    public int getRowCount() {
        return putnici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PutnikEntity putnik=putnici.get(rowIndex);
        switch(columnIndex){
            case 0:
                return putnik.getSifraPutnika();
            case 1:
                return putnik.getIme();
            case 2:
                return putnik.getPrezime();
            case 3:
                return putnik.getJmbg();
            case 4:
                return putnik.getTelefon();
            default:
                return "N/A";
        }
    }
    
}
