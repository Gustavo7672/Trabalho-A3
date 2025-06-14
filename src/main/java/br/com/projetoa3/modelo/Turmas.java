package br.com.projetoa3.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class Turmas {
    private static Map<String, Turmas> turmas = new HashMap<>();
    private static ObservableList<Turmas> turmasObservable = FXCollections.observableArrayList();
    private String nome;
    private String professor;

    public Turmas( String nome, String professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public static void setTurmas(Map<String, Turmas> turmas) {
        Turmas.turmas = turmas;
    }

    public static Map<String, Turmas> getTurmas() {
        return turmas;
    }

    public String getTurma() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public static ObservableList<Turmas> getTurmasObservable() {
        return turmasObservable;
    }

    public static void adicionarTurma(String numero, String nomeTurma, String professor) {
        if (turmas.containsKey(numero)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Turma já existe");
            alert.setContentText("A turma " + nomeTurma + " já está cadastrada.");
            alert.showAndWait();
        } else {
            Turmas turma = new Turmas(nomeTurma, professor);
            turmas.put(numero, turma);
            turmasObservable.add(turma);
        }
    }
}
