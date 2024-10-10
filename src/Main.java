public class Main {
    public static void main(String[] args) {
        ExchangeRateService exchangeRateService = new ExchangeRateService();

        ExchangeRate exchangeRate = exchangeRateService.conversao("BRL");
        System.out.println(exchangeRate.getConversion_rates());
        exchangeRate.exibirFiltradas("BRL");


    }
}