package modelo;

public class Pocao extends Item {

    private int cura;

    public Pocao(int id, String nome, double peso, int valor, int cura) {
        super(id, nome, peso, valor);
        setAtributo(cura);
    }

    @Override
    public String getTipo() {
        return "Poção";
    }

    @Override
    public String getRotuloAtributo() {
        return "Cura (HP)";
    }

    @Override
    public int getAtributo() {
        return cura;
    }

    @Override
    public void setAtributo(int cura) {
        if (cura <= 0) {
            throw new IllegalArgumentException("A cura deve ser maior que zero.");
        }
        this.cura = cura;
    }

    @Override
    public String getDetalhe() {
        return "Cura: " + cura + " HP";
    }
}
