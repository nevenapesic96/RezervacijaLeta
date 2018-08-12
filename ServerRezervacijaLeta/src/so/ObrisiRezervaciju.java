/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomainEntity;
import domen.RezervacijaLetaEntity;
import domen.StavkaRezervacije;

/**
 *
 * @author NEVEN
 */
public class ObrisiRezervaciju extends AbstractGenericOperation{
    RezervacijaLetaEntity rezervacija;

    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof RezervacijaLetaEntity) {
            rezervacija = (RezervacijaLetaEntity) ide;
        } else {
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        rezervacija = (RezervacijaLetaEntity) ide;
        StavkaRezervacije stavka=new StavkaRezervacije();
        stavka.setRezervacija(rezervacija);
        db.obrisi(stavka);
        db.obrisi(rezervacija);
    }
}
