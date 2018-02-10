/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosimples.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author igor
 */
public class DatabasePostgre implements DataBase {
    
    
    private Connection connection;
        
        @Override
        public Connection conectar(){
    
        try{
            
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBSimples","postgres","point246");
            return this.connection;
        
        }catch (SQLException | ClassNotFoundException ex){
            Logger.getLogger(DatabasePostgre.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            }
    }
        
        
    public void desconectar(Connection connection){
        
        try{
        
            connection.close();
        
        
        }catch (SQLException ex) {
            
            Logger.getLogger(DatabasePostgre.class.getName()).log(Level.SEVERE, null, ex);
            
        }       
    }
}
