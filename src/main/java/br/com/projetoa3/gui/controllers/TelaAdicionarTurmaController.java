package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Professor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class TelaAdicionarTurmaController implements Initializable {

    @FXML
    private TextField TurmaDigitada;

    @FXML
    private Button cadastrarTurma;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    cadastrarTurma.setOnAction(event -> {
        String idAleatorio = UUID.randomUUID().toString();
        Alunos.adicionarTurma(idAleatorio, TurmaDigitada.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Turma");
        alert.setHeaderText("Turma cadastrada com sucesso!");
        alert.setContentText("ID: " + idAleatorio + "\nNome da Turma: " + TurmaDigitada.getText());
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        alert.showAndWait();
        });

    }

    }