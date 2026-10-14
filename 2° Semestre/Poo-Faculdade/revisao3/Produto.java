public class Produto {

    // Atributos privados: só podem ser acessados de dentro da classe
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    // Construtor: recebe nome e preço; o estoque sempre começa em 0
    public Produto(String nome, double preco) {
        setNome(nome);   // reaproveita a validação do setter
        setPreco(preco);
        this.quantidadeEmEstoque = 0;
    }

    // ---------- Getters e Setters ----------

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0 || Double.isNaN(preco) || Double.isInfinite(preco)) {
            throw new IllegalArgumentException("O preço deve ser um número maior ou igual a zero.");
        }
        this.preco = preco;
    }

    // Apenas Getter: o estoque só muda pelos métodos de comportamento
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    // ---------- Métodos de comportamento ----------

    public boolean adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida: informe um valor maior que zero.");
            return false;
        }
        // Evita estouro do int (o valor "daria a volta" e ficaria negativo)
        if (quantidade > Integer.MAX_VALUE - quantidadeEmEstoque) {
            System.out.println("Operação recusada: o estoque ultrapassaria o limite máximo.");
            return false;
        }
        quantidadeEmEstoque += quantidade;
        return true;
    }

    public boolean vender(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida para venda: informe um valor maior que zero.");
            return false;
        }
        if (quantidade > quantidadeEmEstoque) {
            System.out.println("Estoque insuficiente para a venda.");
            return false;
        }
        quantidadeEmEstoque -= quantidade;
        return true;
    }

    public void exibirResumo() {
        System.out.printf("Produto: %s | Preço: R$ %.2f | Estoque: %d unidade(s)%n",
                nome, preco, quantidadeEmEstoque);
    }
}
