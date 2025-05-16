import java.util.ArrayList;

public class ListaPresenca {

    private static class Aluno {
        private boolean presente;
        private static ArrayList <ListaPresenca> presenca = new ArrayList<>();

        public Aluno(boolean presente) {
            this.presente = presente;
        }

        public boolean isPresente() {
            return presente;
        }

        public void setPresente(boolean presente) {
            this.presente = presente;
        }

        public void marcarPresenca() {
            setPresente(true);
        }

    }

}