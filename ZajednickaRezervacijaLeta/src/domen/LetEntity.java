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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEVEN
 */
public class LetEntity implements Serializable, IDomainEntity{
    private Long sifraLeta;
    private LocalDate datumPolaska;
    private LocalTime vremePolaska;
    private GradEntity grad;
    private KompanijaEntity kompanija;
    private TipAvionaEntity tipAviona;
    
    private String user;

    public LetEntity() {
    }

    public LetEntity(Long sifraLeta, LocalDate datumPolaska, LocalTime vremePolaska, GradEntity grad, KompanijaEntity kompanija, TipAvionaEntity tipAviona) {
        this.sifraLeta = sifraLeta;
        this.datumPolaska = datumPolaska;
        this.vremePolaska = vremePolaska;
        this.grad = grad;
        this.kompanija = kompanija;
        this.tipAviona = tipAviona;
    }

    public LetEntity(Long sifraLeta, LocalDate datumPolaska, LocalTime vremePolaska, GradEntity grad, KompanijaEntity kompanija, TipAvionaEntity tipAviona, String user) {
        this.sifraLeta = sifraLeta;
        this.datumPolaska = datumPolaska;
        this.vremePolaska = vremePolaska;
        this.grad = grad;
        this.kompanija = kompanija;
        this.tipAviona = tipAviona;
        this.user = user;
    }

    public TipAvionaEntity getTipAviona() {
        return tipAviona;
    }

    public void setTipAviona(TipAvionaEntity tipAviona) {
        this.tipAviona = tipAviona;
    }

    public Long getSifraLeta() {
        return sifraLeta;
    }

    public void setSifraLeta(Long sifraLeta) {
        this.sifraLeta = sifraLeta;
    }

    public LocalDate getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(LocalDate datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public LocalTime getVremePolaska() {
        return vremePolaska;
    }

    public void setVremePolaska(LocalTime vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public GradEntity getGrad() {
        return grad;
    }

    public void setGrad(GradEntity grad) {
        this.grad = grad;
    }

    public KompanijaEntity getKompanija() {
        return kompanija;
    }

    public void setKompanija(KompanijaEntity kompanija) {
        this.kompanija = kompanija;
    }

    @Override
    public String getNazivTabele() {
        return "let";
    }

    @Override
    public IDomainEntity vratiObjekat(ResultSet rs) throws Exception {
        Long sifraL=rs.getLong(1);
        LocalDate datumP=rs.getDate(2).toLocalDate();
        LocalTime vremeP=rs.getTime(3).toLocalTime();
        GradEntity gradG=new GradEntity(rs.getLong(8), rs.getString(9));
        KompanijaEntity kompanijaK=new KompanijaEntity(rs.getLong(10), rs.getString(11));
        TipAvionaEntity tipA=new TipAvionaEntity(rs.getLong(12), rs.getString(13), rs.getInt(14));
        //ovde treba da se izmeni
        return new LetEntity(sifraL, datumP, vremeP, gradG, kompanijaK, tipA);
    }

    @Override
    public String getNaziviKolona() {
        return "sifraLeta,datumPolaska,vremePolaska,sifraGrada,sifraKompanije,sifraTipaAviona,user";
    }

    @Override
    public String getVrednosti() {
        return "?,?,?,?,?,?,?";
    }

    @Override
    public PreparedStatement ubaciVrednosti(PreparedStatement ps) throws Exception{
        ps.setLong(1, sifraLeta);
        ps.setDate(2, Date.valueOf(datumPolaska));
        ps.setTime(3, Time.valueOf(vremePolaska));
        ps.setLong(4, grad.getSifraGrada());
        ps.setLong(5, kompanija.getSifraKompanije());
        ps.setLong(6, tipAviona.getSifraTipaAviona());
        ps.setString(7, user);
        return ps;
    }

    @Override
    public String getNazivIDKolone() {
        return "sifraLeta";
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Long id) {
        this.sifraLeta=id;
    }

    @Override
    public String vratiStringZaJoin() {
        return " INNER JOIN grad ON let.sifraGrada=grad.sifraGrada INNER JOIN kompanija ON let.sifraKompanije=kompanija.sifraKompanije INNER JOIN tipaviona ON let.sifraTipaAviona=tipaviona.sifraTipaAviona";
    }

    @Override
    public String vratiVrednostID() {
        return sifraLeta+"";
    }

    @Override
    public String getVrednostiZaUpdate() {
        return "sifraLeta=?,datumPolaska=?,vremePolaska=?,sifraGrada=?,sifraKompanije=?,sifraTipaAviona=?,user=?";
    }

    @Override
    public String toString() {
        return grad.getNaziv()+": "+datumPolaska+", "+vremePolaska;
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
            ps.setLong(1, sifraLeta);
        } catch (SQLException ex) {
            Logger.getLogger(LetEntity.class.getName()).log(Level.SEVERE, null, ex);
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
