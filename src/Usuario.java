public class Usuario {
    private static String emailVerificar;
    private static String senhaVerificar;
    private static String email;
    private static String senha;
    private static boolean emailValido = true;
    private static boolean senhaValido = true;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static String getEmailVerificar() {
        return emailVerificar;
    }

    public static void setEmailVerificar(String emailVerificar) {
        Usuario.emailVerificar = emailVerificar;
    }

    public static String getSenhaVerificar() {
        return senhaVerificar;
    }

    public static void setSenhaVerificar(String senhaVerificar) {
        Usuario.senhaVerificar = senhaVerificar;
    }

    public static void validarEmail() {
        if(email != null && email.contains("@") && email.contains(".")){
            emailValido = true;
        }else{
            emailValido = false;
        }
        if(emailValido){
        }else{
            System.out.println("email invalido");
            System.exit(0);
        }
    }

    public static void validarSenha() {
        if (senha != null && senha.length() >= 6) {
            senhaValido = true;
        }else{
            senhaValido = false;
        } if(senhaValido){
    }else {
            System.out.println("senha invalido");
            System.exit(0);
        }
    }
    public static String getEmail() {
        return email;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setEmail(String emailUsuario) {
        email = emailUsuario;
    }

    public static void setSenha(String senhaUsuario) {
        senha = senhaUsuario;
    }
}
