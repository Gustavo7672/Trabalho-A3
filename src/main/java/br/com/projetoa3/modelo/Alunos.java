package br.com.projetoa3.modelo;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import br.com.projetoa3.sistema.SistemaAluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Alunos {
    private static Map<Long, Alunos> lista = new HashMap<>();
    private static ObservableList<Alunos> listaObservable = FXCollections.observableArrayList();

    private String nome;
    private long ra;

    public Alunos(String nome, long ra) {
        this.nome = nome;
        this.ra = ra;
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
        return "Aluno: " + nome + " | RA: " + ra;
    }

    public void validarInformacoes() {
                if (String.valueOf(ra).length() == 10) {
                    System.out.println( "Aluno registrado com sucesso");
                } else {
                    System.out.println( "RA inválido! Digite exatamente 10 números.");
                }
            }
    }
