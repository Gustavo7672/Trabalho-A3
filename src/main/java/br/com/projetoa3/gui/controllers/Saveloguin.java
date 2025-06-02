package br.com.projetoa3.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public abstract class Saveloguin implements Initializable {
    public class LoginController {

        @FXML
        private TextField usernameField;

        @FXML
        private PasswordField passwordField;

        private String savedUsername;
        private String savedPassword;

        @FXML
        private void handleLogin(ActionEvent event) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Guardar as informações (exemplo: salvar em variáveis)
            savedUsername = username;
            savedPassword = password;

            System.out.println("Usuário: " + savedUsername);
            System.out.println("Senha: " + savedPassword);
        }

        // Métodos opcionais para acessar os dados salvos
        public String getSavedUsername() {
            return savedUsername;
        }

        public String getSavedPassword() {
            return savedPassword;
        }
    }

}
