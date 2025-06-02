package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Notas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaCadastroController implements Initializable {

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


        Long raLong = Long.parseLong(cadastrarRAId1.getText().trim());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Aluno");
        alert.setHeaderText("Confirmação de Cadastro");
        if (cadastrarNomeId.getText().isEmpty() || cadastrarRAId1.getText().isEmpty() || cadastrarTurmaId11.getText().isEmpty()|| cadastrarRAId1.getText().length() !=10 ) {
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.showAndWait();
            return;
        }
        Integer intNotaA1, intNotaA2, intNotaA3;

        try {
            intNotaA1 = Integer.parseInt(cadastrarNotaA1I.getText().trim());
            intNotaA2 = Integer.parseInt(cadastrarNotaA2.getText().trim());
            intNotaA3 = Integer.parseInt(cadastrarNotaA3.getText().trim());
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
        Alunos.adicionarAluno(new Alunos(cadastrarNomeId.getText().trim(), raLong, cadastrarTurmaId11.getText().trim()));
        Notas notass = new Notas(intNotaA1, intNotaA2, intNotaA3);
        Notas.adicionarNota(raLong, notass);

        alert.setContentText("Aluno cadastrado com sucesso!\nNome: " + cadastrarNomeId.getText() + "\nRA: " + cadastrarRAId1.getText() + "\nTurma: " + cadastrarTurmaId11.getText());
         // Limpar os campos após o cadastro
        alert.showAndWait();
    }
}