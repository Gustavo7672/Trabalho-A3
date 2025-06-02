package br.com.projetoa3.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import br.com.projetoa3.modelo.Alunos;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoverAlunoControllers implements Initializable {

    @FXML
    private Button confirmarRemorcao;

    @FXML
    private TextField removerRA;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmarRemorcao.setOnAction(event -> {
            String ra = removerRA.getText().trim();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (ra.isEmpty()) {
                alert.setContentText("Por favor, digite o RA.");
                alert.showAndWait();
                return;
            }

            if (!ra.matches("\\d+")) {
                alert.setContentText("RA inválido. Digite apenas números.");
                alert.showAndWait();
                return;
            }

            if (ra.length() != 10) {
                alert.setContentText("RA inválido. Deve ter 10 dígitos.");
                alert.showAndWait();
                return;
            }

            long raLong = Long.parseLong(ra);
            if (!Alunos.getLista().containsKey(raLong)) {
                alert.setContentText("Aluno não encontrado.");
                alert.showAndWait();
                return;
            }
            Alunos.removerAluno(raLong);
            alert.setContentText("Aluno removido com sucesso.");
            alert.showAndWait();
        });
    }
}