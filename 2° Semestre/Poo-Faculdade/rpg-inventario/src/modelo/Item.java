package modelo;

/**
 * Classe base (abstrata) de todos os itens do inventário.
 * Subclasses: Arma, Armadura e Pocao.
 */
public abstract class Item {

    private final int id;
    private String nome;
    private double peso;
    private int valor;

    protected Item(int id, String nome, double peso, int valor) {
        this.id = id;
        setNome(nome);
        setPeso(peso);
        setValor(valor);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso < 0) {
            throw new IllegalArgumentException("O peso não pode ser negativo.");
        }
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor não pode ser negativo.");
        }
        this.valor = valor;
    }

    /** Nome do tipo do item (ex.: "Arma"). */
    public abstract String getTipo();

    /** Descrição do atributo específico do subtipo (ex.: "Dano: 12"). */
    public abstract String getDetalhe();

    /** Rótulo do atributo específico, usado nos menus (ex.: "Dano"). */
    public abstract String getRotuloAtributo();

    /** Valor atual do atributo específico. */
    public abstract int getAtributo();

    /** Altera o atributo específico, com validação própria do subtipo. */
    public abstract void setAtributo(int atributo);

    @Override
    public String toString() {
        return String.format("[%d] %-10s | %-20s | %6.2f kg | %5d moedas | %s",
                id, getTipo(), nome, peso, valor, getDetalhe());
    }
}
