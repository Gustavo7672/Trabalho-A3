import java.util.Scanner;

public class Sistema {
    private int tentativas = 1;
    private String opcao;
    private Scanner imput = new Scanner(System.in);

    public void menu() {
        System.out.println("Sistema lista de presença e notas");
        System.out.println("Deseja adicionar presença, notas, alunos ou ver a lista 1/2/3/4");
        int comando = imput.nextInt();
        switch (comando) {
            case 1:
                adicionarPresenca();
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
        if (Usuario.getEmail() == null) {
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
            imput.nextLine();
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

    public void login() {
        do {
            System.out.println("Entre na sua conta:");
            System.out.println("Você tem " + tentativas + " de 5:=");
            System.out.println("Digite seu email:");
            Usuario.setEmailVerificar(imput.nextLine());
            System.out.println("Digite sua senha:");
            Usuario.setSenhaVerificar(imput.nextLine());
            tentativas++;
            if (Usuario.getEmailVerificar().equals(Usuario.getEmail()) && Usuario.getSenhaVerificar().equals(Usuario.getSenha())
            ) {
                System.out.println("Login efetuado com sucesso");
            } else {
                System.out.println("Email ou senha invalido");
            }
        } while (!Usuario.getEmailVerificar().equals(Usuario.getEmail()) || !Usuario.getSenhaVerificar().equals(Usuario.getSenha()));


        menu();
    }

    public void cadastrarNota() {
        Notas nota = new Notas(0, 0, 0, 0, "");
        System.out.println("-------Sistema de notas-------");
        for (int i = 0; i < Alunos.getLista().size(); i++) {
            if (Alunos.getLista().get(i).getNome() == null) {
                System.out.println("Lista vazia");
            } else {
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
                Notas notass = new Notas(nota.getNotaA1(), nota.getNotaA2(), nota.getNotaA3(), nota.getSomaNota(), nota.getStatus());
                notass.adicionar(notass);
                nota.calcularSoma();
                nota.calcularStatus();
                System.out.println("NotaA1: " + notass.getNotaA1() + " NotaA2: " + notass.getNotaA2() + " NotaA3: " + notass.getNotaA3() + " Soma total: " + notass.getSomaNota() + " Status: " + notass.getStatus());
                menu();
            }
        }
    }

    public void adicionarPresenca() {
        for (Alunos aluno : Alunos.getLista()) {
            System.out.println(aluno);
            imput.nextLine();
            System.out.println("Aluno estã presente?");
            ListaPresenca.setListarPresenca(imput.nextLine());
            System.out.println("Data da presença:");
            ListaPresenca.setData(imput.nextLine());
            ListaPresenca p = new ListaPresenca(ListaPresenca.getListarPresenca(), ListaPresenca.getData());
            p.adicionarPresencaNaLista(p);
            menu();
        }
    }

    public void verLista() {
        for (int i = 0; i < Alunos.getLista().size(); i++) {
            System.out.print(Alunos.getLista().get(i) + " |");
            System.out.print(Notas.getNota().get(i) + " |");
            System.out.print(ListaPresenca.getPresenca().get(i));
            menu();
        }
    }
}
