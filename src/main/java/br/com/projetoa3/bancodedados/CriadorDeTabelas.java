package br.com.projetoa3.bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CriadorDeTabelas {


    public final String url = "jdbc:mysql://localhost:3306/bancoarthur";
    public final String usuario = "root";
    public final String senha = "Arthur@18120612";

    public void criarTabelas() {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             Statement stmt = conexao.createStatement()) {


            String sqlPresenca = """
                CREATE TABLE IF NOT EXISTS presenca (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    data DATE NOT NULL,
                    descricao VARCHAR(100)
                );
                """;


            String sqlPresencaAluno = """
                CREATE TABLE IF NOT EXISTS presencaaluno (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    id_presenca INT NOT NULL,
                    nome_aluno VARCHAR(100) NOT NULL,
                    presente BOOLEAN NOT NULL,
                    FOREIGN KEY (id_presenca) REFERENCES presenca(id)
                );
                """;


            stmt.executeUpdate(sqlPresenca);
            stmt.executeUpdate(sqlPresencaAluno);

            System.out.println("Tabelas 'presenca' e 'presencaaluno' criadas com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}


