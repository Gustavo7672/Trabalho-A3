package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Notas;
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
import javafx.stage.Modality;
import br.com.projetoa3.modelo.ListaPresenca;
import javafx.stage.Stage;
import javafx.collections.ListChangeListener;

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

    @FXML
    private Label nomeL;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Parte que chama o calendário
        calendario.setValue(LocalDate.now());

        calendario.valueProperty().addListener((obs, oldDate, newDate) -> {
            carregarPresencas(newDate);
        });
        carregarPresencas(LocalDate.now());
        //Parte do ListView de Alunos
        ObservableList<String> alunosFormatados = FXCollections.observableArrayList();

        for (Alunos aluno : Alunos.getListaObservable()) {
            alunosFormatados.add(aluno.toString());
        }

        Alunos.getListaObservable().addListener((ListChangeListener<Alunos>) change -> {
            alunosFormatados.clear();
            for (Alunos aluno : Alunos.getListaObservable()) {
                alunosFormatados.add(aluno.toString());
            }
        });
        //aqui vai setar os elementos na ListView de Alunos
        listViewId.setItems(alunosFormatados);

        //Aqui tranforma o ra em texto
        listViewId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                String[] partes = newValue.split("\\|");
                String raStr = partes[1].replace("RA:", "").trim();

                try {
                    //aqui tranforma de novo em número
                    Long ra = Long.parseLong(raStr);
                    //aqui vai pegar as notas e colocar na variavel
                    Notas nota = Notas.getNotaPorAluno(ra);
                    if (nota != null) {
                        // aqui vai setar as notas na ListView
                        listaNotasId.getItems().setAll(
                                "A1: " + nota.getNotaA1(),
                                "A2: " + nota.getNotaA2(),
                                "A3: " + nota.getNotaA3(),
                                "Soma: " + nota.getSomaNota(),
                                "Status: " + nota.getStatus()
                        );
                    } else {
                        listaNotasId.getItems().setAll("Sem notas cadastradas");
                    }
                } catch (NumberFormatException e) {
                    listaNotasId.getItems().setAll("Erro ao ler RA");
                }
            }
        });

    }

    private void carregarPresencas(LocalDate data) {
//Lista com ra e boolean de presença
        Map<Long, BooleanProperty> presencaData = ListaPresenca.getPresencas()
                .computeIfAbsent(data, d -> new HashMap<>());
        //chama a lista de alunos e coloca os checkbox desmarcados
        Alunos.getListaObservable().forEach(aluno ->
                presencaData.putIfAbsent(aluno.getRa(), new SimpleBooleanProperty(false))
        );
        //observable list tornara a lista dinamica
        ObservableList<String> nomes = FXCollections.observableArrayList();
        //adicionando os nomes no observable
        Alunos.getListaObservable().forEach(aluno ->
                nomes.add(aluno.getNome() + " | RA: " + aluno.getRa())
        );
        //aqui vai setar os nomes na ListView de presença
        listaDePresenca.setItems(nomes);
// cria os check box na lista de alunos no listview de presença
        listaDePresenca.setCellFactory(lv -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(String item, boolean empty) {
                //se a lista estiver vazia ou nula não vai aparecer nada
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    //se tiver, vai aparacer o check box com nome do alunos na frente
                    checkBox.setText(item);
                    Long ra = Long.parseLong(item.split("RA: ")[1].trim());
                    checkBox.selectedProperty().unbind();
                    checkBox.selectedProperty().bindBidirectional(presencaData.get(ra));
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
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.showAndWait();

    }

    @FXML
    private void abrirTelaNotas() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaCadastrarNotaSeparado.fxml"));
        Parent root = loader.load();
        TelaCadastrarNotasController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Notas");
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.showAndWait();
    }
}

