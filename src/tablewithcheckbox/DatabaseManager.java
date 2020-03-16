/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablewithcheckbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author michaeljonathanamay
 */

public class DatabaseManager {
    
    private static Connection connection;
    public static Statement stat;
           
    /*
    private static final String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
    */
    
    private static final String CREATE_TABLE = "CREATE TABLE Tweets"
            + "(username VARCHAR(255) PRIMARY KEY,"         
            + "tweet VARCHAR(MAX))";
    
    private static final String SIMPLE_TABLE = "CREATE TABLE Tweets"
            + "(tweet VARCHAR(MAX))";
    
    public DatabaseManager(Statement s){
        System.out.print("Start");
        this.stat = s;
        openConnection();
    }
    
    public static Statement statprovider(){
        return stat;
    }
    
    public void openConnection(){
        
        try{
            Class.forName("org.h2.Driver");
            
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
            
            //System.out.println("before: "+ stat);
            
            stat = connection.createStatement();
            
            //System.out.println("after: "+stat);
                                            
            stat.execute(CREATE_TABLE);
            

            
        }catch(Exception ex){
            //JOptionPane.showMessageDialog(null, e);
            System.out.println(ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Statement stat = null;
        
        DatabaseManager DB = new DatabaseManager(stat);
    }
    
}
