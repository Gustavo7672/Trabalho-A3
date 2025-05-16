public class Usuario {
    private String email;
    private String senha;
    private boolean cadastroValido;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    private boolean validarEmail() {
        if(email != null && email.contains("@") && email.contains(".")){
            return true;
        }else{
            return false;
        }
    }

    private boolean validarSenha() {
        if (senha != null && senha.length() >= 6) {
            return true;
        }else{
            return false;
        }
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

    public void setEmail(String email) {
        this.email = email;

    }

    public void setSenha(String senha) {
        this.senha = senha;

    }
}
