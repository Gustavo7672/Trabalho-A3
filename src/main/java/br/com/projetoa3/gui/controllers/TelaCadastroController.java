package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Alunos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaCadastroController implements Initializable {

    @FXML
    private TextField cadastrarNomeId;

    @FXML
    private TextField cadastrarRAId1;

    @FXML
    private ComboBox<String> comboBoxTurma;

    public ComboBox<String> getComboBoxTurma() {
        return comboBoxTurma;
    }
    public void setComboBoxTurma(ComboBox<String> comboBoxTurma) {
        this.comboBoxTurma = comboBoxTurma;
    }

    @FXML
    private Button botaoConfirmarCadastro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(String turma : Alunos.getTurmasObservable()) {
            comboBoxTurma.getItems().add(turma);
        }
    }
    @FXML
    private void confirmarCadastro() throws IOException {

        Long raLong = Long.parseLong(cadastrarRAId1.getText().trim());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Aluno");
        alert.setHeaderText("Confirmação de Cadastro");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        if (cadastrarNomeId.getText().isEmpty() || cadastrarRAId1.getText().isEmpty() || cadastrarRAId1.getText().length() !=10 ) {
            alert.setContentText("Por favor, preencha todos os campos.");
            stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
            return;
        }
        Alunos.adicionarAluno(new Alunos(cadastrarNomeId.getText().trim(), raLong, comboBoxTurma.getValue()));


        alert.setHeaderText("Aluno cadastrado com sucesso!\nNome: " + cadastrarNomeId.getText() + "\nRA: " + cadastrarRAId1.getText() + "\nTurma: " + comboBoxTurma.getValue());
        alert.setContentText("Clique em OK para continuar.");
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        cadastrarNomeId.clear();
        cadastrarRAId1.clear();
        alert.showAndWait();
    }
}