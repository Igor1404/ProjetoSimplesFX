/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosimples.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projetosimples.model.DAO.PessoaDAO;
import projetosimples.model.database.DataBase;
import projetosimples.model.database.DatabaseFactory;
import projetosimples.model.domain.Pessoa;


/**
 *
 * @author igor
 */
public class TelaPrincipalController implements Initializable {
    
    //Todas as ferramentas serão controladas através das váriáveis
    
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label LabelNome;
    @FXML
    private Label LabelCPF;
    @FXML
    private Button BtnAdc;
    @FXML
    private Button BtnDel;
    @FXML
    private TextField TextNome;
    @FXML 
    private TextField TextCPF;
    @FXML
    private TableView<Pessoa> tableviewpessoa;
    @FXML
    private TableColumn<Pessoa,String> ColunaNome;
    @FXML
    private TableColumn<Pessoa,String> ColunaCPF;
  
    private List<Pessoa> listaPessoa;
    private ObservableList<Pessoa> observableListPessoa;
    
    //@FXML
    //private TableView<Cliente> tableViewPessoa;
    
    private final DataBase database = DatabaseFactory.getDatabase("postgresql");
    private final Connection conexao = database.conectar();
    private final PessoaDAO pessoadao = new PessoaDAO();
 
    
    //Classe Pessoa que será utilizada para os diversos 
    private Pessoa pessoa;
    
    public Pessoa getPessoa() {
        return pessoa;
    }

  
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.TextNome.setText(pessoa.getNome());
        this.TextCPF.setText(pessoa.getCPF());
    }
    
//Onde as classes serão inicializadas
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      pessoadao.setConnection(conexao);
     
      carregarTabelaPessoa();
      selecionarItem(null);
      tableviewpessoa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItem(newValue));
        
    }
    
    //Carregar os dados na Tabela
    @FXML
    public void carregarTabelaPessoa(){
        
     //   FXMLLoader loader = new FXMLLoader();
   //     loader.setLocation(TelaPrincipalController.class.getResource("projetosimples/view/TelaPrincipal.fxml"));
 //      TelaPrincipalController controller = loader.getController();
//        controller.setPessoa(pessoa);
        
        ColunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        ColunaCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        listaPessoa = pessoadao.listar();
   
        observableListPessoa = FXCollections.observableArrayList(listaPessoa);
        tableviewpessoa.setItems(observableListPessoa);
        
        //tableviewpessoa.getColumns().addAll(ColunaNome, ColunaCPF);

    }
    

    @FXML
    //Botão Inserir
    public void handleBtnAdc() throws IOException{
        
        Pessoa pessoa = new Pessoa();
         
      if(ValidarPessoa()){
       
          pessoa.setNome(TextNome.getText());
          pessoa.setCPF(TextCPF.getText());
          pessoadao.inserir(pessoa);
          carregarTabelaPessoa();  
           
          TextNome.setText("");
          TextCPF.setText("");
       }
        
    }
    
    @FXML
    //Botão Remover
    public void handleBtnDel() throws IOException{
        Pessoa pessoa = tableviewpessoa.getSelectionModel().getSelectedItem();
        if(pessoa != null){        
            pessoadao.remover(pessoa);
            carregarTabelaPessoa();
            TextNome.setText("");
            TextCPF.setText("");
        }else{
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Escolha algum cliente da Tabela:");
            alert.show();
            TextNome.setText("");
            TextCPF.setText("");
            //carregarTabelaPessoa();
        }
    }
    
    
    //Validar o cadastro da Pessoa
    public boolean ValidarPessoa(){
    String errorMessage = "";
    
    if (TextNome.getText()==null || TextNome.getText().length() == 0){
        errorMessage += "Nome Inválido \n";
    
    }
    
    if (TextCPF.getText() == null || TextCPF.getText().length() == 0) {
        errorMessage += "CPF inválido!\n";
    }
    
    if (errorMessage.length() == 0){
        System.out.println("Cadastro realizado com sucesso: ");
        return true;
   
    }else{
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no cadastro");
        alert.setHeaderText("Campos inválidos, por favor, corrija...");
        alert.setContentText(errorMessage);
        alert.show();
    
        return false;
        }
    
    }
    
    //Selecionar Item da Tabela
    @FXML
    public void selecionarItem(Pessoa pessoa){
        
        if(pessoa != null){
            
            TextNome.setText(pessoa.getNome());
            TextCPF.setText(pessoa.getCPF());
        
        }else{
       
            TextNome.setText("");
            TextCPF.setText("");
        
        }
    
    }
    
    
    @FXML
    public void ExecutarControle() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("projetosimples/view/TelaPrincipal.fxml"));
        AnchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void ExecutarPrograma(Pessoa pessoa) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TelaPrincipalController.class.getResource("projetosimples/view/TelaPrincipal.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        //Setando o Controlador para funcionar
        TelaPrincipalController controller = loader.getController();
        controller.setPessoa(pessoa);
        
    }
    
    @FXML
    public void ExecutarTabelaPessoa(Pessoa pessoa) throws IOException{
        
        ColunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        ColunaCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        
        listaPessoa = pessoadao.listar();
        
        observableListPessoa = FXCollections.observableArrayList(listaPessoa);
        
        tableviewpessoa.setItems(observableListPessoa);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TelaPrincipalController.class.getResource("projetosimples/view/TelaPrincipal.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        TelaPrincipalController controller = loader.getController();
        controller.setPessoa(pessoa);
        //tableviewpessoa.getColumns().addAll(ColunaNome, ColunaCPF);

    }
}

/*

 public void carregarTabelaPessoa(){
        
        ColunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        ColunaCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        
        listaPessoa = pessoadao.listar();
        
        observableListPessoa = FXCollections.observableArrayList(listaPessoa);
        
        tableviewpessoa.setItems(observableListPessoa);
        
        FXMLLoader loader = new FXMLLoader();
        TelaPrincipalController controller = loader.getController();
        controller.setPessoa(pessoa);
        //tableviewpessoa.getColumns().addAll(ColunaNome, ColunaCPF);

    }
*/
  /*  @FXML
    public void handleMenuItemCadastrosClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/projetosimples/view/TelaPrincipal.fxml"));
        AnchorPane.getChildren().setAll(a);
    }*/

    /**
     * @return the pessoa
     */
 
