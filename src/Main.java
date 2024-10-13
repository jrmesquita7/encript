import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, String> MOEDAS = new HashMap<>() {{
        put("USD", "Dólar");
        put("ARS", "Peso Argentino");
        put("BRL", "Real Brasileiro");
        put("COP", "Peso Colombiano");
        put("JPY", "Iene Japonês");
    }};

    public static void main(String[] args) {
        TaxasService taxasService = new TaxasService();
        Scanner leitura = new Scanner(System.in);

        while (true) {
            exibirMenu();
            int opcao = 0;

            try{
                opcao = leitura.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Opção inválida. Por favor, insira um número.");
                leitura.nextLine();
                continue;
            }


            if (opcao == 9) {
                System.out.println("Até logo !!");
                break;
            }

            double valor = 0;

            if (opcao != 7 && opcao != 8) {
                System.out.println("Digite o valor a ser convertido:");
                try{
                    valor = leitura.nextDouble();
                }catch (InputMismatchException e){
                    System.out.println("Valor inválido");
                }

            }

            processarOpcao(opcao, valor, taxasService, leitura);
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
                
                7) Escolher Manualmente
                8) Ver Histórico
                9) Sair
                
                Escolha uma opção válida:
                *********************************************
                """);
    }

    private static void processarOpcao(int opcao, double valor, TaxasService taxasService, Scanner leitura) {
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
                base = escolherMoeda(leitura, "Qual moeda será a base?").toUpperCase();
                target = escolherMoeda(leitura, "Para qual moeda será a conversão?").toUpperCase();
                System.out.println("Digite o valor da conversão:");
                try{
                    valor = leitura.nextDouble();
                }catch (InputMismatchException e){
                    System.out.println("Valor inválido");
                }


                realizarConversao(base, target, valor, taxasService);
                return;
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

    private static String escolherMoeda(Scanner leitura, String mensagem) {
        System.out.println(mensagem);
        String moeda = leitura.next().toUpperCase();
        return moeda;
    }

    private static void realizarConversao(String base, String target, double valor, TaxasService taxasService) {
        Taxas taxasMenu = taxasService.conversao(base);
        double valorConvertido = taxasService.converter(base, target, valor, taxasMenu);
        System.out.printf("Valor convertido: %.2f %s%n", valorConvertido, target);
    }
}
