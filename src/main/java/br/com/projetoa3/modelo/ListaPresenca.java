package br.com.projetoa3.modelo;

import java.util.ArrayList;

public class ListaPresenca {
    private static String data;
        private static String listarPresenca;
        private static ArrayList <ListaPresenca> presenca = new ArrayList<>();

    public ListaPresenca(String listarPresencaa, String dataa) {
        listarPresenca = listarPresencaa;
        data = dataa;
    }
    public static ArrayList<ListaPresenca> getPresenca() {
        return presenca;
    }

    public void adicionarPresencaNaLista(ListaPresenca presencaa){
            presenca.add(presencaa);

    }

    public String getListarPresenca() {
        return listarPresenca;
    }

    public void setListarPresenca(String listarPresencaa) {
       listarPresenca = listarPresencaa;
    }

    public String getData() {
        return data;
    }

    public void setData(String dataa) {
        data = dataa;
    }
}