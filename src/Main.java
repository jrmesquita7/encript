import java.util.Scanner;

public class Main {
    private static final String[] MOEDAS = {"USD", "ARS", "BRL", "COP"};

    public static void main(String[] args) {
        TaxasService taxasService = new TaxasService();
        Scanner leitura = new Scanner(System.in);

        while (true) {
            exibirMenu();
            int opcao = leitura.nextInt();

            if (opcao == 8) {
                System.out.println("Até logo !!");
                break;
            }

            double valor = 0;
            if (opcao != 7) {
                System.out.println("Digite o valor a ser convertido");
                valor = leitura.nextDouble();
            }

            processarOpcao(opcao, valor, taxasService);
        }
    }

    private static void exibirMenu() {
        System.out.println("""
                *********************************************
                Seja bem vindo/a ao Conversor de Moedas =]
                1) Dólar =>> Peso Argentino
                2) Peso Argentino =>> Dólar
                3) Dólar =>> Real Brasileiro
                4) Real Brasileiro =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Ver Histórico
                8) Sair
                Escolha uma opção válida:
                *********************************************
                """);
    }

    private static void processarOpcao(int opcao, double valor, TaxasService taxasService) {
        String base = null;
        String target = null;

        switch (opcao) {
            case 1 -> { base = MOEDAS[0]; target = MOEDAS[1]; }
            case 2 -> { base = MOEDAS[1]; target = MOEDAS[0]; }
            case 3 -> { base = MOEDAS[0]; target = MOEDAS[2]; }
            case 4 -> { base = MOEDAS[2]; target = MOEDAS[0]; }
            case 5 -> { base = MOEDAS[0]; target = MOEDAS[3]; }
            case 6 -> { base = MOEDAS[3]; target = MOEDAS[0]; }
            case 7 -> {
                System.out.println("********** Histórico de conversões **********");
                System.out.println(taxasService.historico());
                return;
            }
            default -> {
                System.out.println("Opção inválida. Tente novamente.");
                return;
            }
        }

        Taxas taxasMenu = taxasService.conversao(base);
        double valorConvertido = taxasService.converter(base, target, valor, taxasMenu);
        System.out.printf("Valor convertido: %.2f %s%n", valorConvertido, target);
    }
}
