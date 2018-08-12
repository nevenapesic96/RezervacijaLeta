/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomainEntity;
import domen.PutnikEntity;
import java.util.List;

/**
 *
 * @author NEVEN
 */
public class UcitajPutnike extends AbstractGenericOperation{
    List<IDomainEntity> lista;
    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof PutnikEntity) {
            PutnikEntity grad = (PutnikEntity) ide;
        } else {
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        lista=db.vratiSve(ide);
    }
    
    public List<IDomainEntity> getLista() {
        return lista;
    }
}
