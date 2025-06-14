package br.com.projetoa3.bancodedados;
import br.com.projetoa3.modelo.Turmas;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TurmaCrud {

    private final String URL = "jdbc:mysql://localhost:3306/projetoa3?serverTimezone=America/Bahia"; // substitua
    private final String USUARIO = "root"; // substitua
    private final String SENHA = "gu7672017";

    public void criarTabelaTurmas() {
        String sql = """
            CREATE TABLE IF NOT EXISTS turmas (
                id VARCHAR(100) PRIMARY KEY,
                nomeDaTurma VARCHAR(100) NOT NULL,
                professores_ra VARCHAR(50),
                FOREIGN KEY (professores_ra) REFERENCES professores(ra)
            );
        """;

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela 'turmas' criada com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public void inserirTurma(String id,String nomeDaTurma, String professores_ra) {
        String sql = "INSERT INTO turmas (id, nomeDaTurma, professores_ra) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, nomeDaTurma);
            pstmt.setString(3, professores_ra);
            pstmt.executeUpdate();
            System.out.println("Turma inserida com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir turma: " + e.getMessage());
        }
    }

    public Map<String, Turmas> listarTurmas() {
        Map<String, Turmas> turmas = new HashMap<>();
        String sql = "SELECT * FROM turmas";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String nome = rs.getString("nomeDaTurma");
                String professoresRa = rs.getString("professores_ra");
                Turmas turma = new Turmas(nome, professoresRa);
                turmas.put(id, turma);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar turmas: " + e.getMessage());
        }
        return turmas;
    }

    public void buscarTurmaPorId(int id) {
        String sql = "SELECT * FROM turmas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Turma encontrada: " + rs.getString("nomeDaTurma"));
            } else {
                System.out.println("Nenhuma turma encontrada com ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar turma por ID: " + e.getMessage());
        }
    }

    public void buscarTurmaPorNome(String nome) {
        String sql = "SELECT * FROM turmas WHERE nomeDaTurma LIKE ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nome + "%");
            ResultSet rs = pstmt.executeQuery();

            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nomeDaTurma"));
            }

            if (!encontrou) {
                System.out.println("Nenhuma turma encontrada com nome contendo: " + nome);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar turma por nome: " + e.getMessage());
        }
    }

    public void atualizarTurma(int id, String novoNome) {
        String sql = "UPDATE turmas SET nomeDaTurma = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, novoNome);
            pstmt.setInt(2, id);

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Turma atualizada com sucesso.");
            } else {
                System.out.println("Nenhuma turma encontrada com ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    public void excluirTurma(int id) {
        String sql = "DELETE FROM turmas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Turma excluída com sucesso.");
            } else {
                System.out.println("Nenhuma turma encontrada com ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir turma: " + e.getMessage());
        }
    }
}