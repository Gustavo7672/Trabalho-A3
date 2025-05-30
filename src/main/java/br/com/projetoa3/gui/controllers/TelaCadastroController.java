package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Professor;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static br.com.projetoa3.gui.controllers.TelaPrincipalController.listaNotas;

public class TelaCadastroController implements Initializable {

    String nome;
    String ra;
    String turma;
    String notaA1;
    String notaA2;
    String notaA3;
    @FXML
    private TextField cadastrarNomeId;

    @FXML
    private TextField cadastrarNotaA1I;

    @FXML
    private TextField cadastrarNotaA2;

    @FXML
    private TextField cadastrarNotaA3;

    @FXML
    private TextField cadastrarRAId1;

    @FXML
    private TextField cadastrarTurmaId11;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void confirmarCadastro() throws IOException {

     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Aluno");
        alert.setHeaderText("Confirmação de Cadastro");

        if (nome.isEmpty() || ra.isEmpty() || turma.isEmpty()) {
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return;
        }
        alert.setContentText("Aluno cadastrado com sucesso!\nNome: " + nome + "\nRA: " + ra + "\nTurma: " + turma);
        alert.showAndWait();
    }
}