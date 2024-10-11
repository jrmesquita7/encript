import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaxasService taxasService = new TaxasService();
        Scanner leitura = new Scanner(System.in);


        int opcao = 0;
        double valor = 0;
        double valorConvertido = 0;

        while (true){
            System.out.println("""
                    *********************************************
                    Seja bem vindo/a ao Conversor de Moedas =]
                    1) Dólar =>> Peso Argentino
                    2) Peso Argentino =>> Dólar
                    3) Dólar =>> Real Brasileiro
                    4) Real Brasileiro =>> Dólar
                    5) Dólar =>> Peso Colombiano
                    6) Peso Colombiano =>> Dólar
                    7) Sair
                    Escolha uma opção válida:
                    """);
            opcao = leitura.nextInt();

            if(opcao == 7){
                System.out.println("Até logo !!");
                break;
            }

            System.out.println("Digite o valor a ser convertido");
            valor = leitura.nextDouble();
            Taxas taxasMenu = null;

            switch (opcao){
                case 1 -> {
                    taxasMenu = taxasService.conversao("USD");
                    valorConvertido = taxasService.converter("USD", "ARS", valor, taxasMenu);
                    System.out.println("Valor convertido: " + valorConvertido + " ARS");
                }
                case 2 -> {
                    taxasMenu = taxasService.conversao("ARS");
                    valorConvertido = taxasService.converter("ARS", "USD", valor, taxasMenu);
                    System.out.println("Valor convertido: " + valorConvertido + " USD");
                }
                case 3 -> {
                    taxasMenu = taxasService.conversao("USD");
                    valorConvertido = taxasService.converter("USD", "BRL", valor, taxasMenu);
                    System.out.println("Valor convertido: " + valorConvertido + " BRL");
                }
                case 4 -> {
                    taxasMenu = taxasService.conversao("BRL");
                    valorConvertido = taxasService.converter("BRL", "USD", valor, taxasMenu);
                    System.out.println("Valor convertido: " + valorConvertido + " USD");
                }
                case 5 -> {
                    taxasMenu = taxasService.conversao("USD");
                    valorConvertido = taxasService.converter("USD", "COP", valor, taxasMenu);
                    System.out.println("Valor convertido: " + valorConvertido + " COP");
                }
                case 6 -> {
                    taxasMenu = taxasService.conversao("COP");
                    valorConvertido = taxasService.converter("COP", "USD", valor, taxasMenu);
                    System.out.println("Valor convertido: " + valorConvertido + " USD");
                }
            }
        }

    }
}