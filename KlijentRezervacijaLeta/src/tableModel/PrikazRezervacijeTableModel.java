/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import domen.RezervacijaLetaEntity;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NEVEN
 */
public class PrikazRezervacijeTableModel extends AbstractTableModel{

    List<RezervacijaLetaEntity> rezervacije;
    String kolone[]={"Sifra rezervacije","Let", "Putnik", "Broj karata", "Datum rezervacije", "Vreme rezervacije"};

    public PrikazRezervacijeTableModel(List<RezervacijaLetaEntity> rezervacije) {
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
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
                return rezervacija.getSifraRezervacije();
            case 1:
                return rezervacija.getLet();
            case 2:
                return rezervacija.getPutnik();
            case 3:
                return rezervacija.getStavke() != null ? rezervacija.getStavke().size() : 0;
            case 4:
                return rezervacija.getDatumRezervacije() != null ? rezervacija.getDatumRezervacije() : "-";
            case 5:
                return rezervacija.getVremeRezervacije() != null ? rezervacija.getVremeRezervacije() : "-";
            default:
                return "N/A";
        }
    }

    public void osveziTabelu(List<RezervacijaLetaEntity> rezervacije) {
        this.rezervacije = rezervacije;
        fireTableDataChanged();
    }
    
}
