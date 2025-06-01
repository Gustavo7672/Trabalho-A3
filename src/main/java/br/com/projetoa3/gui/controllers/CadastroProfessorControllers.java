package br.com.projetoa3.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroProfessorControllers implements Initializable {

    @FXML
    private Button BotãoCadastrar;

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

    }
}