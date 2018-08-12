/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author NEVEN
 */
public class KorisnikEntity implements Serializable{
    private String username;
    private String password;
    private KorisnikEnum korisnik;

    public KorisnikEntity() {
    }

    public KorisnikEntity(String username, String password, KorisnikEnum korisnik) {
        this.username = username;
        this.password = password;
        this.korisnik = korisnik;
    }

    public KorisnikEnum getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikEnum korisnik) {
        this.korisnik = korisnik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getUsername();
    }
    
    
}
