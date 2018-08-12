/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author NEVEN
 */
public interface IDomainEntity {

    public String getNazivTabele();

    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception;

    public String getNaziviKolona();
    
    public String getVrednosti();

    public PreparedStatement ubaciVrednosti(PreparedStatement ps) throws Exception;

    public String getNazivIDKolone();

    public boolean isIdAutoincrement();

    public void setAutoincrementId(Long id);

    public String vratiStringZaJoin();

    public String vratiVrednostID();

    public String getVrednostiZaUpdate();

    public String vratiSlozeniUslov();

    public PreparedStatement ubaciSlozeniID(PreparedStatement ps) throws Exception;

    public boolean daLiJeSlozenKljuc();

    public PreparedStatement ubaciVrednostiSlozenKljuc(PreparedStatement ps) throws Exception;

    public void upisiPrimarni(PreparedStatement ps);

    public String vratiUslov();
    
}
