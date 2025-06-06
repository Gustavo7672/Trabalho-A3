package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CadastroProfessorControllers implements Initializable {

    @FXML
    private Button BotaoCadastrar;

    @FXML
    private TextField Email;

    @FXML
    private TextField Nome;

    @FXML
    private TextField RA;

    @FXML
    private PasswordField Senha;

    // vc tem capturar esses dados, jogar na lista Notas da classe Notas e atualizar a lista
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BotaoCadastrar.setOnAction(event -> {
            salvarDados();
        });

    }
    @FXML
    protected void salvarDados()  {
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Professor professor = new Professor(Nome.getText(), RA.getText(), Email.getText(), Senha.getText());
        if (professor.getNome().isEmpty() || professor.getRa().isEmpty() || professor.getEmail().isEmpty() || professor.getSenha().isEmpty()) {
            alert.setTitle("Cadastro de Professor");
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.setHeaderText("Campos obrigatórios não preenchidos!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        }else if (professor.validarEmail() && professor.validarSenha()) {
            professor.adicionarProfessor(professor);
            alert.setContentText("Clique em OK para ir a tela de login.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Professor cadastrado com sucesso!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
            Stage currentStage = (Stage) BotaoCadastrar.getScene().getWindow();
            currentStage.close();
        } else {
            alert.setContentText("Email ou senha inválidos. A senha deve ter pelo menos 6 caracteres.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        }

        }
    }