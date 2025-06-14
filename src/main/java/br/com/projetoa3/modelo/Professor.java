package br.com.projetoa3.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class Professor {
    private static String nomeLogado;
    private static String raLogado;
    private  String email;
    private  String senha;
    private  String nome;
    private  String ra;
    private static Map<String, Professor> professorLista = new HashMap<>();
    private static ObservableList<Professor> professorListaObservable = FXCollections.observableArrayList();

    public Professor(String nome, String ra, String email, String senha) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.senha = senha;
    }

    public static void setProfessorLista(Map<String, Professor> professorLista) {
        Professor.professorLista = professorLista;
    }

    public static String getNomeLogado() {
        return nomeLogado;
    }

    public static void setNomeLogado(String nomeLogado) {
        Professor.nomeLogado = nomeLogado;
    }

    public static String getRaLogado() {
        return raLogado;
    }

    public static void setRaLogado(String raLogado) {
        Professor.raLogado = raLogado;
    }

    public static Map<String, Professor> getProfessorLista() {
        return professorLista;
    }

    public void adicionarProfessor(Professor professor) {
        professorLista.put(professor.getRa(), professor);
        professorListaObservable.add(professor);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public boolean validarEmail() {

if(email != null && email.contains("@") && email.contains(".") && email.contains("com") && email.length() >= 10 && email.length() <= 50){

           return true;
        }else{

            return false;
        }
    }

    public boolean validarSenha() {
        if (senha != null && senha.length() >= 6) {

            return true;
        }else {

            return false;
        }

    }
    public  String getEmail() {
        return email;
    }

    public  String getSenha() {
        return senha;
    }

}
