public class Produto {

    private final int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(int id, String nome, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    public double getPreco() {
        return preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getEstoque() {
        return estoque;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + "]";
    }
}
