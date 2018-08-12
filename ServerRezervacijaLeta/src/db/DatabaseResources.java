/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constants.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 *
 * @author NEVEN
 */
public class DatabaseResources {
    Properties properties;

    public DatabaseResources() throws Exception {
        properties = new Properties();
        FileInputStream fis = new FileInputStream("./resources/database.config");
        properties.load(fis);
    }
    
    public String getUrl(){
        return properties.getProperty(Constants.URL);
    }
    
    public String getUsername(){
        return properties.getProperty(Constants.USERNAME);
    }
    
    public String getPassword(){
        return properties.getProperty(Constants.PASSWORD);
    }
}
