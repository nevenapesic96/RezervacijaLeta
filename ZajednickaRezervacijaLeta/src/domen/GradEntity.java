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
public class GradEntity implements Serializable, IDomainEntity{
    private Long sifraGrada;
    private String naziv;

    public GradEntity() {
    }

    public GradEntity(Long sifraGrada, String naziv) {
        this.sifraGrada = sifraGrada;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getSifraGrada() {
        return sifraGrada;
    }

    public void setSifraGrada(Long sifraGrada) {
        this.sifraGrada = sifraGrada;
    }

    @Override
    public String toString() {
        return getNaziv();
    }
    

    @Override
    public String getNazivTabele() {
        return "grad";
    }

    @Override
    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception{
        Long sifraGradaRS=rs.getLong("sifraGrada");
        String nazivRS=rs.getString("naziv");
        GradEntity grad=new GradEntity(sifraGradaRS,nazivRS);
        
        return grad;
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
        return "sifraGrada";
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.sifraGrada=id;
    }

    @Override
    public String vratiStringZaJoin() {
        return "";
    }

    @Override
    public String vratiVrednostID() {
        return "sifraGrada";
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
            ps.setLong(1, sifraGrada);
        } catch (SQLException ex) {
            Logger.getLogger(GradEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
