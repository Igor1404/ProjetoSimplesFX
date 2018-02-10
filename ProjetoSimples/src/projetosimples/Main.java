package projetosimples;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projetosimples.controller.TelaPrincipalController;
import projetosimples.model.domain.Pessoa;

/**
 *
 * @author igor
 */
public class Main extends Application {
    
    
    @Override
    public void start(Stage estagio) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaPrincipal.fxml"));
        
       
        
        Scene cena = new Scene(root);
        estagio.setScene(cena);
        //estagio.setTitle("Cadastro de Pessoas");
        estagio.setResizable(false);
        estagio.show();
        //TelaPrincipalController controller = loader.getController();
        //controller.setPessoa(Pessoa);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
