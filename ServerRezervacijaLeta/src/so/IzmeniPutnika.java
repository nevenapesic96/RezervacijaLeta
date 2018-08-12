/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomainEntity;
import domen.PutnikEntity;

/**
 *
 * @author NEVEN
 */
public class IzmeniPutnika extends AbstractGenericOperation{
    PutnikEntity putnik;
    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof PutnikEntity) {
            putnik = (PutnikEntity) ide;
        } else {
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        db.azuriraj(ide);
    }
}
