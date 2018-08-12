/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import domen.LetEntity;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NEVEN
 */
public class LetTableModel extends AbstractTableModel{

    List<LetEntity> letovi;
    String kolone[]={"Sifra leta","Datum polaska","Vreme polaska","Grad","Kompanija","Tip aviona"};

    public LetTableModel(List<LetEntity> letovi) {
        this.letovi = letovi;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return letovi.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        LetEntity let=letovi.get(rowIndex);
        switch(columnIndex){
            case 0:
                return let.getSifraLeta();
            case 1:
                return let.getDatumPolaska();
            case 2:
                return let.getVremePolaska();
            case 3:
                return let.getGrad().getNaziv();
            case 4:
                return let.getKompanija().getNaziv();
            case 5:
                return let.getTipAviona().getNaziv();
            default:
                return "N/A";
        }
    }

    public void osveziTabelu() {
        fireTableDataChanged();
    }
    
}
