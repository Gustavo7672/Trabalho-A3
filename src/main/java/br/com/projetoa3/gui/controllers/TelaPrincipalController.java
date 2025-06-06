package br.com.projetoa3.gui.controllers;

import br.com.projetoa3.modelo.Professor;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import br.com.projetoa3.modelo.ListaPresenca;
import javafx.stage.Stage;
import javafx.collections.ListChangeListener;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class TelaPrincipalController implements Initializable {
    private static final ObservableList<String> alunosFormatados = FXCollections.observableArrayList();

    private static final ObservableList<String> listaNotas = FXCollections.observableArrayList();
    @FXML
    private Label RAL;

    private Stage mainStage;

    @FXML
    private Menu trocarTurmaMenu;

    @FXML
    private MenuItem TrocarTurma;

    @FXML
    private ListView<String> listaNotasId;

    @FXML
    private DatePicker calendario;

    @FXML
    private ListView<String> listaDePresenca;

    @FXML
    private ListView<String> listaAlunosId;

    @FXML
    private Label nomeL;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Alunos.getListaObservable().addListener((ListChangeListener<Alunos>) change -> {
            mostrarTurmas();
        });
        listaNotasId.getItems().clear();
        nomeL.setText("Professor: " + Professor.getNomeLogado());
        RAL.setText("RA: " + Professor.getRaLogado());
        calendario.setValue(LocalDate.now());

        calendario.valueProperty().addListener((obs, oldDate, newDate) -> {
            carregarPresencas(newDate);
        });
        carregarPresencas(LocalDate.now());
        for (Notas nota : Notas.getNotasObservable()) {
            listaNotas.add(nota.toString());
        }

        for (Alunos aluno : Alunos.getListaObservable()) {
            alunosFormatados.add(aluno.toString());
        }

        Alunos.getListaObservable().addListener((ListChangeListener<Alunos>) change -> {
            alunosFormatados.clear();

            for (Alunos aluno : Alunos.getListaObservable()) {
                alunosFormatados.add(aluno.toString());
            }
        });
        listaAlunosId.setItems(alunosFormatados);


        listaAlunosId.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            listaNotasId.getItems().clear();
            if (newValue != null) {
                String[] partes = newValue.split("\\|");
                String raStr = partes[1].replace("RA:", "").trim();

                try {
                    listaNotasId.refresh();
                    Long ra = Long.parseLong(raStr);
                    Notas nota = Notas.getNotaPorAluno(ra);
                    if (nota != null) {
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
        Alunos.getListaObservable().addListener((ListChangeListener<Alunos>) change -> {
            alunosFormatados.clear();
            for (Alunos aluno : Alunos.getListaObservable()) {
                alunosFormatados.add(aluno.toString());
            }
            carregarPresencas(calendario.getValue());
        });
    }

    private void carregarPresencas(LocalDate data) {
        listaDePresenca.refresh();
        Map<Long, BooleanProperty> presencaData = ListaPresenca.getPresencas()
                .computeIfAbsent(data, d -> new HashMap<>());
        Alunos.getListaObservable().forEach(aluno ->
                presencaData.putIfAbsent(aluno.getRa(), new SimpleBooleanProperty(false))
        );
        ObservableList<String> nomes = FXCollections.observableArrayList();
        Alunos.getListaObservable().forEach(aluno ->
                nomes.add(aluno.getNome() + " | RA: " + aluno.getRa())
        );
        listaDePresenca.setItems(nomes);
        listaDePresenca.refresh();
        listaDePresenca.setCellFactory(lv -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        listaDePresenca.refresh();
        stage.showAndWait();

    }

    @FXML
    private void abrirTelaNotas() throws IOException {
        listaNotasId.getItems().clear();
        listaAlunosId.getSelectionModel().clearSelection();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaCadastrarNotaSeparado.fxml"));
        Parent root = loader.load();
        TelaCadastrarNotasController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Notas");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        listaNotasId.refresh();
        stage.showAndWait();

    }

    @FXML
    public void abrirTelaRemoverAluno() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaRemoverAluno.fxml"));
        Parent root = loader.load();
        RemoverAlunoControllers controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Remover Aluno");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 600, 400));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void filtrarAlunosPorTurma(String turma) {
        ObservableList<String> alunosFiltrados = FXCollections.observableArrayList();
        for (Alunos aluno : Alunos.getListaObservable()) {
            if (aluno.getTurma().equals(turma)) {
                alunosFiltrados.add(aluno.toString());
            }
        }
        listaAlunosId.setItems(alunosFiltrados);
    }

    @FXML
    public void mostrarTurmas() {
        trocarTurmaMenu.getItems().clear();

        MenuItem todasTurmas = new MenuItem("Todas as turmas");
        todasTurmas.setOnAction(event -> listaAlunosId.setItems(alunosFormatados));
        trocarTurmaMenu.getItems().add(todasTurmas);

        List<String> turmas = new ArrayList<>(Alunos.getListaObservable().stream()
                .map(Alunos::getTurma)
                .distinct()
                .toList());

        for (String turma : turmas) {
            MenuItem item = new MenuItem(turma);
            item.setOnAction(event -> filtrarAlunosPorTurma(turma));
            trocarTurmaMenu.getItems().add(item);
        }
    }

    @FXML
    public void adicionarTurma() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdicionarTurma.fxml"));
        Parent root = loader.load();
        TelaAdicionarTurmaController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Turma");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/foto/Icone-removebg-preview.png")));
        stage.setScene(new Scene(root, 600, 380));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(mainStage);
        stage.setResizable(false);
        stage.showAndWait();
    }
}

