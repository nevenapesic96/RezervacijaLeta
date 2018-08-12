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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEVEN
 */
public class StavkaRezervacije implements Serializable,IDomainEntity{
    private Long rbr;
    private RezervacijaLetaEntity rezervacija;
    private String putnik;
    private String brojPasosa;
    
    private char status;

    public StavkaRezervacije(Long rbr, RezervacijaLetaEntity rezervacija, String putnik, String brojPasosa, char status) {
        this.rbr = rbr;
        this.rezervacija = rezervacija;
        this.putnik = putnik;
        this.brojPasosa = brojPasosa;
        this.status = status;
    }

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(Long rbr, RezervacijaLetaEntity rezervacija, String putnik, String brojPasosa) {
        this.rbr = rbr;
        this.rezervacija = rezervacija;
        this.putnik = putnik;
        this.brojPasosa = brojPasosa;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public Long getRbr() {
        return rbr;
    }

    public void setRbr(Long rbr) {
        this.rbr = rbr;
    }

    public RezervacijaLetaEntity getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(RezervacijaLetaEntity rezervacija) {
        this.rezervacija = rezervacija;
    }

    public String getPutnik() {
        return putnik;
    }

    public void setPutnik(String putnik) {
        this.putnik = putnik;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String getNazivTabele() {
        return "stavkarezervacije";
    }

    @Override
    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception {
        Long rb=rs.getLong("rbr");
        String putnikS=rs.getString("putnik");
        String pasos=rs.getString("brojPasosa");
        
        return new StavkaRezervacije(rb, null, putnikS, pasos);
    }

    @Override
    public String getNaziviKolona() {
        return "rbr,sifraRezervacije,putnik,brojPasosa";
    }

    @Override
    public String getVrednosti() {
        return "?,?,?,?";
    }

    @Override
    public PreparedStatement ubaciVrednosti(PreparedStatement ps) throws Exception {
        ps.setLong(1, rbr);
        ps.setLong(2, rezervacija.getSifraRezervacije());
        ps.setString(3, putnik);
        ps.setString(4, brojPasosa);
        return ps;
    }

    @Override
    public String getNazivIDKolone() {
        return "sifraRezervacije";
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Long id) {
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String vratiStringZaJoin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostID() {
        return rezervacija.getSifraRezervacije()+"";
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
    public PreparedStatement ubaciSlozeniID(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean daLiJeSlozenKljuc() {
        return false;
    }

    @Override
    public PreparedStatement ubaciVrednostiSlozenKljuc(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void upisiPrimarni(PreparedStatement ps) {
        try {
            ps.setLong(1, rbr);
            ps.setLong(2,rezervacija.getSifraRezervacije());
        } catch (SQLException ex) {
            Logger.getLogger(StavkaRezervacije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiUslov() {
        return "sifraRezervacije="+rezervacija.getSifraRezervacije();
    }
    
    
}
