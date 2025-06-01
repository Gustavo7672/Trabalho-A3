package br.com.projetoa3.modelo;

import javafx.beans.property.BooleanProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaPresenca {
    private static String data;
        private static String listarPresenca;
        private static Map<LocalDate, Map<Long, BooleanProperty>> presencas = new HashMap<>();
    public ListaPresenca(String listarPresencaa, String dataa) {
        listarPresenca = listarPresencaa;
        data = dataa;
    }

    public static Map<LocalDate, Map<Long, BooleanProperty>> getPresencas() {
        return presencas;
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