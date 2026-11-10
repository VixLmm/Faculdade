package servico;

import modelo.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Regras do CRUD. Guarda os itens em memória (sem persistência, conforme o enunciado).
 * Não faz nenhuma leitura/escrita no terminal: isso fica na camada ui.
 */
public class InventarioService {

    private final List<Item> itens = new ArrayList<>();
    private int proximoId = 1;

    /** Gera um id único; o id nunca é reaproveitado, mesmo após exclusão. */
    public int gerarId() {
        return proximoId++;
    }

    // CREATE
    public void adicionar(Item item) {
        itens.add(item);
    }

    // READ
    public List<Item> listar() {
        return Collections.unmodifiableList(itens);
    }

    public Optional<Item> buscarPorId(int id) {
        return itens.stream().filter(i -> i.getId() == id).findFirst();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    // DELETE
    public boolean remover(int id) {
        return itens.removeIf(i -> i.getId() == id);
    }

    // Extras de leitura
    public double pesoTotal() {
        double total = 0;
        for (Item i : itens) {
            total += i.getPeso();
        }
        return total;
    }

    public int valorTotal() {
        int total = 0;
        for (Item i : itens) {
            total += i.getValor();
        }
        return total;
    }

    // UPDATE não precisa de método próprio: o item é obtido com buscarPorId()
    // e alterado pelos setters, que já validam os dados.
}
