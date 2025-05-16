import java.util.ArrayList;

public class Alunos {
    private static ArrayList<Alunos> lista = new ArrayList<>();
    private String nome;
    private long ra;

    public Alunos(String nome, long ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public void cadastrarNaLista(String nome, Long ra){
        Alunos a = new Alunos(nome, ra);
        lista.add(a);
    }

    public static ArrayList<Alunos> getLista() {
        return lista;
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

    public String validarInformacoes() {
                if (String.valueOf(ra).length() == 10) {
                    return "Aluno registrado com sucesso";
                } else {
                    return "RA inválido! Digite exatamente 10 números.";
                }
            }
    }