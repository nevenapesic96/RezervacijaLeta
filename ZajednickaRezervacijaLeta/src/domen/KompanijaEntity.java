/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEVEN
 */
public class KompanijaEntity implements Serializable, IDomainEntity{
    private Long sifraKompanije;
    private String naziv;

    public KompanijaEntity() {
    }

    public KompanijaEntity(Long sifraKompanije, String naziv) {
        this.sifraKompanije = sifraKompanije;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getSifraKompanije() {
        return sifraKompanije;
    }

    public void setSifraKompanije(Long sifraKompanije) {
        this.sifraKompanije = sifraKompanije;
    }

    @Override
    public String toString() {
        return getNaziv();
    }

    @Override
    public String getNazivTabele() {
        return "kompanija";
    }

    @Override
    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception {
        Long sifraKompanijeRS=rs.getLong("sifraKompanije");
        String nazivRS=rs.getString("naziv");
        
        return new KompanijaEntity(sifraKompanijeRS, nazivRS);
    }

    @Override
    public String getNaziviKolona() {
        return null;
    }

    @Override
    public String getVrednosti() {
        return null;
    }

    @Override
    public PreparedStatement ubaciVrednosti(PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNazivIDKolone() {
        return "sifraKompanije";
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.sifraKompanije=id;
    }

    @Override
    public String vratiStringZaJoin() {
        return "";
    }

    @Override
    public String vratiVrednostID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiSlozeniUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement ubaciSlozeniID(PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean daLiJeSlozenKljuc() {
        return false;
    }

    @Override
    public PreparedStatement ubaciVrednostiSlozenKljuc(PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void upisiPrimarni(PreparedStatement ps) {
        try {
            ps.setLong(1, sifraKompanije);
        } catch (SQLException ex) {
            Logger.getLogger(KompanijaEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
