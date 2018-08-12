/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEVEN
 */
public class RezervacijaLetaEntity implements Serializable,IDomainEntity{
    private Long sifraRezervacije;
    private LetEntity let;
    private PutnikEntity putnik;
    private LocalDate datumRezervacije;
    private LocalTime vremeRezervacije;
    private String user;
    
    private List<StavkaRezervacije> stavke;
    public RezervacijaLetaEntity() {
    }

    public RezervacijaLetaEntity(Long sifraRezervacije,LetEntity let, PutnikEntity putnik, LocalDate datumRezervacije, LocalTime vremeRezervacije) {
        this.sifraRezervacije=sifraRezervacije;
        this.let = let;
        this.putnik = putnik;
        this.datumRezervacije = datumRezervacije;
        this.vremeRezervacije = vremeRezervacije;
    }

    public RezervacijaLetaEntity(Long sifraRezervacije, LetEntity let, PutnikEntity putnik, LocalDate datumRezervacije, LocalTime vremeRezervacije, String user, List<StavkaRezervacije> stavke) {
        this.sifraRezervacije = sifraRezervacije;
        this.let = let;
        this.putnik = putnik;
        this.datumRezervacije = datumRezervacije;
        this.vremeRezervacije = vremeRezervacije;
        this.user = user;
        this.stavke = stavke;
    }


    public LocalTime getVremeRezervacije() {
        return vremeRezervacije;
    }

    public void setVremeRezervacije(LocalTime vremeRezervacije) {
        this.vremeRezervacije = vremeRezervacije;
    }

    public LetEntity getLet() {
        return let;
    }

    public void setLet(LetEntity let) {
        this.let = let;
    }

    public PutnikEntity getPutnik() {
        return putnik;
    }

    public void setPutnik(PutnikEntity putnik) {
        this.putnik = putnik;
    }

    public LocalDate getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDate datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    @Override
    public String getNazivTabele() {
        return "rezervacija";
    }

    @Override
    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception {
        LocalDate datumR=rs.getDate(4).toLocalDate();
        LocalTime vremeR=rs.getTime(5).toLocalTime();
        PutnikEntity putnikR=new PutnikEntity(rs.getLong(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18));
        GradEntity gradR=new GradEntity(rs.getLong(20), rs.getString(21));
        KompanijaEntity kompanijaR=new KompanijaEntity(rs.getLong(22), rs.getString(23));
        TipAvionaEntity tipR=new TipAvionaEntity(rs.getLong(24), rs.getString(25), rs.getInt(26));
        LetEntity letR=new LetEntity(rs.getLong(7), rs.getDate(8).toLocalDate(), rs.getTime(9).toLocalTime(), gradR, kompanijaR, tipR);
        Long sifra=rs.getLong(1);   
        return new RezervacijaLetaEntity(sifra,letR, putnikR, datumR, vremeR);
    }

    @Override
    public String getNaziviKolona() {
        return "sifraLeta,sifraPutnika,datumRezervacije,vremeRezervacije,user";
    }

    @Override
    public String getVrednosti() {
        return "?,?,?,?,?";
    }

    @Override
    public PreparedStatement ubaciVrednosti(PreparedStatement ps) throws Exception {
        ps.setLong(1, let.getSifraLeta());
        ps.setLong(2, putnik.getSifraPutnika());
        ps.setDate(3, Date.valueOf(datumRezervacije));
        ps.setTime(4, Time.valueOf(vremeRezervacije));
        ps.setString(5, user);
        
        return ps;
    }

    @Override
    public String getNazivIDKolone() {
        return "sifraRezervacije";
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.sifraRezervacije=id;
    }

    @Override
    public String vratiStringZaJoin() {
        return " INNER JOIN let ON rezervacija.sifraLeta=let.sifraLeta INNER JOIN putnik ON rezervacija.sifraPutnika=putnik.sifraPutnika INNER JOIN grad ON let.sifraGrada=grad.sifraGrada INNER JOIN kompanija ON let.sifraKompanije=kompanija.sifraKompanije INNER JOIN tipaviona ON let.sifraTipaAviona=tipaviona.sifraTipaAviona";
    }

    @Override
    public String vratiVrednostID() {
        return "?";
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "sifraLeta=?,sifraPutnika=?,user=?";
    }

    @Override
    public String vratiSlozeniUslov() {
        return "sifraLeta=? AND sifraPutnika=?";
    }

    @Override
    public PreparedStatement ubaciSlozeniID(PreparedStatement ps) throws Exception{
        ps.setLong(1, let.getSifraLeta());
        ps.setLong(2, putnik.getSifraPutnika());
        
        return ps;
    }

    @Override
    public boolean daLiJeSlozenKljuc() {
        return true;
    }

    @Override
    public PreparedStatement ubaciVrednostiSlozenKljuc(PreparedStatement ps) throws Exception {
        ps.setLong(1, let.getSifraLeta());
        ps.setLong(2, putnik.getSifraPutnika());
        ps.setString(3, user);
        ps.setLong(4, sifraRezervacije);
        return ps;
    }

    @Override
    public void upisiPrimarni(PreparedStatement ps) {
        try {
            ps.setLong(1, sifraRezervacije);
        } catch (SQLException ex) {
            Logger.getLogger(RezervacijaLetaEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getSifraRezervacije() {
        return sifraRezervacije;
    }

    public void setSifraRezervacije(Long sifraRezervacije) {
        this.sifraRezervacije = sifraRezervacije;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
