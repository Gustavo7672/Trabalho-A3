package br.com.projetoa3.sistema;

import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.Notas;
import br.com.projetoa3.modelo.ListaPresenca;

public class SistemaAluno {
    private static String opcao;

    public static void cadastrarNota() {
     /*  Notas nota = new Notas(0, 0, 0, 0, "");
        for (int i = 0; i < Alunos.getLista().size(); i++) {
            if (Alunos.getLista().get(i).getNome() == null) {
                System.out.println("Lista vazia");
            } else {
                System.out.println(Alunos.getLista().get(i).getNome());
                System.out.println("Digite a nota A1:");
                nota.setNotaA1(SistemaMaster.getImput().nextInt());
                SistemaMaster.getImput().nextLine();
                System.out.println("Digite a nota A2:");
                nota.setNotaA2(SistemaMaster.getImput().nextInt());
                SistemaMaster.getImput().nextLine();
                System.out.println("Digite a nota A3");
                nota.setNotaA3(SistemaMaster.getImput().nextInt());
                SistemaMaster.getImput().nextLine();
                Notas notass = new Notas(nota.getNotaA1(), nota.getNotaA2(), nota.getNotaA3(), nota.getSomaNota(), nota.getStatus());
                nota.calcularSoma();
                nota.calcularStatus();
                System.out.println("NotaA1: " + notass.getNotaA1() + " NotaA2: " + notass.getNotaA2() + " NotaA3: " + notass.getNotaA3() + " Soma total: " + notass.getSomaNota() + " Status: " + notass.getStatus());
            }
        }*/
        SistemaMaster.menu();
    }

    public static void adicionarPresenca() {
        ListaPresenca a = new ListaPresenca("","");
        /*or (Alunos aluno : Alunos.getLista()) {
            System.out.println(aluno);
            SistemaMaster.getImput().nextLine();
            System.out.println("Aluno estã presente?");
            a.setListarPresenca(SistemaMaster.getImput().nextLine());
            System.out.println("Data da presença:");
            a.setData(SistemaMaster.getImput().nextLine());
            ListaPresenca p = new ListaPresenca(a.getListarPresenca(), a.getData());
            p.adicionarPresencaNaLista(p);

        }*/
        SistemaMaster.menu();
    }

    public static void cadastrarNaLista(String nome, Long ra){
        Alunos a = new Alunos(nome, ra);
        Alunos.getLista().put(ra, a);
    }
}