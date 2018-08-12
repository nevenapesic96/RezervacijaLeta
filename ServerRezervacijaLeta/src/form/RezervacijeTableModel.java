/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.RezervacijaLetaEntity;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NEVEN
 */
public class RezervacijeTableModel extends AbstractTableModel{

    List<RezervacijaLetaEntity> rezervacije;
    String[] kolone={"Let","Putnik","Vreme Rezervacije"};

    public RezervacijeTableModel(List<RezervacijaLetaEntity> rezervacije) {
        this.rezervacije = rezervacije;
    }
    
    @Override
    public int getRowCount() {
        return rezervacije.size();
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
        RezervacijaLetaEntity rezervacija=rezervacije.get(rowIndex);
        switch(columnIndex){
            case 0:
                return rezervacija.getLet().toString();
            case 1:
                return rezervacija.getPutnik().toString();
            case 2:
                return rezervacija.getVremeRezervacije();
            default:
                return "N/A";
        }
    }

    public void osvezi(List<RezervacijaLetaEntity> rezervacije) {
        this.rezervacije=rezervacije;
        fireTableDataChanged();
    }
    
}
