package br.com.projetoa3.bancodedados;

import br.com.projetoa3.modelo.Notas;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class NotasCrud {

    private final String URL = "jdbc:mysql://localhost:3306/projetoa3?serverTimezone=America/Bahia"; // substitua
    private final String USUARIO = "root"; // substitua
    private final String SENHA = "gu7672017";

    public void criarTabelaNotas() {
        String sql = """
            CREATE TABLE IF NOT EXISTS notas (
                ra int PRIMARY KEY,
                A1 int,
                A2 int,
                A3 int,
                soma VARCHAR(100),
                status VARCHAR(100)
                );
        """;

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'notas' criada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void inserirNotas(Long ra, int A1, int A2, int A3, int soma, String status) {
        String inserirNota = "INSERT INTO notas (ra, A1, A2, A3, soma,status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement psInsere = conn.prepareStatement(inserirNota)) {

            psInsere.setLong(1, ra);
            psInsere.setInt(2, A1);
            psInsere.setInt(3, A2);
            psInsere.setInt(4, A3);
            psInsere.setInt(5, soma);
            psInsere.setString(6, status);
            psInsere.executeUpdate();

            System.out.println("Notas inseridas com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir notas: " + e.getMessage());
        }
    }

    public Map<Long, Notas> listarNotas() {
        Map<Long, Notas> notasMap = new HashMap<>();
        String sql = "SELECT * FROM notas";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Notas notas = new Notas(
                        rs.getInt("A1"),
                        rs.getInt("A2"),
                        rs.getInt("A3")
                );
                notasMap.put(rs.getLong("ra"), notas);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar notas por RA: " + e.getMessage());
        }
        return notasMap;
    }

public void buscarNotasPorId(int idNota) {
    String sql = """
            SELECT n.id, a.nome, a.ra, n.A1, n.A2, n.A3, n.soma
            FROM notas n
            JOIN alunos a ON n.aluno_id = a.id
            WHERE n.id = ?
        """;

    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idNota);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.printf("ID Nota: %d | Aluno: %s | RA: %s | A1: %.2f | A2: %.2f | A3: %.2f | Soma: %.2f%n",
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("ra"),
                    rs.getDouble("A1"),
                    rs.getDouble("A2"),
                    rs.getDouble("A3"),
                    rs.getDouble("soma")
            );
        } else {
            System.out.println("Nenhuma nota encontrada com ID: " + idNota);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao buscar nota por ID: " + e.getMessage());
    }
}

public void atualizarNotas(int idNota, double novaA1, double novaA2, double novaA3) {
    String sql = "UPDATE notas SET A1 = ?, A2 = ?, A3 = ? WHERE id = ?";

    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDouble(1, novaA1);
        stmt.setDouble(2, novaA2);
        stmt.setDouble(3, novaA3);
        stmt.setInt(4, idNota);

        int afetados = stmt.executeUpdate();
        if (afetados > 0) {
            System.out.println("Notas atualizadas com sucesso.");
        } else {
            System.out.println("Nota com ID " + idNota + " não encontrada.");
        }

    } catch (SQLException e) {
        System.err.println("Erro ao atualizar notas: " + e.getMessage());
    }
}

public void excluirNotas(int ra) {
    String sql = "DELETE FROM notas WHERE ra = ?";

    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, ra);
        int afetados = stmt.executeUpdate();

        if (afetados > 0) {
            System.out.println("Notas excluídas com sucesso.");
        } else {
            System.out.println("Nota com ID " + ra + " não encontrada.");
        }

    } catch (SQLException e) {
        System.err.println("Erro ao excluir notas: " + e.getMessage());
    }
}
}

