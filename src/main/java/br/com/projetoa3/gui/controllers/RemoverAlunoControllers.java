package br.com.projetoa3.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import br.com.projetoa3.modelo.Alunos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }

            if (!ra.matches("\\d+")) {
                alert.setContentText("RA inválido. Digite apenas números.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }

            if (ra.length() != 10) {
                alert.setContentText("RA inválido. Deve ter 10 dígitos.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }

            long raLong = Long.parseLong(ra);
            if (!Alunos.getLista().containsKey(raLong)) {
                alert.setContentText("Aluno não encontrado.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
                alert.showAndWait();
                return;
            }
            Alunos.removerAluno(raLong);
            alert.setContentText("Aluno removido com sucesso.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        });
    }
}