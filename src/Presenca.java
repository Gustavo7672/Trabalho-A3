import java.util.ArrayList;
import java.util.Scanner;

public class Presenca {

    private static class Aluno {
        private String nome;
        private boolean presente;

        public Aluno(String nome) {
            this.nome = nome;
            this.presente = false;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public boolean isPresente() {
            return presente;
        }

        public void setPresente(boolean presente) {
            this.presente = presente;
        }

        public void marcarPresenca() {
            setPresente(true);
        }


        public String toString() {
            return getNome() + " - " + (isPresente() ? "Presente" : "Ausente");
        }
    }


    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Aluno> listaAlunos = new ArrayList<>();


        listaAlunos.add(new Aluno("Aluno1"));
        listaAlunos.add(new Aluno("Aluno2"));
        listaAlunos.add(new Aluno("Aluno3"));
        listaAlunos.add(new Aluno("Aluno4"));

        System.out.println("=== Controle de Presença ===");

        for (Aluno aluno : listaAlunos) {
            System.out.print("O aluno " + aluno.getNome() + " esta presente? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                aluno.marcarPresenca();
            }
        }

        System.out.println("\n--- Lista de Presença ---");
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
        }

        scanner.close();
    }
}