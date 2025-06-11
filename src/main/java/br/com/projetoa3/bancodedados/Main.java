package br.com.projetoa3.bancodedados;

public class Main {
    public static void main(String[] args) {
        TurmaCrud turmas = new TurmaCrud();

        turmas.criarTabelaTurmas();

        turmas.inserirTurma();

        turmas.listarTurmas();

        turmas.buscarTurmaPorId();

        turmas.buscarTurmaPorNome();

        turmas.atualizarTurma();

        turmas.excluirTurma();
    }
}