/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomainEntity;
import domen.LetEntity;

/**
 *
 * @author NEVEN
 */
public class ZapamtiLet extends AbstractGenericOperation{
     LetEntity let;

    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide instanceof LetEntity) {
            let = (LetEntity) ide;
        } else {
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        db.zapamti(ide);
    }
}
