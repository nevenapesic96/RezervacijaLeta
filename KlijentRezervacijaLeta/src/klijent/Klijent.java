/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import session.Session;

/**
 *
 * @author NEVEN
 */
public class Klijent {
    public static boolean poveziSe(String host,int port){
        try {
            Socket socket = new Socket(host,port);
            Session.getInstance().setSocket(socket);
            return true;
        } catch (IOException ex) {
            System.out.println("Klijent nije uspeo da se poveze");
            return false;
        }
    }
}
