/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NEVEN
 */
public class ThreadServer extends Thread{
    ServerSocket serverSocket;
    List<Thread> klijenti;

    public ThreadServer(int port) throws Exception{
        serverSocket=new ServerSocket(port);
        klijenti=new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("Server ceka klijenta....");
        while(!isInterrupted()){
            try {
                serverSocket.setSoTimeout(1000);
                Socket socket=serverSocket.accept();
                System.out.println("Klijent se povezao");
                Thread klijent=new ThreadClient(socket);
                klijent.start();
                klijenti.add(klijent);
                System.out.println("Server ceka klijenta....");
            } catch (IOException ex) {
            }
            
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
