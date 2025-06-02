package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Professor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllers implements Initializable {

    @FXML
    private TextField LoginUsuario;

    @FXML
    private PasswordField LoginSenha;

    @FXML
    private Button botaoEntrar;

    @FXML
    private Button botaoCadastrar;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        botaoEntrar.setOnAction(event -> {
            VerificarLogin();
        });
    }

    public void VerificarLogin() {
         boolean loginValido = false;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (LoginUsuario.getText().isEmpty() || LoginSenha.getText().isEmpty()) {
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
        }
        for (Professor professor : Professor.getProfessorLista().values()) {
            if (professor.getEmail().equals(LoginUsuario.getText()) && professor.getSenha().equals(LoginSenha.getText())) {
                loginValido= true;
                alert.setContentText("Login realizado com sucesso!");
                Professor.setNomeLogado(professor.getNome()) ;
                Professor.setRaLogado(professor.getRa());
                break;
            }
        }
        if (loginValido) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaPrincipal.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Lista de notas e Presenças");
                stage.setScene(new Scene(root, 1280, 720));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                Stage currentStage = (Stage) botaoEntrar.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert.setContentText("Usuário ou senha incorretos.");
        }
        alert.showAndWait();
    }

    public void AbrirCadastrar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/layout.fxml"));
        Parent root = loader.load();
        CadastroProfessorControllers controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Aluno");
        stage.setScene(new Scene(root, 430, 480));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
