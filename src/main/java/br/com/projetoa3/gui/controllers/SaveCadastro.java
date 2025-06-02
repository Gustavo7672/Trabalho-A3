package br.com.projetoa3.gui.controllers;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public abstract class SaveCadastro implements Initializable {
    public class Controller {

        @FXML
        private TextField nomeField;

        @FXML
        protected void salvarDados() {
            String nome = nomeField.getText();

            try (FileWriter writer = new FileWriter("dados.txt", true)) {
                writer.write("Nome: " + nome + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public class Main extends Application {

        @Override
        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
            stage.setTitle("Cadastro Simples");
            stage.setScene(new Scene(root));
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

}
