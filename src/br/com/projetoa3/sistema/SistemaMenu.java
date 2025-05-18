package br.com.projetoa3.sistema;

import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.ListaPresenca;

import java.util.Scanner;

public class SistemaMenu {
    private static int tentativas = 1;
    private static Scanner imput = new Scanner(System.in);

    public static Scanner getImput() {
        return imput;
    }

    public static void menu() {
        System.out.println("Sistema lista de presença e notas");
        System.out.println("Deseja adicionar presença, notas, alunos ou ver a lista 1/2/3/4");
        int comando = imput.nextInt();
        switch (comando) {
            case 1:
                SistemaAluno.adicionarPresenca();
                break;
            case 2:
                SistemaAluno.cadastrarNota();
                break;
            case 3:
                SistemaAluno.cadastrarAlunos();
                break;
            case 4:
                verLista();
                break;
            default:
                System.out.println("Número incorreto, escolha entre os 4 números solicitados!");
        }
    }

    public static void login() {
        do {
            System.out.println("Entre na sua conta:");
            System.out.println("Você tem " + tentativas + " de 5:=");
            System.out.println("Digite seu email:");
            Professor.setEmailVerificar(imput.nextLine());
            System.out.println("Digite sua senha:");
            Professor.setSenhaVerificar(imput.nextLine());
            tentativas++;
            if (Professor.getEmailVerificar().equals(Professor.getEmail()) && Professor.getSenhaVerificar().equals(Professor.getSenha())
            ) {
                System.out.println("Login efetuado com sucesso");
            } else {
                System.out.println("Email ou senha invalido");
            }
        } while (!Professor.getEmailVerificar().equals(Professor.getEmail()) || !Professor.getSenhaVerificar().equals(Professor.getSenha()));


        menu();
    }

    public static void verLista() {
        for (int i = 0; i < Alunos.getLista().size(); i++) {
            System.out.print(Alunos.getLista().get(i) + " |");
            System.out.print(Notas.getNota().get(i) + " |");
            System.out.print(ListaPresenca.getPresenca().get(i));

        }
        menu();
    }
}
