package modelo;

public class Armadura extends Item {

    private int defesa;

    public Armadura(int id, String nome, double peso, int valor, int defesa) {
        super(id, nome, peso, valor);
        setAtributo(defesa);
    }

    @Override
    public String getTipo() {
        return "Armadura";
    }

    @Override
    public String getRotuloAtributo() {
        return "Defesa";
    }

    @Override
    public int getAtributo() {
        return defesa;
    }

    @Override
    public void setAtributo(int defesa) {
        if (defesa <= 0) {
            throw new IllegalArgumentException("A defesa deve ser maior que zero.");
        }
        this.defesa = defesa;
    }

    @Override
    public String getDetalhe() {
        return "Defesa: " + defesa;
    }
}
