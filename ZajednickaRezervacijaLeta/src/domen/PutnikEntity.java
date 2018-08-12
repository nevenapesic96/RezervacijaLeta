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
public class PutnikEntity implements Serializable, IDomainEntity{
    private Long sifraPutnika;
    private String ime;
    private String prezime;
    private String jmbg;
    private String telefon;

    private String user;
    
    public PutnikEntity() {
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }
    

    public PutnikEntity(Long sifraPutnika, String ime, String prezime, String jmbg, String telefon) {
        this.sifraPutnika = sifraPutnika;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.telefon = telefon;
    }

    public PutnikEntity(Long sifraPutnika, String ime, String prezime, String jmbg, String telefon, String user) {
        this.sifraPutnika = sifraPutnika;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.telefon = telefon;
        this.user = user;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Long getSifraPutnika() {
        return sifraPutnika;
    }

    public void setSifraPutnika(Long sifraPutnika) {
        this.sifraPutnika = sifraPutnika;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String getNazivTabele() {
        return "putnik";
    }

    @Override
    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception {
        Long sifraP=rs.getLong("sifraPutnika");
        String imeP=rs.getString("ime");
        String prezimeP=rs.getString("prezime");
        String jmbgP=rs.getString("jmbg");
        String telefonP=rs.getString("telefon");
        String userP=rs.getString("user");
        
        return new PutnikEntity(sifraP, imeP, prezimeP, jmbgP, telefonP,userP);
    }

    @Override
    public String getNaziviKolona() {
        return "sifraPutnika,ime,prezime,jmbg,telefon,user";
    }

    @Override
    public String getVrednosti() {
        return "?,?,?,?,?,?";
    }

    @Override
    public PreparedStatement ubaciVrednosti(PreparedStatement ps) throws Exception {
        ps.setLong(1, sifraPutnika);
        ps.setString(2, ime);
        ps.setString(3, prezime);
        ps.setString(4, jmbg);
        ps.setString(5, telefon);
        ps.setString(6, user);
        return ps;
    }

    @Override
    public String getNazivIDKolone() {
        return "sifraPutnika";
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.sifraPutnika=id;
    }

    @Override
    public String vratiStringZaJoin() {
        return "";
    }

    @Override
    public String vratiVrednostID() {
        return sifraPutnika+"";
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "sifraPutnika=?,ime=?,prezime=?,jmbg=?,telefon=?,user=?";
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
            ps.setLong(1, sifraPutnika);
        } catch (SQLException ex) {
            Logger.getLogger(PutnikEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
