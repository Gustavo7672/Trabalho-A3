package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.sistema.SistemaAluno;
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
        nome = cadastrarNomeId.getText();
        ra = cadastrarRAId1.getText();
        turma = cadastrarTurmaId11.getText();
        notaA1 = cadastrarNotaA1I.getText();
        notaA2 = cadastrarNotaA2.getText();
        notaA3 = cadastrarNotaA3.getText();

        Long raLong = Long.parseLong(ra);
        Integer intNotaA1 = Integer.parseInt(notaA1);
        Integer intNotaA2 = Integer.parseInt(notaA2);
        Integer intNotaA3 = Integer.parseInt(notaA3);
        Alunos.adicionarAluno(new Alunos(nome, raLong));
        Notas notass = new Notas(intNotaA1, intNotaA2, intNotaA3);
        Notas.adicionarNota(raLong, notass);
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