import java.util.ArrayList;

public class Alunos {
    private static ArrayList<Alunos> lista = new ArrayList<>();

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

    public String validarInformacoes() {
                if (ra.matches("\\d{10}")) {
                    return "Aluno registrado com sucesso";
                } else {
                    return "RA inválido! Digite exatamente 10 números.";
                }
            }
    }
