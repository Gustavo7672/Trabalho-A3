import java.util.ArrayList;

public class ListaPresenca {
    private static String data;
        private static String listarPresenca;
        private static ArrayList <ListaPresenca> presenca = new ArrayList<>();

    public ListaPresenca(String listarPresencaa, String dataa) {
        listarPresenca = listarPresencaa;
        data = dataa;
    }

    public String presente() {
            return "presente";
        }

        public String ausente() {
            return "ausente";
        }

    public static ArrayList<ListaPresenca> getPresenca() {
        return presenca;
    }

    public void adicionarPresencaNaLista(ListaPresenca presencaa){
            presenca.add(presencaa);

    }

    public static String getListarPresenca() {
        return listarPresenca;
    }

    public static void setListarPresenca(String listarPresencaa) {
       listarPresenca = listarPresencaa;
    }

    public static String getData() {
        return data;
    }

    public static void setData(String dataa) {
        data = dataa;
    }
}