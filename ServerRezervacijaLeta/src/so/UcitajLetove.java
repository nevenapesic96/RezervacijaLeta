/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomainEntity;
import domen.LetEntity;
import java.util.List;

/**
 *
 * @author NEVEN
 */
public class UcitajLetove extends AbstractGenericOperation{
    List<IDomainEntity> lista;
    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof LetEntity) {
            LetEntity grad = (LetEntity) ide;
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
