/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author NEVEN
 */
public class DatabaseConnection {
    private final Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() throws Exception{
        DatabaseResources dbr = new DatabaseResources();
        connection = DriverManager.getConnection(dbr.getUrl(), dbr.getUsername(), dbr.getPassword());
        System.out.println("Uspostavljena konekcija!");
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public static DatabaseConnection getInstance() throws Exception{
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
