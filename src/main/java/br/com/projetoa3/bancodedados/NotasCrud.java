package br.com.projetoa3.bancodedados;

import java.sql.*;

public class NotasCrud {

    private static final String URL = "jdbc:mysql://localhost:3306/bancohaha";
    private static final String USUARIO = "root";
    private static final String SENHA = "240905";

    public void criarTabelaNotas() {
        String sql = """
            CREATE TABLE IF NOT EXISTS notas (
                id INT AUTO_INCREMENT PRIMARY KEY,
                aluno_id INT,
                A1 DOUBLE NOT NULL,
                A2 DOUBLE NOT NULL,
                A3 DOUBLE NOT NULL,
                soma DOUBLE GENERATED ALWAYS AS (A1 + A2 + A3) STORED,
                FOREIGN KEY (aluno_id) REFERENCES alunos(id)
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

    public void inserirNotas(String ra, double A1, double A2, double A3) {
        String buscarAluno = "SELECT id FROM alunos WHERE ra = ?";
        String inserirNota = "INSERT INTO notas (aluno_id, A1, A2, A3) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement psBusca = conn.prepareStatement(buscarAluno);
             PreparedStatement psInsere = conn.prepareStatement(inserirNota)) {

            psBusca.setString(1, ra);
            ResultSet rs = psBusca.executeQuery();

            if (rs.next()) {
                int alunoId = rs.getInt("id");

                psInsere.setInt(1, alunoId);
                psInsere.setDouble(2, A1);
                psInsere.setDouble(3, A2);
                psInsere.setDouble(4, A3);
                psInsere.executeUpdate();

                System.out.println("Notas inseridas com sucesso.");
            } else {
                System.out.println("Aluno com RA " + ra + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir notas: " + e.getMessage());
        }
    }

    public void listarNotasPorRA(String ra) {
        String sql = """
            SELECT n.id, a.nome, a.ra, n.A1, n.A2, n.A3, n.soma
            FROM notas n
            JOIN alunos a ON n.aluno_id = a.id
            WHERE a.ra = ?
        """;

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ra);
            ResultSet rs = stmt.executeQuery();

            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.printf("ID Nota: %d | Aluno: %s | RA: %s | A1: %.2f | A2: %.2f | A3: %.2f | Soma: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("ra"),
                        rs.getDouble("A1"),
                        rs.getDouble("A2"),
                        rs.getDouble("A3"),
                        rs.getDouble("soma")
                );
            }

            if (!encontrou) {
                System.out.println("Nenhuma nota encontrada para RA: " + ra);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar notas por RA: " + e.getMessage());
        }
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

    public void excluirNotas(int idNota) {
        String sql = "DELETE FROM notas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idNota);
            int afetados = stmt.executeUpdate();

            if (afetados > 0) {
                System.out.println("Notas excluídas com sucesso.");
            } else {
                System.out.println("Nota com ID " + idNota + " não encontrada.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir notas: " + e.getMessage());
        }
    }
}
