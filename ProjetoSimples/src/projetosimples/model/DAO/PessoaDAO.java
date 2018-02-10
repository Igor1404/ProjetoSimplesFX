/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosimples.model.DAO;

/**
 *
 * @author igor
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projetosimples.model.domain.Pessoa;


public class PessoaDAO {
    
    private Connection connection;
    
    public Connection getConnection(){
    
            return connection;
    
    }
    
    public void setConnection(Connection connection){
        
        this.connection = connection;
    }
    
    
    
    //Inserção dos dados
     public boolean inserir(Pessoa pessoa){
        
        String sql = "INSERT INTO \"DBSchema\".\"Pessoa\"(\"Nome\", \"CPF\") VALUES(?,?)";
        
        try{
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCPF());
            stmt.execute();
            return true;
            
        }catch(SQLException ex){
        
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        
        }
    }
     
     //Remover dado
     public boolean remover(Pessoa pessoa){
     
     
       
         String sql = "DELETE FROM \"DBSchema\".\"Pessoa\" WHERE \"CPF\"= ?";
     
         try{
         
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getCPF());
            stmt.execute();
            return true;
     
        }
        catch (SQLException ex){
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public List<Pessoa> listar() {
        String sql = "SELECT * FROM \"DBSchema\".\"Pessoa\"";
        
        List<Pessoa> retorno = new ArrayList<>();
        
        try {
           
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(resultado.getString("Nome"));
                pessoa.setCPF(resultado.getString("CPF"));
                retorno.add(pessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
   





 //Alteração de Dados
     
     
     /*
     public boolean alterar(Categoria categoria) {
        String sql = "UPDATE categorias SET descricao=? WHERE cdCategoria=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2, categoria.getCdCategoria());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
      public List<Categoria> listar() {
        String sql = "SELECT * FROM categorias";
        List<Categoria> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Categoria categoria = new Categoria();
                categoria.setCdCategoria(resultado.getInt("cdCategoria"));
                categoria.setDescricao(resultado.getString("descricao"));
                retorno.add(categoria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Categoria buscar(Categoria categoria) {
        String sql = "SELECT * FROM categorias WHERE cdCategoria=?";
        Categoria retorno = new Categoria();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getCdCategoria());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                categoria.setDescricao(resultado.getString("descricao"));
                retorno = categoria;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
     
     */