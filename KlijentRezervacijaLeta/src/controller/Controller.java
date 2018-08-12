/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.GradEntity;
import domen.KompanijaEntity;
import domen.KorisnikEntity;
import domen.LetEntity;
import domen.PutnikEntity;
import domen.RezervacijaLetaEntity;
import domen.StavkaRezervacije;
import domen.TipAvionaEntity;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import request.RequestObject;
import response.ResponseObject;
import session.Session;
import util.IOperation;
import util.IStatus;

/**
 *
 * @author NEVEN
 */
public class Controller {

    public static KorisnikEntity proveriKorisnika(KorisnikEntity korisnik) throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.PROVERI_KORISNIKA);
        request.setData(korisnik);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        ResponseObject response= (ResponseObject) input.readObject();
        int code=response.getCode();
        if(code == IStatus.OK){
            return (KorisnikEntity) response.getData();
        }else{
            return null;
        }
    }

    public static Long vratiSifruLeta() throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_MAX_ID_LET);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (Long) response.getData();
        }else{
            throw new Exception("Nije vratio dobar id!");
        }
    }

    public static List<GradEntity> vratiSveGradove() throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_GRADOVE);
        request.setData(new GradEntity());
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (List<GradEntity>) response.getData();
        }else{
            throw new Exception("Nije se kastovalo grad!");
        }
    }

    public static List<KompanijaEntity> vratiSveKompanije() throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_KOMPANIJE);
        request.setData(new KompanijaEntity());
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (List<KompanijaEntity>) response.getData();
        }else{
            throw new Exception("Nije se kastovalo kompanija!");
        }
    }

    public static List<TipAvionaEntity> vratiSveTipove() throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_TIPOVE);
        request.setData(new TipAvionaEntity());
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (List<TipAvionaEntity>) response.getData();
        }else{
            throw new Exception("Nije se kastovalo tip aviona!");
        }
    }

    public static boolean zapamtiLet(LetEntity let) throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.ZAPAMTI_LET);
        request.setData(let);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static LetEntity vratiLet(Long sifraLeta) throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_LET);
        request.setData(sifraLeta);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (LetEntity) response.getData();
        }else{
            throw new Exception("Nije pronadjen let!");
        }
    }

    public static List<LetEntity> vratiSveLetove() throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_LETOVE);
        request.setData(new LetEntity());
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (List<LetEntity>) response.getData();
        }else{
            throw new Exception("Nisu se ucitali letovi!");
        }
    }

    public static boolean sacuvajIzmeneLet(LetEntity let) throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.ZAPAMTI_IZMENE_LET);
        request.setData(let);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static boolean obrisiLet(Long sifraLeta)  throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.OBRISI_LET);
        request.setData(new LetEntity(sifraLeta, LocalDate.MAX, LocalTime.MAX, null, null, null));
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static List<PutnikEntity> vratiSvePutnike() throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_PUTNIKE);
        request.setData(new PutnikEntity());
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (List<PutnikEntity>) response.getData();
        }else{
            throw new Exception("Nisu se ucitali putnici!");
        }
    }

    public static PutnikEntity vratiPutnika(Long sifra) throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_PUTNIKA);
        request.setData(sifra);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (PutnikEntity) response.getData();
        }else{
            throw new Exception("Nije pronadjen putnik!");
        }
    }

    public static boolean obrisiPutnika(Long sifraPutnika) throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.OBRISI_PUTNIKA);
        request.setData(new PutnikEntity(sifraPutnika, "", "", "", ""));
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static Long vratiSifruPutnika() throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_MAX_ID_PUTNIK);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (Long) response.getData();
        }else{
            throw new Exception("Nije vratio dobar id!");
        }
    }

    public static boolean zapamtiPutnika(PutnikEntity putnik) throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.ZAPAMTI_PUTNIKA);
        request.setData(putnik);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static boolean sacuvajIzmenePutnik(PutnikEntity putnik) throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.ZAPAMTI_IZMENE_PUTNIK);
        request.setData(putnik);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static boolean zapamtiRezervaciju(RezervacijaLetaEntity rezervacija) throws Exception{
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.ZAPAMTI_REZERVACIJU);
        request.setData(rezervacija);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static List<RezervacijaLetaEntity> vratiSveRezervacije() throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_REZERVACIJE);
        request.setData(new RezervacijaLetaEntity());
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (List<RezervacijaLetaEntity>) response.getData();
        }else{
            throw new Exception("Nisu se ucitale rezervacije!");
        }
    }

    public static boolean obrisiRezervaciju(RezervacijaLetaEntity rezervacija) throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.OBRISI_REZERVACIJU);
        request.setData(rezervacija);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static boolean sacuvajIzmeneRezervacija(RezervacijaLetaEntity rezervacija) throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.ZAPAMTI_IZMENE_REZERVACIJA);
        request.setData(rezervacija);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }

    public static RezervacijaLetaEntity vratiRezervaciju(Long sifraRezervacije) throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.VRATI_REZERVACIJU);
        request.setData(sifraRezervacije);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return (RezervacijaLetaEntity) response.getData();
        }else{
            throw new Exception("Nije pronadjena rezervacija!");
        }
    }

    public static boolean zapamtiIzmeneRezervacije(RezervacijaLetaEntity rezervacija) throws Exception {
        RequestObject request=new RequestObject();
        request.setOperation(IOperation.ZAPAMTI_IZMENE_REZERVACIJA);
        request.setData(rezervacija);
        Socket socket=Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if(code == IStatus.OK){
            return true;
        }else{
            return false;
        }
    }
    
}
