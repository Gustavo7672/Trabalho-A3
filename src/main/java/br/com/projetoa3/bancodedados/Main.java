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

        ProfessorCrud crud = new ProfessorCrud();

        crud.criarTabelaProfessores();

        crud.inserirProfessor("", "", "", "");

        crud.listarProfessores();

        crud.buscarProfessorPorRa("");

        crud.atualizarProfessor("", "", "", "");

        crud.listarProfessores();

        crud.excluirProfessor("");

        crud.listarProfessores();
    }
}