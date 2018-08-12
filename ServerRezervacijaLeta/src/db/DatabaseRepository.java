/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.GradEntity;
import domen.IDomainEntity;
import domen.KompanijaEntity;
import domen.KorisnikEntity;
import domen.KorisnikEnum;
import domen.LetEntity;
import domen.PutnikEntity;
import domen.RezervacijaLetaEntity;
import domen.TipAvionaEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class DatabaseRepository {

    public KorisnikEntity proveriKorisnika(KorisnikEntity korisnik) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT password,administrator FROM korisnik WHERE username=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,korisnik.getUsername());
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            String password=rs.getString("password");
            int administrator=rs.getInt("administrator");
            if(password.equals(korisnik.getPassword())){
                KorisnikEntity k=new KorisnikEntity();
                k.setUsername(korisnik.getUsername());
                k.setPassword(korisnik.getPassword());
                if(administrator==1)
                    k.setKorisnik(KorisnikEnum.administrator);
                else
                    k.setKorisnik(KorisnikEnum.radnik);
                return k;
            }
                
        }
            rs.close();
            ps.close();
        
        return null;
    }
    
    public List<IDomainEntity> vratiSve(IDomainEntity ide) throws Exception{
        List<IDomainEntity> stavke = new ArrayList<>();
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM "+ide.getNazivTabele()+ide.vratiStringZaJoin();
        Statement s = connection.createStatement();
        
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            stavke.add(ide.vratiObjekat(rs));
            
        }
        
        rs.close();
        s.close();
        return stavke;
    }

    public IDomainEntity zapamti(IDomainEntity ide) throws Exception{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ")
                .append(ide.getNazivTabele())
                .append("(")
                .append(ide.getNaziviKolona())
                .append(")")
                .append(" VALUES ")
                .append("(")
                .append(ide.getVrednosti())
                .append(")");
        
        String query = sb.toString();
        System.out.println(query);
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps=ide.ubaciVrednosti(ps);
        System.out.println(ps);
        ps.executeUpdate();
        
        if (ide.isIdAutoincrement()) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong(1);
                ide.setAutoincrementId(id);
                System.out.println(id);
            }
        }
        
        return ide;

    }

    public IDomainEntity vratiSaID(IDomainEntity ide) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM "+ide.getNazivTabele()+ide.vratiStringZaJoin()+" WHERE "+ide.getNazivIDKolone()+"=?";
        System.out.println(query);
        PreparedStatement ps = connection.prepareStatement(query);
        ide.upisiPrimarni(ps);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return ide.vratiObjekat(rs);
        }
            rs.close();
            ps.close();
        
        return null;
    }

    public List<IDomainEntity> vratiSaUslovom(IDomainEntity ide) throws Exception{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        List<domen.IDomainEntity> lista = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ")
                    .append(ide.getNazivTabele())
                    .append(" WHERE ")
                    .append(ide.vratiUslov());
            String query = sb.toString();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(ide.vratiObjekat(rs));
            }
            
        return lista;
    }
    
    public Long vratiMaxID(IDomainEntity ide) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query="SELECT MAX("+ide.getNazivIDKolone()+") AS id FROM "+ide.getNazivTabele();
        Statement s = connection.createStatement();
        
        ResultSet rs = s.executeQuery(query);
        Long id=0l;
        while (rs.next()) {
            id=rs.getLong("id");
            
        }
        
        rs.close();
        s.close();
        return id;
    }

    public void azuriraj(IDomainEntity ide) throws Exception{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ")
                .append(ide.getNazivTabele())
                .append(" SET ")
                .append(ide.getVrednostiZaUpdate())
                .append(" WHERE ")
                .append(ide.getNazivIDKolone())
                .append("=")
                .append(ide.vratiVrednostID());
        
        String query = sb.toString();
        System.out.println(query);
        PreparedStatement ps = connection.prepareStatement(query);
        if(ide.daLiJeSlozenKljuc()){
            ps=ide.ubaciVrednostiSlozenKljuc(ps);
        }else{
        ps=ide.ubaciVrednosti(ps);
        }
        System.out.println(ps);
        ps.executeUpdate();
    }

    public void obrisi(IDomainEntity ide) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ")
                .append(ide.getNazivTabele())
                .append(" WHERE ")
                .append(ide.getNazivIDKolone())
                .append("=")
                .append(ide.vratiVrednostID());

        String query = sb.toString();
        System.out.println(query);
        PreparedStatement ps = connection.prepareStatement(query);
        if(ide.daLiJeSlozenKljuc()){
            ide.upisiPrimarni(ps);
        }else{
       // ps=ide.ubaciVrednosti(ps);
        }
        System.out.println(ps);
        ps.executeUpdate();
    }

    public void startTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().setAutoCommit(false);
    }

    public void commitTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().commit();
    }

    public void rollbackTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().rollback();
    }
    
    public boolean zapamtiSve(List<RezervacijaLetaEntity> stavke) throws Exception{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps=null;
        try {
            for (RezervacijaLetaEntity stavka : stavke) {
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT INTO ")
                        .append(stavka.getNazivTabele())
                        .append("(")
                        .append(stavka.getNaziviKolona())
                        .append(")")
                        .append(" VALUES ")
                        .append("(")
                        .append(stavka.getVrednosti())
                        .append(")");
                String query = sb.toString();
                System.out.println(query);
                ps = connection.prepareStatement(query);
                ps=stavka.ubaciVrednosti(ps);
                ps.executeUpdate();
            }
            ps.close();

            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("Greska prilikom cuvanja rezervacija. " + e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

}
