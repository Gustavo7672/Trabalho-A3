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
    private Button comfirmarRemorcao;

    @FXML
    private TextField removerRA;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comfirmarRemorcao.setOnAction(event -> {
            String ra = removerRA.getText();
            Integer raInt = Integer.parseInt(ra);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (raInt.toString().length() != 10) {

                alert.setContentText("RA inválido. Deve ter 10 dígitos.");
                alert.showAndWait();
                return;
            } else if (!Alunos.getLista().containsKey(raInt.longValue())) {
                alert.setContentText("Aluno não encontrado.");
                alert.showAndWait();
                return;
            }
            Alunos.removerAluno(raInt.longValue());
            alert.setContentText("Aluno removido com sucesso.");
            alert.showAndWait();
        });
    }
}