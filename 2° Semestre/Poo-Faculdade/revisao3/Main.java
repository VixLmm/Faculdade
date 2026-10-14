public class Main {

    public static void main(String[] args) {

        System.out.println("===== 1. Fluxo pedido no enunciado =====");
        Produto notebook = new Produto("Notebook", 3500.00);
        notebook.exibirResumo();                 // estoque inicial = 0

        notebook.adicionarEstoque(10);
        System.out.println("Venda de 3 (válida): " + notebook.vender(3));
        System.out.println("Venda de 50 (maior que o estoque): " + notebook.vender(50));
        notebook.exibirResumo();                 // esperado: 7

        System.out.println("\n===== 2. Casos negativos =====");
        System.out.println("Adicionar 0: " + notebook.adicionarEstoque(0));
        System.out.println("Adicionar -5: " + notebook.adicionarEstoque(-5));
        System.out.println("Vender 0: " + notebook.vender(0));
        System.out.println("Vender -2: " + notebook.vender(-2));
        notebook.exibirResumo();                 // estoque não pode ter mudado: 7

        System.out.println("\n===== 3. Casos extremos =====");
        System.out.println("Vender exatamente o estoque (7): " + notebook.vender(7));
        notebook.exibirResumo();                 // esperado: 0
        System.out.println("Vender 1 com estoque zerado: " + notebook.vender(1));

        notebook.adicionarEstoque(Integer.MAX_VALUE);
        System.out.println("Adicionar +1 além do limite do int: " + notebook.adicionarEstoque(1));
        notebook.exibirResumo();                 // deve continuar em 2147483647, sem ficar negativo

        Produto brinde = new Produto("Chaveiro (brinde)", 0.0); // preço zero é permitido
        brinde.exibirResumo();

        System.out.println("\n===== 4. Getters e Setters =====");
        notebook.setNome("Notebook Gamer");
        notebook.setPreco(4299.90);
        System.out.println("Nome: " + notebook.getNome());
        System.out.println("Preço: " + notebook.getPreco());

        System.out.println("\n===== 5. Dados inválidos são rejeitados =====");
        testarCriacao("", 10.0);
        testarCriacao(null, 10.0);
        testarCriacao("Mouse", -1.0);

        try {
            notebook.setPreco(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("setPreco(-100) recusado: " + e.getMessage());
        }
        notebook.exibirResumo();                 // preço continua 4299,90
    }

    private static void testarCriacao(String nome, double preco) {
        try {
            new Produto(nome, preco);
            System.out.println("Criado: " + nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Criação recusada (nome=" + nome + ", preço=" + preco + "): " + e.getMessage());
        }
    }
}
