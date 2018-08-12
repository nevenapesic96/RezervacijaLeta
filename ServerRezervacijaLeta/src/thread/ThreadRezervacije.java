/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import db.DatabaseRepository;
import domen.IDomainEntity;
import domen.RezervacijaLetaEntity;
import form.RezervacijeTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import so.UcitajRezervacije;

/**
 *
 * @author NEVEN
 */
public class ThreadRezervacije extends Thread{

    JTable jtblTransakcije;

    public ThreadRezervacije(JTable jtblTransakcije) {
        this.jtblTransakcije = jtblTransakcije;
    }

    @Override
    public void run(){ 
        List<RezervacijaLetaEntity> rezervacije;
        TableModel tm;
        try {
            rezervacije=ucitajRezervacije();
            tm=new RezervacijeTableModel(rezervacije);
            jtblTransakcije.setModel(tm);
        } catch (Exception ex) {
            Logger.getLogger(ThreadRezervacije.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(!isInterrupted()){
            try {
                rezervacije=ucitajRezervacije();
                RezervacijeTableModel rtm=(RezervacijeTableModel) jtblTransakcije.getModel();
                rtm.osvezi(rezervacije);
            } catch (Exception ex) {
                Logger.getLogger(ThreadRezervacije.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRezervacije.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<RezervacijaLetaEntity> ucitajRezervacije(){
        List<RezervacijaLetaEntity> lista=new ArrayList<>();
        try {
            UcitajRezervacije ucitajRezervacije=new UcitajRezervacije();
            ucitajRezervacije.templateExecute(new RezervacijaLetaEntity());
            List<IDomainEntity> rezervacijeSve=ucitajRezervacije.getLista();
            for (IDomainEntity iDomainEntity : rezervacijeSve) {
                RezervacijaLetaEntity rez=(RezervacijaLetaEntity) iDomainEntity;
                if(rez.getDatumRezervacije().isEqual(LocalDate.now()))
                    lista.add(rez);
            }
        } catch (Exception ex) {
            Logger.getLogger(ThreadRezervacije.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
