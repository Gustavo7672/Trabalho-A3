package br.com.projetoa3.modelo;

import java.util.Map;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Alunos extends Turmas {
    private static Map<String, Alunos> lista = new HashMap<>();
    private static ObservableList<Alunos> listaObservable = FXCollections.observableArrayList();

    private final String nome;
    private long ra;

    public Alunos(String nome, long ra, String turma, String professor) {
        super(turma, professor);
        this.nome = nome;
        this.ra = ra;
    }

    public static void setLista(Map<String, Alunos> listaAlunos) {
        lista = listaAlunos;
    }

    public static void setListaObservable(ObservableList<Alunos> listaObs) {
        listaObservable = listaObs;
    }

    public static Map<String, Alunos> getLista() {
        return lista;
    }

    public static ObservableList<Alunos> getListaObservable() {
        return listaObservable;
    }

    public static void adicionarAluno(Alunos aluno) {
        String raString = String.valueOf(aluno.getRa());
        lista.put(raString, aluno);
        listaObservable.add(aluno);
    }

    public static void removerAluno(String ra) {
        Alunos aluno = lista.remove(ra);
        if (aluno != null) {
            listaObservable.remove(aluno);
        }
    }

    public String getNome() {
        return nome;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(long ra) {
        this.ra = ra;
    }

    public String toString() {
        return "Aluno: " + nome + " | RA: " + ra + " | Turma: " + getTurma();
    }



}