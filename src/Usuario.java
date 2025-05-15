public class Usuario {
    // Atributos privados
    private String nome;
    private String ra;
    private String email;
    private String senha;
    private boolean cadastroValido;

    // Construtor
    public Usuario(String nome, String ra, String email, String senha) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.senha = senha;
        validarCadastro();
    }

    // Método para validar o e-mail
    private boolean validarEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Método para validar a senha
    private boolean validarSenha() {
        return senha != null && senha.length() >= 6;
    }

    // Método para validar todo o cadastro
    private void validarCadastro() {
        if (nome != null && !nome.isEmpty() &&
                ra != null && !ra.isEmpty() &&
                validarEmail() && validarSenha()) {
            cadastroValido = true;
        } else {
            cadastroValido = false;
        }
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getRa() {
        return ra;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isCadastroValido() {
        return cadastroValido;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
        validarCadastro();
    }

    public void setRa(String ra) {
        this.ra = ra;
        validarCadastro();
    }

    public void setEmail(String email) {
        this.email = email;
        validarCadastro();
    }

    public void setSenha(String senha) {
        this.senha = senha;
        validarCadastro();
    }
}
