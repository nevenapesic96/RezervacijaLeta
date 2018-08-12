/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.GradEntity;
import domen.IDomainEntity;
import java.util.List;

/**
 *
 * @author NEVEN
 */
public class UcitajGradove extends AbstractGenericOperation{

    List<IDomainEntity> lista;
    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof GradEntity) {
            GradEntity grad = (GradEntity) ide;
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
