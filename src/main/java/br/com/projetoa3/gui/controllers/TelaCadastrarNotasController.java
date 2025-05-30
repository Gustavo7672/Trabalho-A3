package br.com.projetoa3.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaCadastrarNotasController implements Initializable {

    @FXML
    private ListView<?> ListaNomesNotas;

    @FXML
    private TextField cadastrarNotaA1;

    @FXML
    private TextField cadastrarNotaA2;

    @FXML
    private TextField cadastrarNotaA3;

// vc tem capturar esses dados, jogar na lista Notas da classe Notas e atualizar a lista
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
