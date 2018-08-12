/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import db.DatabaseRepository;
import domen.GradEntity;
import domen.IDomainEntity;
import domen.KompanijaEntity;
import domen.KorisnikEntity;
import domen.LetEntity;
import domen.PutnikEntity;
import domen.RezervacijaLetaEntity;
import domen.TipAvionaEntity;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import request.RequestObject;
import response.ResponseObject;
import so.AbstractGenericOperation;
import so.IzmeniLet;
import so.IzmeniPutnika;
import so.IzmeniRezervaciju;
import so.ObrisiLet;
import so.ObrisiPutnika;
import so.ObrisiRezervaciju;
import so.UcitajGradove;
import so.UcitajKompanije;
import so.UcitajLet;
import so.UcitajLetove;
import so.UcitajPutnika;
import so.UcitajPutnike;
import so.UcitajRezervacije;
import so.UcitajRezervaciju;
import so.UcitajTipoveAviona;
import so.ZapamtiLet;
import so.ZapamtiPutnika;
import so.ZapamtiRezervaciju;
import util.IOperation;
import util.IStatus;

/**
 *
 * @author NEVEN
 */
public class ThreadClient extends Thread{
    Socket socket;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            try {
                ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
                RequestObject request=(RequestObject) input.readObject();
                
                ResponseObject response=new ResponseObject();
                switch(request.getOperation()){
                    case IOperation.PROVERI_KORISNIKA:
                        KorisnikEntity korisnik=(KorisnikEntity) request.getData();
                        korisnik=new DatabaseRepository().proveriKorisnika(korisnik);
                        if (korisnik!=null){
                            response.setCode(IStatus.OK);
                            response.setData(korisnik);
                        }else{
                            response.setCode(IStatus.ERROR);
                        }
                        break;
                    case IOperation.VRATI_SVE_GRADOVE:
                        UcitajGradove ucitajGradove=new UcitajGradove();
                        ucitajGradove.templateExecute((IDomainEntity) request.getData());
                        List<IDomainEntity> gradovi= ucitajGradove.getLista();
                        response.setCode(IStatus.OK);
                        response.setData(gradovi);
                        break;
                    case IOperation.VRATI_SVE_KOMPANIJE:
                        UcitajKompanije ucitajKompanije=new UcitajKompanije();
                        ucitajKompanije.templateExecute((IDomainEntity) request.getData());
                        List<IDomainEntity> kompanije= ucitajKompanije.getLista();
                        response.setCode(IStatus.OK);
                        response.setData(kompanije);
                        break;
                    case IOperation.VRATI_SVE_TIPOVE:
                        UcitajTipoveAviona ucitajTipoveAviona=new UcitajTipoveAviona();
                        ucitajTipoveAviona.templateExecute((IDomainEntity) request.getData());
                        List<IDomainEntity> tipovi= ucitajTipoveAviona.getLista();
                        response.setCode(IStatus.OK);
                        response.setData(tipovi);
                        break;
                    case IOperation.ZAPAMTI_LET:
                        try{
                        AbstractGenericOperation zapamtiLet=new ZapamtiLet();
                        zapamtiLet.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                            response.setData((IDomainEntity) request.getData());
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_LETOVE:
                        UcitajLetove ucitajLetove=new UcitajLetove();
                        ucitajLetove.templateExecute((IDomainEntity) request.getData());
                        List<IDomainEntity> letovi=ucitajLetove.getLista();
                        response.setCode(IStatus.OK);
                        response.setData(letovi);
                        break;
                    case IOperation.VRATI_LET:
                        Long sifraLeta=(Long) request.getData();
                        LetEntity letsaID=new LetEntity();
                        letsaID.setSifraLeta(sifraLeta);
                        UcitajLet ucitajLet=new UcitajLet();
                        ucitajLet.templateExecute(letsaID);
                        letsaID=(LetEntity) ucitajLet.vratiObjekat();
                        response.setCode(IStatus.OK);
                        response.setData(letsaID);
                        break;
                    case IOperation.VRATI_MAX_ID_LET:
                        Long id=new DatabaseRepository().vratiMaxID(new LetEntity());
                        response.setCode(IStatus.OK);
                        response.setData(id);
                        break;
                    case IOperation.ZAPAMTI_IZMENE_LET:
                        try{
                            AbstractGenericOperation izmeniLet=new IzmeniLet();
                            izmeniLet.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                            response.setData((IDomainEntity) request.getData());
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.OBRISI_LET:
                        try{
                            AbstractGenericOperation obrisiLet=new ObrisiLet();
                            obrisiLet.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_PUTNIKE:
                        UcitajPutnike ucitajPutnike=new UcitajPutnike();
                        ucitajPutnike.templateExecute((IDomainEntity) request.getData());
                        List<IDomainEntity> putnici= ucitajPutnike.getLista();
                        response.setCode(IStatus.OK);
                        response.setData(putnici);
                        break;
                    case IOperation.VRATI_PUTNIKA:
                        Long sifraPutnika=(Long) request.getData();
                        PutnikEntity putniksaID=new PutnikEntity();
                        putniksaID.setSifraPutnika(sifraPutnika);
                        UcitajPutnika ucitajPutnika=new UcitajPutnika();
                        ucitajPutnika.templateExecute(putniksaID);
                        putniksaID=(PutnikEntity) ucitajPutnika.vratiObjekat();
                        response.setCode(IStatus.OK);
                        response.setData(putniksaID);
                        break;
                    case IOperation.OBRISI_PUTNIKA:
                        try{
                            AbstractGenericOperation obrisiPutnika=new ObrisiPutnika();
                            obrisiPutnika.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_MAX_ID_PUTNIK:
                        Long idPutnik=new DatabaseRepository().vratiMaxID(new PutnikEntity());
                        response.setCode(IStatus.OK);
                        response.setData(idPutnik);
                        break;
                    case IOperation.ZAPAMTI_PUTNIKA:
                        try{
                            AbstractGenericOperation zapamtiPutnika=new ZapamtiPutnika();
                            zapamtiPutnika.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                            response.setData((IDomainEntity) request.getData());
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.ZAPAMTI_IZMENE_PUTNIK:
                        try{
                            AbstractGenericOperation izmeniPutnika=new IzmeniPutnika();
                            izmeniPutnika.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                            response.setData((IDomainEntity) request.getData());
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.ZAPAMTI_REZERVACIJU:
                        try{
                            AbstractGenericOperation zapamtiRezervaciju=new ZapamtiRezervaciju();
                            zapamtiRezervaciju.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_REZERVACIJE:
                        UcitajRezervacije ucitajRezervacije=new UcitajRezervacije();
                        ucitajRezervacije.templateExecute((IDomainEntity) request.getData());
                        List<IDomainEntity> rezervacijeSve=ucitajRezervacije.getLista();
                        response.setCode(IStatus.OK);
                        response.setData(rezervacijeSve);
                        break;
                    case IOperation.OBRISI_REZERVACIJU:
                        try{
                            AbstractGenericOperation obrisiRezervaciju=new ObrisiRezervaciju();
                            obrisiRezervaciju.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.ZAPAMTI_IZMENE_REZERVACIJA:
                        try{
                            AbstractGenericOperation izmeniRezervaciju=new IzmeniRezervaciju();
                            izmeniRezervaciju.templateExecute((IDomainEntity) request.getData());
                            response.setCode(IStatus.OK);
                        }catch(Exception e){
                            response.setCode(IStatus.ERROR);
                            System.out.println(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_REZERVACIJU:
                        Long sifraRez=(Long) request.getData();
                        RezervacijaLetaEntity rezsaID=new RezervacijaLetaEntity();
                        rezsaID.setSifraRezervacije(sifraRez);
                        UcitajRezervaciju ucitajRezervaciju=new UcitajRezervaciju();
                        ucitajRezervaciju.templateExecute(rezsaID);
                        rezsaID=(RezervacijaLetaEntity) ucitajRezervaciju.vratiObjekat();
                        response.setCode(IStatus.OK);
                        response.setData(rezsaID);
                        break;
                }
                
                ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(response);
                output.flush();
            } catch (Exception ex) {
                System.out.println("Greska prilikom komunikacije sa klijentom");
                return;
            }
        }
    }
    
    
}
