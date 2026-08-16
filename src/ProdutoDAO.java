import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Properties;

public class ProdutoDAO {

    private String url;
    private String usuario;
    private String senha;

    public ProdutoDAO() {
        Properties props = new Properties();
        try {

            InputStream input = ProdutoDAO.class.getClassLoader().getResourceAsStream("config.properties");
            props.load(input);

            url = props.getProperty("db.url");
            usuario = props.getProperty("db.usuario");
            senha = props.getProperty("db.senha");

        } catch (IOException e) {
            System.out.println("Erro ao carregar configurações: " + e.getMessage());
        }
    }

    public void inserir(Produto produto) {

        String sql = "INSERT INTO produtos(nome, preco, estoque) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.printf("Erro ao conectar: " + e.getMessage());
        }
    }

    public List<Produto> listarTodos() {

        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int estoque = rs.getInt("estoque");

                Produto produto = new Produto(id, nome, preco, estoque);
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.out.printf("Erro ao conectar: " + e.getMessage());
        }

        return produtos;
    }

    public Produto buscarPorId(int id) {

        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int estoque = rs.getInt("estoque");

                return new Produto(id, nome, preco, estoque);
            }

        } catch (SQLException e) {
            System.out.printf("Erro ao conectar: " + e.getMessage());
        }

        return null;
    }

    public void atualizar(Produto produto) {

        String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3,produto.getEstoque());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.printf("Erro ao conectar: " + e.getMessage());
        }
    }

    public void deletar(int id) {

        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.printf("Erro ao conectar: " + e.getMessage());
        }
    }

    public void deletarTudo() {

        String sql = "TRUNCATE TABLE produtos";

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)){

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}