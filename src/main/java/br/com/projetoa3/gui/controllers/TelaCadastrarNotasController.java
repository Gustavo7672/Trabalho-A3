package br.com.projetoa3.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.Alunos;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaCadastrarNotasController implements Initializable {
    private Alunos alunoSelecionado;

    @FXML
    private Button confirmarCadastro;

    @FXML
    private ListView<Alunos> ListaNomesNotas;

    @FXML
    private TextField cadastrarNotaA1;

    @FXML
    private TextField cadastrarNotaA2;

    @FXML
    private TextField cadastrarNotaA3;

    @FXML
    private Label labelNomeSelecionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaNomesNotas.setItems(Alunos.getListaObservable());

        ListaNomesNotas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                alunoSelecionado = newVal;
                exibirNotasDoAluno(newVal);
            }
        });
    }

    @FXML
    public void cadastrarNotas() {
        try {
            int notaA1 = Integer.parseInt(cadastrarNotaA1.getText());
            int notaA2 = Integer.parseInt(cadastrarNotaA2.getText());
            int notaA3 = Integer.parseInt(cadastrarNotaA3.getText());

            Notas novaNota = new Notas(notaA1, notaA2, notaA3);

            Notas.adicionarNota(alunoSelecionado.getRa(), novaNota);

            cadastrarNotaA1.clear();
            cadastrarNotaA2.clear();
            cadastrarNotaA3.clear();
        } catch (NumberFormatException e) {
            System.out.println("Erro: Insira apenas números válidos.");

        }
    }

    @FXML
    private void exibirNotasDoAluno(Alunos aluno) {
        labelNomeSelecionado.setText(aluno.getNome()); // optional, for display

        Notas notas = Notas.getNotaPorAluno(aluno.getRa());
        if (notas != null) {
            cadastrarNotaA1.setText(String.valueOf(notas.getNotaA1()));
            cadastrarNotaA2.setText(String.valueOf(notas.getNotaA2()));
            cadastrarNotaA3.setText(String.valueOf(notas.getNotaA3()));
        } else {
            cadastrarNotaA1.clear();
            cadastrarNotaA2.clear();
            cadastrarNotaA3.clear();
        }
    }
}