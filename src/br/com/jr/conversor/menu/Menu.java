package br.com.jr.conversor.menu;

import br.com.jr.conversor.service.Taxas;
import br.com.jr.conversor.service.TaxasService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void exibirMenu() {
        System.out.println("""
                *********************************************
                Seja bem vindo/a ao Conversor de Moedas =]
                
                1) Dólar =>> Peso Argentino
                2) Peso Argentino =>> Dólar
                3) Dólar =>> Real Brasileiro
                4) Real Brasileiro =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                
                7) Escolher Manualmente
                8) Ver Histórico
                9) Sair
                
                Escolha uma opção válida:
                *********************************************
                """);
    }

    public void processarOpcao(int opcao, double valor, TaxasService taxasService, Scanner leitura) {
        String base = null;
        String target = null;

        switch (opcao) {
            case 1 -> { base = "USD"; target = "ARS"; }
            case 2 -> { base = "ARS"; target = "USD"; }
            case 3 -> { base = "USD"; target = "BRL"; }
            case 4 -> { base = "BRL"; target = "USD"; }
            case 5 -> { base = "USD"; target = "COP"; }
            case 6 -> { base = "COP"; target = "USD"; }
            case 7 -> {
                base = escolherMoeda(leitura, "Qual moeda será a base?");
                target = escolherMoeda(leitura, "Para qual moeda será a conversão?");
                System.out.println("Digite o valor da conversão:");
                valor = obterValor(leitura);
            }
            case 8 -> {
                System.out.println("********** Histórico de conversões **********");
                System.out.println(taxasService.historico());
                return;
            }
            default -> {
                System.out.println("Opção inválida. Tente novamente.");
                return;
            }
        }

        realizarConversao(base, target, valor, taxasService);
    }

    private String escolherMoeda(Scanner leitura, String mensagem) {
        System.out.println(mensagem);
        return leitura.next().toUpperCase();
    }

    private double obterValor(Scanner leitura) {
        double valor = 0;
        try {
            valor = leitura.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido");
            leitura.nextLine(); // Limpa o buffer
        }
        return valor;
    }

    private void realizarConversao(String base, String target, double valor, TaxasService taxasService) {
        Taxas taxas = taxasService.conversao(base);
        double valorConvertido = taxasService.converter(base, target, valor, taxas);
        System.out.printf("Valor convertido: %.2f %s%n", valorConvertido, target);
    }
}
