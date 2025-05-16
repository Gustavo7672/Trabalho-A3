
import java.util.ArrayList;

public class Notas {
    private static ArrayList<Notas> nota = new ArrayList<>();

    private int notaA1;
    private int notaA2;
    private int notaA3;
    private int somaNota;
    private String status;


    public Notas(int notaA1, int notaA2, int notaA3, int somaNota, String status) {
        this.notaA1 = notaA1;
        this.notaA2 = notaA2;
        this.notaA3 = notaA3;
        this.somaNota = somaNota;
        this.status = status;
    }

    private void calcularSoma() {
        for (int i = 0; i < nota.size(); i++) {
            somaNota = nota.get(i).notaA1 + nota.get(i).notaA2 + nota.get(i).notaA3;
            nota.get(i).setSomaNota(somaNota);
        }
    }
    private void calcularStatus() {
        for (int i = 0; i < nota.size(); i++) {
            if (nota.get(i).somaNota >= 70) {
                status = "Aprovado";
                nota.get(i).setStatus(status);
            } else {
                status = "Reprovado";
                nota.get(i).setStatus(status);
            }
        }
    }

    public static ArrayList<Notas> getNota() {
        return nota;
    }


    public int getNotaA1() {
        return notaA1;
    }

    public int getNotaA2() {
        return notaA2;
    }

    public int getNotaA3() {
        return notaA3;
    }

    public int getSomaNota() {
        return somaNota;
    }

    public String getStatus() {
        return status;
    }

    public void setNotaA1(int notaA1) {
        this.notaA1 = notaA1;
        
    }

    public void setNotaA2(int notaA2) {
        this.notaA2 = notaA2;
        
    }

    public void setNotaA3(int notaA3) {
        this.notaA3 = notaA3;
      
    }

    public void setSomaNota(int somaNota) {
        this.somaNota = somaNota;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}