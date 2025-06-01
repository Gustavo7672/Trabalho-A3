package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Notas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
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

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Aluno");
        alert.setHeaderText("Confirmação de Cadastro");
        if (nome.isEmpty() || ra.isEmpty() || turma.isEmpty()|| ra.length() !=10 ) {
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return;
        }
        Integer intNotaA1, intNotaA2, intNotaA3;

        try {
            intNotaA1 = Integer.parseInt(notaA1);
            intNotaA2 = Integer.parseInt(notaA2);
            intNotaA3 = Integer.parseInt(notaA3);
        } catch (NumberFormatException e) {
            alert.setContentText("Notas devem ser números inteiros.");
            alert.showAndWait();
            return;
        }

        if (intNotaA1 < 0 || intNotaA2 < 0 || intNotaA3 < 0 || intNotaA1 > 30 || intNotaA2 > 30 || intNotaA3 > 40) {
            alert.setContentText("Notas fora do limite permitido.");
            alert.showAndWait();
            return;
        }
        Alunos.adicionarAluno(new Alunos(nome, raLong, turma));
        Notas notass = new Notas(intNotaA1, intNotaA2, intNotaA3);
        Notas.adicionarNota(raLong, notass);

        alert.setContentText("Aluno cadastrado com sucesso!\nNome: " + nome + "\nRA: " + ra + "\nTurma: " + turma);
        alert.showAndWait();
    }
}