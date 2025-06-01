package br.com.projetoa3.modelo;

import java.util.Map;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Alunos {
    private static Map<Long, Alunos> lista = new HashMap<>();
    private static ObservableList<Alunos> listaObservable = FXCollections.observableArrayList();

    private String nome;
    private long ra;
    private String turma;

    public Alunos(String nome, long ra, String turma) {
        this.nome = nome;
        this.ra = ra;
        this.turma = turma;
    }

    public String getTurma() {
        return turma;
    }


    public static Map<Long, Alunos> getLista() {
        return lista;
    }

    public static ObservableList<Alunos> getListaObservable() {
        return listaObservable;
    }

    public static void adicionarAluno(Alunos aluno) {
        lista.put(aluno.getRa(), aluno);
        listaObservable.add(aluno);
    }

    public static void removerAluno(Long ra) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRa(long ra) {
        this.ra = ra;
    }

    public String toString() {
        return "Aluno: " + nome + " | RA: " + ra + " | Turma: " + turma;
    }


}