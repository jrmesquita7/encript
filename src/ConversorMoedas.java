import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConversorMoedas {
    private final TaxasService taxasService = new TaxasService();
    private final Scanner leitura = new Scanner(System.in);
    private final Menu menu = new Menu();
    private final Log log = new Log();

    public void iniciar() {
        while (true) {
            menu.exibirMenu();
            int opcao = obterOpcao();

            if (opcao == 9) {
                System.out.println("Até logo!!");
                break;
            }

            double valor = 0;
            if (opcao != 7 && opcao != 8) {
                valor = obterValor();
            }

            menu.processarOpcao(opcao, valor, taxasService, leitura);
        }
    }

    private int obterOpcao() {
        int opcao = 0;
        try {
            opcao = leitura.nextInt();
        } catch (InputMismatchException e) {
            String mensagemErro = "Opção inválida. Por favor, insira um número.";
            registrarErro(mensagemErro, e);
            System.out.println(mensagemErro);
            leitura.nextLine(); // Limpa o buffer
        }
        return opcao;
    }

    private double obterValor() {
        double valor = 0;
        System.out.println("Digite o valor a ser convertido:");
        try {
            valor = leitura.nextDouble();
        } catch (InputMismatchException e) {
            String mensagemErro = "Valor inválido";
            registrarErro(mensagemErro, e);
            System.out.println(mensagemErro);
            leitura.nextLine(); // Limpa o buffer
        }
        return valor;
    }

    private void registrarErro(String mensagemErro, Exception e) {
        try {
            log.gerarLog(mensagemErro + " - Detalhes: " + e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
