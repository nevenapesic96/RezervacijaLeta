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
public class TipAvionaEntity implements Serializable, IDomainEntity{
    private Long sifraTipaAviona;
    private String naziv;
    private int brojSedista;

    public TipAvionaEntity() {
    }

    public TipAvionaEntity(Long sifraTipaAviona, String naziv, int brSedista) {
        this.sifraTipaAviona = sifraTipaAviona;
        this.naziv = naziv;
        this.brojSedista = brSedista;
    }

    public int getBrSedista() {
        return brojSedista;
    }

    public void setBrSedista(int brojSedista) {
        this.brojSedista = brojSedista;
    }

    public Long getSifraTipaAviona() {
        return sifraTipaAviona;
    }

    public void setSifraTipaAviona(Long sifraTipaAviona) {
        this.sifraTipaAviona = sifraTipaAviona;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return getNaziv();
    }

    @Override
    public String getNazivTabele() {
        return "tipaviona";
    }

    @Override
    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception {
        Long sifraTipaAvionaRS=rs.getLong("sifraTipaAviona");
        String nazivRS=rs.getString("naziv");
        int brojSedistaRS=rs.getInt("brojSedista");
        
        return new TipAvionaEntity(sifraTipaAvionaRS, nazivRS, brojSedistaRS);
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
        return "sifraTipaAviona";
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.sifraTipaAviona=id;
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
            ps.setLong(1, sifraTipaAviona);
        } catch (SQLException ex) {
            Logger.getLogger(TipAvionaEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
