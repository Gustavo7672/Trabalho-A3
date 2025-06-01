package br.com.projetoa3.sistema;

import br.com.projetoa3.modelo.Professor;

public class SistemaProfessor {
    public static void cadastrarProfessor() {
        if (Professor.getEmail() != null) {
            Professor.validarEmail();
            Professor.validarSenha();
        }
    }


}
