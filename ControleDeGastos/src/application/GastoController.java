package application;

import model.entities.Gasto;
import model.entities.GerenciadorDeGasto;
import model.enums.Categoria;
import util.InputValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GastoController {

    private final Scanner sc;
    private final DateTimeFormatter fmt;
    private final GerenciadorDeGasto gerenciador;

    public GastoController(Scanner sc, DateTimeFormatter fmt, GerenciadorDeGasto gerenciador) {
        this.sc = sc;
        this.fmt = fmt;
        this.gerenciador = gerenciador;
    }

    public void exibirMenu() {
        System.out.println("\nDeseja:");
        System.out.println("1 - Adicionar gasto");
        System.out.println("2 - Ver total do dia");
        System.out.println("3 - Ver total do mês");
        System.out.println("4 - Gerar relatório");
        System.out.println("0 - Sair");
        System.out.print("Opção - ");
    }

    public int lerOpcao() {
        return InputValidator.lerInteiro(sc, "");
    }

    public void executarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> adicionarGasto();
            case 2 -> totalDoDia();
            case 3 -> totalDoMes();
            case 4 -> gerarRelatorio();
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida!");
        }
    }


    private void adicionarGasto() {
        int n = lerQuantidadeValida();

        for (int i = 0; i < n; i++) {
            System.out.println("\nGasto " + (i + 1) + ":");

            String descricao = lerDescricaoValida();
            double valor = lerValorValido();
            LocalDate data = lerDataValida("Digite a data (dd/MM/yyyy): ");

            Categoria categoria = escolherCategoria();

            Gasto gasto = new Gasto(data, descricao, valor,categoria);
            gerenciador.adicionarGasto(gasto);

            System.out.println("Gasto registrado com sucesso!");
        }
    }

    private void totalDoDia() {
        LocalDate dia = lerDataValida("Digite a data (dd/MM/yyyy): ");
        double total = gerenciador.totalDoDia(dia);
        System.out.println("Total do dia: " + total);
    }

    private void totalDoMes() {
        int mes = InputValidator.lerInteiro(sc, "Digite o mês (1-12): ");
        int ano = InputValidator.lerInteiro(sc, "Digite o ano: ");

        double total = gerenciador.totalDoMes(mes, ano);
        System.out.println("Total do mês: " + total);
    }


    private void gerarRelatorio() {
        LocalDate dataReferencia = lerDataValida("Digite uma data do mês (dd/MM/yyyy): ");
        int mesRelatorio = dataReferencia.getMonthValue();
        int anoRelatorio = dataReferencia.getYear();

        System.out.println("\nRELATÓRIO DE GASTOS DO MÊS:");
        double totalMes = 0.0;

        for (Gasto g : gerenciador.getGastos()) {
            if (g.getData().getMonthValue() == mesRelatorio &&
                    g.getData().getYear() == anoRelatorio) {

                System.out.println(
                        g.getData().format(fmt) + " - " +
                                g.getDescricao() + " - " +
                                g.getValor()
                );

                totalMes += g.getValor();
            }
        }

        System.out.println("\nTOTAL GASTO NO MÊS: " + totalMes);
    }


    private int lerQuantidadeValida() {
        int n;
        do {
            n = InputValidator.lerInteiro(sc, "Quantos gastos deseja inserir hoje? ");
            if (n <= 0) {
                System.out.println("Quantidade inválida. Digite um número maior que zero.");
            }
        } while (n <= 0);
        return n;
    }

    private String lerDescricaoValida() {
        String descricao;
        do {
            System.out.print("Digite a descrição do gasto: ");
            descricao = sc.nextLine();
            if (descricao.trim().isEmpty()) {
                System.out.println("A descrição não pode ser vazia.");
            }
        } while (descricao.trim().isEmpty());
        return descricao;
    }

    private double lerValorValido() {
        double valor;
        do {
            valor = InputValidator.lerDouble(sc, "Digite o valor: ");
            if (valor <= 0) {
                System.out.println("O valor deve ser maior que zero.");
            }
        } while (valor <= 0);
        return valor;
    }

    private LocalDate lerDataValida(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String texto = sc.nextLine();
                return LocalDate.parse(texto, fmt);
            } catch (Exception e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }

    private Categoria escolherCategoria() {

        System.out.println("Escolha a categoria:");
        System.out.println("1 - Alimentação");
        System.out.println("2 - Transporte");
        System.out.println("3 - Acessório");
        System.out.println("4 - Lazer");
        System.out.println("5 - Moradia");
        System.out.println("6 - Outros");

        int opcao = InputValidator.lerInteiro(sc, "Opção: ");

        return switch (opcao) {
            case 1 -> Categoria.ALIMENTACAO;
            case 2 -> Categoria.TRANSPORTE;
            case 3 -> Categoria.ACESSORIO;
            case 4 -> Categoria.LAZER;
            case 5 -> Categoria.MORADIA;
            case 6 -> Categoria.OUTROS;
            default -> {
                System.out.println("⚠ Categoria inválida. Usando OUTROS.");
                yield Categoria.OUTROS;
            }
        };
    }
}