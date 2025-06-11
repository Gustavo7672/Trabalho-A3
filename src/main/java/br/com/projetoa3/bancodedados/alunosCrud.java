package br.com.projetoa3.bancodedados;

import java.sql.*;

public class alunosCrud {
    // Configurações do banco de dados
    private final String url = "jdbc:mysql://localhost:3306/meubanco"; // substitua
    private final String usuario = "root"; // substitua
    private final String senha = "ijklsm6644"; // substitua

    public void criarTabelaAlunos() {
        String sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                "RA INT PRIMARY KEY," +
                "nome VARCHAR(100) NOT NULL," +
                "presenca_id INT," +
                "turma_id INT," +
                "notas_id INT," +
                "FOREIGN KEY (presenca_id) REFERENCES presenca(id)," +
                "FOREIGN KEY (turma_id) REFERENCES turma(id)," +
                "FOREIGN KEY (notas_id) REFERENCES notas(id)" +
                ") ENGINE=InnoDB;";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabela 'alunos' criada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public void inserirAluno(int ra, String nome, Integer presencaId, Integer turmaId, Integer notasId) {
        String sql = "INSERT INTO alunos (RA, nome, presenca_id, turma_id, notas_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ra);
            stmt.setString(2, nome);
            stmt.setObject(3, presencaId, Types.INTEGER);
            stmt.setObject(4, turmaId, Types.INTEGER);
            stmt.setObject(5, notasId, Types.INTEGER);

            stmt.executeUpdate();
            System.out.println("Aluno inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public void listarAlunos() {
        String sql = "SELECT * FROM alunos";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Lista de alunos:");
            while (rs.next()) {
                System.out.println("RA: " + rs.getInt("RA") +
                        ", Nome: " + rs.getString("nome") +
                        ", Presença ID: " + rs.getInt("presenca_id") +
                        ", Turma ID: " + rs.getInt("turma_id") +
                        ", Notas ID: " + rs.getInt("notas_id"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
    }

    public void buscarAlunoPorRA(int ra) {
        String sql = "SELECT * FROM alunos WHERE RA = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ra);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Aluno encontrado:");
                System.out.println("RA: " + rs.getInt("RA") +
                        ", Nome: " + rs.getString("nome") +
                        ", Presença ID: " + rs.getInt("presenca_id") +
                        ", Turma ID: " + rs.getInt("turma_id") +
                        ", Notas ID: " + rs.getInt("notas_id"));
            } else {
                System.out.println("Aluno com RA " + ra + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno por RA: " + e.getMessage());
        }
    }

    public void buscarAlunoPorNome(String nome) {
        String sql = "SELECT * FROM alunos WHERE nome LIKE ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            System.out.println("Resultados da busca por nome:");
            while (rs.next()) {
                System.out.println("RA: " + rs.getInt("RA") +
                        ", Nome: " + rs.getString("nome") +
                        ", Presença ID: " + rs.getInt("presenca_id") +
                        ", Turma ID: " + rs.getInt("turma_id") +
                        ", Notas ID: " + rs.getInt("notas_id"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno por nome: " + e.getMessage());
        }
    }

    public void atualizarAluno(int ra, String novoNome, Integer novaPresencaId, Integer novaTurmaId, Integer novaNotasId) {
        String sql = "UPDATE alunos SET nome = ?, presenca_id = ?, turma_id = ?, notas_id = ? WHERE RA = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setObject(2, novaPresencaId, Types.INTEGER);
            stmt.setObject(3, novaTurmaId, Types.INTEGER);
            stmt.setObject(4, novaNotasId, Types.INTEGER);
            stmt.setInt(5, ra);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Aluno atualizado com sucesso.");
            } else {
                System.out.println("Aluno com RA " + ra + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void excluirAluno(int ra) {
        String sql = "DELETE FROM alunos WHERE RA = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ra);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Aluno excluído com sucesso.");
            } else {
                System.out.println("Aluno com RA " + ra + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}
