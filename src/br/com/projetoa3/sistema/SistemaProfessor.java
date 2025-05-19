package br.com.projetoa3.sistema;

import br.com.projetoa3.modelo.Professor;

public class SistemaProfessor {
    public static void cadastrarProfessor() {
        if (Professor.getEmail() == null) {
            System.out.println("-------Cadastro-------");
            System.out.println("Digite o seu email para registro:");
            Professor.setEmail(SistemaMaster.getImput().nextLine());
            Professor.validarEmail();
            System.out.println("Digite a senha para registro:");
            Professor.setSenha(SistemaMaster.getImput().nextLine());
            Professor.validarSenha();

            SistemaMaster.login();
        } else {
            SistemaMaster.login();
        }
    }


}
