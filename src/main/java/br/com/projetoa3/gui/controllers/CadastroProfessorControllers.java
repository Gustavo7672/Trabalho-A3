package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.bancodedados.ProfessorCrud;
import br.com.projetoa3.modelo.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class CadastroProfessorControllers implements Initializable {

    @FXML
    private Button BotaoCadastrar;

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
        BotaoCadastrar.setOnAction(event -> {
            salvarDados();
        });

    }
    @FXML
    protected void salvarDados() {
        ProfessorCrud crud = new ProfessorCrud();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Professor professor = new Professor(Nome.getText(), RA.getText(), Email.getText(), Senha.getText());
        if (professor.getNome().isEmpty() || professor.getRa().isEmpty() || professor.getEmail().isEmpty() || professor.getSenha().isEmpty()) {
            alert.setTitle("Cadastro de Professor");
            alert.setContentText("Por favor, preencha todos os campos.");
            alert.setHeaderText("Campos obrigatórios não preenchidos!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        } else if (professor.getRa().length() != 10) {
            alert.setContentText("RA inválido. Deve ter 10 dígitos.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Erro no cadastro");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        } else if (Professor.getProfessorLista().containsKey(professor.getRa())) {
            alert.setContentText("Professor já cadastrado com este RA.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Erro no cadastro");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        } else if(Professor.getProfessorLista().containsKey(professor.getEmail())){
            alert.setContentText("Professor já cadastrado com este email.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Erro no cadastro");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();

    }else if (professor.validarEmail() && professor.validarSenha()) {
            professor.adicionarProfessor(professor);
            for(Map.Entry<String, Professor> entry : Professor.getProfessorLista().entrySet()) {
                crud.inserirProfessor(entry.getKey(), entry.getValue().getNome(), entry.getValue().getEmail(), entry.getValue().getSenha());
            }
            alert.setContentText("Clique em OK para ir a tela de login.");
            alert.setTitle("Cadastro de Professor");
            alert.setHeaderText("Professor cadastrado com sucesso!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
            Stage currentStage = (Stage) BotaoCadastrar.getScene().getWindow();
            currentStage.close();
        } else {
            alert.setContentText("Email ou senha inválidos. A senha deve ter pelo menos 6 caracteres.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
            alert.showAndWait();
        }

        }
    }