public class Notas {
    // Atributos privados
    private int notaA1;
    private int notaA2;
    private int notaA3;
    private int somaNota;
    private String status;

    // Construtor
    public Notas(int notaA1, int notaA2, int notaA3) {
        
        this.notaA1 = notaA1;
        this.notaA2 = notaA2;
        this.notaA3 = notaA3;
        
    }

    // Método para calcular a soma das notas
    private void calcularSoma() {
        somaNota = notaA1 + notaA2 + notaA3;
    }

    // Método para verificar aprovação
    private void calcularStatus() {
        if (somaNota >= 70) {
            status = "Aprovado";
        } else {
            status = "Reprovado";
        }
    }

    // Métodos getters
    
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

    // Métodos setters
    
    public void setNotaA1(int notaA1) {
        this.notaA1 = notaA1;
        
    }

    public void setNotaA2(int notaA2) {
        this.notaA2 = notaA2;
        
    }

    public void setNotaA3(int notaA3) {
        this.notaA3 = notaA3;
      
    }
}