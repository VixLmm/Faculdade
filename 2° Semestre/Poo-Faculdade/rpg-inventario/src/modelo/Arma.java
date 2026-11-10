package modelo;

public class Arma extends Item {

    private int dano;

    public Arma(int id, String nome, double peso, int valor, int dano) {
        super(id, nome, peso, valor);
        setAtributo(dano);
    }

    @Override
    public String getTipo() {
        return "Arma";
    }

    @Override
    public String getRotuloAtributo() {
        return "Dano";
    }

    @Override
    public int getAtributo() {
        return dano;
    }

    @Override
    public void setAtributo(int dano) {
        if (dano <= 0) {
            throw new IllegalArgumentException("O dano deve ser maior que zero.");
        }
        this.dano = dano;
    }

    @Override
    public String getDetalhe() {
        return "Dano: " + dano;
    }
}
