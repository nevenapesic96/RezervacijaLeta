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
public class IzmeniRezervaciju extends AbstractGenericOperation{
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
        db.startTransaction();
        rezervacija=(RezervacijaLetaEntity) ide;
        try {
            db.azuriraj(rezervacija);
            StavkaRezervacije s=new StavkaRezervacije();
            s.setRezervacija(rezervacija);
            db.obrisi(s);
            System.out.println(rezervacija.getStavke().size());
            for (StavkaRezervacije stavka : rezervacija.getStavke()) {
                stavka.getRezervacija().setSifraRezervacije(rezervacija.getSifraRezervacije());
                try {
                    db.zapamti(stavka);
                } catch (Exception ex) {
                    db.rollbackTransaction();
                }
            }
            db.commitTransaction();
        } catch (Exception ex) {
            db.rollbackTransaction();
        }
    }
}
