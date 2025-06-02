package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroProfessorControllers implements Initializable {

    @FXML
    private Button BotãoCadastrar;

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
        BotãoCadastrar.setOnAction(event -> {
            salvarDados();
        });

    }
    @FXML
    protected void salvarDados() {
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Professor professor = new Professor(Nome.getText(), RA.getText(), Email.getText(), Senha.getText());
        if (Nome.getText().isEmpty() || RA.getText().isEmpty() || Email.getText().isEmpty() || Senha.getText().isEmpty()) {
            alert.setContentText("Por favor, preencha todos os campos.");
        } else if (professor.validarEmail() && professor.validarSenha()) {
            alert.setContentText("Cadastro realizado com sucesso!");
            professor.adicionarProfessor(professor);
        } else {

        }

        }
    }