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
    private static Map<Long, Professor> professorLista = new HashMap<>();
    private static ObservableList<Professor> professorListaObservable = FXCollections.observableArrayList();

    public Professor(String nome, String ra, String email, String senha) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.senha = senha;
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

    public static Map<Long, Professor> getProfessorLista() {
        return professorLista;
    }

    public void adicionarProfessor(Professor professor) {
        professorLista.put(Long.parseLong(professor.getRa()), professor);
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
    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Email valido");
    alert.showAndWait();
           return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email invalido");
            alert.showAndWait();
            return false;
        }
    }

    public boolean validarSenha() {
        if (senha != null && senha.length() >= 6) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "senha valida");
            alert.showAndWait();
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "senha invalida");
            alert.showAndWait();
            return false;
        }
    }
    public  String getEmail() {
        return email;
    }

    public  String getSenha() {
        return senha;
    }

    public  void setEmail(String emailUsuario) {
        email = emailUsuario;
    }

    public  void setSenha(String senhaUsuario) {
        senha = senhaUsuario;
    }

    public static Professor getProfessorLogado(String nome, String ra) {
        for (Professor professor : professorLista.values()) {
            if (professor.getEmail().equals(nome) && professor.getSenha().equals(ra)) {
                return professor;
            }
        }
        return null;
    }
}
