package br.com.projetoa3.sistema;

import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.Alunos;

import java.util.Scanner;

public class SistemaMaster {
    private static int tentativas = 1;
    private static Scanner imput = new Scanner(System.in);

    public static Scanner getImput() {
        return imput;
    }

    public static String login() {
        do {
            if (Professor.getEmailVerificar().equals(Professor.getEmail()) && Professor.getSenhaVerificar().equals(Professor.getSenha())
            ) {
                return ("Login efetuado com sucesso");
            } else {
                return ("Email ou senha invalido");
            }
        } while (!Professor.getEmailVerificar().equals(Professor.getEmail()) || !Professor.getSenhaVerificar().equals(Professor.getSenha()));

    }
}
