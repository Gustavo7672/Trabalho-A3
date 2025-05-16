import java.util.Scanner;

public class Sistema {
    private int tentativas=1;
    private String opcao;
    private Scanner imput = new Scanner(System.in);

    public void menu() {
        System.out.println("Sistema lista de presença e notas");
        System.out.println("Deseja adicionar presença, notas, alunos ou ver a lista 1/2/3/4");
        int comando = imput.nextInt();
        switch (comando) {
            case 1:
                cadastrarListaDePresenca();
                break;
            case 2:
                cadastrarNota();
                break;
            case 3:
                cadastrarAlunos();
                break;
            case 4:
                verLista();
                break;
            default:
                System.out.println("Número incorreto, escolha entre os 4 números solicitados!");
        }
    }

    public void cadastrarProfessor() {
        if (Usuario.getEmail() == null){
            System.out.println("-------Cadastro-------");
            System.out.println("Digite o seu email para registro:");
            Usuario.setEmail(imput.nextLine());
            Usuario.validarEmail();
            System.out.println("Digite a senha para registro:");
            Usuario.setSenha(imput.nextLine());
            Usuario.validarSenha();

            login();
    } else {
            login();
        }
    }

    public void cadastrarAlunos() {
        do {
            Alunos aluno = new Alunos("", 0);
            System.out.println("Cadastro de alunos!!");
            System.out.println("Nome do aluno:");
            aluno.setNome(imput.nextLine());
            System.out.println("Ra do aluno:");
            aluno.setRa(imput.nextLong());
            imput.nextLine();
            aluno.cadastrarNaLista(aluno.getNome(), aluno.getRa());
            System.out.println("Desejar continuar? s/n");
            opcao = imput.nextLine();
        } while (opcao.equalsIgnoreCase("s"));
        menu();

    }

    public void login(){
        do {
            System.out.println("Entre na sua conta:");
            System.out.println("Você tem "+tentativas+" de 5:=");
            System.out.println("Digite seu email:");
            Usuario.setEmailVerificar(imput.nextLine());
            System.out.println("Digite sua senha:");
            Usuario.setSenhaVerificar(imput.nextLine());
            tentativas++;
            if (Usuario.getEmailVerificar().equals(Usuario.getEmail()) && Usuario.getSenhaVerificar().equals(Usuario.getSenha())
            ) {
                System.out.println("Login efetuado com sucesso");
            } else{
                System.out.println("Email ou senha invalido");
            }
        } while (!Usuario.getEmailVerificar().equals(Usuario.getEmail()) || !Usuario.getSenhaVerificar().equals(Usuario.getSenha()));


            menu();
    }

    public void cadastrarNota() {
        Notas nota = new Notas(0, 0, 0,0 , "");
        System.out.println("-------Sistema de notas-------");
        for (int i = 0; i < Alunos.getLista().size();i++) {
            if (Alunos.getLista().get(i).getNome()==null) {
                System.out.println("Lista vazia");
            } else{
                System.out.println(Alunos.getLista().get(i).getNome());
                System.out.println("Digite a nota A1:");
                nota.setNotaA1(imput.nextInt());
                imput.nextLine();
                System.out.println("Digite a nota A2:");
                nota.setNotaA2(imput.nextInt());
                imput.nextLine();
                System.out.println("Digite a nota A3");
                nota.setNotaA3(imput.nextInt());
                imput.nextLine();
                nota.calcularSoma();
            }

        }
    }

    public void cadastrarListaDePresenca() {


    }

    public void verLista(){

    }
}
