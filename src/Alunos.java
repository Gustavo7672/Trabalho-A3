import java.util.ArrayList;
import java.util.Scanner;

public class Alunos {
    private String nome;
    private String ra;

    public Alunos(String nome, String ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public String getRa() {
        return ra;
    }

    public String toString() {
        return "Aluno: " + nome + " | RA: " + ra;
    }

    public static void breno123() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alunos> lista = new ArrayList<>();

        String continuar;
        do {
            System.out.print("Digite o nome do aluno: ");
            String nome = scanner.nextLine();

            String ra;
            while (true) {
                System.out.print("Digite o RA do aluno: ");
                ra = scanner.nextLine();

                if (ra.matches("\\d{10}")) {
                    break;
                } else {
                    System.out.println("RA inválido! Digite exatamente 10 números.");
                }
            }

            Alunos aluno = new Alunos(nome, ra);
            lista.add(aluno);

            System.out.print("Deseja registrar outro aluno? (s/n): ");
            continuar = scanner.nextLine().trim().toLowerCase();
        } while (continuar.equals("s"));


        System.out.println();
        System.out.println("--- Alunos presentes na aula: ---");
        for (Alunos aluno : lista) {
            System.out.println(aluno);
        }

        scanner.close();
    }
}