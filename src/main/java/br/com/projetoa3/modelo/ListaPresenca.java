package br.com.projetoa3.modelo;

import javafx.beans.property.BooleanProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaPresenca {
        private static Map<LocalDate, Map<Long, BooleanProperty>> presencas = new HashMap<>();

    public static Map<LocalDate, Map<Long, BooleanProperty>> getPresencas() {
        return presencas;
    }
}