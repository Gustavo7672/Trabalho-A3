package br.com.projetoa3.gui.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import br.com.projetoa3.modelo.Professor;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class TelaPrincipalController implements Initializable {
    @FXML
    private Label RAL;

    private Stage mainStage;



    @FXML
    private ListView<String> listaNotasId;

    @FXML
    private DatePicker calendario;

    @FXML
    private ListView<String> listaDePresenca;

    @FXML
    private ListView<String> listViewId;

    public ListView<String> getListViewId() {
        return listViewId;
    }

    @FXML
    private Label nomeL;

    public ListView<String> getListaNotasId() {
        return listaNotasId;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calendario.setValue(LocalDate.now());

        calendario.valueProperty().addListener((obs, oldDate, newDate) -> {
            carregarPresencas(newDate);
        });
        carregarPresencas(LocalDate.now());


                //Nesse espaço é a lista de alunos e notas VC PRECISA CRIAR O OBSERVABLE LIST
        listViewId.getItems().addAll(       .keySet());


        listViewId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                // nesse espaço tambem é lista de notas
                listaNotasId.getItems().setAll(         .get(newValue));
            }
        });

    }

    private void carregarPresencas(LocalDate data) {
        List<String> alunos = listViewId.getItems();

        Map<String, BooleanProperty> presencaData = presencas.computeIfAbsent(data, d -> {
            Map<String, BooleanProperty> map = new HashMap<>();
            for (String aluno : alunos) {
                map.put(aluno, new SimpleBooleanProperty(false));
            }
            return map;
        });

        listaDePresenca.setItems(FXCollections.observableArrayList(alunos));
        listaDePresenca.setCellFactory(lv -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(String nome, boolean empty) {
                super.updateItem(nome, empty);
                if (empty || nome == null) {
                    setGraphic(null);
                } else {
                    checkBox.setText(nome);
                    checkBox.selectedProperty().unbind();
                    checkBox.selectedProperty().bindBidirectional(presencaData.get(nome));
                    setGraphic(checkBox);
                }
            }
        });
    }

    @FXML
    private void abrirTelaCadastro() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaCadastro.fxml"));
        Parent root = loader.load();
        TelaCadastroController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Aluno");
        stage.setScene(new Scene(root, 720, 500));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.showAndWait();

    }
}