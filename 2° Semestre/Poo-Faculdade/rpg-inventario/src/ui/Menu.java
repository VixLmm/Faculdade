package ui;

import modelo.Arma;
import modelo.Armadura;
import modelo.Item;
import modelo.Pocao;
import servico.InventarioService;

import java.util.Optional;
import java.util.Scanner;

/**
 * Interface de linha de comando (CLI): mostra o menu e chama o serviço.
 */
public class Menu {

    private final InventarioService service;
    private final Entrada entrada;

    public Menu(InventarioService service, Scanner scanner) {
        this.service = service;
        this.entrada = new Entrada(scanner);
    }

    public void iniciar() {
        System.out.println("=== RPG de Inventário ===");
        try {
            int opcao;
            do {
                mostrarMenu();
                opcao = entrada.lerInt("Escolha uma opção: ", 0, 4);
                System.out.println();
                switch (opcao) {
                    case 1 -> adicionar();
                    case 2 -> listar();
                    case 3 -> atualizar();
                    case 4 -> remover();
                    default -> { /* 0 = sair */ }
                }
            } while (opcao != 0);
        } catch (Entrada.EntradaEncerrada e) {
            System.out.println("\n(entrada encerrada)");
        }
        System.out.println("Até a próxima aventura!");
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("1 - Adicionar item");
        System.out.println("2 - Listar itens");
        System.out.println("3 - Atualizar item");
        System.out.println("4 - Remover item");
        System.out.println("0 - Sair");
        System.out.println("--------------------------");
    }

    // ---------- CREATE ----------
    private void adicionar() {
        System.out.println("Tipo do item:");
        System.out.println("  1 - Arma");
        System.out.println("  2 - Armadura");
        System.out.println("  3 - Poção");
        int tipo = entrada.lerInt("Tipo: ", 1, 3);

        String nome = entrada.lerTexto("Nome: ");
        double peso = entrada.lerDouble("Peso (kg): ", 0);
        int valor = entrada.lerInt("Valor (moedas): ", 0, Integer.MAX_VALUE);
        int id = service.gerarId();

        Item item;
        switch (tipo) {
            case 1 -> item = new Arma(id, nome, peso, valor, entrada.lerInt("Dano: ", 1, Integer.MAX_VALUE));
            case 2 -> item = new Armadura(id, nome, peso, valor, entrada.lerInt("Defesa: ", 1, Integer.MAX_VALUE));
            default -> item = new Pocao(id, nome, peso, valor, entrada.lerInt("Cura (HP): ", 1, Integer.MAX_VALUE));
        }
        service.adicionar(item);
        System.out.println("Item adicionado com sucesso! ID = " + id);
    }

    // ---------- READ ----------
    private void listar() {
        if (service.estaVazio()) {
            System.out.println("O inventário está vazio.");
            return;
        }
        System.out.println("Itens no inventário:");
        for (Item item : service.listar()) {
            System.out.println(item);
        }
        System.out.printf("%nTotal: %d item(ns) | %.2f kg | %d moedas%n",
                service.listar().size(), service.pesoTotal(), service.valorTotal());
    }

    // ---------- UPDATE ----------
    private void atualizar() {
        if (service.estaVazio()) {
            System.out.println("O inventário está vazio. Nada para atualizar.");
            return;
        }
        Optional<Item> encontrado = pedirItem();
        if (encontrado.isEmpty()) {
            return;
        }
        Item item = encontrado.get();
        System.out.println("Editando: " + item);
        System.out.println("(pressione Enter para manter o valor atual)");

        // Cada campo é validado separadamente: um valor inválido não perde os demais.
        String nome = entrada.lerOpcional("Nome [" + item.getNome() + "]: ");
        if (!nome.isEmpty()) {
            item.setNome(nome);
        }

        while (true) {
            String txt = entrada.lerOpcional("Peso [" + item.getPeso() + "]: ").replace(',', '.');
            if (txt.isEmpty()) break;
            try {
                item.setPeso(Double.parseDouble(txt));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  ! Peso inválido (use um número maior ou igual a 0).");
            }
        }

        while (true) {
            String txt = entrada.lerOpcional("Valor [" + item.getValor() + "]: ");
            if (txt.isEmpty()) break;
            try {
                item.setValor(Integer.parseInt(txt));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  ! Valor inválido (use um inteiro maior ou igual a 0).");
            }
        }

        while (true) {
            String txt = entrada.lerOpcional(item.getRotuloAtributo() + " [" + item.getAtributo() + "]: ");
            if (txt.isEmpty()) break;
            try {
                item.setAtributo(Integer.parseInt(txt));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("  ! " + item.getRotuloAtributo() + " inválido (use um inteiro maior que 0).");
            }
        }

        System.out.println("Item atualizado: " + item);
    }

    // ---------- DELETE ----------
    private void remover() {
        if (service.estaVazio()) {
            System.out.println("O inventário está vazio. Nada para remover.");
            return;
        }
        Optional<Item> encontrado = pedirItem();
        if (encontrado.isEmpty()) {
            return;
        }
        Item item = encontrado.get();
        if (entrada.confirmar("Remover '" + item.getNome() + "'?")) {
            service.remover(item.getId());
            System.out.println("Item removido.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    /** Mostra a lista e pede um ID. Retorna vazio (com aviso) se não existir. */
    private Optional<Item> pedirItem() {
        listar();
        System.out.println();
        int id = entrada.lerInt("ID do item: ", 1, Integer.MAX_VALUE);
        Optional<Item> item = service.buscarPorId(id);
        if (item.isEmpty()) {
            System.out.println("Nenhum item com o ID " + id + ".");
        }
        return item;
    }
}
