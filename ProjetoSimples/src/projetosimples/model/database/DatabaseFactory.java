/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosimples.model.database;

/**
 *
 * @author igor
 */
public class DatabaseFactory {
    
     public static DataBase getDatabase(String nome){
        if(nome.equals("postgresql")){
            return new DatabasePostgre();
        }else if(nome.equals("mysql")){
            return new DatabaseMysql();
        }
        return null;
    }
    
}
