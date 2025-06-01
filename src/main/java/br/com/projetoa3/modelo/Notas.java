package br.com.projetoa3.modelo;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Notas {
    private static Map<Long, Notas> notasPorAluno = new HashMap<>();

    private int notaA1;
    private int notaA2;
    private int notaA3;
    private int somaNota;
    private String status;


    public Notas(int notaA1, int notaA2, int notaA3) {
        this.notaA1 = notaA1;
        this.notaA2 = notaA2;
        this.notaA3 = notaA3;
        this.somaNota = notaA1 + notaA2 + notaA3;
        this.status = (somaNota >= 70) ? "Aprovado" : "Reprovado";
    }

    public static void adicionarNota(Long ra, Notas nota) {
        notasPorAluno.put(ra, nota);
    }

    public static Notas getNotaPorAluno(Long ra) {
        return notasPorAluno.get(ra);
    }

    public static Map<Long, Notas> getNotasPorAluno() {
        return notasPorAluno;
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
}
