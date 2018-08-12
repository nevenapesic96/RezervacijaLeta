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
public class UcitajRezervaciju extends AbstractGenericOperation{
    RezervacijaLetaEntity rezervacija;

    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof RezervacijaLetaEntity) {
            RezervacijaLetaEntity rezervacija = (RezervacijaLetaEntity) ide;
        } else {
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        rezervacija = (RezervacijaLetaEntity) db.vratiSaID(ide);
        StavkaRezervacije stavka=new StavkaRezervacije();
        stavka.setRezervacija(rezervacija);
        List<IDomainEntity> stavke=db.vratiSaUslovom(stavka);
        List<StavkaRezervacije> stavkeE=new ArrayList<>();
        for (IDomainEntity id : stavke) {
            StavkaRezervacije s=(StavkaRezervacije) id;
            s.setRezervacija(rezervacija);
            stavkeE.add(s);
        }
        rezervacija.setStavke(stavkeE);
    }

    public IDomainEntity vratiObjekat() {
        return rezervacija;
    }
}
