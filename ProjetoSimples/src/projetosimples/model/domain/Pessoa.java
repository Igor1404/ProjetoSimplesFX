/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosimples.model.domain;


import java.io.Serializable;
/**
 *
 * @author igor
 */
public class Pessoa implements Serializable{
    
    
   private String Nome;
   private String CPF;
    
   public Pessoa(){
   
   }
   
   public Pessoa(String Nome, String CPF){
       
       this.Nome = Nome;
       this.CPF = CPF;
   
   }

    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the CPF
     */
    public String getCPF() {
        return CPF;
    }

    /**
     * @param CPF the CPF to set
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
    //@Override
    public String toStringCPF(){
        return this.CPF;
    }
}
