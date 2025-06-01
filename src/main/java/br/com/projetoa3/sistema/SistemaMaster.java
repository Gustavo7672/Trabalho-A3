package br.com.projetoa3.sistema;

import br.com.projetoa3.modelo.Professor;
import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.ListaPresenca;

import java.util.Scanner;

public class SistemaMaster {
    private static int tentativas = 1;
    private static Scanner imput = new Scanner(System.in);

    public static Scanner getImput() {
        return imput;
    }

    public static void menu() {
        System.out.println("Sistema lista de presença e notas");
        System.out.println("Deseja cadastrar Alunos, Notas, Presença, ver a lista ou sair. 1/2/3/4/5");
        int comando = imput.nextInt();
        switch (comando) {
            case 1:
                //istemaAluno.cadastrarAlunos();
                break;
            case 2:
                SistemaAluno.cadastrarNota();
                break;
            case 3:
                SistemaAluno.adicionarPresenca();
                break;
            case 4:
                    System.out.println("Listas de notas ou de presença tá vazia");
                break;
            case 5:
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

    if (Alunos.getLista().isEmpty()) {

    }else {
        menu();
    }
    }

    /*public static void verLista() {
        for (int i = 0; i < Alunos.getLista().size(); i++) {
            System.out.println(Alunos.getLista().get(i) + " |");
            System.out.println("Nota A1: "+Notas.getNota().get(i).getNotaA1() +
                    " |"+" Nota A2: " +Notas.getNota().get(i).getNotaA2() +
                    " |"+" Nota A3: "+Notas.getNota().get(i).getNotaA3() +
                    " |"+" Soma: "+Notas.getNota().get(i).getSomaNota() +
                    " |"+" Status: "+Notas.getNota().get(i).getStatus()+" ");
            System.out.println("Presença: "+ ListaPresenca.getPresenca().get(i).getListarPresenca()+" |"+" Data: "+ListaPresenca.getPresenca().get(i).getData());
            System.out.println("-------------------------------");
        }
        menu();
    }*/
}
