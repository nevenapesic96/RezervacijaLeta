/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import domen.StavkaRezervacije;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NEVEN
 */
public class RezervacijaTableModel extends AbstractTableModel{

    List<StavkaRezervacije> stavke;
    String kolone[]={"Rbr", "Putnik","Broj pasosa"};

    public RezervacijaTableModel(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        return stavke.size();
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
        StavkaRezervacije stavka=stavke.get(rowIndex);
        switch(columnIndex){
            case 0:
                return stavka.getRbr();
            case 1:
                return stavka.getPutnik();
            case 2:
                return stavka.getBrojPasosa();
            default:
                return "N/A";
        }
    }

    public void dodajStavku(StavkaRezervacije stavka) {
        if(!daLiPostojiStavka(stavka)){
            stavka.setRbr(Long.valueOf(stavke.size()+1));
            stavke.add(stavka);
            fireTableDataChanged();
        }
    }

    public void izbaci(int selektovaRed) {
        stavke.remove(selektovaRed);
        osveziRedneBrojeve();
        fireTableDataChanged();
    }

    public List<StavkaRezervacije> vratiListu() {
        return stavke;
    }

    private boolean daLiPostojiStavka(StavkaRezervacije rez) {
        for (StavkaRezervacije stavkaRez : stavke) {
            if(stavkaRez.getPutnik().equals(rez.getPutnik()) &&
                    stavkaRez.getBrojPasosa().equals(rez.getBrojPasosa()))
                return true;
        }
        return false;
    }

    private void osveziRedneBrojeve() {
        int brojac=1;
        for (StavkaRezervacije stavkaRezervacije : stavke) {
            stavkaRezervacije.setRbr(Long.valueOf(brojac++));
        }
    }

    public void osveziTabelu(List<StavkaRezervacije> stavke) {
        this.stavke=stavke;
        fireTableDataChanged();
    }
    
}
