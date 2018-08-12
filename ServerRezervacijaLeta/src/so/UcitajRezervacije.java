/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomainEntity;
import domen.RezervacijaLetaEntity;
import domen.StavkaRezervacije;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NEVEN
 */
public class UcitajRezervacije extends AbstractGenericOperation{
    List<IDomainEntity> listaD=new ArrayList<>();
    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof RezervacijaLetaEntity) {
            RezervacijaLetaEntity rez = (RezervacijaLetaEntity) ide;
        } else {
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        List<IDomainEntity> lista=db.vratiSve(ide);
        for (IDomainEntity iDomainEntity : lista) {
            RezervacijaLetaEntity rez=(RezervacijaLetaEntity) iDomainEntity;
            StavkaRezervacije stavka=new StavkaRezervacije();
            stavka.setRezervacija(rez);
            List<IDomainEntity> listaStav=db.vratiSaUslovom(stavka);
            List<StavkaRezervacije> stZaRez=new ArrayList<>();
            for (IDomainEntity stavkaI : listaStav) {
                StavkaRezervacije st=(StavkaRezervacije) stavkaI;
                st.setRezervacija(rez);
                stZaRez.add(st);
            }
            rez.setStavke(stZaRez);
        listaD.add(rez);
        }
    }
    
    public List<IDomainEntity> getLista() {
        return listaD;
    }
}
